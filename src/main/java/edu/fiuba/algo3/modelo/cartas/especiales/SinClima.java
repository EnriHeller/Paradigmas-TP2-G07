package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;

import java.util.List;

public class SinClima implements Clima {
    @Override
    public boolean hayCLima() {
        return false;
    }

    @Override
    public void afectarCartas(List<CartaUnidad> cartas) {

    }
}
