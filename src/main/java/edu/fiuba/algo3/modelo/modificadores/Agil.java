package edu.fiuba.algo3.modelo.modificadores;

public class Agil implements Modificador {

    private final Modificador modificador;

    public Agil(Modificador modificador){
        this.modificador = modificador;
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Agil" ;
    }

    @Override
    public void modificar(String seccion) {

    }
}

