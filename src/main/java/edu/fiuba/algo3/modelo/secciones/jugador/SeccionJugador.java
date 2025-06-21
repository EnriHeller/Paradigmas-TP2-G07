package edu.fiuba.algo3.modelo.secciones.jugador;

import edu.fiuba.algo3.modelo.cartas.Carta;

import java.util.ArrayList;
import java.util.List;

public class SeccionJugador {

    private List<Carta> cartas;

    public SeccionJugador() {
        this.cartas = new ArrayList<>();
    }

    public int cartasRestantes(){
        return this.cartas.size();
    }

    //Intentar remover este mensaje
    public Carta removerCarta(int indice) {

        return cartas.remove(indice);
    }

    public Carta removerCarta(Carta carta) {
        for (int i = 0; i < cartas.size(); i++) {
            if (cartas.get(i).equals(carta)) {
                return cartas.remove(i);
            }
        }
        throw new IllegalArgumentException("La carta no está en la mano");
    }

    public List<Carta>  removerCartas(List<Carta> cartas) {
        for (Carta carta : cartas) {
            removerCarta(carta);
        }
        return cartas;
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public void agregarCartas(List<Carta> cartas){

        this.cartas.addAll(cartas);

    }

}
