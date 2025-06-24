package edu.fiuba.algo3.vistas.juego.cartas;

import edu.fiuba.algo3.modelo.cartas.Carta;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public abstract class CartaVisual extends VBox {

    protected Carta carta;

    public CartaVisual(Carta carta) {
        this.carta = carta;
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setAlignment(Pos.CENTER);
        this.setStyle(
                "-fx-background-color: #fff;" +
                        "-fx-border-color: black;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 3, 0, 2, 2);"
        );
    }

    public abstract void construirVista();
}