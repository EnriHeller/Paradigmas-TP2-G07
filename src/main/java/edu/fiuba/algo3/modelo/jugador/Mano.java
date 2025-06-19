package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.errores.CartaInvalidadError;

import java.util.List;

public class Mano {

    private final List<Carta> mano;

    public Mano(List<Carta> mano) {
        this.mano = mano;

    }
    public void agregarCarta(Carta carta) {
        this.mano.add(carta);
    }

    public Carta quitarCarta(Carta carta) {
        for (Carta c : mano) {
            if (c.equals(carta)) {
                mano.remove(c);
                return c;
            }
        }
        throw new CartaInvalidadError();
    }
    public int cantidad() {
        return mano.size();
    }
    public List<Carta> obtenerCartas() {
        return mano;
    }

}
