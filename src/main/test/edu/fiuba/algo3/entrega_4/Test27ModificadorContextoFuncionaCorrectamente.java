package edu.fiuba.algo3.entrega_4;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test27ModificadorContextoFuncionaCorrectamente {


    @Test
    public void testModificadorContextoFunciona() throws Exception, TipoDeSeccionInvalidaError {
        // Tablero requiere TipoDeSeccionInvalidaError
        Tablero tablero = null;
        try {
            tablero = new Tablero();
        } catch (TipoDeSeccionInvalidaError e) {
            throw new RuntimeException("No se pudo crear el tablero para el test", e);
        }
        Seccion seccion = new Seccion("CuerpoACuerpo", 0);
        CartaUnidad carta = new CartaUnidad("A", List.of("CuerpoACuerpo"), 8, new Unidas(new Base()));
        Jugador jugador = new Jugador("JugadorTest");

        seccion.agregarCarta(carta);

        Contexto contexto = new Contexto(tablero, seccion, carta, jugador);
        assertEquals(tablero, contexto.getTablero());
        assertEquals(seccion, contexto.getSeccion());
        assertEquals(carta, contexto.getCarta());
        assertEquals(jugador, contexto.getJugador());
        assertEquals(1, contexto.getSeccion().getCartas().size());
        assertEquals(carta, contexto.getSeccion().getCartas().get(0));
    }
}
