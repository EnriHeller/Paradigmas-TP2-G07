package edu.fiuba.algo3.modelo.secciones.jugador;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.Errores.CartaNoEncontradaError;

import java.util.ArrayList;
import java.util.List;

public abstract class SeccionJugador {
    protected List<Carta> cartas;

    public SeccionJugador() {
        this.cartas = new ArrayList<>();
    }

    public int cartasRestantes() {
        return cartas.size();
    }

    public Carta removerCarta(Carta carta) {
        int i = cartas.indexOf(carta);
        if (i == -1) throw new CartaNoEncontradaError("La carta no está en la sección");
        return cartas.remove(i);
    }

    public Carta removerCarta(int indice) {
        return cartas.remove(indice);
    }

    public List<Carta> removerCartas(List<Carta> cartasARemover) {
        for (Carta carta : cartasARemover) {
            removerCarta(carta);
        }
        return cartasARemover;
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public void agregarCartas(List<Carta> cartasNuevas) {
        cartas.addAll(cartasNuevas);
    }

    public List<Carta> obtenerCartas() {
        return cartas;
    }
}
