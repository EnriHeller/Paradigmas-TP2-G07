package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.vistas.juego.TableroView;
import edu.fiuba.algo3.vistas.juego.cartas.CartaVisual;

public class HandlerUnidadMano implements CartaClickHandler {
    private CartaVisual seleccionada;
    private final TableroView tableroView;

    public HandlerUnidadMano(TableroView tableroView) {
        this.tableroView = tableroView;
    }

    @Override
    public void alClic(CartaVisual cartaVisual) {
        if (seleccionada == cartaVisual) {
            cartaVisual.animarDeseleccion();
            seleccionada = null;
            tableroView.setCartaElegida(null);
            return;
        }
        if (seleccionada != null) {
            seleccionada.animarDeseleccion();
        }
        cartaVisual.animarSeleccion();
        seleccionada = cartaVisual;
        tableroView.setCartaElegida(cartaVisual.getCarta()); 
    }
}
