package com.quadcode.simo.controller;

import com.quadcode.simo.dao.NuevaVentaDao;
import com.quadcode.simo.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * NuevaVentaController.java
 * --------------------------------------------------------------
 * Controlador para manejar el apartado de NuevaVenta
 * ---------------------------------------------------------------
 * @author: Avila Carrillo Jorge Armando                           |
 * Fecha de creacion: 06 - AGOSTO - 2024                            |
 * Ultima Actualizacion: 06 - AGOSTO - 2024                        |
 * ---------------------------------------------------------------
 */

public class NuevaVentaController extends NavBarController{
    @FXML
    private TextField fldNombrePaciente;
    @FXML
    private DatePicker dateBoxVenta;
    @FXML
    private ComboBox menuArmazon;
    @FXML
    private ComboBox menuMica;
    @FXML
    private ComboBox menuTinte;
    @FXML
    private ComboBox menuMetodo;
    @FXML
    private ComboBox menuAbono;
    @FXML
    private Label lblTotal;
    @FXML
    private ComboBox menuPago;
    @FXML
    private TextField fdlEnganche;
    @FXML
    private TableView<ProductosyMicas> tbNuevaVenta;
    @FXML
    private ListView<PacienteSearch> listView;
    @FXML
    private Label lblSaldo;

    private NuevaVentaDao nuevaVentaDao;
    private ObservableList<ProductosyMicas> nuevaVenta2;

    @FXML
    public void initialize() {
        nuevaVentaDao = new NuevaVentaDao();
        listaMicasLentes();
        setMenuUser();
        autocompletarPacientes();
        configurarEventos();
        configurarListeners();

    }

    public void listaMicasLentes(){
        List<String> nombrelentes = nuevaVentaDao.obtenerNombresProductos();
        ObservableList<String> observableList = FXCollections.observableArrayList(nombrelentes);
        menuArmazon.setItems(observableList);

        List<String> nombremicas = nuevaVentaDao.obtenerNombresMicas();
        ObservableList<String> observableList2 = FXCollections.observableArrayList(nombremicas);
        menuMica.setItems(observableList2);
    }

    private void autocompletarPacientes(){
        fldNombrePaciente.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && !newValue.trim().isEmpty()){
                try{
                    List<PacienteSearch> pacientes = nuevaVentaDao.obtenerNombrePacientes(newValue);
                    ObservableList<PacienteSearch> observableList = FXCollections.observableArrayList(pacientes);
                    listView.setItems(observableList);
                    listView.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                listView.setItems(FXCollections.emptyObservableList());
                listView.setVisible(false);
            }
        });

        listView.setOnMouseClicked(event -> {
            PacienteSearch selectedCliente = listView.getSelectionModel().getSelectedItem();
            if(selectedCliente != null){
                fldNombrePaciente.setText(selectedCliente.getName());
                listView.setItems(FXCollections.emptyObservableList());
                listView.setVisible(false);
            }
        });


    }

    public void configurarEventos(){
        actualizarEstadoAbono(String.valueOf(menuPago.getValue()));

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

    private void ConfigurarAcciones(){
        mostrarPrecios();
        Mica micas = nuevaVentaDao.obtenerPrecioMica(String.valueOf(menuMica.getValue()));
        Lentes lentes = nuevaVentaDao.obtenerPrecioProducto(String.valueOf(menuArmazon.getValue()));
        float costoLente = 0.0f;
        float costoMica = 0.0f;
        float enganche= 0.0f;
        float costoTotal = 0.0f;
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

        deuda = costoLente + costoMica;
        costoTotal = deuda - enganche;
        lblTotal.setText(String.valueOf(deuda));
        lblSaldo.setText(String.valueOf(costoTotal));
    }

    public void Limpiar(){
        fldNombrePaciente.setText("");
        dateBoxVenta.setValue(null);
        menuPago.setValue(null);
        menuMica.setValue(null);
        menuArmazon.setValue(null);
        menuTinte.setValue(null);
        lblTotal.setText("$0");
        lblSaldo.setText("$0");
        nuevaVenta2.clear();
    }

    public void mostrarPrecios(){
        if(menuArmazon.getValue() != null && menuMica.getValue() != null){
            try {
                tbNuevaVenta.getColumns().clear();
                List<TableColumn<ProductosyMicas, ?>> columnas = nuevaVentaDao.getColums(menuArmazon.getValue().toString(), menuMica.getValue().toString());
                tbNuevaVenta.getColumns().addAll(columnas);
                List<ProductosyMicas> nuevaVenta = nuevaVentaDao.obtenerProductosMicas(menuArmazon.getValue().toString(), menuMica.getValue().toString());
                nuevaVenta2 = FXCollections.observableArrayList(nuevaVenta);
                tbNuevaVenta.setItems(nuevaVenta2);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void generarVenta(){
        try{
            NuevaVenta nuevaVenta = new NuevaVenta();
            nuevaVenta.setNombrePaciente(fldNombrePaciente.getText());
            nuevaVenta.setFechaVenta(Date.valueOf(dateBoxVenta.getValue()));
            nuevaVenta.setNombreProducto(menuArmazon.getValue().toString());
            nuevaVenta.setNombreMica(menuMica.getValue().toString());
            nuevaVenta.setTinte(menuTinte.getValue().toString());
            if(menuAbono.getValue() != null){
                nuevaVenta.setPeriodoAbonos(menuAbono.getValue().toString());
            }else{
                nuevaVenta.setPeriodoAbonos("");
            }
            nuevaVenta.setMetodoPago(menuPago.getValue().toString());
            nuevaVenta.setCostoTotal(Float.parseFloat(lblTotal.getText()));
            nuevaVenta.setSaldoActual(Float.parseFloat(lblSaldo.getText()));
            nuevaVenta.setEnganche(Float.parseFloat(fdlEnganche.getText()));
            nuevaVentaDao.insertarVenta(nuevaVenta);
            showAlert(Alert.AlertType.INFORMATION, "Agregar Venta", "Venta Agregada con exito!");
        }catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Error al agregar Venta");
        }
    }

    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
