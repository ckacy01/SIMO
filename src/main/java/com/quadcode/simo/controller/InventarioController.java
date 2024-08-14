package com.quadcode.simo.controller;


import com.quadcode.simo.dao.InventarioDao;
import com.quadcode.simo.model.ClienteDetalle;
import com.quadcode.simo.model.Lentes;
import com.quadcode.simo.model.Mica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * InvetarioController.java
 * --------------------------------------------------------------
 * Controlador para manejar la ventana de Inventario
 * ---------------------------------------------------------------
 * @author: Avila Carrillo Jorge Armando                           |
 * Fecha de creacion: 06 - AGOSTO - 2024                            |
 * Ultima Actualizacion: 06 - AGOSTO - 2024                        |
 * ---------------------------------------------------------------
 */
public class InventarioController extends NavBarController{
    @FXML
    private TableView<Mica>tbMica;
    @FXML
    private TableView<Lentes>tbLentes;
    @FXML
    private TextField  fldBuscarLente;
    @FXML
    private TextField  fldBuscarMica;
    @FXML
    private TextField fldNombreL;
    @FXML
    private TextField fldStockL;
    @FXML
    private TextArea DescripcionL;
    @FXML
    private TextField fldContadoL;
    @FXML
    private TextField fldCreditoL;
    @FXML
    private TextField fldBuscarCliente1;
    @FXML
    private TextField fldNombreM;
    @FXML
    private TextField fldContadoM;
    @FXML
    private TextField fldCreditoM;

    private InventarioDao inventarioDao;
    private int MicaId;
    private int LenteId;

    public void initialize() {
        setMenuUser();
        inventarioDao = new InventarioDao();
        List<TableColumn<Lentes, ?>> columns = inventarioDao.getColumnsFromDatabaseLentes();
        tbLentes.getColumns().addAll(columns);
        mostarDatosLentes();
        List<TableColumn<Mica, ?>> columns1 = inventarioDao.getColumnsFromDatabaseMicas();
        tbMica.getColumns().addAll(columns1);
        mostarDatosMicas();
        eventoTablas();
        validarNumeros();
        configurarBusqueda();

    }

    public void mostarDatosLentes(){
        try{
            List<Lentes> lentes = inventarioDao.obetenerLentes();
            ObservableList<Lentes> observableList = FXCollections.observableArrayList(lentes);
            tbLentes.setItems(observableList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void mostarDatosMicas(){
        try{
            List<Mica> mica = inventarioDao.obetenerMicas();
            ObservableList<Mica> observableList = FXCollections.observableArrayList(mica);
            tbMica.setItems(observableList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void eventoTablas(){
        tbMica.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           if (newValue != null) {
               MicaId = newValue.getId();
               fldNombreM.setText(newValue.getTipo());
               fldContadoM.setText(String.valueOf(newValue.getContado()));
               fldCreditoM.setText(String.valueOf(newValue.getCredito()));
           }
        });
        tbLentes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                LenteId = newValue.getId();
                fldNombreL.setText(newValue.getNombre());
                fldStockL.setText(String.valueOf(newValue.getStock()));
                fldContadoL.setText(String.valueOf(newValue.getPrecioContado()));
                fldCreditoL.setText(String.valueOf(newValue.getPrecioCredito()));
                DescripcionL.setText(String.valueOf(newValue.getDescripcion()));
            }
        });
    }
    private void configurarBusqueda() {
        fldBuscarMica.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarMicasPorNombre(newValue);
        });

