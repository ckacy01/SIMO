/**
 * Main.java
 * Punto de entrada de la aplicación.
 * Inicia la vista principal de la aplicación de escritorio.
 * Author: [Avila Carrillo Jorge Armando]
 * Fecha de creación: [19 - MAYO - 2024]
 */

package com.quadcode.simo;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main.java
 *
 * @author Jorge A.
 */

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("SIMO");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
