package edu.fiuba.algo3.vistas.juego;

import java.util.Objects;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.vistas.juego.cartas.CartaUnidadVisual;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class TableroView {

    private final Tablero tablero;

    public TableroView(Tablero tablero) {
        this.tablero = tablero;
    }

    public StackPane construir() {
        StackPane root = new StackPane();
        root.setPrefSize(1099, 768);

        Image fondo = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/emptyBoard.png")).toExternalForm());
        ImageView fondoView = new ImageView(fondo);
        fondoView.setFitWidth(1099);
        fondoView.setFitHeight(768);

        Pane overlay = new Pane();

        agregarSeccion(overlay, "Asedio1", 275, 80);
        agregarSeccion(overlay, "Rango1", 275, 100);
        agregarSeccion(overlay, "CuerpoACuerpo1", 275, 210);
        agregarSeccion(overlay, "CuerpoACuerpo0", 275, 300);
        agregarSeccion(overlay, "Rango0", 275, 380);
        agregarSeccion(overlay, "Asedio0", 275, 460);

        root.getChildren().addAll(fondoView, overlay);
        return root;
    }

    private void agregarSeccion(Pane contenedor, String clave, double x, double y) {
        Seccion seccion = tablero.obtenerSeccionPorClave(clave);

        HBox visual = new HBox(5);
        visual.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-border-color: black;");
        visual.setPrefSize(600, 60); // ancho ajustado a la imagen
        visual.setLayoutX(x);
        visual.setLayoutY(y);

        for (CartaUnidad carta : seccion.getCartas()) {
            visual.getChildren().add(new CartaUnidadVisual(carta));
        }

        contenedor.getChildren().add(visual);
    }



    private VBox construirVistaDeSeccion(Seccion seccion) {
        VBox contenedor = new VBox(5);
        contenedor.setAlignment(Pos.CENTER_LEFT);
        contenedor.setPrefSize(800, 80);

        Label nombre = new Label(seccion.getClave());
        nombre.setStyle("-fx-text-fill: white; -fx-font-size: 14;");

        HBox cartas = new HBox(5);
        cartas.setAlignment(Pos.CENTER_LEFT);

        for (CartaUnidad carta : seccion.getCartas()) {
            cartas.getChildren().add(new CartaUnidadVisual(carta));
        }

        contenedor.getChildren().addAll(nombre, cartas);
        return contenedor;
    }
}
