package com.quadcode.simo.controller;


import com.quadcode.simo.model.Lentes;
import com.quadcode.simo.model.Mica;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;

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
    private TextField  fldBuscarCliente;
    @FXML
    private TextField fldNombreL;
    @FXML
    private TextField fldStockL;
    @FXML
    private TextArea DescripcionL;
    @FXML
    private TextField fldContadoL;
    @FXML
    private TextField fldContadoC;
    @FXML
    private TextField fldBuscarCliente1;
    @FXML
    private TextField fldNombreL1;
    @FXML
    private TextField fldContadoL1;
    @FXML
    private TextField fldContadoC1;



    public void initialize() {
        setMenuUser();

    }
}
