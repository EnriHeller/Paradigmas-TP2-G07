package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.errores.CartaInvalidadError;

import java.util.ArrayList;
import java.util.List;

public class PilaDeDescarte {

    private final List<Carta> cartas;

    public PilaDeDescarte() {
        this.cartas = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public Carta recuperarCarta(Carta carta) {

        for (Carta c : cartas) {
            if (c.equals(carta)) {
                cartas.remove(c);
                return c;
            }
        }
        throw new CartaInvalidadError();
    }

    public void agregarCartas(List<Carta> cartas) {
        this.cartas.addAll(cartas);
    }

    public List<Carta> obtenerCartas() {
        return new ArrayList<>(cartas);
    }

}