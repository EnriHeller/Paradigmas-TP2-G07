package edu.fiuba.algo3.modelo.secciones.jugador;

import edu.fiuba.algo3.modelo.cartas.Carta;

import java.util.ArrayList;
import java.util.List;

public abstract class SeccionJugador<Carta> {
    protected List<Carta> cartas;

    public SeccionJugador() {
        this.cartas = new ArrayList<>();
    }

    public int cartasRestantes() {
        return cartas.size();
    }

    public Carta removerCarta(Carta carta) {
        for (int i = 0; i < cartas.size(); i++) {
            if (cartas.get(i).equals(carta)) {
                return cartas.remove(i);
            }
        }
        throw new IllegalArgumentException("La carta no está en la sección");
    }

    public List<Carta> removerCartas(List<Carta> cartasARemover) {
        for (Carta carta : cartasARemover) {
            removerCarta(carta);
        }
        return cartasARemover;
    }

    // Delega el casteo a la subclase si es necesario
    public abstract void agregarCarta(Carta carta);

    public void agregarCartas(List<Carta> cartas) {
        this.cartas.addAll(cartas);
    }

    public List<Carta> getCartas(){
        return cartas;
    }
}
