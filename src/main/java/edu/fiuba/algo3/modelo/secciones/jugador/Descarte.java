package edu.fiuba.algo3.modelo.secciones.jugador;


import edu.fiuba.algo3.modelo.cartas.Carta;

public class Descarte extends SeccionJugador<Carta> {

    public Descarte() {
        super();
    }

    @Override
    public void agregarCarta(Carta carta) {
        if (carta.esEspecial()) {
            throw new IllegalArgumentException("Solo se pueden agregar instancias de CartaUnidad al descarte");
        }
        cartas.add(carta);
    }

    public Carta removerUnidad() {
        return cartas.remove(cartas.size() - 1);
    }

    public Carta getCartaPila(){
        if(cartas.isEmpty())
            return null;

        return cartas.get(cartas.size() - 1);
    }
}
