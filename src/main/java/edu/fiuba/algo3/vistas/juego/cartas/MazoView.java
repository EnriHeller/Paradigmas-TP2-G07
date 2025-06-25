package edu.fiuba.algo3.vistas.juego.cartas;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class MazoView extends Pane {

    public MazoView(int cantidadCartas) {
        String rutaDorso = "/imagenes/dorso.png";
        Image dorso = new Image(Objects.requireNonNull(getClass().getResourceAsStream(rutaDorso)));

        for (int i = 0; i < cantidadCartas; i++) {
            ImageView carta = new ImageView(dorso);
            carta.setFitWidth(70);
            carta.setFitHeight(90);
            carta.setLayoutX(i * 1.5);
            carta.setLayoutY(i * 1.5);
            this.getChildren().add(carta);
        }

        this.setPrefSize(70 + cantidadCartas * 1.5, 90 + cantidadCartas * 1.5);
        this.setStyle("-fx-background-color: transparent;");
    }
}
