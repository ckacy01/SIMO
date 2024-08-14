package com.quadcode.simo.controller;

import com.quadcode.simo.dao.PacienteDao;
import com.quadcode.simo.model.Cliente;
import com.quadcode.simo.model.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * PacienteController.java
 * --------------------------------------------------------------
 * Controlador para manejar el apartado de Pacientes
 * ---------------------------------------------------------------
 * @author: Avila Carrillo Jorge Armando                           |
 * Fecha de creacion: 06 - AGOSTO - 2024                            |
 * Ultima Actualizacion: 06 - AGOSTO - 2024                        |
 * ---------------------------------------------------------------
 */

public class PacienteController extends NavBarController{
    @FXML
    private TableView<Paciente> tbPacientes;
    @FXML
    private TextField lblNombrePaciente;
    @FXML
    private ComboBox cbMaterial;
    @FXML
    private ComboBox cbTratamiento;
    @FXML
    private ComboBox cbLente;
    @FXML
    private TextField lblNombreCliente;
    @FXML
    private TextField lblEsfera;
    @FXML
    private TextField lblAdicion;
    @FXML
    private TextField lblEje;
    @FXML
    private TextField lblCilindro;
    @FXML
    private TextField lblEsferad;
    @FXML
    private TextField lblAdiciond;
    @FXML
    private TextField lblEjed;
    @FXML
    private TextField lblCilindrod;
    @FXML
    private ListView<Cliente> listClienteNombre;
    @FXML
    private TextField lblDI;
    @FXML
    private TextField fldBuscarPaciente;

    private PacienteDao pacienteDao;
    private int PacienteId;


    @FXML
    public void initialize() {
        pacienteDao = new PacienteDao();
        setMenuUser();
        List<TableColumn<Paciente, ?>> columns = pacienteDao.getColumnsFromDatabase();
        tbPacientes.getColumns().addAll(columns);
        cargarDatosEnTabla();
        configurarEventos();
        autocompletarClientes();
        validarNumeros();
        configurarBusqueda();
    }

    private void cargarDatosEnTabla() {
        try{
            List<Paciente> pacientes = pacienteDao.obtenerPacientes();
            ObservableList<Paciente> observableList = FXCollections.observableArrayList(pacientes);
            tbPacientes.setItems(observableList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void configurarBusqueda() {
        fldBuscarPaciente.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarTablaPorNombre(newValue);
        });
    }

    private void filtrarTablaPorNombre(String nombre) {
        try {
            List<Paciente> pacientes = pacienteDao.obtenerPacientes();

            // Filtrar la lista de pacientes por nombre
            List<Paciente> pacientesFiltrados = pacientes.stream()
                    .filter(paciente -> paciente.getNombrePaciente().toLowerCase().contains(nombre.toLowerCase()))
                    .collect(Collectors.toList());

            // Actualizar la tabla con la lista filtrada
            ObservableList<Paciente> observableList = FXCollections.observableArrayList(pacientesFiltrados);
            tbPacientes.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configurarEventos(){
        tbPacientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                PacienteId = newValue.getPacienteId();
                lblNombrePaciente.setText(newValue.getNombrePaciente());
                cbMaterial.setValue(newValue.getMaterial());
                cbTratamiento.setValue(newValue.getTratamiento());
                cbLente.setValue(newValue.getLente());
                lblNombreCliente.setText(newValue.getNombreCliente());
                lblEsfera.setText(String.valueOf(newValue.getEsferaIzquierdo()));
                lblAdicion.setText(String.valueOf(newValue.getAdicionIzquierdo()));
                lblEje.setText(String.valueOf(newValue.getEjeIzquierdo()));
                lblCilindro.setText(String.valueOf(newValue.getCilindroIzquierdo()));
                lblEjed.setText(String.valueOf(newValue.getEjeIzquierdo()));
                lblCilindrod.setText(String.valueOf(newValue.getCilindroIzquierdo()));
                lblEsferad.setText(String.valueOf(newValue.getEsferaIzquierdo()));
                lblAdiciond.setText(String.valueOf(newValue.getAdicionIzquierdo()));
                lblDI.setText(String.valueOf(newValue.getDIDerecho()));
            }
        });
    }


