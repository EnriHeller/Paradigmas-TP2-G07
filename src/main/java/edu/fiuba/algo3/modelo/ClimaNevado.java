package edu.fiuba.algo3.modelo;

import java.util.List;

public class ClimaNevado implements Clima{

    @Override
    public void afectarCartas(List<CartaUnidad> cartas) {
        for (CartaUnidad carta: cartas){
            carta.modificarValor(1);
        }
    }
}
