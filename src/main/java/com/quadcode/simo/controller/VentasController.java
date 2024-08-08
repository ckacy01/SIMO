package com.quadcode.simo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * VentasController.java
 * --------------------------------------------------------------
 * Controlador para manejar el apartado de Ventas
 * ---------------------------------------------------------------
 * @author: Avila Carrillo Jorge Armando                           |
 * Fecha de creacion: 06 - AGOSTO - 2024                            |
 * Ultima Actualizacion: 06 - AGOSTO - 2024                        |
 * ---------------------------------------------------------------
 */

public class VentasController extends NavBarController{
    @FXML
    public void initialize() {
        setMenuUser();
    }

    @FXML
    public void verAbonos(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quadcode/simo/view/AbonosView.fxml"));
            Parent abonosView = loader.load();
            Scene scene = new Scene(abonosView);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Abonos");
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
