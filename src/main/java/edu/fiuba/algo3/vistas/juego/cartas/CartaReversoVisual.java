package edu.fiuba.algo3.vistas.juego.cartas;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

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
}
