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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main.java
 *
 * @author Jorge A.
 */

public class Main extends Application {
    private double xMouse, yMouse;
    @Override
    public void start(Stage primaryStage){
        Parent root = null;

       try {
           root = FXMLLoader.load(getClass().getResource("/com/quadcode/simo/view/LoginView.fxml"));
           root.getStylesheets().add(getClass().getResource("/com/quadcode/simo/styles/styles.css").toExternalForm());
       }catch (IOException ex){
           Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
       }

       Scene scene = new Scene(root);

       primaryStage.setScene(scene);
       primaryStage.initStyle(StageStyle.UNDECORATED);
       primaryStage.setTitle("SIMO");
       primaryStage.show();

       root.setOnMousePressed(mouseEvent -> {
          xMouse = mouseEvent.getSceneX();
          yMouse = mouseEvent.getSceneY();
       });
       root.setOnMouseDragged(mouseEvent -> {
           primaryStage.setX(mouseEvent.getScreenX() - xMouse);
           primaryStage.setY(mouseEvent.getScreenY() - yMouse);
       });
       }


    public static void main(String[] args) {
        launch(args);
    }


}
