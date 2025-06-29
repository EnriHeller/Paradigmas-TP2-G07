package edu.fiuba.algo3.vistas.juego.cartas;

import java.util.Objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CartaReversoVisual extends StackPane {
    public CartaReversoVisual() {
        construirVista();
    }

    private void construirVista() {
        String ruta = "/imagenes/dorso.png";
        Image imagen;
        try {
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        } catch (Exception e) {
            System.err.println("[CartaReversoVisual] No se pudo cargar la imagen del dorso: " + ruta);
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/placeholder.png")));
        }
        ImageView vistaImagen = new ImageView(imagen);
        vistaImagen.setFitWidth(70);
        vistaImagen.setFitHeight(90);
        vistaImagen.setPreserveRatio(false);
        vistaImagen.setPickOnBounds(false);

        this.getChildren().clear();
        this.setPrefSize(70, 90);
        this.setMinSize(70, 90);
        this.setMaxSize(70, 90);
        this.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        this.getChildren().add(vistaImagen);
    }
}
