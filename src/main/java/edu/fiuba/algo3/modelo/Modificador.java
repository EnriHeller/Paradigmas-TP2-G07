package edu.fiuba.algo3.modelo;

import java.util.List;

public interface Modificador {

    public String mostrarModificadores();

    public void modificarComportamiento(CartaUnidad carta);

    public void modificarComportamientoDeCartas(List<CartaUnidad> cartas);

}
