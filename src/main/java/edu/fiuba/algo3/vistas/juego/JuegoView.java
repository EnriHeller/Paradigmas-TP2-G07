package edu.fiuba.algo3.vistas.juego;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.principal.Juego;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Objects;

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

        stack.getChildren().addAll(fondoView, tableroRegion, manoRegion);

        // El StackPane se centra en la ventana y nunca se estira
        BorderPane root = new BorderPane();
        root.setCenter(stack);
        return root;
    }
}
