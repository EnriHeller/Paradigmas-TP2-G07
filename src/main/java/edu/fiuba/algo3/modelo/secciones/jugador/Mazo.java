package edu.fiuba.algo3.modelo.secciones.jugador;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Mazo {
    private final List<Carta> cartas;

    public Mazo(List<Carta> cartas) {

        this.cartas = cartas;
    }

    public int cantidadDeCartas(){
        return cartas.size();
    }

    public void mezclar() {
        Collections.shuffle(this.cartas);
    }

    public void recibirCarta(Carta carta) {
        this.cartas.add(carta);
        mezclar();
    }

    public List<Carta> repartirCarta(int n) throws NoSePuedeCumplirSolicitudDeCartas {

        if (n > cartas.size()) {
            throw new NoSePuedeCumplirSolicitudDeCartas();
        }

        List<Carta> cartasRepartidas = new ArrayList<Carta>();

        for (int i = 0; i < n; i++) {
            Carta cartaSacada = cartas.removeLast();
            cartasRepartidas.add(cartaSacada);
        }

        return cartasRepartidas;
    }

}