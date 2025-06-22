package edu.fiuba.algo3.modelo.secciones.jugador;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Mazo implements SeccionJugador<Carta> {
    private List<Carta> cartas;

    public Mazo(List<Carta> cartas) {

        this.cartas = cartas;
    }

    @Override
    public int cartasRestantes(){
        return cartas.size();
    }


    @Override
    public Carta removerCarta(Carta carta) {
        for (int i = 0; i < cartas.size(); i++) {
            if (cartas.get(i).equals(carta)) {
                return cartas.remove(i);
            }
        }
        throw new IllegalArgumentException("La carta no está en la mazo");
    }

    @Override
    public List<Carta> removerCartas(List<Carta> cartas) {
        for (Carta carta : cartas) {
            removerCarta(carta);
        }
        return cartas;
    }

    @Override
    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    @Override
    public void agregarCartas(List<Carta> cartas) {
        this.cartas.addAll(cartas);
        mezclar();
    }

    public void mezclar() {
        Collections.shuffle(this.cartas);
    }

    public List<Carta> repartirCarta(int n) throws NoSePuedeCumplirSolicitudDeCartas {

        if (n > cartas.size()) {
            throw new NoSePuedeCumplirSolicitudDeCartas();
        }

        List<Carta> cartasRepartidas = new ArrayList<Carta>();

        for (int i = 0; i < n; i++) {
            Carta cartaSacada = cartas.remove(cartas.size() -1);
            cartasRepartidas.add(cartaSacada);
        }

        return cartasRepartidas;
    }

}