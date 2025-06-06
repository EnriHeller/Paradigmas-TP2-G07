package edu.fiuba.algo3.modelo.secciones.jugador;

import edu.fiuba.algo3.modelo.cartas.Carta;

import java.util.ArrayList;
import java.util.List;

public class SeccionSinPuntaje {

    private List<Carta> cartasEnMano;

    public SeccionSinPuntaje() {
        this.cartasEnMano = new ArrayList<>();
    }

    public int cartasRestantes(){
        return this.cartasEnMano.size();
    }

    //Intentar remover este mensaje
    public Carta removerCarta(int indice) {

        return cartasEnMano.remove(indice);
    }

    public Carta removerCarta(Carta carta) {
        for (int i = 0; i < cartasEnMano.size(); i++) {
            if (cartasEnMano.get(i).equals(carta)) {
                return cartasEnMano.remove(i);
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
        cartasEnMano.add(carta);
    }

    public void agregarCartas(List<Carta> cartas){

        cartasEnMano.addAll(cartas);

    }

}
