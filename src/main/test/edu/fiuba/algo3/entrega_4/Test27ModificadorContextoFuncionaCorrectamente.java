package edu.fiuba.algo3.entrega_4;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.cartas.unidades.NoEsLaMismaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.principal.ModificadorContexto;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test27ModificadorContextoFuncionaCorrectamente {


    @Test
    public void testModificadorContextoFunciona() throws Exception {
        // Tablero requiere TipoDeSeccionInvalidaError
        Tablero tablero = null;
        try {
            tablero = new Tablero();
        } catch (TipoDeSeccionInvalidaError e) {
            throw new RuntimeException("No se pudo crear el tablero para el test", e);
        }
        String seccion = "CuerpoACuerpo";
        CartaUnidad carta = new CartaUnidad("A", Collections.singletonList(seccion), 8, new Unidas(new Base()));
        Jugador jugador = new Jugador("JugadorTest");
        ModificadorContexto contexto = new ModificadorContexto(tablero, seccion, carta, Collections.singletonList(carta), jugador);
        assertEquals(tablero, contexto.getTablero());
        assertEquals(seccion, contexto.getSeccion());
        assertEquals(carta, contexto.getCarta());
        assertEquals(jugador, contexto.getJugador());
        assertEquals(1, contexto.getCartasDeLaSeccion().size());
        assertEquals(carta, contexto.getCartasDeLaSeccion().get(0));
    }
}
