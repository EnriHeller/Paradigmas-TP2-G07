package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.cartas.especiales.EscarchaMordaz;
import edu.fiuba.algo3.modelo.cartas.especiales.TiempoDespejado;
import edu.fiuba.algo3.mocks.CartaUnidadMock;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class Test08EsPosibleEliminarAfectoDelClimaEnTablero {
    @Test
    public void EsPosibleEliminarAfectoDelClimaEnTablero () throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        Tablero tablero = new Tablero();
        EscarchaMordaz nevada = new EscarchaMordaz();
        TiempoDespejado tiempoDespejado = new TiempoDespejado();
        Seccion seccionMock = new Seccion("Rango", 0);
        CartaUnidadMock cartaMock = new CartaUnidadMock();
        Jugador jugadorMock = new Jugador("Mock");
        Contexto contexto = new Contexto(tablero, seccionMock, cartaMock, jugadorMock);
        nevada.modificar(contexto);
        assertDoesNotThrow(() -> tiempoDespejado.modificar(contexto));
    }

    @Test
    public void testDestructoraDeClimaMetodosBasicos() throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        TiempoDespejado destructora = new TiempoDespejado();
        // esEspecial
        org.junit.jupiter.api.Assertions.assertTrue(destructora.esEspecial());
        // mostrarCarta
        org.junit.jupiter.api.Assertions.assertEquals("tiempoDespejado", destructora.mostrarCarta());
        // mostrarModificadores
        org.junit.jupiter.api.Assertions.assertEquals("tiempoDespejado", destructora.mostrarModificadores());
        // aplicarModificador (ya cubierto indirectamente, pero lo forzamos directo)
        Tablero tablero = new Tablero();
        Seccion seccion = new Seccion("Rango", 0);
        CartaUnidadMock cartaMock = new CartaUnidadMock();
        Jugador jugadorMock = new Jugador("Mock");
        Contexto contexto = new Contexto(tablero, seccion, cartaMock, jugadorMock);
        // Primero agregamos clima para que no lance excepción
        new EscarchaMordaz().modificar(contexto);
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> destructora.aplicarModificador(contexto));
    }
}
