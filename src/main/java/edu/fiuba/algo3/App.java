package edu.fiuba.algo3;

import java.util.Objects;

import edu.fiuba.algo3.controller.BienvenidaView;
import edu.fiuba.algo3.controller.MenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("GWENT");
        mostrarBienvenida();
        stage.show();
    }

    public static void mostrarBienvenida() {
        BienvenidaView bienvenida = new BienvenidaView();
        Scene escenaBienvenida = new Scene(bienvenida.construir(), 1366, 768);
        stage.setScene(escenaBienvenida);
    }

    public static void mostrarMenu() {
        MenuView menu = new MenuView();
        Scene escenaMenu = new Scene(menu.construir(), 800, 600);
        stage.setScene(escenaMenu);
    }

    public static void cambiarEscena(Scene nuevaEscena) {
        stage.setScene(nuevaEscena);
    }

    public static void main(String[] args) {
        launch();
    }
}
