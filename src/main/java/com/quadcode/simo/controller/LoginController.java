/**
 * LoginController.java
 * Se creara la logica de la interfaz contectada al login
 * Sus funciones, que hace cada boton y como funciona la interfaz en si
 * Author: [Avila Carrillo Jorge Armando]
 * Fecha de creación: [06 - Junio - 2024]
 */
package com.quadcode.simo.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * LoginController.java
 * @author Jorge A.
 * */

public class LoginController implements Initializable {

    @FXML
    // Se declara el atributo exit de tipo pane para poderlo vincular con nuestro FXML
    private Pane exit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void labCerrar(){
        // Selecciona la ventana actual y la cierra
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

}
