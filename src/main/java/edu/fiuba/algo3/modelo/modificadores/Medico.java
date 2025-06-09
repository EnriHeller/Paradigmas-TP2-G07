package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesSinPuntaje;

public class Medico implements Modificador {

    private Modificador modificador;

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Medico";
    }

    @Override
    public void modificar(Contexto contextoModificador) {
        if (contextoModificador.getSinPuntaje().cartasRestantes("Descarte") == 0) throw new PilaDescarteNula();

    }


}
