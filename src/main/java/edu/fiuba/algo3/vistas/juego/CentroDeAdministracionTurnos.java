package edu.fiuba.algo3.vistas.juego;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    private boolean jugador1YaDescarto = false;
    private boolean jugador2YaDescarto = false;
    private Button botonDescarteRef;

    public CentroDeAdministracionTurnos(Juego juego) {
        this.juego = juego;
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

        // Botón de descarte
        botonDescarteRef = new Button("Descartar Cartas");
        botonDescarteRef.setStyle("-fx-background-color: rgba(255,255,255,0.8);");
        botonDescarteRef.setOnAction(e -> {
            botonDescarteRef.setDisable(true);
            Jugador jugadorActual = juego.jugadorActual();
            if(jugadorActual == juego.getJugador1()){
                if(!jugador1YaDescarto){
                    jugador1YaDescarto = true;
                    DescarteView ventanaDescarte = new DescarteView(juego.mostrarManoActual(), mano, jugadorActual);
                    ventanaDescarte.setScene(new javafx.scene.Scene(ventanaDescarte.construir(), 1200, 600));
                    ventanaDescarte.setTitle("Descartar Cartas");
                    javafx.scene.image.Image icono = new javafx.scene.image.Image(getClass().getResourceAsStream("/imagenes/gwentLogo.png"));
                    ventanaDescarte.getIcons().add(icono);
                    ventanaDescarte.initModality(javafx.stage.Modality.APPLICATION_MODAL);
                    ventanaDescarte.show();
                }
            }else{
                if(!jugador2YaDescarto){
                    jugador2YaDescarto = true;
                    DescarteView ventanaDescarte = new DescarteView(juego.mostrarManoActual(), mano, jugadorActual);
                    ventanaDescarte.setScene(new javafx.scene.Scene(ventanaDescarte.construir(), 1200, 600));
                    ventanaDescarte.setTitle("Descartar Cartas");
                    javafx.scene.image.Image icono = new javafx.scene.image.Image(getClass().getResourceAsStream("/imagenes/gwentLogo.png"));
                    ventanaDescarte.getIcons().add(icono);
                    ventanaDescarte.initModality(javafx.stage.Modality.APPLICATION_MODAL);
                    ventanaDescarte.show();
                }
            }
        });

        // Botón pasar
        Button botonPasar = Botones.Boton_1("Pasar", () -> {
            if (clicksSiguiente >= 1) {
                textoJugador.setText("Finalización de ronda");
                PauseTransition espera = new PauseTransition(Duration.seconds(2));
                espera.setOnFinished(e -> {
                    juego.finalizarRonda();
                    actualizarHistorialPuntos();
                    jugador1YaDescarto = false;
                    jugador2YaDescarto = false;
                    if (onTurnoFinalizado != null) {
                        Platform.runLater(onTurnoFinalizado);
                    }
                    if (!juego.juegoTerminado()) {
                        juego.tirarMoneda();
                        mostrarMoneda(juego.actual());
                        actualizarTextoJugador();
                        mano.actualizarCartas(juego.mostrarManoActual());
                        tablero.refrescar();
                    }
                });
                espera.play();
                clicksSiguiente = 0;
            } else {
                juego.siguienteJugador();

                // Ya no es necesario marcar el primer turno aquí
                actualizarBotonDescarte();
                mano.actualizarCartas(juego.mostrarManoActual());
                mostrarMoneda(juego.actual());
                actualizarTextoJugador();
                if (onTurnoFinalizado != null) {
                    Platform.runLater(onTurnoFinalizado);
                }
                clicksSiguiente++;
            }
        });

        mostrarMoneda(juego.actual());
        actualizarTextoJugador();
        actualizarHistorialPuntos();
        actualizarBotonDescarte();

        HBox grupoBotonMoneda = new HBox(10, botonPasar, monedaView);
        grupoBotonMoneda.setAlignment(Pos.CENTER_LEFT);
        HBox descarteBox = new HBox(15, botonDescarteRef);
        descarteBox.setAlignment(Pos.CENTER);
        descarteBox.setPadding(new Insets(10, 0, 0, 0));

        contenedor.getChildren().addAll(textoJugador, grupoBotonMoneda, descarteBox);
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
        String nombreJ1 = juego.getJugador1().getNombre();
        String nombreJ2 = juego.getJugador2().getNombre();

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

    private void actualizarBotonDescarte() {
        if (botonDescarteRef == null) return;
        // Usar el número de ronda real en vez de getPuntosPorRonda().size()
        boolean esPrimeraRonda = juego.getNumeroRondaActual() == 1;
        boolean habilitar = esPrimeraRonda && clicksSiguiente <= 1;
        botonDescarteRef.setDisable(!habilitar);
    }
}
