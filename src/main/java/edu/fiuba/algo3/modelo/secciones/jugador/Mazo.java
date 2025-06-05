package edu.fiuba.algo3.modelo.secciones.jugador;
import edu.fiuba.algo3.modelo.cartas.Carta;

import java.util.ArrayList;
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
        // Lógica para mezclar las cartas (stub)
    }

    public List<Carta> repartirCarta(int n) {
        List<Carta> cartasRepartidas = new ArrayList<Carta>();

        for (int i = 0; i < n; i++) {
            Carta cartaSacada = cartas.remove(cartas.size() -1);
            cartasRepartidas.add(cartaSacada);
        }

        return cartasRepartidas;
    }

}