package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.cartas.Carta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {

    private List<Carta> mazo;

    public Mazo(List<Carta> mazo) {
        this.mazo = mazo;
        mezclar();
    }

    public List<Carta> repartirMano() {
        List<Carta> mano = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mano.add(obtenerCarta());
        }
        return mano;
    }
    public Mazo copiar() {
        return new Mazo(mazo);
    }
    public Carta obtenerCarta() {
        return mazo.remove(0);
    }
    public void agregarCarta(Carta carta) {
        mazo.add(carta);
    }
    private void mezclar() {
        Collections.shuffle(mazo);
    }

}
