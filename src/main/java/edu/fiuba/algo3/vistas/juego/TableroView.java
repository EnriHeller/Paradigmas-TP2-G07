package edu.fiuba.algo3.vistas.juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.vistas.juego.cartas.CartaUnidadVisual;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class TableroView {
    private final Tablero tablero;
    private final int seccionWidth = 630;
    private final int seccionHeight = 75;
    private final int tableroWidth = 1300;
    private final int tableroHeight = 700;

    private Carta cartaElegida;
    private final List<HBox> seccionesVisuales = new ArrayList<>();

    public void setCartaElegida(Carta carta) {
        System.out.println("Carta elegida: " + carta.mostrarCarta());
        this.cartaElegida = carta;
    }

    public TableroView(Tablero tablero) {
        this.tablero = tablero;
    }

    public Region construir() {
        StackPane root = new StackPane();
        root.setPrefSize(tableroWidth, tableroHeight);
        root.setMinSize(tableroWidth, tableroHeight);
        root.setMaxSize(tableroWidth, tableroHeight);

        // Fondo del tablero
        try {
            Image boardBg = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/emptyBoard.png")).toExternalForm());
            javafx.scene.layout.BackgroundSize bgSize = new javafx.scene.layout.BackgroundSize(tableroWidth, tableroHeight, false, false, false, false);
            javafx.scene.layout.BackgroundImage bgImg = new javafx.scene.layout.BackgroundImage(
                    boardBg,
                    javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                    javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                    javafx.scene.layout.BackgroundPosition.DEFAULT,
                    bgSize
            );
            root.setBackground(new javafx.scene.layout.Background(bgImg));
        } catch (Exception e) {
            System.err.println("[ERROR] No se pudo cargar emptyBoard.png: " + e);
        }

        Pane overlay = new Pane();
        overlay.prefWidthProperty().bind(root.widthProperty());
        overlay.prefHeightProperty().bind(root.heightProperty());

        int x_seccion = 460;
        int ultimo_y = 10;
        int espacio = 14;

        List<String> claves = List.of("Asedio1", "Rango1", "CuerpoACuerpo1", "Asedio0", "Rango0", "CuerpoACuerpo0");

        for (String clave : claves) {
            agregarSeccion(overlay, clave, x_seccion, ultimo_y);
            ultimo_y += espacio + seccionHeight;
        }

        root.getChildren().add(overlay);
        return root;
    }

    private void agregarSeccion(Pane contenedor, String clave, double x, double y) {
        Seccion seccion = tablero.obtenerSeccionPorClave(clave);
        HBox visual = new HBox(5);
        visual.setStyle("-fx-background-color: transparent; -fx-border-color: black;");
        visual.setPrefSize(seccionWidth, seccionHeight);
        visual.setLayoutX(x);
        visual.setLayoutY(y);

        visual.setOnMouseEntered(event -> {
            visual.setStyle("-fx-background-color: rgba(200,200,200,0.4); -fx-border-color: gray; -fx-border-width: 1; -fx-border-radius: 5;");
        });

        visual.setOnMouseExited(event -> {
            visual.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        visual.setOnMouseClicked(event -> {
            if (cartaElegida != null) {
                CartaUnidad cartaUnidad = (CartaUnidad) cartaElegida;
                seccion.agregarCarta(cartaUnidad);
                actualizarSeccion(visual, cartaUnidad);
                cartaElegida = null;
            }
        });

        for (CartaUnidad carta : seccion.getCartas()) {
            CartaUnidadVisual cartaVisual = new CartaUnidadVisual(carta);
            cartaVisual.construirVista();
            visual.getChildren().add(cartaVisual);
        }

        contenedor.getChildren().add(visual);
        seccionesVisuales.add(visual);
    }

    private void actualizarSeccion(HBox visual, CartaUnidad cartaUnidad) {
        CartaUnidadVisual cartaVisual = new CartaUnidadVisual(cartaUnidad);
        cartaVisual.setStyle("-fx-border-color: blue; -fx-background-color: #e0e0e0; -fx-border-width: 2px;");
        cartaVisual.construirVista();
        visual.getChildren().add(cartaVisual);
    }

    public void limpiarTablero() {
        for (HBox seccionVisual : seccionesVisuales) {
            seccionVisual.getChildren().clear();
        }
    }
}
