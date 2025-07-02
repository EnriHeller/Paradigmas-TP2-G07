package edu.fiuba.algo3.vistas.juego;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class PilaDescarteView extends StackPane {

    private boolean hayCartas = false;

    private ImageView cartaView;

    public PilaDescarteView() {
        Image img = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/" + "dorso.png")).toExternalForm());
        cartaView = new ImageView(img);
    }

    public void actualizarPila(boolean hayCartas) {
        String nombreImagen = hayCartas ? "dorso.png" : "ImagenSinNada.png";
        Image img = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/" + nombreImagen)).toExternalForm());
        cartaView.setImage(img);

    }

    public Region construir() {
        int cantidadCartas = 1;
        double ancho = 70 + cantidadCartas * 1.5;
        double alto = 90 + cantidadCartas * 1.5;

        Pane contenedor = new Pane();
        contenedor.setPrefSize(ancho, alto);

        actualizarPila(hayCartas);

        for (int i = 0; i < cantidadCartas; i++) {
            cartaView.setFitWidth(70);
            cartaView.setFitHeight(90);
            cartaView.setLayoutX(i * 1.5);
            cartaView.setLayoutY(i * 1.5);
            contenedor.getChildren().add(cartaView);
        }

        return contenedor;
    }
}
