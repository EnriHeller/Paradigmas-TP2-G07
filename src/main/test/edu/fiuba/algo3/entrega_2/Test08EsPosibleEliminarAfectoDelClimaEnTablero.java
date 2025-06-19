package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.cartas.especiales.CartaNevada;
import edu.fiuba.algo3.modelo.cartas.especiales.DestructoraDeClima;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class Test08EsPosibleEliminarAfectoDelClimaEnTablero {
    @Test
    public void EsPosibleEliminarAfectoDelClimaEnTablero () throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        Tablero tablero = Tablero.getInstancia();
        CartaNevada nevada = new CartaNevada();
        DestructoraDeClima destructoraDeClima = new DestructoraDeClima();
        Contexto contexto = new Contexto(tablero,"",new CartaUnidad(),0,new Jugador("Test"));

        nevada.modificar(contexto);
        assertDoesNotThrow(() -> destructoraDeClima.modificar(contexto));
    }
}
