package edu.fiuba.algo3.vistas.juego.cartas;

import java.util.Objects;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class CartaUnidadVisual extends CartaVisual {

    public CartaUnidadVisual(CartaUnidad carta) {
        super(carta);
        // construirVista() se debe llamar externamente después de la construcción
    }

    private String normalizarNombreParaImagen(String nombre) {
        String normalizado = java.text.Normalizer.normalize(nombre, java.text.Normalizer.Form.NFD)
            .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
            .replaceAll("[^A-Za-z0-9]", "");
        return normalizado;
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
        mainStack.getChildren().addAll(vistaImagen, overlayPane, hoverBorder); // overlay entre imagen y hoverBorder
        if (!this.getChildren().contains(mainStack)) {
            this.getChildren().add(mainStack);
        }

        this.setOnMouseEntered(e -> this.setCursor(javafx.scene.Cursor.HAND));
        this.setOnMouseExited(e -> this.setCursor(javafx.scene.Cursor.DEFAULT));
    }
}
