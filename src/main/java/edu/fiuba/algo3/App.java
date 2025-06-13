package edu.fiuba.algo3;

import edu.fiuba.algo3.controller.MenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("GWENT - Menú");
        MenuView menu = new MenuView();
        Scene escena = new Scene(menu.construir(), 600, 400);
        stage.setScene(escena);
        stage.show();
    }

    public static void cambiarEscena(Scene nuevaEscena) {
        stage.setScene(nuevaEscena);
    }

    public static void main(String[] args) {
        launch();
    }
}
