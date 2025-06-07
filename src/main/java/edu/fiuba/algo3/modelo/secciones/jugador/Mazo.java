package edu.fiuba.algo3.modelo.secciones.jugador;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolcitudDeCartas;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Mazo {
    private List<Carta> cartas;

    public Mazo(List<Carta> cartas) {

        this.cartas = cartas;
    }

    public int cantidadDeCartas(){
        return cartas.size();
    }

    public void mezclar() {
        Collections.shuffle(this.cartas);
    }

    public void recibirCartas(List<Carta> cartas) {
        this.cartas.addAll(cartas);
        mezclar();
    }

    public List<Carta> repartirCarta(int n) throws NoSePuedeCumplirSolcitudDeCartas {

        if (n > cartas.size()) {
            throw new NoSePuedeCumplirSolcitudDeCartas();
        }

        List<Carta> cartasRepartidas = new ArrayList<Carta>();

        for (int i = 0; i < n; i++) {
            Carta cartaSacada = cartas.remove(cartas.size() -1);
            cartasRepartidas.add(cartaSacada);
        }

        return cartasRepartidas;
    }

}