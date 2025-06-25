package edu.fiuba.algo3.vistas.juego.cartas;

import java.util.Objects;

import edu.fiuba.algo3.modelo.cartas.Carta;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaEspecialVisual extends CartaVisual {

    public CartaEspecialVisual(Carta carta) {
        super(carta);
        // Do not call construirVista() here; it will be called externally as with CartaUnidadVisual
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
        overlay.setMaxSize(26,26);
        overlay.setMinSize(26,26);
        overlay.setPrefSize(26,26);
        javafx.scene.layout.Pane overlayPane = new javafx.scene.layout.Pane(overlay);
        overlayPane.setPrefSize(0,0);
        overlayPane.setMouseTransparent(true);

        setTamanioEstandar();
        mainStack.getChildren().clear();
        mainStack.getChildren().addAll(vistaImagen, overlayPane, hoverBorder); // overlay entre imagen y hoverBorder

        this.setOnMouseEntered(e -> this.setCursor(javafx.scene.Cursor.HAND));
        this.setOnMouseExited(e -> this.setCursor(javafx.scene.Cursor.DEFAULT));
    }
}