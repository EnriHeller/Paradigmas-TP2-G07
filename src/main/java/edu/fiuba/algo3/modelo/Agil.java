package edu.fiuba.algo3.modelo;

public class Agil implements Modificador{

    private final Modificador modificador;

    public Agil(Modificador modificador){
        this.modificador = modificador;
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Agil" ;
    }

    @Override
    public void modificarComportamiento(CartaUnidad carta) {

    }

    @Override
    public void modificarComportamientoSeccion(Seccion seccion) {

    }
}

