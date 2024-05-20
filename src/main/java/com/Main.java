/**
 * Main.java
 * 
 * Punto de entrada de la aplicación.
 * Inicia la vista principal de la aplicación de escritorio.
 * 
 * Autor: [Avila Carrillo Jorge Armando]
 * Fecha de creación: [19 - MAYO - 2024]
 */

package com;

import com.view.MainView;

public class Main {
    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.display();
    }
}
