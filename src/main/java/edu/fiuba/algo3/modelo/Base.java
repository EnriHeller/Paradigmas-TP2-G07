package edu.fiuba.algo3.modelo;

import java.util.List;

public class Base implements Modificador{

    public Base(){
    }

    @Override
    public String mostrarModificadores() {
        return "";
    }

    @Override
    public void modificarComportamiento(CartaUnidad carta) {

    }

    @Override
    public void modificarComportamientoSeccion(List<CartaUnidad> cartas) {

    }
}
