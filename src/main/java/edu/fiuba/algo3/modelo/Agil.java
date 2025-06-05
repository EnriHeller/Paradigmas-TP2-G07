package edu.fiuba.algo3.modelo;

import java.util.List;

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
    public void modificar() {

    }
}

