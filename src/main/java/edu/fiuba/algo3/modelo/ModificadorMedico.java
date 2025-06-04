package edu.fiuba.algo3.modelo;

public class ModificadorMedico implements Modificador {

    private Modificador modificador;

    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Medico";
    }

    @Override
    public void aplicarModificador(){

    }

}
