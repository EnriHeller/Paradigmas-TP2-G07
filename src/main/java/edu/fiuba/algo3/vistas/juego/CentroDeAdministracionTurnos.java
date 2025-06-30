package edu.fiuba.algo3.vistas.juego;

import java.util.Objects;

import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.vistas.Botones;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CentroDeAdministracionTurnos {
    private final Juego juego;
    private int clicksSiguiente = 0;
    private ImageView monedaView;
    private Label textoJugador;
    private Runnable onTurnoFinalizado;

    public CentroDeAdministracionTurnos(Juego juego) {
        this.juego = juego;
    }

    public void setOnTurnoFinalizado(Runnable handler) {
        this.onTurnoFinalizado = handler;
    }

    public VBox construir(TableroView tablero, ManoView mano) {
        VBox contenedor = new VBox(5);
        contenedor.setAlignment(Pos.CENTER_LEFT);
        contenedor.setPadding(new Insets(20, 0, 0, 30));

        // Texto de jugador actual
        textoJugador = new Label();
        textoJugador.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");

        // Imagen de la moneda
        monedaView = new ImageView();
        monedaView.setFitWidth(50);
        monedaView.setFitHeight(50);

        // Botón de siguiente jugador
        Button botonSiguiente = Botones.Boton_1("Siguiente jugador", () -> {
            if (clicksSiguiente >= 1) {
                textoJugador.setText("Finalización de ronda");
                PauseTransition espera = new PauseTransition(Duration.seconds(2));
                espera.setOnFinished(e -> {
                    juego.finalizarRonda();
                    
                    // Notificar que la ronda terminó y verificar fin de juego
                    if (onTurnoFinalizado != null) {
                        Platform.runLater(() -> onTurnoFinalizado.run());
                    }

                    // Solo continuar si el juego no ha terminado
                    if (!juego.juegoTerminado()) {
                        juego.tirarMoneda();
                        mostrarMoneda(juego.actual());
                        actualizarTextoJugador(juego.actual());
                        mano.actualizarCartas(juego.mostrarManoActual());
                        tablero.refrescar(); // <- actualiza el tablero visual y lógicamente
                    }
                });
                espera.play();
                clicksSiguiente = 0;
            } else {
                juego.siguienteJugador();
                mano.actualizarCartas(juego.mostrarManoActual());

                mostrarMoneda(juego.actual());
                actualizarTextoJugador(juego.actual());
                clicksSiguiente++;
            }

        });

        // // Datos iniciales inicial de moneda
        mostrarMoneda(juego.actual());
        actualizarTextoJugador(juego.actual());


        // Layout horizontal para botón y moneda
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