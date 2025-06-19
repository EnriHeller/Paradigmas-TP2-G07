package edu.fiuba.algo3.vistas.CartasView;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.especiales.CartaEspecial;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Objects;

public class CartaEspecialVisual extends CartaVisual {

    public CartaEspecialVisual(Carta carta) {
        super(carta);
        construirVista();
    }

    @Override
    public void construirVista() {
        this.setSpacing(5);

        if (!(carta instanceof CartaEspecial)) {
            // Si no es una carta especial, no se puede construir la vista correctamente
            Label error = new Label("Carta no especial");
            this.getChildren().add(error);
            return;
        }

        CartaEspecial cartaEspecial = (CartaEspecial) carta;

        // ~~~~~~~ Tipo de carta (T arrasada, nevada, etc) ~~~~~~~
        Label tipo = new Label(cartaEspecial.getTipo());
        tipo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");
        StackPane encabezado = new StackPane();
        encabezado.setPrefSize(200, 30);
        encabezado.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, new CornerRadii(10), Insets.EMPTY)));
        encabezado.getChildren().add(tipo);
        encabezado.setAlignment(Pos.CENTER);

        // ~~~~~~~ Imagen ~~~~~~~
        String ruta = "/imagenes/cartas/" + cartaEspecial.getNombre().replaceAll(" ", "") + ".png";
        Image imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        ImageView vistaImagen = new ImageView(imagen);
        vistaImagen.setFitWidth(100);
        vistaImagen.setPreserveRatio(true);

        // ~~~~~~~ Detalles de la carta ~~~~~~~
        VBox detalles = new VBox(5);
        detalles.setPadding(new Insets(10));
        detalles.setAlignment(Pos.CENTER_LEFT);
        detalles.setBackground(new Background(new BackgroundFill(Color.web("#ddeeffcc"), new CornerRadii(5), Insets.EMPTY)));

        Label nombre = new Label("Nombre: " + cartaEspecial.getNombre());
        nombre.setStyle("-fx-font-weight: bold;");

        String descripcionStr = (cartaEspecial.getDescripcion() != null) ? cartaEspecial.getDescripcion() : "";
        Label descripcion = new Label("Descripción: " + descripcionStr);
        descripcion.setWrapText(true);

        // Campo "afectado" (si existe)
        String afectadoStr = "";
        List<String> afectado = cartaEspecial.getAfectado();
        if (afectado != null && !afectado.isEmpty()) {
            afectadoStr = String.join(", ", afectado);
        }
        Label afectadoLabel = new Label("Afectado: " + (afectadoStr.isEmpty() ? "Ninguno" : afectadoStr));
        afectadoLabel.setStyle("-fx-font-style: italic;");

        detalles.getChildren().addAll(nombre, descripcion, afectadoLabel);

        // ~~~~~~~ Ensamblaje ~~~~~~~
        this.getChildren().addAll(encabezado, vistaImagen, detalles);
    }
}