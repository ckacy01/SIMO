package com.quadcode.simo.controller;

import com.quadcode.simo.model.NuevaVenta;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

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
    private ComboBox menuMetodo;
    @FXML
    private ComboBox menuAbono;
    @FXML
    private Label lblSubTotal;
    @FXML
    private TextField fdlEnganche;
    @FXML
    private Pane btnGenerarImpri;
    @FXML
    private Pane btnGenerar;
    @FXML
    private Pane btnLimpiar;
    @FXML
    private TableView<NuevaVenta> tbNuevaVenta;

    @FXML
    public void initialize() {
        setMenuUser();
    }
}
