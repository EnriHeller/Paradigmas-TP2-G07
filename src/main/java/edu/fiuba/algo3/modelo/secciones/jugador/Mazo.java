package edu.fiuba.algo3.modelo.secciones.jugador;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeCumplirSolicitudDeCartas;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Mazo extends SeccionJugador {
    public Mazo(List<Carta> cartas) {
        super();
        this.cartas.addAll(cartas);
    }

    public int cantidadDeCartas() {
        return cartas.size();
    }

    public void mezclar() {
        Collections.shuffle(this.cartas);
    }

    public void recibirCarta(Carta carta) {
        this.cartas.add(carta);
        mezclar();
    }

    public void repartirCartas(int n, Mano mano) throws NoSePuedeCumplirSolicitudDeCartas {
        if (n > cartas.size()) {
            throw new NoSePuedeCumplirSolicitudDeCartas();
        }

        List<Carta> cartasARepartir = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Carta cartaSacada = cartas.remove(cartas.size() - 1);
            cartasARepartir.add(cartaSacada);
        }
        
        mano.agregarCartas(cartasARepartir);
    }
}