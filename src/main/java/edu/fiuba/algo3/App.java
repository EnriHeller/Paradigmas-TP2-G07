package edu.fiuba.algo3;

import edu.fiuba.algo3.controller.Bienvenida;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;

public class App extends Application {

    private static Stage stage;

    
    public static final int WIDTH = 1360;
    public static final int HEIGHT = 768;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("GWENT");

        Image icono = new Image(getClass().getResourceAsStream("/imagenes/gwentLogo.png"));
        stage.getIcons().add(icono);

        // Obtener las dimensiones de la pantalla
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Establecer un tamaño máximo del 80% de la pantalla
        stage.setWidth(Math.min(WIDTH, screenBounds.getWidth() * 0.8));
        stage.setHeight(Math.min(HEIGHT, screenBounds.getHeight() * 0.8));

        // Centrar la ventana
        stage.centerOnScreen();

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
