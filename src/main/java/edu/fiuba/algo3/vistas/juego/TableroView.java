package edu.fiuba.algo3.vistas.juego;

import java.util.Objects;

import edu.fiuba.algo3.App;
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
import java.util.List;

public class TableroView {

    private final Tablero tablero;
    private int seccionWidth = 630;
    private int seccionHeight = 70;


    public TableroView(Tablero tablero) {
        this.tablero = tablero;
    }

    public StackPane construir() {
        StackPane root = new StackPane();
        root.setPrefSize(App.WIDTH, App.HEIGHT);

        Image fondo = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/emptyBoard.png")).toExternalForm());
        ImageView fondoView = new ImageView(fondo);
        fondoView.setPreserveRatio(false); // Se estira horizontalmente
        fondoView.setFitWidth(App.WIDTH);  // Cubre todo el ancho
        // No setFitHeight: la altura se ajusta automáticamente
        fondoView.setSmooth(true);
        fondoView.setCache(true);

        Pane overlay = new Pane();
        int x_seccion = 500;
        int ultimo_y = 10;
        int espacio = 20;

        List<String> claves = List.of("Asedio1", "Rango1", "CuerpoACuerpo1", "Asedio0", "Rango0", "CuerpoACuerpo0");

        for (String clave:claves){
            agregarSeccion(overlay, clave, x_seccion, ultimo_y);
            ultimo_y = ultimo_y + espacio + seccionHeight;
        }


        root.getChildren().addAll(fondoView, overlay);
        return root;
    }

    private void agregarSeccion(Pane contenedor, String clave, double x, double y) {
        Seccion seccion = tablero.obtenerSeccionPorClave(clave);

        HBox visual = new HBox(5);

        visual.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-border-color: black;");
        visual.setPrefSize(seccionWidth, seccionHeight); // ancho ajustado a la imagen
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
