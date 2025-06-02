package edu.fiuba.algo3.modelo;
import java.util.LinkedList;
import java.util.List;
import edu.fiuba.algo3.modelo.Carta;


public class Mazo {
    private LinkedList<Carta> cartas;

    public Mazo(LinkedList<Carta> cartas) {

        this.cartas = cartas;
    }

    public int cantidadDeCartas(){
        return cartas.size();
    }

    public void mezclar() {
        // Lógica para mezclar las cartas (stub)
    }

    public List<Carta> repartirCarta(int n) {
        List<Carta> cartasRepartidas = new LinkedList<Carta>();

        for (int i = 0; i < n; i++) {

            cartasRepartidas.add(cartas.removeFirst());
        }

        return cartasRepartidas;
    }

}