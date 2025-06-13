package edu.fiuba.algo3.vistas.CartasView;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.cartas.unidades.Puntuable;

import javafx.scene.control.Label;

public class CartaUnidadVisual extends CartaVisual {

    public CartaUnidadVisual(CartaUnidad carta) {
        super(carta);
        construirVista();
    }

    @Override
    public void construirVista() {

        if (carta instanceof Puntuable) {
            Label nombre = new Label(((Puntuable) carta).getNombre());
            Puntuable cartaPuntuable = (Puntuable) carta;
            Label puntos = new Label(String.valueOf(cartaPuntuable.ValorActual()));
            Label secciones = new Label(String.join(", ", ((CartaUnidad) carta).getSecciones()));

            this.getChildren().addAll(nombre, puntos, secciones);
        }

    }
}