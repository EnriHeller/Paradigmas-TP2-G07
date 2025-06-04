package edu.fiuba.algo3.modelo;

import java.util.List;

public class Base implements Modificador{

    private CartaUnidad cartaUnidad;

    public Base(CartaUnidad cartaUnidad) {
        this.cartaUnidad = cartaUnidad;
    }

    @Override
    public String CartaQueLoPosee(){
        return cartaUnidad.nombre;
    }

    @Override
    public String mostrarModificadores() {
        return "";
    }

    @Override
    public void modificarComportamiento(CartaUnidad carta) {

    }

    @Override
    public void modificarComportamientoDeCartas(List<CartaUnidad> cartas) {

    }
}
