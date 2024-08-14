package com.quadcode.simo.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * HomeController.java
 * Se creara la logica de la pagina principal Home
 * Sus funciones, que hace cada boton y como funciona la interfaz en si
 * Author: [Avila Carrillo Jorge Armando]
 * Fecha de creación: [24 - Julio - 2024]
 */

public class HomeController extends NavBarController{

    @FXML
    public void initialize() {
        setMenuUser();
    }
}
