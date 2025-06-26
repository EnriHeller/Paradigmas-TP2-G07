package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controller.Audio;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class BotonMusica {

    private static final String ICONO_SONIDO = "/imagenes/icono_sonido.png";
    private static final String ICONO_SONIDO_OFF = "/imagenes/icono_sonido_sin_fondo.png";

    public static Button crear() {
        Audio audio = Audio.getInstance();

        ImageView imagenView = new ImageView();
        imagenView.setFitWidth(40);  // Podés ajustar el tamaño
        imagenView.setFitHeight(40);

        actualizarImagen(audio, imagenView);

        Button boton = new Button();
        boton.setGraphic(imagenView);
        boton.setStyle("-fx-background-color: transparent;"); // Sin fondo ni borde

        boton.setOnAction(e -> {
            if (audio.estaActivo()) {
                audio.stop();
            } else {
                audio.activar();
            }
            actualizarImagen(audio, imagenView);
        });

        return boton;
    }

    private static void actualizarImagen(Audio audio, ImageView imagenView) {
        String ruta = audio.estaActivo() ? ICONO_SONIDO : ICONO_SONIDO_OFF;
        Image icono = new Image(Objects.requireNonNull(BotonMusica.class.getResource(ruta)).toExternalForm());
        imagenView.setImage(icono);
    }

    public static void agregarA(StackPane layout) {
        Button boton = crear();
        StackPane.setAlignment(boton, Pos.TOP_RIGHT);
        StackPane.setMargin(boton, new Insets(10));
        layout.getChildren().add(boton);
    }
}
