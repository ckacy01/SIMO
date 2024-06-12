/**
 * Main.java
 * Punto de entrada de la aplicación.
 * Inicia la vista principal de la aplicación de escritorio.
 * Author: [Avila Carrillo Jorge Armando]
 * Fecha de creación: [19 - MAYO - 2024]
 */

package com.quadcode.simo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main.java
 *
 * @author Jorge A.
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quadcode/simo/view/LoginView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Iniciar Sesión (SIMO)");
            primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
