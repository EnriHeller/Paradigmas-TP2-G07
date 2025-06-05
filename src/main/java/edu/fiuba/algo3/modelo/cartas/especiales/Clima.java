package edu.fiuba.algo3.modelo.cartas.especiales;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;

import java.util.List;


public interface Clima {

    public abstract void afectarCartas(List<CartaUnidad> cartas);

}
