package edu.fiuba.algo3.modelo.secciones.jugador;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.cartas.Carta;

public class PilaDescarte {
    private List<Carta> cartasDescartadas;

    public PilaDescarte() {
        this.cartasDescartadas = new ArrayList<>();
    }

    public void descartar(Carta carta) {
        cartasDescartadas.add(carta);
    }

    public int cantidad() {
        return cartasDescartadas.size();
    }

    public void agregarCartas(List<Carta> cartas){

        cartasDescartadas.addAll(cartas);

    }

    public int cartasEnElDescarte() {

        return cartasDescartadas.size();

    }

}
