package edu.fiuba.algo3.modelo.secciones.jugador;


import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;

public class Descarte extends SeccionJugador<CartaUnidad> {

    public Descarte() {
        super();
    }

    @Override
    public void agregarCarta(CartaUnidad carta) {
        cartas.add(carta);
    }

    public CartaUnidad removerUltimaUnidad() {
        if (cartas.isEmpty()) {
            throw new edu.fiuba.algo3.modelo.modificadores.PilaDescarteNula();
        }
        return cartas.remove(cartas.size() - 1);
    }

}
