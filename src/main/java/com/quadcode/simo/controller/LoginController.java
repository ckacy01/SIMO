/**
 * LoginController.java
 * Se creara la logica de la interfaz contectada al login
 * Sus funciones, que hace cada boton y como funciona la interfaz en si
 * Author: [Avila Carrillo Jorge Armando]
 * Fecha de creación: [06 - Junio - 2024]
 */
package com.quadcode.simo.controller;

import com.quadcode.simo.Main;
import com.quadcode.simo.dao.LoginDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * LoginController.java
 * @author Jorge A.
 * */

public class LoginController {

    @FXML
    // Se declara el objeto exit de tipo pane para poderlo vincular con nuestro FXML
    private Pane pnExit;
    @FXML
    // Objeto para la salida del login, cambia de color cuando detecta entrada del cursor
    private Text txtExit;
    @FXML
    private TextField fldUser;
    @FXML
    private PasswordField fldPassword;
    @FXML
    private Pane btnLogin;
    @FXML
    private Label lblError;

    /*
     * Primero se manipula el objeto para poder salir del Login
     */
    @FXML
    public void labCerrar() {
        // Selecciona la ventana actual y la cierra
        Stage stage = (Stage) pnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void changeColorRedExit() {
        txtExit.setFill(Paint.valueOf("#CB2E2D"));
    }

    @FXML
    public void changeNormalColor() {
        txtExit.setFill(Paint.valueOf("#b0c4de"));
    }

    /*
     * Area donde se manipula los datos ingresados por el usuario
     */
    public void Login() {
        if(fldUser.getText().isEmpty() && fldPassword.getText().isEmpty()) {
            lblError.setText("Usuario y/o Contraseña incorrectos");
            fldUser.getStyleClass().add("field-error");
            fldPassword.getStyleClass().add("field-error");
        }else{
            validateLogin();
        }
    }

    public void validateLogin() {
        LoginDao connection = new LoginDao();
        Connection con = connection.getConnection();

        String user = fldUser.getText();
        String pass = fldPassword.getText();

        String verifyLogin = "SELECT count(1) FROM usuarios WHERE Nombre = '" + user + "' AND Contraseña = '" + pass + "'";

        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(verifyLogin);

            while(rs.next()){
                if(rs.getInt(1) == 1){
                    labCerrar();
                    Stage homeStage = new Stage();
                    homeView(homeStage);

                }else {
                    lblError.setText("Usuario y/o Contraseña incorrecta");
                    fldUser.getStyleClass().add("field-error");
                    fldPassword.getStyleClass().add("field-error");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void homeView(Stage homeStage){
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/com/quadcode/simo/view/HomeView.fxml"));
            root.getStylesheets().add(getClass().getResource("/com/quadcode/simo/styles/styles.css").toExternalForm());
        }catch (IOException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);

        homeStage.setScene(scene);
        homeStage.setResizable(false);
        homeStage.setTitle("SIMO");
        homeStage.show();
    }

}
