package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.vistas.juego.TableroView;
import edu.fiuba.algo3.vistas.juego.cartas.CartaVisual;

public class HandlerSeleccionarCarta {
    private final TableroView tableroView;
    private CartaVisual seleccionada;

    public HandlerSeleccionarCarta(TableroView tableroView) {
        this.tableroView = tableroView;
    }

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

    public void limpiarSeleccion() {
        if (seleccionada != null) {
            seleccionada.animarDeseleccion();
            seleccionada = null;
        }
        tableroView.setCartaElegida(null);
    }
}
