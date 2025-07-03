package edu.fiuba.algo3.vistas.juego;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import edu.fiuba.algo3.controller.Audio;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.vistas.Botones;
import edu.fiuba.algo3.vistas.DescarteView;
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

    private Label puntosJugador1Externos;
    private Label puntosJugador2Externos;
    private PilaDescarteView pilaDescarteView;

    public CentroDeAdministracionTurnos(Juego juego) {
        this.juego = juego;
    }

    public void setPilaDescarteView(PilaDescarteView pilaDescarteView) {
        this.pilaDescarteView = pilaDescarteView;
    }

    public void setLabelsExternos(Label j1, Label j2) {
        this.puntosJugador1Externos = j1;
        this.puntosJugador2Externos = j2;
    }

    public void setOnTurnoFinalizado(Runnable handler) {
        this.onTurnoFinalizado = handler;
    }

    public VBox construir(TableroView tablero, ManoView mano) {
        VBox contenedor = new VBox(10);
        contenedor.setAlignment(Pos.CENTER_LEFT);
        contenedor.setPadding(new Insets(20, 0, 0, 30));

        textoJugador = new Label();
        textoJugador.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");

        monedaView = new ImageView();
        monedaView.setFitWidth(50);
        monedaView.setFitHeight(50);

        descartarCartas(mano);

        // Botón pasar
        Button botonPasar = Botones.Boton_1("Pasar", () -> {
            if (clicksSiguiente >= 1) {
                textoJugador.setText("Finalización de ronda");
                Audio audio = Audio.getInstance();
                try {
                    audio.play("/audio/FinalizacionRonda.wav");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                PauseTransition espera = new PauseTransition(Duration.seconds(2));
                espera.setOnFinished(e -> {
                    actualizarHistorialPuntos();
                    juego.finalizarRonda();
                    actualizarHistorialPuntos();

                    if (pilaDescarteView != null) {
                        int cantidadDeCartasEnElDescarte1 = juego.cartasRestantesJugador("Descarte", 0);
                        int cantidadDeCartasEnElDescarte2 = juego.cartasRestantesJugador("Descarte", 1);
                        boolean hayCartas = cantidadDeCartasEnElDescarte1 != 0 || cantidadDeCartasEnElDescarte2 != 0;
                        pilaDescarteView.actualizarPila(hayCartas);
                    }

                    if (onTurnoFinalizado != null) {
                        Platform.runLater(onTurnoFinalizado);
                    }
                    if (!juego.juegoTerminado()) {
                        juego.tirarMoneda();
                        mostrarMoneda(juego.actual());
                        try {
                            audio.play("/audio/siguienteJugador.wav");
                        } catch (Exception eb) {
                            throw new RuntimeException(eb);
                        }
                        actualizarTextoJugador();
                        mano.actualizarCartas(juego.mostrarManoActual());
                        tablero.refrescar();
                    }
                });
                espera.play();
                clicksSiguiente = 0;
            } else {
                juego.siguienteJugador();
                descartarCartas(mano);
                mano.actualizarCartas(juego.mostrarManoActual());
                mostrarMoneda(juego.actual());
                Audio audio = Audio.getInstance();
                try {
                    audio.play("/audio/siguienteJugador.wav");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                actualizarTextoJugador();
                clicksSiguiente++;
            }
        });

        mostrarMoneda(juego.actual());
        actualizarTextoJugador();
        actualizarHistorialPuntos();

        HBox grupoBotonMoneda = new HBox(10, botonPasar, monedaView);
        grupoBotonMoneda.setAlignment(Pos.CENTER_LEFT);

        contenedor.getChildren().addAll(textoJugador, grupoBotonMoneda);
        return contenedor;
    }

    private void mostrarMoneda(int jugador) {
        String nombreImagen = (jugador == 0) ? "monedaJugador1.png" : "monedaJugador2.png";
        Image img = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/" + nombreImagen)).toExternalForm());
        monedaView.setImage(img);
    }

    private void actualizarTextoJugador() {
        textoJugador.setText("Turno de: " + juego.jugadorActual().getNombre());
    }

    private void actualizarHistorialPuntos() {
        List<Map<String, Integer>> puntosPorRonda = juego.getPuntosPorRonda();

        if (puntosPorRonda.isEmpty()) {
            return;
        }

        Map<String, Integer> primeraRonda = puntosPorRonda.get(0);
        String[] jugadores = primeraRonda.keySet().toArray(new String[0]);

        if (jugadores.length < 2) return;

        String nombreJ1 = jugadores[0];
        String nombreJ2 = jugadores[1];

        StringBuilder textoJ1 = new StringBuilder("Puntos por ronda de " + nombreJ1 + ": \n");
        StringBuilder textoJ2 = new StringBuilder("Puntos por ronda de " + nombreJ2 + ": \n");

        for (int i = 0; i < puntosPorRonda.size(); i++) {
            Map<String, Integer> ronda = puntosPorRonda.get(i);
            textoJ1.append(ronda.getOrDefault(nombreJ1, 0));
            textoJ2.append(ronda.getOrDefault(nombreJ2, 0));
            if (i < puntosPorRonda.size() - 1) {
                textoJ1.append(", ");
                textoJ2.append(", ");
            }
        }

        if (puntosJugador1Externos != null) puntosJugador1Externos.setText(textoJ1.toString());
        if (puntosJugador2Externos != null) puntosJugador2Externos.setText(textoJ2.toString());
    }

    private void descartarCartas(ManoView mano){
        System.out.println("Descartando cartas... " + juego.getNumeroRondaActual());
        if (juego.getNumeroRondaActual() == 1){
            Jugador jugadorActual = juego.jugadorActual();
            DescarteView ventanaDescarte = new DescarteView(juego.mostrarManoActual(), mano, jugadorActual);
            ventanaDescarte.setScene(new javafx.scene.Scene(ventanaDescarte.construir(), 1200, 600));
            ventanaDescarte.setTitle("Descartar Cartas");
            javafx.scene.image.Image icono = new javafx.scene.image.Image(getClass().getResourceAsStream("/imagenes/gwentLogo.png"));
            ventanaDescarte.getIcons().add(icono);
            ventanaDescarte.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            ventanaDescarte.show();
        }
    }

}
