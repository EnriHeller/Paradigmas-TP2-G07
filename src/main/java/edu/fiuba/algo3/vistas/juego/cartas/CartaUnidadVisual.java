package edu.fiuba.algo3.vistas.juego.cartas;

import java.util.Objects;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CartaUnidadVisual extends CartaVisual {

    public CartaUnidadVisual(CartaUnidad carta) {
        super(carta);
        // construirVista() se debe llamar externamente después de la construcción
    }

    @Override
    public void construirVista() {
        CartaUnidad unidad = (CartaUnidad) carta;

        // Imagen de la carta
        String nombreBase;
        try {
            nombreBase = unidad.getNombre();
        } catch (Exception e) {
            String mostrar = unidad.mostrarCarta();
            int idx = mostrar.indexOf(' ');
            if (idx > 0) nombreBase = mostrar.substring(0, idx);
            else nombreBase = mostrar;
        }
        String ruta = "/imagenes/" + nombreBase.replaceAll(" ", "") + ".png";
        Image imagen;
        try {
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        } catch (Exception e) {
            System.err.println("[CartaUnidadVisual] Imagen no encontrada para: " + ruta + ". Usando imagen por defecto. Excepcion: " + e);
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/BirnaBrant.png")));
        }
        ImageView vistaImagen = crearImagenEstandar(imagen);

        // Circulito de valor (más chico y a la izquierda)
        Label valor = new Label(String.valueOf(unidad.ValorActual()));
        valor.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");
        StackPane circuloValor = new StackPane();
        circuloValor.setPrefSize(20, 20);
        circuloValor.setMaxSize(20, 20);
        circuloValor.setMinSize(20, 20);
        circuloValor.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(javafx.scene.paint.Color.DARKORANGE, new javafx.scene.layout.CornerRadii(10), javafx.geometry.Insets.EMPTY)));
        circuloValor.getChildren().add(valor);
        circuloValor.setAlignment(Pos.CENTER);

        // StackPane para superponer el circulito arriba a la izquierda
        StackPane stack = new StackPane(vistaImagen, circuloValor);
        StackPane.setAlignment(circuloValor, Pos.TOP_LEFT);
        stack.setPrefSize(ANCHO, ALTO);
        stack.setMinSize(ANCHO, ALTO);
        stack.setMaxSize(ANCHO, ALTO);
        stack.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        this.getChildren().clear();
        setTamanioEstandar();
        mainStack.getChildren().clear();
        mainStack.getChildren().addAll(stack, hoverBorder); // hoverBorder siempre arriba
        // Permite que el VBox reciba los eventos de mouse aunque el StackPane esté encima
        stack.setMouseTransparent(true);
        vistaImagen.setMouseTransparent(true);
        circuloValor.setMouseTransparent(true);
    }
}
