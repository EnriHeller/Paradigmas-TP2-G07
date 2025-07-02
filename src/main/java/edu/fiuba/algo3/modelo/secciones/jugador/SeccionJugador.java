package edu.fiuba.algo3.modelo.secciones.jugador;

import java.util.ArrayList;
import java.util.List;

public abstract class SeccionJugador<T> {
    protected List<T> cartas;

    public SeccionJugador() {
        this.cartas = new ArrayList<>();
    }

    public int cartasRestantes() {
        return cartas.size();
    }

    public T removerCarta(T carta) {
        for (int i = 0; i < cartas.size(); i++) {
            if (cartas.get(i).equals(carta)) {
                return cartas.remove(i);
            }
        }
        throw new IllegalArgumentException("La carta no está en la sección");
    }

    public List<T> removerCartas(List<T> cartasARemover) {
        for (T carta : cartasARemover) {
            removerCarta(carta);
        }
        return cartasARemover;
    }

    // Delega el casteo a la subclase si es necesario
    public abstract void agregarCarta(T carta);

    public void agregarCartas(List<T> cartas) {
        this.cartas.addAll(cartas);
    }

    public List<T> getCartas(){
        return cartas;
    }
}