        fldBuscarLente.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarLentesPorNombre(newValue);
        });
    }
    private void filtrarMicasPorNombre(String nombreMica) {
        try {
            List<Mica> micas = inventarioDao.obetenerMicas();

            // Filtrar la lista de micas por nombre
            List<Mica> micasFiltradas = micas.stream()
                    .filter(mica -> mica.getTipo().toLowerCase().contains(nombreMica.toLowerCase()))
                    .collect(Collectors.toList());

            // Actualizar la tabla con la lista filtrada
            ObservableList<Mica> observableList = FXCollections.observableArrayList(micasFiltradas);
            tbMica.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filtrarLentesPorNombre(String nombreLente) {
        try {
            List<Lentes> lentes = inventarioDao.obetenerLentes();

            // Filtrar la lista de lentes por nombre
            List<Lentes> lentesFiltrados = lentes.stream()
                    .filter(lente -> lente.getNombre().toLowerCase().contains(nombreLente.toLowerCase()))
                    .collect(Collectors.toList());

            // Actualizar la tabla con la lista filtrada
            ObservableList<Lentes> observableList = FXCollections.observableArrayList(lentesFiltrados);
            tbLentes.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertarLente(){
        try{
        Lentes lente = new Lentes();
        lente.setNombre(fldNombreL.getText());
        lente.setPrecioCredito(Float.parseFloat(fldCreditoL.getText()));
        lente.setPrecioContado(Float.parseFloat(fldContadoL.getText()));
        lente.setDescripcion(DescripcionL.getText());
        lente.setStock(Integer.parseInt(fldStockL.getText()));
        inventarioDao.insertarLente(lente);
        showAlert(Alert.AlertType.INFORMATION, "Agregar Lente", "Lente agregado exitosamente!");
        mostarDatosLentes();
        limpiarCamposLente();
        }catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error al Insertar!", "Hubo un error al intentar insertar el Lente: " + fldNombreL.getText() + "favor de verificar los campos obligatorios marcados con '*'");
        }
    }


    public void insertarMica(){
        try {
        Mica mica = new Mica();
        mica.setTipo(fldNombreM.getText());
        mica.setContado(Float.parseFloat(fldContadoM.getText()));
        mica.setCredito(Float.parseFloat(fldCreditoM.getText()));
        inventarioDao.insertarMica(mica);
        showAlert(Alert.AlertType.INFORMATION, "Agregar Mica", "Mica agregada!");
        mostarDatosMicas();
        limpiarCamposMicas();
        }catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error al Insretar!", "Hubo un error al intentar insertar la Mica: " + fldNombreM.getText() + "favor de verificar los campos obligatorios marcados con '*'");
        }
    }

    public void modificarLente() {
        try {
            Lentes lente = new Lentes();
            lente.setId(LenteId);
            lente.setNombre(fldNombreL.getText());
            lente.setStock(Integer.parseInt(fldStockL.getText()));
            lente.setPrecioContado(Float.parseFloat(fldContadoL.getText()));
            lente.setPrecioCredito(Float.parseFloat(fldCreditoL.getText()));
            lente.setDescripcion(DescripcionL.getText());
            Alert alert;
            alert = showAlertConfirmation("Modificar Lente", "¿Seguro que desea modificar el Lente: " + lente.getNombre());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                inventarioDao.modificarLente(lente);
            }
            mostarDatosLentes();
            limpiarCamposLente();
        }catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error al Modificar!", "Hubo un error al intentar modificar el Lente: " + fldNombreL.getText() + "favor de verificar los campos obligatorios marcados con '*'");
        }
    }

    public void modificarMica(){
        try{
            Mica mica = new Mica();
            mica.setId(MicaId);
            mica.setTipo(fldNombreM.getText());
            mica.setCredito(Float.parseFloat(fldCreditoM.getText()));
            mica.setContado(Float.parseFloat(fldContadoM.getText()));
            Alert alert;
            alert = showAlertConfirmation("Modificar Mica","¿Seguro que desea modificar la Mica: " + mica.getTipo());
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                inventarioDao.modificarMica(mica);
            }
            mostarDatosMicas();
            limpiarCamposMicas();
        }catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error al Modificar!", "Hubo un error al intentar modificar la Mica: " + fldNombreM.getText() + "favor de verificar los campos obligatorios marcados con '*'" );
        }
    }

    public void limpiarCamposLente(){
        fldNombreL.setText("");
        fldContadoL.setText("");
        fldContadoL.setText("");
        fldCreditoL.setText("");
        fldStockL.setText("");
        DescripcionL.setText("");
    }

    public void limpiarCamposMicas(){
        fldNombreM.setText("");
        fldContadoM.setText("");
        fldCreditoM.setText("");
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

    private void validarNumeros(){
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*\\.?[0-9]*")) {
                return change;
            }
            return null;
        };
        fldContadoM.setTextFormatter(new TextFormatter<>(filter));
        fldCreditoM.setTextFormatter(new TextFormatter<>(filter));
        fldCreditoL.setTextFormatter(new TextFormatter<>(filter));
        fldContadoL.setTextFormatter(new TextFormatter<>(filter));
        fldStockL.setTextFormatter(new TextFormatter<>(filter));
    }

}
