package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.principal.Contexto;

public class Medico implements Modificador {

    private Modificador modificador;

    public Medico(Modificador modificador) {
        this.modificador = modificador;
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Medico";
    }

    @Override
    public void modificar(Contexto contextoModificador) {
        //if (contextoModificador.getSeccionJugador().cartasRestantes("Descarte") == 0) throw new PilaDescarteNula();

    }


}
