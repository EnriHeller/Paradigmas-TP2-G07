package edu.fiuba.algo3.entrega_4;

import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Test33TableroYSeccionFuncionaCorrectamente {
    @Test
    public void testSeccionMetodosBasicos() throws TipoDeSeccionInvalidaError {
        Seccion seccion = new Seccion("Rango", 0);
        CartaUnidad carta = new CartaUnidad("A", Collections.singletonList("Rango"), 5, new Base());
        seccion.agregarCarta(carta);
        assertTrue(seccion.contiene(carta));
        assertEquals(1, seccion.getCartas().size());
        assertEquals("Rango", seccion.getClave());
        assertEquals(0, seccion.getJugadorId());
        assertEquals(5, seccion.getPuntajeTotal());
        assertEquals(carta, seccion.removerCarta(carta));
        assertEquals(0, seccion.getCartas().size());
        // Test removerCartas
        seccion.agregarCarta(carta);
        assertEquals(1, seccion.removerCartas().size());
    }

    @Test
    public void testTableroMetodosBasicos() throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        Tablero tablero = new Tablero();
        Seccion seccion = new Seccion("Rango", 0);
        CartaUnidad carta = new CartaUnidad("A", Collections.singletonList("Rango"), 5, new Base());
        tablero.agregarCarta(seccion, carta);
        assertTrue(tablero.contiene(seccion, carta));
        assertEquals(5, tablero.getPuntaje("Rango0"));
        assertEquals(5, tablero.PuntajeSeccion(seccion));
        assertEquals(5, tablero.PuntajeTotalSecciones());
        assertEquals(carta, tablero.removerCarta(seccion, carta));
        assertEquals(0, tablero.getCartasSeccion(seccion).size());
    }

    @Test
    public void testSeccionClaveInvalidaLanzaError() {
        assertThrows(TipoDeSeccionInvalidaError.class, () -> new Seccion("Invalida", 0));
    }
}
