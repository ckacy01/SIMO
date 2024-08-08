package com.quadcode.simo.controller;


import com.quadcode.simo.dao.ClienteDao;
import com.quadcode.simo.dao.DatabaseConnection;
import com.quadcode.simo.model.ClienteDetalle;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
                fldNombre1.setText(newValue.getClienteNombre());
                fldTel1.setText(newValue.getClienteTelefono());
                fldNombre2.setText(newValue.getReferidoNombre());
                fldTel2.setText(newValue.getReferidoTelefono());
                cbRelacion.setValue(newValue.getReferidoRelacion());
                fldCalle1.setText(newValue.getDireccionClienteCalleNumero());
                fldColonia1.setText(newValue.getDireccionClienteColonia());
                fldCp1.setText(newValue.getDireccionClienteCodigoPostal());
                fldEntreC1.setText(newValue.getDireccionClienteEntreCalles());
                fldReferencia.setText(newValue.getDireccionClienteReferencia());
                fldCalle2.setText(newValue.getDireccionReferidoCalleNumero());
                fldColonia2.setText(newValue.getDireccionReferidoColonia());
                fldEntreC2.setText(newValue.getDireccionReferidoEntreCalles());
                fldReferencia2.setText(newValue.getDireccionReferidoReferencia());
                fldCp2.setText(newValue.getDireccionReferidoCodigoPostal());
            }
        });
    }

    @FXML
    public void insertarCliente() {
        // Obtener los datos de los campos de texto
        String clienteNombre = fldNombre1.getText();
        String clienteTelefono = fldTel1.getText();
        String referidoNombre = fldNombre2.getText();
        String referidoTelefono = fldTel2.getText();
        String referidoRelacion = cbRelacion.getValue();
        String direccionClienteCalleNumero = fldCalle1.getText();
        String direccionClienteCodigoPostal = fldCp1.getText();
        String direccionClienteEntreCalles = fldEntreC1.getText();
        String direccionClienteColonia = fldColonia1.getText();
        String direccionClienteReferencia = fldReferencia.getText();
        String direccionReferidoCalleNumero = fldCalle2.getText();
        String direccionReferidoCodigoPostal = fldCp2.getText();
        String direccionReferidoEntreCalles = fldEntreC2.getText();
        String direccionReferidoColonia = fldColonia2.getText();
        String direccionReferidoReferencia = fldReferencia2.getText();

        // Crear una instancia de ClienteDetalle
        ClienteDetalle clienteDetalle = new ClienteDetalle();
        clienteDetalle.setClienteNombre(clienteNombre);
        clienteDetalle.setClienteTelefono(clienteTelefono);
        clienteDetalle.setReferidoNombre(referidoNombre);
        clienteDetalle.setReferidoTelefono(referidoTelefono);
        clienteDetalle.setReferidoRelacion(referidoRelacion);
        clienteDetalle.setDireccionClienteCalleNumero(direccionClienteCalleNumero);
        clienteDetalle.setDireccionClienteCodigoPostal(direccionClienteCodigoPostal);
        clienteDetalle.setDireccionClienteEntreCalles(direccionClienteEntreCalles);
        clienteDetalle.setDireccionClienteColonia(direccionClienteColonia);
        clienteDetalle.setDireccionClienteReferencia(direccionClienteReferencia);
        clienteDetalle.setDireccionReferidoCalleNumero(direccionReferidoCalleNumero);
        clienteDetalle.setDireccionReferidoCodigoPostal(direccionReferidoCodigoPostal);
        clienteDetalle.setDireccionReferidoEntreCalles(direccionReferidoEntreCalles);
        clienteDetalle.setDireccionReferidoColonia(direccionReferidoColonia);
        clienteDetalle.setDireccionReferidoReferencia(direccionReferidoReferencia);

        System.out.println(clienteDetalle.getClienteNombre());

        // Crear una instancia de ClienteDao
        ClienteDao clienteDao = new ClienteDao();

        // Insertar datos en la base de datos
        clienteDao.insertarClienteConReferido(clienteDetalle);
        cargarDatosEnTabla();
    }

    @FXML
    public void Limpiar(){
        fldNombre1.setText("");
        fldTel1.setText("");
        fldNombre2.setText("");
        fldTel2.setText("");
        fldCalle1.setText("");
        fldCp1.setText("");
        fldEntreC1.setText("");
        fldColonia1.setText("");
        fldEntreC2.setText("");
        fldReferencia.setText("");
        fldCalle2.setText("");
        fldCp2.setText("");
        cbRelacion.setValue(null);
        fldReferencia2.setText("");
        fldColonia2.setText("");
    }

}
