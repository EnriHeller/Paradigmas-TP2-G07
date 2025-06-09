package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;

public interface Modificador {

    /*
     *
      Fragmentar Modificador por interfaz unica
     *
     *
     *
     * medico hace lo suyo
     *
     * legendaria, agil y espia modifican UNA Carta
     *
     * Unidas y suma valor base modicican un listado de Cartas
     *
     */

    String mostrarModificadores();

<<<<<<< HEAD
    void modificar();
=======
    public void modificar(Contexto modificadorContexto) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima;
>>>>>>> aMoyano

}
