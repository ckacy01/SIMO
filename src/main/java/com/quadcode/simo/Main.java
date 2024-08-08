package com.quadcode.simo;

import com.quadcode.simo.controller.LoginController;
import com.quadcode.simo.dao.LoginDao;
import com.quadcode.simo.service.LoginService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Main.java
 * --------------------------------------------------------------
 * Clase principal que inicia la aplicacion JavaFX.
 * Esta clase configura y muestra la ventana de inicio de sesión.
 * ---------------------------------------------------------------
 * @author: Avila Carrillo Jorge Armando                           |
 * Fecha de creacion: 19 - MAYO - 2024                            |
 * Ultima Actualizacion: 25 - JULIO - 2024                        |
 * ---------------------------------------------------------------
 */

public class Main extends Application {

    // Atrributos que capturan la posicion del cursor
    private double xMouse, yMouse;

    // Punto de entrada para la aplicacion.
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Inicializa la aplicacion y carga la interfaz de usuario del Login.
     *
     * @param primaryStage El escenario principal de la aplicación.
     * @throws Exception Si ocurre algun error al cargar el archivo FXML.
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Imagen para el logo de la aplicacion
        Image logo = new Image("/com/quadcode/simo/img/LogoMasOptica.jpg");

        // Carga el archivo FXML para la vista de inicio de sesión
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quadcode/simo/view/LoginView.fxml"));

        // Carga el objeto raiz desde el archivo FXML.
        Parent root = loader.load();

        // Obtiene la hoja de estilos
        root.getStylesheets().add(getClass().getResource("/com/quadcode/simo/styles/styles.css").toExternalForm());

        // Obtiene el controlador de vista del Login
        LoginController loginController = loader.getController();

        // Configura el servicio de usuario y el DAO
        LoginDao loginDao = new LoginDao();
        LoginService loginService = new LoginService(loginDao);

        // Establece el servicio de usuario en el controlador
        loginController.setLoginService(loginService);

        // Configura la escena principal con el objeto raiz cargado desde FXML
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("SIMO");
        primaryStage.show();
        primaryStage.getIcons().add(logo);

        // Obtiene la posicion del cursor, y mueve la ventana mientras esta es presionada por el usuario
        // Esto es por que la ventana esta tipo UNDECORATED
        root.setOnMousePressed(mouseEvent -> {
            xMouse = mouseEvent.getSceneX();
            yMouse = mouseEvent.getSceneY();
        });
        root.setOnMouseDragged(mouseEvent -> {
            primaryStage.setX(mouseEvent.getScreenX() - xMouse);
            primaryStage.setY(mouseEvent.getScreenY() - yMouse);
        });
    }
}
