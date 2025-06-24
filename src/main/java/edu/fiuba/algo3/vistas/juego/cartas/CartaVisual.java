package edu.fiuba.algo3.vistas.juego.cartas;

import edu.fiuba.algo3.modelo.cartas.Carta;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public abstract class CartaVisual extends VBox {

    protected Carta carta;
    public static final int ANCHO = 80;
    public static final int ALTO = 100;

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

    protected void setTamanioEstandar() {
        this.setPrefSize(ANCHO, ALTO);
        this.setMinSize(ANCHO, ALTO);
        this.setMaxSize(ANCHO, ALTO);
        this.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
    }

    protected ImageView crearImagenEstandar(Image imagen) {
        ImageView vistaImagen = new ImageView(imagen);
        vistaImagen.setFitWidth(ANCHO);
        vistaImagen.setFitHeight(ALTO);
        vistaImagen.setPreserveRatio(false);
        vistaImagen.setPickOnBounds(false);
        return vistaImagen;
    }

    public abstract void construirVista();
}