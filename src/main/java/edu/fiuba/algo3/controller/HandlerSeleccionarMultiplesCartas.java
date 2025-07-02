package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.vistas.juego.cartas.CartaVisual;

import java.util.ArrayList;
import java.util.List;

public class HandlerSeleccionarMultiplesCartas {
    private final int maxSeleccion;
    private final List<CartaVisual> seleccionadas = new ArrayList<>();

    public HandlerSeleccionarMultiplesCartas(int maxSeleccion) {
        this.maxSeleccion = maxSeleccion;
    }

    public boolean cartaSeleccionada(CartaVisual visual) {
        return seleccionadas.contains(visual);
    }

    public void alClic(CartaVisual visual) {
        if (seleccionadas.contains(visual)) {
            visual.animarDeseleccion();
            seleccionadas.remove(visual);
        } else {
            if (seleccionadas.size() < maxSeleccion) {
                seleccionadas.add(visual);
                visual.animarSeleccion();
            }
        }
    }

    public List<CartaVisual> obtenerSeleccionadas() {
        return new ArrayList<>(seleccionadas);
    }

    public void limpiarSeleccion() {
        for (CartaVisual v : seleccionadas) {
            v.animarDeseleccion();
        }
        seleccionadas.clear();
    }
}
