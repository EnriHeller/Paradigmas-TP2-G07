package edu.fiuba.algo3;

import edu.fiuba.algo3.controller.Bienvenida;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("GWENT");
        Bienvenida.mostrarBienvenida(stage);
        stage.show();
    }



    public static void cambiarEscena(Scene nuevaEscena) {
        stage.setScene(nuevaEscena);
    }

    public static void main(String[] args) {
        launch();
    }
}
