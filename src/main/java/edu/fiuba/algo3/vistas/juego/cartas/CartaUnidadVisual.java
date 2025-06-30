package edu.fiuba.algo3.vistas.juego.cartas;

import java.util.Objects;

import edu.fiuba.algo3.controller.CartaClickHandler;
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

public class CartaUnidadVisual extends CartaVisual {
    private final CartaClickHandler handler;

    public CartaUnidadVisual(CartaUnidad carta, CartaClickHandler handler) {
        super(carta);
        this.handler = handler;
        // construirVista() se debe llamar externamente después de la construcción
    }

    public CartaUnidad getCarta() {
        return (CartaUnidad) carta;
    }

    private String normalizarNombreParaImagen(String nombre) {
        String normalizado = java.text.Normalizer.normalize(nombre, java.text.Normalizer.Form.NFD)
            .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
            .replaceAll("[^A-Za-z0-9]", "");
        return normalizado;
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
        CartaUnidad unidad = (CartaUnidad) carta;
        String nombreBase;
        try {
            nombreBase = unidad.getNombre();
        } catch (Exception e) {
            String mostrar = unidad.mostrarCarta();
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
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/BirnaBrant.png")));
        }
        ImageView vistaImagen = crearImagenEstandar(imagen);

        // Crear el círculo de puntos (valor de la carta)
        Circle circulo = new Circle(13); // radio 13px (más chico)
        circulo.getStyleClass().add("carta-unidad-circulo");
        circulo.setFill(Color.rgb(30,30,30,0.85));
        circulo.setStroke(Color.GOLD);
        circulo.setStrokeWidth(2);

        int valorActual = unidad.ValorActual();
        Text textoValor = new Text(String.valueOf(valorActual));
        textoValor.getStyleClass().add("carta-unidad-valor");
        textoValor.setFill(Color.WHITE);
        textoValor.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        StackPane overlay = new StackPane(circulo, textoValor);
        overlay.setPickOnBounds(false);
        overlay.setMouseTransparent(true);
        // Ubicar arriba a la derecha
        overlay.setTranslateX(52); // mover a la derecha (ajustar según tamaño carta)
        overlay.setTranslateY(0); // mover arriba (ajustar según tamaño carta)

        overlay.setMaxSize(26,26);
        overlay.setMinSize(26,26);
        overlay.setPrefSize(26,26);
        Pane overlayPane = new Pane(overlay);
        overlayPane.setPrefSize(0,0);
        overlayPane.setMouseTransparent(true);

        // Solo imagen, overlay y hoverBorder
        this.getChildren().clear();
        setTamanioEstandar();
        mainStack.getChildren().clear();
        mainStack.getChildren().addAll(vistaImagen, overlayPane, hoverBorder);
        if (!mainStack.getChildren().contains(infoOverlay)) {
            mainStack.getChildren().add(infoOverlay);
        }
        infoOverlay.setMinWidth(300); // Mínimo razonable

        if (!this.getChildren().contains(mainStack)) {
            this.getChildren().add(mainStack);
        }

        this.setOnMouseClicked(e -> {
            if (handler != null) handler.alClic(this);

            if (!seleccionada){
                animarSeleccion();
            }else{
                animarDeseleccion();
            }

        });
    }

    @Override
    public void construirCamposInfo() {
        infoOverlay.getChildren().clear();
        try {
            CartaUnidad unidad = (CartaUnidad) carta;
            Label nombre = new Label("Nombre: " + unidad.getNombre());
            nombre.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
            infoOverlay.getChildren().add(nombre);

            String secciones = String.join(", ", unidad.getSecciones());
            Label seccion = new Label("Sección: " + secciones);
            seccion.setStyle("-fx-text-fill: #e0e0e0;");
            infoOverlay.getChildren().add(seccion);

            Label valor = new Label("Valor: " + unidad.ValorActual());
            valor.setStyle("-fx-text-fill: #ffd700;");
            infoOverlay.getChildren().add(valor);

            String mods = unidad.getModificadores();
            if (mods != null && !mods.isEmpty() && !mods.equals("Base")) {
                Label modificadores = new Label("Modificadores: " + mods);
                modificadores.setStyle("-fx-text-fill: #b0e57c;");
                infoOverlay.getChildren().add(modificadores);
            }
        } catch (Exception e) {
            infoOverlay.getChildren().add(new Label("Error mostrando info de carta unidad."));
            System.err.println("[CartaUnidadVisual] Error en construirCamposInfo: " + e);
        }
    }

    @Override
    protected boolean estaSeleccionada() {
        return seleccionada;
    }
}
