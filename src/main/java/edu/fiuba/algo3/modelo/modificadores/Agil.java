package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.Modificador;
import edu.fiuba.algo3.modelo.secciones.Seccion;

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
    public void modificarComportamiento(Carta carta) {

    }

    @Override
    public void modificarComportamientoSeccion(Seccion seccion) {

    }
}

