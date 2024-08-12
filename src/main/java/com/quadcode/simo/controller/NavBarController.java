package com.quadcode.simo.controller;

import com.quadcode.simo.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

/**
 * NavBarController.java
 * --------------------------------------------------------------
 * Controlador para manejar cada uno de los paneles que se pueden seleccionar
 * ---------------------------------------------------------------
 * @author: Avila Carrillo Jorge Armando                           |
 * Fecha de creacion: 06 - AGOSTO - 2024                            |
 * Ultima Actualizacion: 06 - AGOSTO - 2024                        |
 * ---------------------------------------------------------------
 */

public class NavBarController {

    private static String username;
    private static LocalDate fecha = LocalDate.now();
    @FXML
    private Label lblFecha;
    @FXML
    private MenuButton menuUsuario;

    @FXML
    private Pane somePane;

    @FXML
    public void btnInicio(MouseEvent event) {
        navigateto("/com/quadcode/simo/view/HomeView.fxml", (Pane) event.getSource());
    }

    @FXML
    public void btnNuevaVenta(MouseEvent event) {
        navigateto("/com/quadcode/simo/view/NuevaVentaView.fxml", (Pane) event.getSource());
    }

    @FXML
    public void btnClientes(MouseEvent event) {
        navigateto("/com/quadcode/simo/view/ClienteView.fxml", (Pane) event.getSource());
    }

    @FXML
    public void btnPacientes(MouseEvent event) {
        navigateto("/com/quadcode/simo/view/PacienteView.fxml", (Pane) event.getSource());
    }

    @FXML
    public void btnVentas(MouseEvent event) {
        navigateto("/com/quadcode/simo/view/VentasView.fxml", (Pane) event.getSource());
    }

    @FXML
    public void btnInventario(MouseEvent event) {
        navigateto("/com/quadcode/simo/view/InventarioView.fxml", (Pane) event.getSource());
    }

    @FXML
    public void btnCerrarSesion(){
        mostrarAlertaCierreSesion();
    }

    @FXML
    public void btnConfiguracion(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quadcode/simo/view/ConfiguracionView.fxml"));
            Parent confview = loader.load();
            Scene scene = new Scene(confview);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Configuracion de: " + username);
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void navigateto(String fxml, Pane pane) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("/com/quadcode/simo/styles/styles.css").toExternalForm());
            Stage stage = (Stage) pane.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlertaCierreSesion() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmar cierre de sesión");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Está seguro que desea cerrar sesión?");
        Optional<ButtonType> result = alerta.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            cerrarSesion();
        }
    }

    private void cerrarSesion() {
        try{
            Main main = new Main();
            Stage stage = new Stage();
            Stage stage2 = (Stage) somePane.getScene().getWindow();
            main.start(stage);
            stage2.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    public void setUser(String username) {
        this.username = username;
        setMenuUser();
    }

    public void setMenuUser(){
        menuUsuario.setText(username);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        String fechaFormateada = fecha.format(formatter);
        lblFecha.setText(fechaFormateada);
    }

}
