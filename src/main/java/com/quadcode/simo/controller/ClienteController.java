package com.quadcode.simo.controller;


import com.quadcode.simo.dao.ClienteDao;
import com.quadcode.simo.dao.DatabaseConnection;
import com.quadcode.simo.model.ClienteDetalle;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * ClienteController.java
 * --------------------------------------------------------------
 * Controlador para manejar la ventana de Clientes
 * ---------------------------------------------------------------
 * @author: Avila Carrillo Jorge Armando                           |
 * Fecha de creacion: 06 - AGOSTO - 2024                            |
 * Ultima Actualizacion: 06 - AGOSTO - 2024                        |
 * ---------------------------------------------------------------
 */

public class ClienteController extends NavBarController{

    @FXML
    private TableView<ClienteDetalle> tbClientes;
    @FXML
    private TextField fldNombre1;
    @FXML
    private TextField fldTel1;
    @FXML
    private TextField fldNombre2;
    @FXML
    private TextField fldTel2;
    @FXML
    private ComboBox<String> cbRelacion;
    @FXML
    private TextField fldCalle1;
    @FXML
    private TextField fldColonia1;
    @FXML
    private TextField fldCp1;
    @FXML
    private TextField fldEntreC1;
    @FXML
    private TextField fldReferencia;
    @FXML
    private TextField fldCalle2;
    @FXML
    private TextField fldColonia2;
    @FXML
    private TextField fldCp2;
    @FXML
    private TextField fldEntreC2;
    @FXML
    private TextField fldReferencia2;


    private ClienteDao clienteDao;

    @FXML
    public void initialize() {
        clienteDao = new ClienteDao();
        setMenuUser();
        List<TableColumn<ClienteDetalle, ?>> columns = clienteDao.getColumnsFromDatabase();
        tbClientes.getColumns().addAll(columns);
        // cargar datos en la tabla
        cargarDatosEnTabla();
        configurarEventos();
    }

    private void cargarDatosEnTabla() {
        try{
            List<ClienteDetalle> clientesDetalles = clienteDao.obtenerClientesDetalle();
            ObservableList<ClienteDetalle> observableList = FXCollections.observableArrayList(clientesDetalles);
            tbClientes.setItems(observableList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void configurarEventos(){
        tbClientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                fldNombre1.setText(newValue.getNombre());
                fldTel1.setText(newValue.getTelefono());
                fldNombre2.setText(newValue.getNombre());
                fldTel2.setText(newValue.getTelefono());
                cbRelacion.setValue(newValue.getReferencia());
                fldCalle1.setText(newValue.getCalle1());
                fldColonia1.setText(newValue.getColonia1());
                fldCp1.setText(newValue.getCodigoPostal1());
                fldEntreC1.setText(newValue.getEntreCalles1());
                fldReferencia.setText(newValue.getReferencia());
                fldCalle2.setText(newValue.getCalle2());
                fldColonia2.setText(newValue.getColonia2());
                fldEntreC2.setText(newValue.getEntreCalles2());
                fldReferencia2.setText(newValue.getReferencia2());
                fldCp2.setText(newValue.getCodigoPostal2());
            }
        });
    }
}
