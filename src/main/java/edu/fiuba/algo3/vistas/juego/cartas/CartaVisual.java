package edu.fiuba.algo3.vistas.juego.cartas;

import edu.fiuba.algo3.modelo.cartas.Carta;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class CartaVisual extends VBox {

    protected Carta carta;
    public static final int ANCHO = 80;
    public static final int ALTO = 100;
    protected Rectangle hoverBorder;
    protected StackPane mainStack;
    protected VBox infoOverlay;

    public CartaVisual(Carta carta) {
        this.carta = carta;
        this.setPadding(new Insets(0, 0, 0, 0));
        this.setAlignment(Pos.CENTER);
        this.setMouseTransparent(false);
        this.setPickOnBounds(true);

        hoverBorder = new Rectangle(ANCHO, ALTO);
        hoverBorder.setFill(Color.TRANSPARENT);
        hoverBorder.setStroke(Color.YELLOW);
        hoverBorder.setStrokeWidth(2);
        hoverBorder.setVisible(false);
        hoverBorder.setMouseTransparent(true);
        mainStack = new StackPane();
        mainStack.setPrefSize(ANCHO, ALTO);
        mainStack.setMinSize(ANCHO, ALTO);
        mainStack.setMaxSize(ANCHO, ALTO);
        this.getChildren().add(mainStack);

        // Overlay de información
        infoOverlay = new VBox();
        infoOverlay.setVisible(false);
        infoOverlay.setMouseTransparent(true);
        infoOverlay.setStyle("-fx-background-color: rgba(30,30,30,0.95); -fx-border-color: gold; -fx-border-width: 2; -fx-background-radius: 8; -fx-border-radius: 8;");
        infoOverlay.setPadding(new Insets(8));
        infoOverlay.setTranslateY(-ALTO - 10); // Aparece arriba de la carta
        infoOverlay.setSpacing(4);
        infoOverlay.setAlignment(Pos.TOP_LEFT);
        mainStack.getChildren().add(infoOverlay);
    }

    protected void mostrarInfoOverlay() {
        infoOverlay.getChildren().clear();
        construirCamposInfo();
        infoOverlay.setVisible(true);
    }

    protected void ocultarInfoOverlay() {
        infoOverlay.setVisible(false);
    }

    // Cada subclase debe implementar esto para mostrar los campos correctos
    public abstract void construirCamposInfo();

    protected void setTamanioEstandar() {
        this.setPrefSize(ANCHO, ALTO);
        this.setMinSize(ANCHO, ALTO);
        this.setMaxSize(ANCHO, ALTO);
    }

    protected ImageView crearImagenEstandar(Image imagen) {
        ImageView vistaImagen = new ImageView(imagen);
        vistaImagen.setFitWidth(ANCHO);
        vistaImagen.setFitHeight(ALTO);
        vistaImagen.setPreserveRatio(false);
        vistaImagen.setPickOnBounds(false);

        // Efecto hover dorado para todas las cartas visuales
        vistaImagen.setOnMouseEntered(e -> {
            this.setCursor(javafx.scene.Cursor.HAND);
            hoverBorder.setStroke(Color.GOLD);
            hoverBorder.setStrokeWidth(4);
            hoverBorder.setFill(Color.rgb(255, 215, 0, 0.18)); // dorado semitransparente
            hoverBorder.setVisible(true);
            if (mainStack.getChildren().contains(hoverBorder)) {
                hoverBorder.toFront();
            }
            // Mostrar overlay de información solo si la carta NO está seleccionada
            if (!estaSeleccionada()) {
                mostrarInfoOverlay();
            } else {
                ocultarInfoOverlay();
            }
        });
        vistaImagen.setOnMouseExited(e -> {
            this.setCursor(javafx.scene.Cursor.DEFAULT);
            // Solo ocultar el hoverBorder si la carta NO está seleccionada
            if (!estaSeleccionada()) {
                hoverBorder.setVisible(false);
                ocultarInfoOverlay();
            }
        });
        
        return vistaImagen;
    }

    public Carta getCarta(){
        return carta;
    }

    // Método para que las subclases indiquen si están seleccionadas
    protected boolean estaSeleccionada() {
        return false;
    }

    public abstract void construirVista();
    public abstract void animarSeleccion();
    public abstract void animarDeseleccion();
}