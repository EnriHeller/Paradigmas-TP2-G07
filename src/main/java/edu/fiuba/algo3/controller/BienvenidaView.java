package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Objects;

public class BienvenidaView {

    public StackPane construir() {
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
        Button boton = new Button("Comenzar");
        boton.setStyle("-fx-background-color: rgba(255,255,255,0.7); -fx-background-radius: 10;");
        boton.setOnAction(e -> App.mostrarMenu());


        StackPane.setAlignment(boton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(boton, new javafx.geometry.Insets(0, 0, 60, 0));

        root.getChildren().addAll(texto, boton);
        return root;
    }
}
