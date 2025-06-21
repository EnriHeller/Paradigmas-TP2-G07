package edu.fiuba.algo3.modelo.secciones.jugador;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

import java.util.ArrayList;
import java.util.List;

public class Mano implements SeccionJugador<Carta>{

    private List<Carta> cartas;

    public Mano() {
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
        throw new IllegalArgumentException("La carta no está en la mano");
    }

    @Override
    public List<Carta>  removerCartas(List<Carta> cartas) {
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
    public void agregarCartas(List<Carta> cartas){

        this.cartas.addAll(cartas);

    }

}
