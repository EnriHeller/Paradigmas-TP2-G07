package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.secciones.Seccion;

public interface Modificador {

    public String mostrarModificadores();

    public void modificarComportamiento(Carta carta);

    public void modificarComportamientoSeccion(Seccion seccion);

}
