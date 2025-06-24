package edu.fiuba.algo3;

import edu.fiuba.algo3.controller.Bienvenida;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;
    public static final int WIDTH = 1360;
    public static final int HEIGHT = 768;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("GWENT");
        Bienvenida.mostrarBienvenida();
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}
