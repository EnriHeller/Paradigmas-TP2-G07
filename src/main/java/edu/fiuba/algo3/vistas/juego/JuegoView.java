package edu.fiuba.algo3.vistas.juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.vistas.juego.cartas.MazoView;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class JuegoView {
    private final Juego juego;

    public JuegoView(Juego juego) {
        this.juego = juego;
    }

    public BorderPane construir() {
        // StackPane de tamaño fijo para fondo, tablero y mano
        StackPane stack = new StackPane();
        stack.setPrefSize(App.WIDTH, App.HEIGHT);
        stack.setMinSize(App.WIDTH, App.HEIGHT);
        stack.setMaxSize(App.WIDTH, App.HEIGHT);

        // Fondo
        Image fondo = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/emptyBoard.png")).toExternalForm());
        ImageView fondoView = new ImageView(fondo);
        fondoView.setPreserveRatio(false);
        fondoView.setFitWidth(App.WIDTH);
        fondoView.setFitHeight(App.HEIGHT);
        fondoView.setSmooth(true);
        fondoView.setCache(true);

        // Centro: vista del tablero
        TableroView tablero = new TableroView(juego.getTablero());
        Region tableroRegion = tablero.construir();
        StackPane.setAlignment(tableroRegion, Pos.CENTER);

        // Abajo: vista de la mano
        ManoView mano = new ManoView(juego.mostrarManoActual());
        Region manoRegion = mano.construir();
        StackPane.setAlignment(manoRegion, Pos.BOTTOM_CENTER);
        StackPane.setMargin(manoRegion, new javafx.geometry.Insets(620, 0, 0, 250)); // mueve la mano 30px más abajo

        // Vista del mazo del jugador actual
        int cartasRestantes = juego.cartasEnMazoActual();
        MazoView mazoView = new MazoView(cartasRestantes);
        StackPane.setAlignment(mazoView, Pos.BOTTOM_RIGHT);
        mazoView.setTranslateX(1195);
        mazoView.setTranslateY(465);

        stack.getChildren().addAll(fondoView, tableroRegion, manoRegion, mazoView);
        Platform.runLater(() -> animarReparto(stack, mazoView, manoRegion));
        // Dentro de construir()
        PilaDescarteView pilaDescarteJugador = new PilaDescarteView(juego.getUltimaCartaDeLaPilaDeDescarte());
        Region pilaRegion = pilaDescarteJugador.construir();
        StackPane.setAlignment(pilaRegion, Pos.TOP_RIGHT);

        pilaRegion.setTranslateX(1190);
        pilaRegion.setTranslateY(150);
        stack.getChildren().addAll(fondoView, tableroRegion, manoRegion, mazoRegion, pilaRegion);

        // El StackPane se centra en la ventana y nunca se estira
        BorderPane root = new BorderPane();
        root.setCenter(stack);
        return root;
    }

    private void animarReparto(StackPane stack, MazoView mazoView, Region manoRegion) {
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
