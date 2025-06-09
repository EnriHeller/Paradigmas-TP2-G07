package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaUnidad;
import edu.fiuba.algo3.modelo.jugador.Mazo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConstructorMazo {

    // Aca se van a hardcodear las cartas
    public Mazo construirMazo() {
        List<Carta> cartasJugador = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            cartasJugador.add(new CartaUnidad("carta1", Collections.singletonList("Asedio"), 1));
        }
        return new Mazo(cartasJugador);
    }
}
