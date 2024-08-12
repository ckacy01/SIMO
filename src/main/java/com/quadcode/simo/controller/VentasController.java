package com.quadcode.simo.controller;

import com.quadcode.simo.dao.InventarioDao;
import com.quadcode.simo.dao.VentasDao;
import com.quadcode.simo.model.Lentes;
import com.quadcode.simo.model.Mica;
import com.quadcode.simo.model.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * VentasController.java
 * --------------------------------------------------------------
 * Controlador para manejar el apartado de Ventas
 * ---------------------------------------------------------------
 * @author: Avila Carrillo Jorge Armando                           |
 * Fecha de creacion: 06 - AGOSTO - 2024                            |
 * Ultima Actualizacion: 06 - AGOSTO - 2024                        |
 * ---------------------------------------------------------------
 */

public class VentasController extends NavBarController{
    @FXML
    private TextField fldBuscarCliente;
    @FXML
    private DatePicker dateBox;
    @FXML
    private TableView<Venta> tblVentas;
    @FXML
    private Label lblNventa;
    @FXML
    private Label lblNabonos;
    @FXML
    private DatePicker dateBoxVenta;
    @FXML
    private TextField fldNombreC;
    @FXML
    private TextField fldNombreP;
    @FXML
    private ComboBox menuPago;
    @FXML
    private ComboBox menuAbono;
    @FXML
    private ComboBox menuArmazon;
    @FXML
    private ComboBox menuMica;
    @FXML
    private ComboBox menuTinte;
    @FXML
    private Label lblSaldo;
    @FXML
    private Label lblTotal;
    @FXML
    private TextField fdlEnganche;

    private VentasDao ventasDao;
    private int ventaId;
    private String nombreCliente;
    private String nombreP;
    private Float CostoTotal;
    private Float Deuda;

    @FXML
    public void initialize() {
        setMenuUser();
        ventasDao = new VentasDao();
        List<TableColumn <Venta, ?>> columns =  ventasDao.getColumnsFromDatabase();
        tblVentas.getColumns().addAll(columns);
        mostrarVentas();
        configurarEventos();
        listaMicasLentes();
        configurarListeners();

    }

