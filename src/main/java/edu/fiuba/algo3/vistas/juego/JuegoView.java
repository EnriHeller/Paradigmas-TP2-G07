package edu.fiuba.algo3.vistas.juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.fiuba.algo3.controller.FinalizadorDeJuego;
import edu.fiuba.algo3.controller.TableroController;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.vistas.BotonMusica;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class JuegoView {
    private final Juego juego;
    private final FinalizadorDeJuego finalizadorDeJuego;
    private final int xMazo = 1140;
    private final int yMazo = 425; 


    public JuegoView(Juego juego) {
        this.juego = juego;
        this.finalizadorDeJuego = new FinalizadorDeJuego(juego);
    }

    public BorderPane construir() throws Exception {
        BorderPane root = new BorderPane();

        // Fondo de madera ocupa toda la pantalla (en root, no en stack)
        try {
            Image woodBg = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/woodBackground.png")).toExternalForm());
            BackgroundImage bgImage = new BackgroundImage(
                woodBg,
                javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                javafx.scene.layout.BackgroundPosition.CENTER,
                new javafx.scene.layout.BackgroundSize(100, 100, true, true, true, true)
            );
            root.setBackground(new javafx.scene.layout.Background(bgImage));
        } catch (Exception e) {
            System.err.println("[ERROR] No se pudo cargar woodBackground.png: " + e);
        }

        StackPane stack = new StackPane();

        // --- Bloque de juego fijo ---
        Pane bloqueJuego = new Pane();
        bloqueJuego.setPrefSize(1300, 700); // Tamaño fijo del bloque de juego
        bloqueJuego.setMinSize(1300, 700);
        bloqueJuego.setMaxSize(1300, 700);

        // Tablero (centrado dentro del bloque)
        TableroController tableroController = new TableroController(juego);
        TableroView tablero = new TableroView(tableroController);
        Region tableroRegion = tablero.construir();
        tableroRegion.setLayoutX(0); // El tablero ya está centrado en su propio StackPane
        tableroRegion.setLayoutY(0);
        bloqueJuego.getChildren().add(tableroRegion);

        // Mano (abajo, centrada respecto al bloque)
        ManoView mano = new ManoView(juego.mostrarManoActual(), carta -> {
            tablero.setCartaElegida(carta);
            tablero.refrescar(); // <- actualiza el tablero visual y lógicamente
        });

        Region manoRegion = mano.construir();
        manoRegion.setLayoutX(310); // Ajusta según el diseño
        manoRegion.setLayoutY(560); // Ajusta según el diseño
        bloqueJuego.getChildren().add(manoRegion);

        // Centro de turnos (izquierda)
        CentroDeAdministracionTurnos turnos = new CentroDeAdministracionTurnos(juego);
        VBox panelTurno = turnos.construir(tablero);
        panelTurno.setLayoutX(-10);
        panelTurno.setLayoutY(210);
        bloqueJuego.getChildren().add(panelTurno);
        // Listener para fin de juego
        turnos.setOnTurnoFinalizado(() -> Platform.runLater(() -> {
            try {
                finalizadorDeJuego.verificarFinDeJuego();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }));
        // Pila de descarte (arriba derecha)
        PilaDescarteView pilaDescarteJugador = new PilaDescarteView(juego.getUltimaCartaDeLaPilaDeDescarte());
        Region pilaRegion = pilaDescarteJugador.construir();
        pilaRegion.setLayoutX(xMazo);
        pilaRegion.setLayoutY(yMazo - 110);
        bloqueJuego.getChildren().add(pilaRegion);
        
        // Mazo (abajo derecha, relativo al bloque)
        int cartasRestantes = juego.cartasEnMazoActual();
        edu.fiuba.algo3.vistas.juego.cartas.MazoView mazoView = new edu.fiuba.algo3.vistas.juego.cartas.MazoView(cartasRestantes);
        mazoView.setLayoutX(xMazo); // Ajusta según el diseño del bloque
        mazoView.setLayoutY(yMazo); 
        bloqueJuego.getChildren().add(mazoView);

        //Boton de musica
        BotonMusica botonMusica = new BotonMusica();
        Button botonMusicaReal = botonMusica.crear();
        StackPane.setAlignment(botonMusicaReal, Pos.TOP_RIGHT);
        StackPane.setMargin(botonMusicaReal, new Insets(10));

        bloqueJuego.getChildren().add(botonMusicaReal);

        // Centrar el bloque de juego en el StackPane
        stack.getChildren().add(bloqueJuego);
        StackPane.setAlignment(bloqueJuego, Pos.CENTER);

        Platform.runLater(() -> animarReparto(stack, manoRegion));
        stack.getStylesheets().add(getClass().getResource("/carta-visual.css").toExternalForm());

        root.setCenter(stack);
        return root;
    }

    private void animarReparto(StackPane stack, Region manoRegion) {
        manoRegion.setVisible(false);

        Image dorso = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/dorso.png")).toExternalForm());
        List<ImageView> animadas = new ArrayList<>();

        double startX = xMazo - 618;
        double startY = yMazo - 315;

        for (int i = 0; i < 10; i++) {
            ImageView carta = new ImageView(dorso);
            carta.setFitWidth(80);
            carta.setFitHeight(100);
            carta.setPreserveRatio(false);

            carta.setTranslateX(startX);
            carta.setTranslateY(startY);
            stack.getChildren().add(carta);
            animadas.add(carta);

            TranslateTransition anim = new TranslateTransition(Duration.millis(400), carta);
            anim.setToX(-300 + (i * 85)); // Mayor espaciado entre cartas (85 en lugar de 75)
            anim.setToY(250);             // ajustá también según tu layout

            anim.setDelay(Duration.millis(i * 75));
            anim.play();
        }

        PauseTransition esperar = new PauseTransition(Duration.millis(10 * 75 + 300));
        esperar.setOnFinished(e -> {
            // Animar fade out de las cartas del dorso
            int total = animadas.size();
            for (int i = 0; i < total; i++) {
                ImageView carta = animadas.get(i);
                javafx.animation.FadeTransition fade = new javafx.animation.FadeTransition(Duration.millis(250), carta);
                fade.setFromValue(1.0);
                fade.setToValue(0.0);
                fade.setDelay(Duration.millis(i * 30)); // efecto en cascada
                if (i == total - 1) {
                    fade.setOnFinished(ev -> {
                        animadas.forEach(stack.getChildren()::remove);
                        // Fade in para la mano
                        manoRegion.setOpacity(0);
                        manoRegion.setVisible(true);
                        javafx.animation.FadeTransition fadeIn = new javafx.animation.FadeTransition(Duration.millis(350), manoRegion);
                        fadeIn.setFromValue(0.0);
                        fadeIn.setToValue(1.0);
                        fadeIn.play();
                    });
                }
                fade.play();
            }
        });
        esperar.play();
    }
}