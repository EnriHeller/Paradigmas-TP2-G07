package edu.fiuba.algo3.vistas.CartasView;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;

import javafx.scene.control.Label;

public class CartaUnidadVisual extends CartaVisual {

    public CartaUnidadVisual(CartaUnidad carta) {
        super(carta);
        construirVista();
    }

    @Override
    public void construirVista() {
        // Ya no se usa Puntuable, solo se asume que carta es CartaUnidad
        Label nombre = new Label(((CartaUnidad) carta).getNombre());
        Label puntos = new Label(String.valueOf(((CartaUnidad) carta).ValorActual()));
        Label secciones = new Label(String.join(", ", ((CartaUnidad) carta).getSecciones()));
        this.getChildren().addAll(nombre, puntos, secciones);
    }
}