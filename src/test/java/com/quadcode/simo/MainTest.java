/**
 * MainTest.java
 * Punto de entrada de la aplicación.
 * Inicia la vista principal de la aplicación de escritorio.
 * Autor: [Avila Carrillo Jorge Armando]
 * Fecha de creación: [19 - MAYO - 2024]
 */

package com.quadcode.simo;


import javafx.application.Application;
import javafx.stage.Stage;

public class MainTest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("SIMO TEST");
        stage.show();
    }

    public static void main(String[] args) {
      launch(args);
    }
}
