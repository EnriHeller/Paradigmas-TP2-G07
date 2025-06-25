package edu.fiuba.algo3.vistas.juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.fiuba.algo3.controller.FinalizadorDeJuego;
import edu.fiuba.algo3.modelo.principal.Juego;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
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

    public JuegoView(Juego juego) {
        this.juego = juego;
        this.finalizadorDeJuego = new FinalizadorDeJuego(juego);
    }

    public BorderPane construir() {
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
        TableroView tablero = new TableroView(juego.getTablero());
        Region tableroRegion = tablero.construir();
        tableroRegion.setLayoutX(0); // El tablero ya está centrado en su propio StackPane
        tableroRegion.setLayoutY(0);
        bloqueJuego.getChildren().add(tableroRegion);

        // Mano (abajo, centrada respecto al bloque)
        ManoView mano = new ManoView(juego.mostrarManoActual());
        Region manoRegion = mano.construir();
        manoRegion.setLayoutX(310); // Ajusta según el diseño
        manoRegion.setLayoutY(560); // Ajusta según el diseño
        bloqueJuego.getChildren().add(manoRegion);

        // Centro de turnos (izquierda)
        CentroDeAdministracionTurnos turnos = new CentroDeAdministracionTurnos(juego);
        VBox panelTurno = turnos.construir();
        panelTurno.setLayoutX(30);
        panelTurno.setLayoutY(300);
        bloqueJuego.getChildren().add(panelTurno);
        // Listener para fin de juego
        turnos.setOnTurnoFinalizado(() -> Platform.runLater(() -> finalizadorDeJuego.verificarFinDeJuego()));

        // Pila de descarte (arriba derecha)
        PilaDescarteView pilaDescarteJugador = new PilaDescarteView(juego.getUltimaCartaDeLaPilaDeDescarte());
        Region pilaRegion = pilaDescarteJugador.construir();
        pilaRegion.setLayoutX(1190);
        pilaRegion.setLayoutY(150);
        bloqueJuego.getChildren().add(pilaRegion);

        // Si hay mazo, agregarlo aquí con setLayoutX/Y
        // ...

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

        double startX = 580;
        double startY = 150;

        for (int i = 0; i < 10; i++) {
            ImageView carta = new ImageView(dorso);
            carta.setFitWidth(70);
            carta.setFitHeight(90);
            carta.setPreserveRatio(false);

            carta.setTranslateX(startX);
            carta.setTranslateY(startY);
            stack.getChildren().add(carta);
            animadas.add(carta);

            TranslateTransition anim = new TranslateTransition(Duration.millis(400), carta);
            anim.setToX(-300 + (i * 80)); // Mayor espaciado entre cartas (85 en lugar de 75)
            anim.setToY(300);             // ajustá también según tu layout

            anim.setDelay(Duration.millis(i * 80));
            anim.play();
        }

        PauseTransition esperar = new PauseTransition(Duration.millis(10 * 80 + 500));
        esperar.setOnFinished(e -> {
            animadas.forEach(stack.getChildren()::remove);
            manoRegion.setVisible(true);
        });
        esperar.play();
    }
}