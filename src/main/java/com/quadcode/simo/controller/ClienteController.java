package com.quadcode.simo.controller;


import com.quadcode.simo.dao.ClienteDao;
import com.quadcode.simo.model.ClienteDetalle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.Optional;

/**
 * ClienteController.java
 * --------------------------------------------------------------
 * Controlador para manejar la ventana de Clientes
 * ---------------------------------------------------------------
 * @author: Avila Carrillo Jorge Armando                           |
 * Fecha de creacion: 06 - AGOSTO - 2024                            |
 * Ultima Actualizacion: 08 - AGOSTO - 2024                        |
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
    private int clienteId;

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
                clienteId = newValue.getClienteId(); // Esta variable es usada para el modificar cliente
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
        showAlert(Alert.AlertType.INFORMATION, "Agregar Cliente", "El cliente" +  clienteDetalle.getClienteNombre() +  " fue agreado con exito! :)");
        cargarDatosEnTabla();
        Limpiar();
    }

    public void modificarCliente(){
        ClienteDetalle clienteDetalle= new ClienteDetalle();
        clienteDetalle.setClienteId(clienteId);
        clienteDetalle.setClienteNombre(fldNombre1.getText());
        clienteDetalle.setClienteTelefono(fldTel1.getText());
        clienteDetalle.setReferidoNombre(fldNombre2.getText());
        clienteDetalle.setReferidoTelefono(fldTel2.getText());
        clienteDetalle.setReferidoRelacion(cbRelacion.getValue());
        clienteDetalle.setDireccionClienteCalleNumero(fldCalle1.getText());
        clienteDetalle.setDireccionClienteCodigoPostal(fldCp1.getText());
        clienteDetalle.setDireccionClienteEntreCalles(fldEntreC1.getText());
        clienteDetalle.setDireccionClienteColonia(fldColonia1.getText());
        clienteDetalle.setDireccionClienteReferencia(fldReferencia.getText());
        clienteDetalle.setDireccionReferidoCalleNumero(fldCalle2.getText());
        clienteDetalle.setDireccionReferidoCodigoPostal(fldCp2.getText());
        clienteDetalle.setDireccionReferidoEntreCalles(fldEntreC2.getText());
        clienteDetalle.setDireccionReferidoColonia(fldColonia2.getText());
        clienteDetalle.setDireccionReferidoReferencia(fldReferencia2.getText());

        ClienteDao clienteDao = new ClienteDao();
        Alert alert;
        alert = showAlertConfirmation(Alert.AlertType.CONFIRMATION, "Modificar cliente", "¿Seguro que desea modificar al cliente " + clienteDetalle.getClienteNombre() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
           clienteDao.modificarCliente(clienteDetalle);
        }
        cargarDatosEnTabla();
        Limpiar();
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


    public Alert showAlertConfirmation(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
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

}
