package edu.fiuba.algo3.modelo;

import java.util.List;

public class Medico implements Modificador{

    private final Modificador modificador;

    public Medico(Modificador modificador){
        this.modificador = modificador;
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Medico";
    }

    @Override
    public void modificarComportamiento(CartaUnidad carta) {

    }

    @Override
    public void modificarComportamientoSeccion(List<CartaUnidad> cartas) {

    }
}
