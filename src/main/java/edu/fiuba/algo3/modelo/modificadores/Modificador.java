package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeEliminarClimaSiNoHayClima;

public interface Modificador {
    String mostrarModificadores();

    default void prepararContexto(Contexto contexto) {}

    void modificar(Contexto modificadorContexto) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima;
}
