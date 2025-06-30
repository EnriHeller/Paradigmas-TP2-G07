package edu.fiuba.algo3;

import edu.fiuba.algo3.controller.Bienvenida;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;
    public static final int WIDTH = 1360;
    public static final int HEIGHT = 768;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("GWENT");
        try {
            Image icono = new Image(getClass().getResourceAsStream("/imagenes/gwentLogo.png"));
            stage.getIcons().add(icono);
        } catch (Exception e) {
            System.err.println("[App] No se pudo cargar el icono gwentLogo.png: " + e);
        }
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
