
package edu.fiuba.algo3.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Bienvenida {
    public static void mostrarMenu(Stage stage) {
        MenuView menu = new MenuView();
        Scene escenaMenu = new Scene(menu.construir(), 800, 600);
        stage.setScene(escenaMenu);
    }
}
