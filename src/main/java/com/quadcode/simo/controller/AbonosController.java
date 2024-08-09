package com.quadcode.simo.controller;

import com.quadcode.simo.model.Abonos;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AbonosController {
    @FXML
    private TableView <Abonos> tbAbonos;
    @FXML
    private TextField fldNombreC;
    @FXML
    private TextField fldNombreP;
    @FXML
    private TextField fldNombreC1;
    @FXML
    private DatePicker dateBoxVenta;

}

