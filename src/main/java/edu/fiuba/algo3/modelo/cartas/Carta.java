package edu.fiuba.algo3.modelo.cartas;

import edu.fiuba.algo3.modelo.principal.Contexto;

public interface Carta {

    boolean esEspecial();

    String mostrarCarta();

    void aplicarModificador(Contexto contexto);

    default void retrotraerModificacion(Contexto contexto){}

}
