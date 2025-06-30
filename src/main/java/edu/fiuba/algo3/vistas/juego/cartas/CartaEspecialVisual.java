package edu.fiuba.algo3.vistas.juego.cartas;

import java.util.Objects;

import edu.fiuba.algo3.controller.HandlerEspecialMano;
import edu.fiuba.algo3.modelo.cartas.Carta;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaEspecialVisual extends CartaVisual {
    private final HandlerEspecialMano handler;
    private boolean seleccionada = false;
    private javafx.scene.control.Button botonActivar;

    public CartaEspecialVisual(Carta carta, HandlerEspecialMano handler) {
        super(carta);
        this.handler = handler;
        // construirVista() se debe llamar externamente después de la construcción
    }

    private String normalizarNombreParaImagen(String nombre) {
        // Quita tildes, espacios, mayúsculas, y caracteres especiales
        String normalizado = java.text.Normalizer.normalize(nombre, java.text.Normalizer.Form.NFD)
            .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
            .replaceAll("[^A-Za-z0-9]", "");
        return normalizado;
    }

    @Override
    public void construirVista() {
        String nombreBase;
        // Si la carta tiene getNombre(), úsalo. Si no, parsea el nombre base.
        try {
            // Intenta usar getNombre() si existe
            nombreBase = (String) carta.getClass().getMethod("getNombre").invoke(carta);
        } catch (Exception e) {
            // Fallback: usa mostrarCarta() y toma solo la primera palabra (antes de un espacio o modificador)
            String mostrar = carta.mostrarCarta();
            int idx = mostrar.indexOf(' ');
            if (idx > 0) nombreBase = mostrar.substring(0, idx);
            else nombreBase = mostrar;
        }
        String nombreImagen = normalizarNombreParaImagen(nombreBase);
        String ruta = "/imagenes/" + nombreImagen + ".png";
        Image imagen;
        try {
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        } catch (Exception e) {
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Lluviatorrencial.png")));
        }
        ImageView vistaImagen = crearImagenEstandar(imagen);

        // Crear el círculo de "E" para cartas especiales
        javafx.scene.shape.Circle circulo = new javafx.scene.shape.Circle(13); // radio 13px (igual que unidades)
        circulo.getStyleClass().add("carta-unidad-circulo");
        circulo.setFill(javafx.scene.paint.Color.rgb(30,30,30,0.85));
        circulo.setStroke(javafx.scene.paint.Color.GOLD);
        circulo.setStrokeWidth(2);
        javafx.scene.text.Text textoE = new javafx.scene.text.Text("E");
        textoE.getStyleClass().add("carta-unidad-valor");
        textoE.setFill(javafx.scene.paint.Color.WHITE);
        textoE.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        javafx.scene.layout.StackPane overlay = new javafx.scene.layout.StackPane(circulo, textoE);
        
        overlay.setPickOnBounds(false);
        overlay.setMouseTransparent(true);
        overlay.setTranslateX(0); // igual que unidades
        overlay.setTranslateY(0);
        javafx.scene.layout.Pane overlayPane = new javafx.scene.layout.Pane(overlay);
        overlayPane.setPrefSize(0,0);
        overlayPane.setMouseTransparent(true);

        setTamanioEstandar();
        mainStack.getChildren().clear();
        mainStack.getChildren().addAll(vistaImagen, overlayPane, hoverBorder);
        if (!mainStack.getChildren().contains(infoOverlay)) {
            mainStack.getChildren().add(infoOverlay);
        }
        // Configuración de tamaño: ancho adaptable, sin altura fija
        infoOverlay.setMaxWidth(600);
        infoOverlay.setPrefWidth(USE_COMPUTED_SIZE);
        infoOverlay.setMinWidth(300);

        infoOverlay.setPrefHeight(USE_COMPUTED_SIZE);
        infoOverlay.setMinHeight(120);
        infoOverlay.setMaxHeight(Double.MAX_VALUE);

        // Botón Activar (negro y dorado)
        botonActivar = new javafx.scene.control.Button("Activar");
        botonActivar.setStyle("-fx-background-color: black; -fx-text-fill: gold; -fx-font-weight: bold; -fx-border-color: gold; -fx-border-width: 2; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 6 18; -fx-font-size: 15px;");
        botonActivar.setVisible(false);
        botonActivar.setOnAction(e -> {
            if (handler != null) handler.alClicEspecial(this);
        });
        javafx.scene.layout.StackPane.setAlignment(botonActivar, javafx.geometry.Pos.CENTER);
        if (!mainStack.getChildren().contains(botonActivar)) {
            mainStack.getChildren().add(botonActivar);
        }

        this.setOnMouseClicked(e -> {
            
            if (handler != null) handler.alClicEspecial(this);
        });
    }

    @Override
    public void construirCamposInfo() {
        infoOverlay.getChildren().clear();
        try {
            // Usar reflexión para obtener los campos si existen
            String nombre = null, tipo = null, descripcion = null;
            java.util.List<String> afectados = null;
            try {
                nombre = (String) carta.getClass().getMethod("getNombre").invoke(carta);
            } catch (ReflectiveOperationException | SecurityException ignored) {}
            try {
                tipo = (String) carta.getClass().getMethod("getTipo").invoke(carta);
            } catch (ReflectiveOperationException | SecurityException ignored) {}
            try {
                descripcion = (String) carta.getClass().getMethod("getDescripcion").invoke(carta);
            } catch (ReflectiveOperationException | SecurityException ignored) {}
            try {
                Object afectadosObj = carta.getClass().getMethod("getAfectado").invoke(carta);
                if (afectadosObj instanceof java.util.List<?>) {
                    afectados = new java.util.ArrayList<>();
                    for (Object o : (java.util.List<?>) afectadosObj) {
                        if (o != null) afectados.add(o.toString());
                    }
                }
            } catch (ReflectiveOperationException | SecurityException ignored) {}

            if (nombre != null) {
                Label l = new Label("Nombre: " + nombre);
                l.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
                l.setMouseTransparent(true);
                infoOverlay.getChildren().add(l);
            } else {
                Label l = new Label("Nombre: " + carta.mostrarCarta());
                l.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
                l.setMouseTransparent(true);

                infoOverlay.getChildren().add(l);
            }
            if (tipo != null) {
                Label l = new Label("Tipo: " + tipo);
                l.setStyle("-fx-text-fill: #e0e0e0;");
                l.setMouseTransparent(true);

                infoOverlay.getChildren().add(l);
            }
            if (afectados != null && !afectados.isEmpty()) {
                Label l = new Label("Afecta: " + String.join(", ", afectados));
                l.setStyle("-fx-text-fill: #b0e57c;");
                l.setMouseTransparent(true);

                infoOverlay.getChildren().add(l);
            }
            if (descripcion != null && !descripcion.isEmpty()) {
                Label l = new Label(descripcion);
                l.setWrapText(true);
                l.setStyle("-fx-text-fill: #cccccc; -fx-font-size: 11;");
                l.setMouseTransparent(true);

                infoOverlay.getChildren().add(l);
            }
        } catch (Exception e) {
            infoOverlay.getChildren().add(new Label("Error mostrando info de carta especial."));
            System.err.println("[CartaEspecialVisual] Error en construirCamposInfo: " + e);
        }
    }

    @Override
    public void animarSeleccion() {
        javafx.animation.ScaleTransition st = new javafx.animation.ScaleTransition(javafx.util.Duration.millis(180), this);
        st.setToX(1.25);
        st.setToY(1.25);
        st.play();
        hoverBorder.setStroke(javafx.scene.paint.Color.GOLD);
        hoverBorder.setStrokeWidth(4);
        hoverBorder.setFill(javafx.scene.paint.Color.TRANSPARENT);
        hoverBorder.setVisible(true);
        if (mainStack.getChildren().contains(hoverBorder)) {
            hoverBorder.toFront();
        }
        // Mostrar el botón Activar y traerlo al frente
        if (botonActivar != null) {
            botonActivar.setVisible(true);
            botonActivar.toFront();
        }
        seleccionada = true;
        ocultarInfoOverlay(); // Oculta el overlay de información al seleccionar
    }

    @Override
    public void animarDeseleccion() {
        javafx.animation.ScaleTransition st = new javafx.animation.ScaleTransition(javafx.util.Duration.millis(180), this);
        st.setToX(1.0);
        st.setToY(1.0);
        st.play();
        hoverBorder.setVisible(false);
        // Ocultar el botón Activar
        if (botonActivar != null) botonActivar.setVisible(false);
        seleccionada = false;
        // No mostrar overlay aquí, solo se mostrará en hover si corresponde
    }

    @Override
    protected boolean estaSeleccionada() {
        return seleccionada;
    }
}