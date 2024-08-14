package com.quadcode.simo.controller;

import com.quadcode.simo.Main;
import com.quadcode.simo.dao.ConfigDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;

public class ConfiguracionController implements Initializable {
    private String password;
    private String usuario;

    @FXML
    private Pane somePane;
    @FXML
    private TextField fldPassword;
    @FXML
    private TextField fldPasswordNew1;
    @FXML
    private TextField fldPasswordNew2;
    @FXML
    private Pane btnGuardar;
    @FXML
    private Pane btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Listeners();
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getUsuario() {
        return usuario;
    }

    public void Listeners(){
        fldPassword.textProperty().addListener((observable) -> validacionDePasswordActual());
        fldPasswordNew1.textProperty().addListener((observable) -> validacionDePasswordActual());
        fldPasswordNew2.textProperty().addListener((observable) -> validacionDePasswordActual());
    }

    public void validacionDePasswordActual() {
            if (fldPassword.getText().equals(password)) {
                btnGuardar.setVisible(true);
            }else{
                btnGuardar.setVisible(false);
            }
    }

    public void validacionDePassword() {
        if (fldPasswordNew2.getText().isEmpty() ||fldPasswordNew1.getText().isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Campos vacios!", "Favor de ingresar una nueva contraseña!");
        }else if(fldPasswordNew2.getText().equals(fldPasswordNew1.getText())){
            Alert alert = showAlertConfirmation("Confirmación", "¿Seguro que desea volver a cambiar su contraseña?(Tendrá que volver a iniciar sesion!)");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                try {
                    password = fldPasswordNew1.getText();
                    ConfigDao configDao = new ConfigDao();
                    configDao.modificarContra(usuario, password);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else {
            showAlert(Alert.AlertType.ERROR, "Error", "Ha ocurrido un error vuelva a ingresar las contraseñas!");
            fldPassword.setText("");
            fldPasswordNew2.setText("");
            fldPasswordNew1.setText("");
        }
    }


    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Alert showAlertConfirmation( String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }





}
