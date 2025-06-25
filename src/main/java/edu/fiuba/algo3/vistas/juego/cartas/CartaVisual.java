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

    public CartaVisual(Carta carta) {
        this.carta = carta;
        this.setPadding(new Insets(0, 0, 0, 0));
        this.setAlignment(Pos.CENTER);
        this.setMouseTransparent(false);
        this.setPickOnBounds(true);
        hoverBorder = new Rectangle(ANCHO, ALTO);
        hoverBorder.setFill(Color.TRANSPARENT);
        hoverBorder.setStroke(Color.YELLOW);
        hoverBorder.setStrokeWidth(3);
        hoverBorder.setVisible(false);
        hoverBorder.setMouseTransparent(true);
        mainStack = new StackPane();
        mainStack.setPrefSize(ANCHO, ALTO);
        mainStack.setMinSize(ANCHO, ALTO);
        mainStack.setMaxSize(ANCHO, ALTO);
        this.getChildren().add(mainStack);
        this.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_ENTERED_TARGET, evt -> {
            hoverBorder.setVisible(true);
        });
        this.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_EXITED_TARGET, evt -> {
            hoverBorder.setVisible(false);
        });
    }

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