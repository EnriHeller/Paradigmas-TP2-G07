package edu.fiuba.algo3.vistas.juego;

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
        layout.setPrefSize(1000, 700);

        // Fondo del juego (tablero)
        var url = getClass().getResource("/imagenes/emptyBoard.png");
        if (url == null) {
            System.err.println("[ERROR] No se encontró la imagen de fondo: /imagenes/emptyBoard.png");
            // Opcional: mostrar alerta visual si se desea
        }
        Image fondo = url != null ? new Image(url.toExternalForm()) : null;
        if (fondo != null) {
            BackgroundImage bgImage = new BackgroundImage(
                    fondo,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false)
            );
            layout.setBackground(new Background(bgImage));
        }

        // Parte superior: nombre del jugador 2
        Label nombreJ2 = new Label(juego.getJugador2().getNombre());
        nombreJ2.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        layout.setTop(nombreJ2);
        BorderPane.setAlignment(nombreJ2, Pos.TOP_CENTER);

        // Centro: vista del tablero
        TableroView tablero = new TableroView(juego.getTablero());
        layout.setCenter(tablero.construir());

        return layout;
    }
}
