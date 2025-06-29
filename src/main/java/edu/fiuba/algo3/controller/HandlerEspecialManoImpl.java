package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.vistas.juego.TableroView;
import edu.fiuba.algo3.vistas.juego.cartas.CartaEspecialVisual;

public class HandlerEspecialManoImpl implements HandlerEspecialMano {
    private final TableroView tableroView;
    private CartaEspecialVisual seleccionada;

    public HandlerEspecialManoImpl(TableroView tableroView) {
        this.tableroView = tableroView;
    }

    @Override
    public void alClicEspecial(CartaEspecialVisual visual) {
        if (seleccionada == visual) {
            visual.animarDeseleccion();
            seleccionada = null;
            tableroView.setCartaElegida(null);
            return;
        }
        if (seleccionada != null) {
            seleccionada.animarDeseleccion();
        }
        visual.animarSeleccion();
        seleccionada = visual;
        tableroView.setCartaElegida(visual.getCarta());
    }
}
