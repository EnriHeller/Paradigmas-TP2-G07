package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;


public class Mano {
    private List<Carta> cartasEnMano;

    public Mano() {
        this.cartasEnMano = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        cartasEnMano.add(carta);
    }

    public void agregarCartas(List<Carta> cartas){

        cartasEnMano.addAll(cartas);

    }

    public int mostrarCartas(){
        for (Carta carta: cartasEnMano){
            carta.mostrarCarta();
        }
    }

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

    public List<Carta> obtenerCartas() {
        return new ArrayList<>(cartasEnMano);
    }

    public int cartasRestantes() {

        return cartasEnMano.size();

    }
}

