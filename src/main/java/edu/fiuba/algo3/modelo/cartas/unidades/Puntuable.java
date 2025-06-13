package edu.fiuba.algo3.modelo.cartas.unidades;

import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

import java.util.List;

public interface Puntuable {

    int ValorActual();

    String getNombre();

    List<String> getSecciones();
}
