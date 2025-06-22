package edu.fiuba.algo3.modelo.secciones.jugador;


import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;

import java.util.ArrayList;
import java.util.List;

public class Descarte extends SeccionJugador<Carta> {

    public Descarte() {
        super();
    }

    @Override
    public void agregarCarta(Carta carta) {
        if (!(carta instanceof CartaUnidad)) {
            throw new IllegalArgumentException("Solo se pueden agregar instancias de CartaUnidad al descarte");
        }
        cartas.add((CartaUnidad) carta);
    }

    public Carta removerUnidad() {
        return cartas.remove(cartas.size() - 1);
    }
}
