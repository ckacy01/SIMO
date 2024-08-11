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
    private InventarioDao inventarioDao;

    @FXML
    public void initialize() {
        setMenuUser();
        ventasDao = new VentasDao();
        inventarioDao = new InventarioDao();
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

    private void listaMicasLentes(){
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
            System.out.println(costoLente);
            System.out.println(costoMica);
        }else if (menuMica.getValue() != null || menuArmazon.getValue() != null){
            costoLente = lentes.getPrecioCredito();
            costoMica = micas.getCredito();
            System.out.println(costoMica);
            System.out.println(costoLente);
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
    public void verAbonos(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quadcode/simo/view/AbonosView.fxml"));
            Parent abonosView = loader.load();
            Scene scene = new Scene(abonosView);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Abonos");
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
