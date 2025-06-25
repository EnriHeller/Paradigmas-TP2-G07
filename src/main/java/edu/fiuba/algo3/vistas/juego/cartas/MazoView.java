package edu.fiuba.algo3.vistas.juego.cartas;

import java.util.Objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class MazoView {

    private final int cantidadCartas;

    public MazoView(int cantidadCartas) {
        this.cantidadCartas = cantidadCartas;
    }

    public Region construir() {
        Pane contenedor = new Pane();
        
        String rutaDorso = "/imagenes/dorso.png";
        Image dorso = new Image(Objects.requireNonNull(getClass().getResourceAsStream(rutaDorso)));

        for (int i = 0; i < cantidadCartas; i++) {
            ImageView carta = new ImageView(dorso);
            carta.setFitWidth(70);
            carta.setFitHeight(90);
            carta.setLayoutX(i * 1.5); // desplazamiento horizontal leve
            carta.setLayoutY(i * 1.5); // desplazamiento vertical leve
            contenedor.getChildren().add(carta);
        }

        contenedor.setPrefSize(70 + cantidadCartas * 1.5, 90 + cantidadCartas * 1.5);
        return contenedor;
    }
}
