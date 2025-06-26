package edu.fiuba.algo3.controller;

import java.util.Objects;

import edu.fiuba.algo3.vistas.juego.TableroView;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;

public class Juego {
    private final edu.fiuba.algo3.modelo.principal.Juego juego;

    public Juego(edu.fiuba.algo3.modelo.principal.Juego juego) {
        this.juego = juego;
    }

    public BorderPane construir() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(1000, 700);

        // Fondo del juego (tablero)
        Image fondo = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/tablero.jpg")).toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(
                fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        layout.setBackground(new Background(bgImage));

        // Parte superior: nombre del jugador 2
        Label nombreJ2 = new Label(juego.getJugador2().getNombre());
        nombreJ2.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        layout.setTop(nombreJ2);
        BorderPane.setAlignment(nombreJ2, Pos.TOP_CENTER);

        // Centro: vista del tablero usando TableroController
        TableroController tableroController = new TableroController(juego);
        TableroView tableroView = new TableroView(tableroController);
        layout.setCenter(tableroView.construir());
        return layout;
    }
}
