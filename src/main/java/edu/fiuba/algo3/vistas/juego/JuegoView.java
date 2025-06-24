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
        BorderPane layout = new BorderPane();
        layout.setPrefSize(App.WIDTH, App.HEIGHT);

        //Fondo
        Image fondo = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/emptyBoard.png")).toExternalForm());
        ImageView fondoView = new ImageView(fondo);
        fondoView.setPreserveRatio(false);
        fondoView.setFitWidth(App.WIDTH);
        fondoView.setFitHeight(App.HEIGHT);
        fondoView.setSmooth(true);
        fondoView.setCache(true);
        fondoView.setLayoutX(0);
        fondoView.setLayoutY(0);

        // Centro: vista del tablero
        TableroView tablero = new TableroView(juego.getTablero());
        layout.setCenter(tablero.construir());

        return layout;
    }
}
