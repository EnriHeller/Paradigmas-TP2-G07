package edu.fiuba.algo3.modelo.secciones.jugador;


import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;

import java.util.ArrayList;
import java.util.List;

public class Descarte implements SeccionJugador<CartaUnidad> {

    private List<CartaUnidad> cartas;

    public Descarte() {
        this.cartas = new ArrayList<>();
    }

    @Override
    public int cartasRestantes(){
        return this.cartas.size();
    }


    @Override
    public Carta removerCarta(Carta carta) {
        for (int i = 0; i < cartas.size(); i++) {
            if (cartas.get(i).equals(carta)) {
                return cartas.remove(i);
            }
        }
        throw new IllegalArgumentException("La carta no está en la descarte");
    }

    @Override
    public List<CartaUnidad>  removerCartas(List<CartaUnidad> cartas) {
        for (Carta carta : cartas) {
            removerCarta(carta);
        }
        return cartas;
    }

    @Override
    public void agregarCarta(Carta carta) {
        cartas.add((CartaUnidad) carta);
    }

    @Override
    public void agregarCartas(List<CartaUnidad> cartas){

        this.cartas.addAll(cartas);

    }

    public CartaUnidad removerUnidad(){
        return cartas.remove(cartas.size()-1);
    }

}
