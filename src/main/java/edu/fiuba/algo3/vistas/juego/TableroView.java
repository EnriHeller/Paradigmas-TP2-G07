package edu.fiuba.algo3.vistas.juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.vistas.juego.cartas.CartaUnidadVisual;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class TableroView {
    private final Tablero tablero;
    private final int seccionWidth = 650;
    private final int seccionHeight = 75;
    private final int tableroWidth = 1300;
    private final int tableroHeight = 700;

    private Carta cartaElegida;
    private HBox contenedorMano;
    private final List<HBox> seccionesVisuales = new ArrayList<>();
    private Juego juego;

    public void setCartaElegida(Carta carta) {
        System.out.println("Carta elegida: " + carta.mostrarCarta());
        this.cartaElegida = carta;
    }

    public void setContenedorMano(HBox contenedorMano) {
        this.contenedorMano = contenedorMano;
    }

    public TableroView(Tablero tablero, Juego juego) {
        this.tablero = tablero;
        this.juego = juego;
    }

    public Region construir() {
        StackPane root = new StackPane();
        root.setPrefSize(tableroWidth, tableroHeight);
        root.setMinSize(tableroWidth, tableroHeight);
        root.setMaxSize(tableroWidth, tableroHeight);

        // Fondo del tablero
        try {
            Image boardBg = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/emptyBoard.png")).toExternalForm());
            BackgroundSize bgSize = new BackgroundSize(tableroWidth, tableroHeight, false, false, false, false);
            BackgroundImage bgImg = new BackgroundImage(
                    boardBg,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    bgSize
            );
            root.setBackground(new Background(bgImg));
        } catch (Exception e) {
            System.err.println("[ERROR] No se pudo cargar emptyBoard.png: " + e);
        }

        Pane overlay = new Pane();
        overlay.prefWidthProperty().bind(root.widthProperty());
        overlay.prefHeightProperty().bind(root.heightProperty());

        int x_seccion = 396;
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

        // Contenedor de fila: [puntaje] [cartas]
        HBox fila = new HBox(10);
        fila.setLayoutX(x);
        fila.setLayoutY(y);
        fila.setPrefHeight(seccionHeight);

        // Label de puntaje
        Label puntajeLabel = new Label(String.valueOf(seccion.getPuntajeTotal()));
        HBox.setMargin(puntajeLabel, new Insets(0, 14, 0, 0));
        puntajeLabel.setFont(Font.font("Arial", 18));
        puntajeLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        puntajeLabel.setMinWidth(40);
        puntajeLabel.setMaxHeight(seccionHeight);

        // Sección visual de cartas
        HBox visual = new HBox(5);
        visual.setStyle("-fx-background-color: transparent; -fx-border-color: black;");
        visual.setPrefSize(seccionWidth, seccionHeight);

        visual.setOnMouseEntered(event -> {
            visual.setStyle("-fx-background-color: rgba(200,200,200,0.4); -fx-border-color: gray; -fx-border-width: 1; -fx-border-radius: 5;");
        });

        visual.setOnMouseExited(event -> {
            visual.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        // Acción al hacer click en una sección
        visual.setOnMouseClicked(event -> {
            if (cartaElegida != null && (!cartaElegida.esEspecial())) {
                if (seccion.puedeAgregar((CartaUnidad) cartaElegida)) {
                    try {
                        juego.jugarCarta(cartaElegida, seccion);
                    } catch (TipoDeSeccionInvalidaError ignored) {
                    }
                    actualizarSeccion(visual, puntajeLabel, (CartaUnidad) cartaElegida, seccion);
                    removerCartaDeLaMano((CartaUnidad) cartaElegida);
                    cartaElegida = null;
                }
            }
        });

        for (CartaUnidad carta : seccion.getCartas()) {
            CartaUnidadVisual cartaVisual = new CartaUnidadVisual(carta);
            cartaVisual.construirVista();
            visual.getChildren().add(cartaVisual);
        }

        fila.getChildren().addAll(puntajeLabel, visual);
        contenedor.getChildren().add(fila);
        seccionesVisuales.add(visual);
    }

    private void actualizarSeccion(HBox visual, Label puntajeLabel, CartaUnidad cartaUnidad, Seccion seccion) {
        CartaUnidadVisual cartaVisual = new CartaUnidadVisual(cartaUnidad);
        cartaVisual.setStyle("-fx-border-color: blue; -fx-background-color: #e0e0e0; -fx-border-width: 2px;");
        cartaVisual.construirVista();
        visual.getChildren().add(cartaVisual);

        // Actualizar puntaje
        puntajeLabel.setText(String.valueOf(seccion.getPuntajeTotal()));
    }

    private void removerCartaDeLaMano(CartaUnidad carta) {
        if (contenedorMano == null) return;

        Platform.runLater(() -> {
            for (Node nodo : contenedorMano.getChildren()) {
                if (nodo instanceof CartaUnidadVisual) {
                    CartaUnidadVisual visual = (CartaUnidadVisual) nodo;
                    if (visual.getCarta().equals(carta)) {
                        contenedorMano.getChildren().remove(nodo);
                        break;
                    }
                }
            }
        });
    }

    public void refrescar() {
        limpiarTablero();
        seccionesVisuales.clear(); // Esto limpia referencias anteriores

        Pane nuevoOverlay = new Pane();
        nuevoOverlay.prefWidthProperty().setValue(tableroWidth);
        nuevoOverlay.prefHeightProperty().setValue(tableroHeight);

        int x_seccion = 396;
        int ultimo_y = 10;
        int espacio = 14;

        List<String> claves = List.of("Asedio1", "Rango1", "CuerpoACuerpo1", "Asedio0", "Rango0", "CuerpoACuerpo0");

        for (String clave : claves) {
            agregarSeccion(nuevoOverlay, clave, x_seccion, ultimo_y);
            ultimo_y += espacio + seccionHeight;
        }

        // Quitar el overlay viejo y poner el nuevo
        StackPane parent = (StackPane) seccionesVisuales.get(0).getParent().getParent();
        parent.getChildren().remove(1); // Remueve el viejo Pane (overlay)
        parent.getChildren().add(nuevoOverlay); // Agrega el nuevo
    }


    public void limpiarTablero() {
        for (HBox seccionVisual : seccionesVisuales) {
            seccionVisual.getChildren().clear();
        }
    }
}
