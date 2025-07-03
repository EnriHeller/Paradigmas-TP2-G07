package edu.fiuba.algo3.vistas.juego.cartas;

import java.util.Objects;

import edu.fiuba.algo3.controller.HandlerSeleccionarCarta;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class CartaMoralBoostVisual extends CartaVisual{

    private final HandlerSeleccionarCarta handler;

    public CartaMoralBoostVisual(Carta carta, HandlerSeleccionarCarta handler) {
        super(carta);
        this.handler = handler;
    }

    public Carta getCarta() {
        return carta;
    }

    private String normalizarNombreParaImagen(String nombre) {
        // Quita tildes y caracteres especiales, deja solo letras y espacios
        String sinTildes = java.text.Normalizer.normalize(nombre, java.text.Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .replaceAll("[^A-Za-z0-9 ]", "");
        // Convierte a camelCase
        String[] partes = sinTildes.split(" ");
        StringBuilder camelCase = new StringBuilder();
        for (int i = 0; i < partes.length; i++) {
            String parte = partes[i];
            if (parte.isEmpty()) continue;
            if (i == 0) {
                camelCase.append(parte.substring(0, 1).toLowerCase()).append(parte.substring(1));
            } else {
                camelCase.append(parte.substring(0, 1).toUpperCase()).append(parte.substring(1));
            }
        }
        return camelCase.toString();
    }

    private boolean seleccionada = false;
    private static final double ESCALA_SELECCION = 1.25;
    private static final Duration DURACION_ANIM = Duration.millis(180);

    @Override
    public void animarSeleccion() {
        ScaleTransition st = new ScaleTransition(DURACION_ANIM, this);
        st.setToX(ESCALA_SELECCION);
        st.setToY(ESCALA_SELECCION);
        st.play();
        hoverBorder.setStroke(Color.GOLD);
        hoverBorder.setStrokeWidth(4);
        hoverBorder.setFill(Color.TRANSPARENT); // Asegura que solo se vea el borde
        hoverBorder.setVisible(true);
        if (mainStack.getChildren().contains(hoverBorder)) {
            hoverBorder.toFront();
        }
        seleccionada = true;
        ocultarInfoOverlay(); // Oculta el overlay de información al seleccionar
    }

    @Override
    public void animarDeseleccion() {
        ScaleTransition st = new ScaleTransition(DURACION_ANIM, this);
        st.setToX(1.0);
        st.setToY(1.0);
        st.play();
        hoverBorder.setVisible(false);
        seleccionada = false;
        // No mostrar overlay aquí, solo se mostrará en hover si corresponde
    }

    // Llamar esto desde TableroView cuando se juega la carta por click
    public void animarMovimientoAHasta(double destinoX, double destinoY, Runnable onFinish) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(350), this);
        tt.setToX(destinoX - this.getLayoutX());
        tt.setToY(destinoY - this.getLayoutY());
        tt.setOnFinished(e -> {
            this.setTranslateX(0);
            this.setTranslateY(0);
            if (onFinish != null) onFinish.run();
        });
        tt.play();
    }

    @Override
    public void construirVista() {
        String nombreBase = "Morale Boost";

        String ruta = "/imagenes/moraleBoost.png";
        Image imagen;
        try {
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        } catch (Exception e) {
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/falta-unidad.png")));
        }
        ImageView vistaImagen = crearImagenEstandar(imagen);

        // Solo imagen, overlay y hoverBorder
        this.getChildren().clear();
        setTamanioEstandar();
        mainStack.getChildren().clear();
        mainStack.getChildren().addAll(vistaImagen, hoverBorder);
        if (!mainStack.getChildren().contains(infoOverlay)) {
            mainStack.getChildren().add(infoOverlay);
        }
        infoOverlay.setMinWidth(300); // Mínimo razonable

        if (!this.getChildren().contains(mainStack)) {
            this.getChildren().add(mainStack);
        }

        this.setOnMouseClicked(e -> {
            if (manoView != null) {
                manoView.seleccionarCarta(this);
            }
            if (handler != null) handler.alClic(this);
        });
    }

    @Override
    public void construirCamposInfo() {
        infoOverlay.getChildren().clear();
        try {
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
    protected boolean estaSeleccionada() {
        return seleccionada;
    }
}
