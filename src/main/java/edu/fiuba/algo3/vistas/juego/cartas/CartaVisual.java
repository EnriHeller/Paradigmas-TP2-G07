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
        hoverBorder.setFill(Color.rgb(255, 255, 0, 0.3)); // Amarillo semitransparente
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
        infoOverlay.setSpacing(4);
        infoOverlay.setMaxWidth(180);
        infoOverlay.setTranslateY(-ALTO - 10); // Aparece arriba de la carta
        infoOverlay.setAlignment(Pos.TOP_LEFT);
        mainStack.getChildren().add(infoOverlay);

        this.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_ENTERED_TARGET, evt -> {
            hoverBorder.setVisible(true);
            construirCamposInfo();
            infoOverlay.setVisible(true);
            infoOverlay.toFront();
        });
        this.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_EXITED_TARGET, evt -> {
            hoverBorder.setVisible(false);
            infoOverlay.setVisible(false);
        });
    }

    protected void mostrarInfoOverlay() {
        infoOverlay.getChildren().clear();
        construirCamposInfo();
        infoOverlay.setVisible(true);
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
        return vistaImagen;
    }

    public abstract void construirVista();
}