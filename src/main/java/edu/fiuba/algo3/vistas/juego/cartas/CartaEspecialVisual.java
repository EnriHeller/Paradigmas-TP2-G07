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

    @Override
    public void construirVista() {
        //this.setSpacing(5);

        // // ~~~~~~~ Tipo de carta (T arrasada, nevada, etc) ~~~~~~~
        // Label tipo = new Label(cartaEspecial.getTipo());
        // tipo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");
        // StackPane encabezado = new StackPane();
        // encabezado.setPrefSize(200, 30);
        // encabezado.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, new CornerRadii(10), Insets.EMPTY)));
        // encabezado.getChildren().add(tipo);
        // encabezado.setAlignment(Pos.CENTER);

        String nombre = carta.mostrarCarta();
        String ruta = "/imagenes/" + nombre.replaceAll(" ", "") + ".png";
        Image imagen;
        try {
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        } catch (Exception e) {
            System.err.println("[CartaEspecialVisual] Imagen no encontrada para: " + ruta + ". Usando imagen por defecto. Excepcion: " + e);
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Lluviatorrencial.png")));
        }
        ImageView vistaImagen = new ImageView(imagen);
        vistaImagen.setFitWidth(70);
        vistaImagen.setFitHeight(90);
        vistaImagen.setPreserveRatio(false);
        vistaImagen.setPickOnBounds(false);
        this.setPrefSize(70, 90);
        this.setMinSize(70, 90);
        this.setMaxSize(70, 90);
        this.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        // // ~~~~~~~ Detalles de la carta ~~~~~~~
        // VBox detalles = new VBox(5);
        // detalles.setPadding(new Insets(10));
        // detalles.setAlignment(Pos.CENTER_LEFT);
        // detalles.setBackground(new Background(new BackgroundFill(Color.web("#ddeeffcc"), new CornerRadii(5), Insets.EMPTY)));

        // Label nombre = new Label(cartaEspecial.getNombre());
        // nombre.setStyle("-fx-font-weight: bold;");

        // String descripcionStr = (cartaEspecial.getDescripcion() != null) ? cartaEspecial.getDescripcion() : "";
        // Label descripcion = new Label(descripcionStr);
        // descripcion.setWrapText(true);

        // // Campo "afectado" (si existe)
        // String afectadoStr = "";
        // List<String> afectado = cartaEspecial.getAfectado();
        // if (afectado != null && !afectado.isEmpty()) {
        //     afectadoStr = String.join(", ", afectado);
        // }
        // Label afectadoLabel = new Label("Afectado: " + (afectadoStr.isEmpty() ? "Ninguno" : afectadoStr));
        // afectadoLabel.setStyle("-fx-font-style: italic;");

        // detalles.getChildren().addAll(nombre, descripcion, afectadoLabel);

        // ~~~~~~~ Ensamblaje ~~~~~~~
        // this.getChildren().addAll(encabezado, vistaImagen, detalles);
        this.getChildren().clear();
        this.getChildren().add(vistaImagen);
    }
}