package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;

import java.util.List;

public class ClimaNevado implements Clima {

    @Override
    public void afectarCartas(List<CartaUnidad> cartas) {
        for (CartaUnidad carta: cartas){
            carta.modificarValor(1);
        }
    }
}
