package edu.fiuba.algo3.vistas.juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.fiuba.algo3.controller.TableroController;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.vistas.juego.cartas.CartaUnidadVisual;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class TableroView {
    private final int seccionWidth = 650;
    private final int seccionHeight = 75;
    private final int tableroWidth = 1300;
    private final int tableroHeight = 700;

    private Carta cartaElegida;
    private final TableroController tableroController;
    private final List<HBox> seccionesVisuales = new ArrayList<>();
    private Pane overlayActual;
    private StackPane rootStackPane;
    private ManoView manoView;

    public void setCartaElegida(Carta carta) {
        if(carta != null){
            System.out.println("Carta elegida: " + carta.mostrarCarta());
        }
        this.cartaElegida = carta;
    }

    public void setManoView(ManoView manoView) {
        this.manoView = manoView;
    }

    public TableroView(TableroController tableroController) {
        this.tableroController = tableroController;
    }

    public Region construir() {
        rootStackPane = new StackPane();
        rootStackPane.setPrefSize(tableroWidth, tableroHeight);
        rootStackPane.setMinSize(tableroWidth, tableroHeight);
        rootStackPane.setMaxSize(tableroWidth, tableroHeight);

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
            rootStackPane.setBackground(new Background(bgImg));
        } catch (Exception e) {
            System.err.println("[ERROR] No se pudo cargar emptyBoard.png: " + e);
        }

        Pane overlay = new Pane();
        overlay.prefWidthProperty().bind(rootStackPane.widthProperty());
        overlay.prefHeightProperty().bind(rootStackPane.heightProperty());
        overlayActual = overlay;

        int x_seccion = 396;
        int ultimo_y = 10;
        int espacio = 14;

        List<String> claves = tableroController.getClavesSecciones();

        for (String clave : claves) {
            agregarSeccion(overlay, clave, x_seccion, ultimo_y);
            ultimo_y += espacio + seccionHeight;
        }

        rootStackPane.getChildren().add(overlayActual);
        return rootStackPane;
    }

    private void agregarSeccion(Pane contenedor, String clave, double x, double y) {
        // Seccion y datos del modelo a través del controller
        // Contenedor de fila: [puntaje] [cartas]
        HBox fila = new HBox(10);
        fila.setLayoutX(x);
        fila.setLayoutY(y);
        fila.setPrefHeight(seccionHeight);
        fila.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        // Label de puntaje
        Label puntajeLabel = new Label(String.valueOf(tableroController.getPuntajeSeccion(clave)));
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
            if (visual.getScene() != null && visual.getScene().getWindow() != null) {
                visual.getScene().setCursor(javafx.scene.Cursor.HAND);
            }
            visual.setStyle("-fx-background-color: rgba(255, 230, 0, 0.4); -fx-border-color: gray; -fx-border-width: 1; -fx-border-radius: 5;");
        });

        visual.setOnMouseExited(event -> {
            if (visual.getScene() != null && visual.getScene().getWindow() != null) {
                visual.getScene().setCursor(javafx.scene.Cursor.DEFAULT);
            }
            visual.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        // Acción al hacer click en una sección
        visual.setOnMouseClicked(event -> {
            if (cartaElegida != null && (!cartaElegida.esEspecial())) {
                if (tableroController.puedeAgregar(clave, (CartaUnidad) cartaElegida)) {
                    try {
                        tableroController.jugarCarta(cartaElegida, clave);
                    } catch (TipoDeSeccionInvalidaError | CartaNoJugable ignored) {
                        edu.fiuba.algo3.controller.Utils.mostrarPopupError(rootStackPane, "¡No podés jugar en esta sección!");
                    }

                    actualizarSeccion(visual, puntajeLabel, (CartaUnidad) cartaElegida, clave);

                    if (manoView != null) {
                        tableroController.removerCartaEnMano(cartaElegida);
                        manoView.actualizarCartas(tableroController.getJuego().mostrarManoActual());
                        refrescar();
                    } else {
                        System.out.println("manoView es null!!");
                    }
                    cartaElegida = null;
                }
            }
        });

        for (CartaUnidad carta : tableroController.getCartasEnSeccion(clave)) {
            CartaUnidadVisual cartaVisual = new CartaUnidadVisual(carta, null);
            cartaVisual.construirVista();
            visual.getChildren().add(cartaVisual);
        }

        fila.getChildren().addAll(puntajeLabel, visual);
        contenedor.getChildren().add(fila);
        seccionesVisuales.add(visual);
    }

    private void actualizarSeccion(HBox visual, Label puntajeLabel, CartaUnidad cartaUnidad, String claveSeccion) {
        CartaUnidadVisual cartaVisual = new CartaUnidadVisual(cartaUnidad, null);
        cartaVisual.construirVista();

        // Anulamos eventos de click
        cartaVisual.setOnMouseClicked(e -> {}); // No hace nada al click

        visual.getChildren().add(cartaVisual);

        // Actualizamos puntaje
        puntajeLabel.setText(String.valueOf(tableroController.getPuntajeSeccion(claveSeccion)));
    }

    public void refrescar() {
        limpiarTablero();
        seccionesVisuales.clear();

        Pane nuevoOverlay = new Pane();
        nuevoOverlay.prefWidthProperty().setValue(tableroWidth);
        nuevoOverlay.prefHeightProperty().setValue(tableroHeight);

        int x_seccion = 396;
        int ultimo_y = 10;
        int espacio = 14;

        List<String> claves = tableroController.getClavesSecciones();

        for (String clave : claves) {
            agregarSeccion(nuevoOverlay, clave, x_seccion, ultimo_y);
            ultimo_y += espacio + seccionHeight;
        }

        if (rootStackPane != null && overlayActual != null) {
            rootStackPane.getChildren().remove(overlayActual);
            rootStackPane.getChildren().add(nuevoOverlay);
            overlayActual = nuevoOverlay;
        }
    }


    public void limpiarTablero() {
        for (HBox seccionVisual : seccionesVisuales) {
            seccionVisual.getChildren().clear();
        }
    }

    
}