    private void autocompletarClientes(){
        lblNombreCliente.textProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue != null && !newValue.trim().isEmpty()){
               try{
                   List<Cliente> clientes = pacienteDao.obtenerNombreClientes(newValue);
                   ObservableList<Cliente> observableList = FXCollections.observableArrayList(clientes);
                   listClienteNombre.setItems(observableList);
                   listClienteNombre.setVisible(true);
               }catch (Exception e){
                   e.printStackTrace();
               }
           }else{
               listClienteNombre.setItems(FXCollections.emptyObservableList());
               listClienteNombre.setVisible(false);
           }
        });

        listClienteNombre.setOnMouseClicked(event -> {
           Cliente selectedCliente = listClienteNombre.getSelectionModel().getSelectedItem();
           if(selectedCliente != null){
               lblNombreCliente.setText(selectedCliente.getNombre());
               listClienteNombre.setItems(FXCollections.emptyObservableList());
               listClienteNombre.setVisible(false);
           }
        });
    }

    @FXML
    public void guardarDiagnostico() {
        try {
            pacienteDao.insertarDiagnostico(
                    lblNombreCliente.getText(),
                    lblNombrePaciente.getText(),
                    cbMaterial.getValue().toString(),
                    cbTratamiento.getValue().toString(),
                    cbLente.getValue().toString(),
                    Float.parseFloat(lblEsferad.getText()),
                    Float.parseFloat(lblCilindrod.getText()),
                    Float.parseFloat(lblAdicion.getText()),
                    Float.parseFloat(lblDI.getText()),
                    Float.parseFloat(lblEjed.getText()),
                    Float.parseFloat(lblEsfera.getText()),
                    Float.parseFloat(lblCilindro.getText()),
                    Float.parseFloat(lblAdicion.getText()),
                    Float.parseFloat(lblDI.getText()),
                    Float.parseFloat(lblEje.getText())
            );
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores aquí
        }
        cargarDatosEnTabla();
        showAlert(Alert.AlertType.INFORMATION, "Paciente", "Diagnostico guardado exitosamente para el paciente: " + lblNombrePaciente.getText());
        Limpiar();
    }

    public void modificarDiagnostico() {
        Paciente paciente = new Paciente();
        paciente.setPacienteId(PacienteId);
        paciente.setNombreCliente(lblNombreCliente.getText());
        paciente.setNombrePaciente(lblNombrePaciente.getText());
        paciente.setMaterial(cbMaterial.getValue().toString());
        paciente.setTratamiento(cbTratamiento.getValue().toString());
        paciente.setLente(cbLente.getValue().toString());
        paciente.setEsferaDerecho(Float.parseFloat(lblEsferad.getText()));
        paciente.setCilindroDerecho(Float.parseFloat(lblCilindrod.getText()));
        paciente.setAdicionDerecho(Float.parseFloat(lblAdicion.getText()));
        paciente.setDIDerecho(Float.parseFloat(lblDI.getText()));
        paciente.setEjeDerecho(Float.parseFloat(lblEjed.getText()));
        paciente.setEsferaIzquierdo(Float.parseFloat(lblEsfera.getText()));
        paciente.setCilindroIzquierdo(Float.parseFloat(lblCilindro.getText()));
        paciente.setAdicionIzquierdo(Float.parseFloat(lblAdicion.getText()));
        paciente.setDIIzquierdo(Float.parseFloat(lblDI.getText()));
        paciente.setEjeIzquierdo(Float.parseFloat(lblEje.getText()));

        PacienteDao pacienteDao = new PacienteDao();
        Alert alert;
        alert = showAlertConfirmation(Alert.AlertType.CONFIRMATION, "Modificar Paciente", "¿Seguro que desea modificar al paciente " + paciente.getNombrePaciente() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            pacienteDao.modificarPaciente(paciente);
        }
        cargarDatosEnTabla();
        Limpiar();
    }


    @FXML
    public void Limpiar(){
        lblNombrePaciente.setText("");
        cbMaterial.setValue(null);
        cbTratamiento.setValue(null);
        cbLente.setValue(null);
        lblNombreCliente.setText("");
        lblEsfera.setText("");
        lblAdicion.setText("");
        lblEje.setText("");
        lblCilindro.setText("");
        lblEjed.setText("");
        lblCilindrod.setText("");
        lblEsferad.setText("");
        lblAdiciond.setText("");
        lblDI.setText("");

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

    private void validarNumeros() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getControlNewText();
            if (text.matches("-?[0-9]*\\.?[0-9]*")) {
                return change;
            }
            return null;
        };
        lblDI.setTextFormatter(new TextFormatter<>(filter));
        lblEjed.setTextFormatter(new TextFormatter<>(filter));
        lblCilindro.setTextFormatter(new TextFormatter<>(filter));
        lblAdicion.setTextFormatter(new TextFormatter<>(filter));
        lblEje.setTextFormatter(new TextFormatter<>(filter));
        lblCilindrod.setTextFormatter(new TextFormatter<>(filter));
        lblAdiciond.setTextFormatter(new TextFormatter<>(filter));
        lblEsfera.setTextFormatter(new TextFormatter<>(filter));
        lblEsferad.setTextFormatter(new TextFormatter<>(filter));
    }
}

