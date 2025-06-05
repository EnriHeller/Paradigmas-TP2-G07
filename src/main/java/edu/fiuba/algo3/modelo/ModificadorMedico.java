package edu.fiuba.algo3.modelo;

public class ModificadorMedico implements Modificador {

    private Modificador modificador;

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Medico";
    }

    @Override
    public void modificar(){

    }

}
