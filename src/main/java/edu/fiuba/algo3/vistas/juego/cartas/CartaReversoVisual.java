package edu.fiuba.algo3.vistas.juego.cartas;

import java.util.Objects;

import edu.fiuba.algo3.modelo.cartas.Carta;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CartaReversoVisual extends CartaVisual {
    public CartaReversoVisual(Carta carta) {
        super(carta);
        construirVista();
    }

    @Override
    public void construirVista() {
        String ruta = "/imagenes/dorso.png";
        Image imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        ImageView vistaImagen = new ImageView(imagen);
        vistaImagen.setFitWidth(70);
        vistaImagen.setFitHeight(90);
        vistaImagen.setPreserveRatio(false);
        vistaImagen.setPickOnBounds(false);

        StackPane stack = new StackPane(vistaImagen);
        stack.setPrefSize(70, 90);
        stack.setMinSize(70, 90);
        stack.setMaxSize(70, 90);
        stack.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        this.getChildren().clear();
        this.setPrefSize(70, 90);
        this.setMinSize(70, 90);
        this.setMaxSize(70, 90);
        this.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        this.getChildren().add(stack);
    }

    @Override
    public void construirCamposInfo() {
        // No muestra overlay de información para el reverso
        infoOverlay.getChildren().clear();
    }
}
