package edu.fiuba.algo3.vistas.juego;

import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.vistas.Botones;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Objects;

public class CentroDeAdministracionTurnos {

    private final Juego juego;
    private int clicksSiguiente = 0;
    private ImageView monedaView;
    private Label textoJugador;

    public CentroDeAdministracionTurnos(Juego juego) {
        this.juego = juego;
    }

    public VBox construir() {
        VBox contenedor = new VBox(5);
        contenedor.setAlignment(Pos.CENTER_LEFT);
        contenedor.setPadding(new Insets(20, 0, 0, 30)); // Espaciado desde el borde

        // Texto arriba del botón
        textoJugador = new Label();
        textoJugador.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");

        // Imagen de la moneda
        monedaView = new ImageView();
        monedaView.setFitWidth(50);
        monedaView.setFitHeight(50);

        // Botón
        Button botonSiguiente = Botones.Boton_1("Siguiente jugador", () -> {
            if (clicksSiguiente >= 1) {
                textoJugador.setText("Finalización de ronda");
                PauseTransition espera = new PauseTransition(Duration.seconds(2));
                espera.setOnFinished(e -> {
                    juego.finalizarRonda();
                    juego.tirarMoneda();
                    mostrarMoneda(juego.actual());
                    actualizarTextoJugador(juego.actual());
                });
                espera.play();
                clicksSiguiente = 0;
            } else {
                juego.siguienteJugador();
                mostrarMoneda(juego.actual());
                actualizarTextoJugador(juego.actual());
                clicksSiguiente++;
            }
        });

        // Tirada inicial
        juego.tirarMoneda();
        mostrarMoneda(juego.actual());
        actualizarTextoJugador(juego.actual());

        // Layout horizontal con botón + moneda
        HBox grupoBotonMoneda = new HBox(10, botonSiguiente, monedaView);
        grupoBotonMoneda.setAlignment(Pos.CENTER_LEFT);

        // Agregar texto + grupo de control a VBox
        contenedor.getChildren().addAll(textoJugador, grupoBotonMoneda);
        return contenedor;
    }

    private void mostrarMoneda(int jugador) {
        String nombreImagen = (jugador == 0) ? "monedaJugador1.png" : "monedaJugador2.png";
        Image img = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/" + nombreImagen)).toExternalForm());
        monedaView.setImage(img);
    }

    private void actualizarTextoJugador(int jugador) {
        textoJugador.setText("Juega: jugador " + (jugador + 1));
    }
}


