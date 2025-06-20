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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class Test08EsPosibleEliminarAfectoDelClimaEnTablero {
    @Test
    public void EsPosibleEliminarAfectoDelClimaEnTablero () throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        Jugador j1 = new Jugador("Test1");
        Jugador j2 = new Jugador("Test2");
        Tablero tablero = new Tablero(j1, j2);
        ArrayList<String> secciones = new ArrayList<>();
        secciones.add("Cuerpo a Cuerpo");
        CartaNevada nevada = new CartaNevada("Nevada", "Reduce el valor de las cartas a 1", secciones);
        DestructoraDeClima destructoraDeClima = new DestructoraDeClima();

        // Buscar la sección "Cuerpo a Cuerpo" en el tablero
        edu.fiuba.algo3.modelo.secciones.tablero.Seccion seccionCuerpo = null;
        for (edu.fiuba.algo3.modelo.secciones.tablero.Seccion s : tablero.mostrarTableroParaJugador(j1)) {
            if (s.getNombre().equals("Cuerpo a Cuerpo")) {
                seccionCuerpo = s;
                break;
            }
        }
        Contexto contexto = new Contexto(tablero, seccionCuerpo, new CartaUnidad("CartaTest", secciones, 3), j1);

        nevada.modificar(contexto);
        assertDoesNotThrow(() -> destructoraDeClima.modificar(contexto));
    }
}
