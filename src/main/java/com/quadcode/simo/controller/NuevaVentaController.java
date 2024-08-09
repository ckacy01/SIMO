package com.quadcode.simo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * NuevaVentaController.java
 * --------------------------------------------------------------
 * Controlador para manejar el apartado de NuevaVenta
 * ---------------------------------------------------------------
 * @author: Avila Carrillo Jorge Armando                           |
 * Fecha de creacion: 06 - AGOSTO - 2024                            |
 * Ultima Actualizacion: 06 - AGOSTO - 2024                        |
 * ---------------------------------------------------------------
 */

public class NuevaVentaController extends NavBarController{
    @FXML
    private TextField fldNombrePaciente;
    @FXML
    private DatePicker dateBoxVenta;
    @FXML
    private ComboBox menuArmazon;
    @FXML
    private ComboBox menuMica;
    @FXML
    private ComboBox menuTinte;


    @FXML
    public void initialize() {
        setMenuUser();
    }
}
