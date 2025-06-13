package edu.fiuba.algo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/fiuba/algo3/pantallaInicio.fxml"));
        Scene escena = new Scene(loader.load(), 400, 300);

        stage.setTitle("GWENT - Menú");
        stage.setScene(escena);
        stage.show();
    }

}
