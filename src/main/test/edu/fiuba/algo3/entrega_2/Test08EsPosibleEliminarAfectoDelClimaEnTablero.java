package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.cartas.especiales.CartaNevada;
import edu.fiuba.algo3.modelo.cartas.especiales.DestructoraDeClima;
import edu.fiuba.algo3.mocks.CartaUnidadMock;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class Test08EsPosibleEliminarAfectoDelClimaEnTablero {
    @Test
    public void EsPosibleEliminarAfectoDelClimaEnTablero () throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        Tablero tablero = new Tablero();
        CartaNevada nevada = new CartaNevada();
        DestructoraDeClima destructoraDeClima = new DestructoraDeClima();
        Seccion seccionMock = new Seccion("Rango", 0);
        CartaUnidadMock cartaMock = new CartaUnidadMock();
        Jugador jugadorMock = new Jugador("Mock", null);
        Contexto contexto = new Contexto(tablero, seccionMock, cartaMock, jugadorMock);
        nevada.modificar(contexto);
        assertDoesNotThrow(() -> destructoraDeClima.modificar(contexto));
    }
}
