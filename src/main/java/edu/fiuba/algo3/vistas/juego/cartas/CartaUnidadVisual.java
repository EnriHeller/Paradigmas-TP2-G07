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
        construirVista();
    }

    @Override
    public void construirVista() {
        CartaUnidad unidad = (CartaUnidad) carta;

        // Imagen de la carta
        String ruta = "/imagenes/BirnaBrant.png";
        Image imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
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
        this.getChildren().add(stack);
    }
}
