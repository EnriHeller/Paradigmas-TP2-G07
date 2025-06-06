package edu.fiuba.algo3.modelo.modificadores;

public class Medico implements Modificador {

    private Modificador modificador;

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Medico";
    }

    @Override
    public void modificar(String seccion){

    }

}
