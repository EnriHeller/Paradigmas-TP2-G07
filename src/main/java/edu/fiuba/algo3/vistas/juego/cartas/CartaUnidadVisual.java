package edu.fiuba.algo3.vistas.juego.cartas;

import java.util.Objects;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CartaUnidadVisual extends CartaVisual {

    public CartaUnidadVisual(CartaUnidad carta) {
        super(carta);
        construirVista();
    }

    @Override
    public void construirVista() {
        CartaUnidad unidad = (CartaUnidad) carta;

        this.setSpacing(5);

        // ~~~~~~~ Valor en círculito ~~~~~~~
        Label valor = new Label(String.valueOf(unidad.ValorActual()));
        valor.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
        StackPane circuloValor = new StackPane();
        circuloValor.setPrefSize(30, 30);
        circuloValor.setMaxSize(30, 30);
        circuloValor.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, new CornerRadii(15), Insets.EMPTY)));
        circuloValor.getChildren().add(valor);
        circuloValor.setAlignment(Pos.CENTER_LEFT);

        // ~~~~~~~ Imagen ~~~~~~~
        //String ruta = "/imagenes/cartas/" + unidad.getNombre().replaceAll(" ", "") + ".png";
        String ruta = "/imagenes/Unidad.png";
        Image imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        ImageView vistaImagen = new ImageView(imagen);
        vistaImagen.setFitWidth(100);
        vistaImagen.setPreserveRatio(true);

        // ~~~~~~~ Zócalo inferior ~~~~~~~
        VBox zocalo = new VBox(2);
        zocalo.setPadding(new Insets(5));
        zocalo.setAlignment(Pos.CENTER_LEFT);
        zocalo.setBackground(new Background(new BackgroundFill(Color.web("#ddddddcc"), new CornerRadii(5), Insets.EMPTY)));

        Label nombre = new Label(unidad.getNombre());
        nombre.setStyle("-fx-font-weight: bold;");

        Label secciones = new Label("Secciones: " + String.join(", ", unidad.getSecciones()));
        Label mods = new Label("Modificadores: " + unidad.getModificadores());

        zocalo.getChildren().addAll(nombre, secciones, mods);

        // ~~~~~~~ Ensamblado ~~~~~~~
        this.getChildren().addAll(circuloValor, vistaImagen, zocalo);
    }
}
