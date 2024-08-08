
package com.quadcode.simo.controller;

import com.quadcode.simo.model.UserLogin;
import com.quadcode.simo.service.LoginService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * LoginController.java
 * --------------------------------------------------------------
 * Controlador para manejar el proceso de inicio de sesion
 * ---------------------------------------------------------------
 * @author: Avila Carrillo Jorge Armando                           |
 * Fecha de creacion: 06 - JUNIO - 2024                            |
 * Ultima Actualizacion: 25 - JULIO - 2024                        |
 * ---------------------------------------------------------------
 */

public class LoginController {

    @FXML
    private Pane pnExit;
    @FXML
    private Text txtExit;
    @FXML
    private TextField fldUser;
    @FXML
    private PasswordField fldPassword;
    @FXML
    private Label lblError;

    private LoginService loginService;

    /**
     * Establece el servicio de usuario.
     *
     * @param loginService El servicio de usuario a utilizar.
     */

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Controla el cierre de la ventana del login
     */
    @FXML
    public void labCerrar() {
        // Selecciona la ventana actual y la cierra
        Stage stage = (Stage) pnExit.getScene().getWindow();
        stage.close();
    }

    /**
     * Controla el color de la cruz de salida, al momento de que el usuario pone el mouse por encima de esta
     */
    @FXML
    public void changeColorRedExit() {
        txtExit.setFill(Paint.valueOf("#CB2E2D"));
    }

    @FXML
    public void changeNormalColor() {
        txtExit.setFill(Paint.valueOf("#b0c4de"));
    }

    /**
     * Autentica al usuario con los datos ingresados
     */
    @FXML
    public void authenticateUser() {
        String username = fldUser.getText();
        String password = fldPassword.getText();

        UserLogin userLogin = loginService.authenticate(username, password);

        if (userLogin != null) {
            showAlert(AlertType.INFORMATION, "Inicio de sesión exitoso!", "Bienvenido " + userLogin.getUsername());
            labCerrar();
            homeView(userLogin);
        } else {
            loginError();
        }
    }

    /**
     * En caso de que sean datos incorrectos, cambiara de color nuestros campos.
     */
    public void loginError() {
        lblError.setText("Usuario y/o Contraseña incorrectos");
        fldUser.getStyleClass().add("field-error");
        fldPassword.getStyleClass().add("field-error");
    }

    /**
     * Muestra una alerta con el mensaje especificado.
     *
     * @param alertType El tipo de alerta
     * @param title     El titulo de la alerta
     * @param message   Mensaje de la alerta
     */
    public void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Carga la vista principal despues de un inicio de sesion exitoso
     *
     * @param userLogin El usuario autenticado.
     */

    public void homeView(UserLogin userLogin) {
        try {
            Image logo = new Image("/com/quadcode/simo/img/LogoMasOptica.jpg");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quadcode/simo/view/HomeView.fxml"));
            Parent homeview = loader.load();
            HomeController homeController = loader.getController();
            homeController.setUser(userLogin.getUsername());
            homeview.getStylesheets().add(getClass().getResource("/com/quadcode/simo/styles/styles.css").toExternalForm());
            Scene scene = new Scene(homeview);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(logo);
            stage.setTitle("SIMO");
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
