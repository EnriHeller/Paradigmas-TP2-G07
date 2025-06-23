package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.vistas.MenuView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Bienvenida {
    public static void mostrarMenu(Stage stage) {
        MenuView menu = new MenuView();
        Scene escenaMenu = new Scene(menu.construir(),800, 600);
        stage.setScene(escenaMenu);
    }

    public static void mostrarBienvenida(Stage stage) {
        edu.fiuba.algo3.vistas.Bienvenida bienvenida = new edu.fiuba.algo3.vistas.Bienvenida();
        Scene escenaBienvenida = new Scene(bienvenida.construir(stage), 1366, 768);
        stage.setScene(escenaBienvenida);
    }
}