    public void mostrarVentas() {
        try{
            List<Venta> ventas = ventasDao.obtenerVentas();
            ObservableList<Venta> observableList = FXCollections.observableArrayList(ventas);
            tblVentas.setItems(observableList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void configurarEventos(){
        tblVentas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           if (newValue != null) {
               ventaId = newValue.getId();
               CostoTotal = newValue.getCostoTotal();
               Deuda = newValue.getSaldoActual();
               nombreCliente = newValue.getNombreCliente();
               nombreP = newValue.getNombrePaciente();
               lblNventa.setText(String.valueOf(newValue.getId()));
               lblNabonos.setText(String.valueOf(ventasDao.obtenerNAbonos(newValue.getId())));
               fldNombreC.setText(newValue.getNombreCliente());
               fldNombreP.setText(newValue.getNombrePaciente());
               menuPago.setValue(newValue.getMetodoPago());
               menuArmazon.setValue(newValue.getNombreProducto());
               menuMica.setValue(newValue.getNombreMica());
               menuTinte.setValue(newValue.getTinte());
               lblSaldo.setText(newValue.getSaldoActual().toString());
               lblTotal.setText(newValue.getCostoTotal().toString());
               fdlEnganche.setText(newValue.getEnganche().toString());
               dateBoxVenta.setValue(newValue.getFechaVenta().toLocalDate());
               menuAbono.setValue(newValue.getPeriodoAbonos());

               actualizarEstadoAbono(String.valueOf(menuPago.getValue()));
           }
        });
        menuPago.valueProperty().addListener((observable, oldValue, newValue) -> {
            actualizarEstadoAbono(newValue.toString());
        });
    }

    private void actualizarEstadoAbono(String metodoPago){
        if("Contado".equals(metodoPago)){
            menuAbono.setDisable(true);
        } else {
            menuAbono.setDisable(false);
        }
    }

    private void configurarListeners(){
        menuArmazon.valueProperty().addListener((observable, oldValue, newValue) -> ConfigurarAcciones());
        fdlEnganche.textProperty().addListener((observable, oldValue, newValue) -> ConfigurarAcciones());
        menuPago.valueProperty().addListener((observable, oldValue, newValue) -> ConfigurarAcciones());
        menuMica.valueProperty().addListener((observable, oldValue, newValue) -> ConfigurarAcciones());
    }

    public void listaMicasLentes(){
        List<String> nombrelentes = ventasDao.obtenerNombresProductos();
        ObservableList<String> observableList = FXCollections.observableArrayList(nombrelentes);
        menuArmazon.setItems(observableList);

        List<String> nombremicas = ventasDao.obtenerNombresMicas();
        ObservableList<String> observableList2 = FXCollections.observableArrayList(nombremicas);
        menuMica.setItems(observableList2);
    }

    private void ConfigurarAcciones() {
        Mica micas = ventasDao.obtenerPrecioMica(String.valueOf(menuMica.getValue()));
        Lentes lentes = ventasDao.obtenerPrecioProducto(String.valueOf(menuArmazon.getValue()));
        float costoLente = 0.0f;
        float costoMica = 0.0f;
        float enganche= 0.0f;
        float costoTotal = 0.0f;
        float abonos = 0.0f;
        float deuda = 0.0f;

        if (menuPago.valueProperty().getValue().equals("Contado") && (menuMica.getValue() != null || menuArmazon.getValue() != null)) {
            costoLente = lentes.getPrecioContado();
            costoMica = micas.getContado();
        }else if (menuMica.getValue() != null || menuArmazon.getValue() != null){
            costoLente = lentes.getPrecioCredito();
            costoMica = micas.getCredito();
        }

        if(!fdlEnganche.getText().isEmpty()){
            enganche = Float.parseFloat(fdlEnganche.getText());
        }

        abonos = ventasDao.obtenerSumaAbonos(Integer.parseInt(lblNventa.getText()));

        costoTotal = costoLente + costoMica - enganche;
        deuda = costoTotal - abonos;
        lblTotal.setText(String.valueOf(costoTotal));
        lblSaldo.setText(String.valueOf(deuda));
    }

    @FXML
    public void modificarVenta(){
        try{
            Venta venta = new Venta();
            venta.setId(Integer.parseInt(lblNventa.getText()));
            venta.setNombreProducto(String.valueOf(menuArmazon.getValue()));
            venta.setNombreMica(String.valueOf(menuMica.getValue()));
            venta.setEnganche(Float.parseFloat(fdlEnganche.getText()));
            venta.setCostoTotal(Float.parseFloat(lblTotal.getText()));
            venta.setSaldoActual(Float.parseFloat(lblSaldo.getText()));
            venta.setFechaVenta(Date.valueOf(dateBoxVenta.getValue()));
            venta.setTinte(String.valueOf(menuTinte.getValue()));
            venta.setMetodoPago(String.valueOf(menuPago.getValue()));
            venta.setPeriodoAbonos(String.valueOf(menuAbono.getValue()));

            Alert alert = showAlertConfirmation("Modificar Venta!", "Al modificar la venta cambiara la deuda del cliente, ¿Seguro que desea modificar?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                ventasDao.modificarVenta(venta);
            }
            mostrarVentas();
            limpiarCampos();
        }catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error al Modificar!", "Hubo un error al intentar modificar el Lente: " + fldNombreP.getText() + "favor de verificar los campos obligatorios marcados con '*'");

        }
    }


    @FXML
    public void limpiarCampos(){
        fldNombreP.clear();
        fldNombreC.clear();
        fdlEnganche.clear();
        lblNabonos.setText("0");
        lblNventa.setText("0");
        lblTotal.setText("$0");
        lblSaldo.setText("$0");
        menuArmazon.setValue(null);
        menuMica.setValue(null);
        menuTinte.setValue(null);
        menuPago.setValue(null);
        menuAbono.setValue(null);


    }


    public Alert showAlertConfirmation( String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }

    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void verAbonos(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quadcode/simo/view/AbonosView.fxml"));
            Parent abonosView = loader.load();
            AbonosController controller = loader.getController();
            controller.setDatos(nombreCliente, nombreP, Deuda, CostoTotal, ventaId);
            Scene scene = new Scene(abonosView);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Abonos");
            stage.setResizable(false);
            stage.show();
            stage.centerOnScreen();
            stage.toFront();
            stage.setOnCloseRequest(event -> {
              mostrarVentas();
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
