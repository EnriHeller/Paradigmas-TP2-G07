package edu.fiuba.algo3.vistas;

import java.util.Objects;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controller.Bienvenida;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BienvenidaView{

    Stage stage = App.getStage();
    public StackPane construir() throws Exception {
        // Fondo
        Image fondo = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/bienvenida.png")).toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(
                fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        StackPane root = new StackPane();
        root.setBackground(new Background(bgImage));

        // Título superpuesto
        Text texto = new Text("Gwent versión FIUBA - G07 - 2025C1");
        texto.setFont(Font.font("Georgia", 24));
        texto.setStyle("-fx-fill: white; -fx-font-weight: bold;");

        StackPane.setAlignment(texto, Pos.TOP_CENTER);
        StackPane.setMargin(texto, new javafx.geometry.Insets(60, 0, 0, 0));

        // Botón comenzar
        Button boton = Botones.Boton_1("Comenzar", () -> {
            try {
                Bienvenida.mostrarMenu();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        boton.setStyle("-fx-background-color: #222; -fx-background-radius: 18px; -fx-border-radius: 18px; -fx-border-color: #7CFC00; -fx-border-width: 2px; -fx-text-fill: #7CFC00; -fx-font-weight: bold; -fx-font-size: 18px; -fx-padding: 10 32; -fx-cursor: hand;");

        StackPane.setAlignment(boton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(boton, new javafx.geometry.Insets(0, 0, 60, 0));
        BotonMusica botonMusica = new BotonMusica();
        Button botonMusicaReal = botonMusica.crear();
        StackPane.setAlignment(botonMusicaReal, Pos.TOP_RIGHT);
        StackPane.setMargin(botonMusicaReal, new Insets(10));
        root.getChildren().addAll(texto, boton, botonMusicaReal);
        return root;
    }
}

