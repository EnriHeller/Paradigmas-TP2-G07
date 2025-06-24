package edu.fiuba.algo3.vistas.juego;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.principal.Juego;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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

        // Centro: vista del tablero
        TableroView tablero = new TableroView(juego.getTablero());
        layout.setCenter(tablero.construir());

        return layout;
    }
}
