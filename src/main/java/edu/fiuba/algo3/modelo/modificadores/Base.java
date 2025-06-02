package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.Modificador;
import edu.fiuba.algo3.modelo.secciones.Seccion;

public class Base implements Modificador {

    public Base(){
    }

    @Override
    public String mostrarModificadores() {
        return "";
    }

    @Override
    public void modificarComportamiento(Carta carta) {

    }

    @Override
    public void modificarComportamientoSeccion(Seccion seccion) {

    }
}
