package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.mocks.ConstructorDeMazoMock;
import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;
import edu.fiuba.algo3.modelo.cartas.especiales.EscarchaMordaz;
import edu.fiuba.algo3.modelo.cartas.especiales.TiempoDespejado;
import edu.fiuba.algo3.mocks.CartaUnidadMock;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class Test08EsPosibleEliminarAfectoDelClimaEnTablero {
    @Test
    public void testEscarchaMordaz() {
        try {
            List<Jugador> jugadores = new ArrayList<>();
            for (var mazo : ConstructorDeMazoMock.crearDosMazosDeUnidades().construirMazos(null)) {
                Jugador jugador = new Jugador("Jugador");
                jugador.agregarMazo(mazo);
                jugadores.add(jugador);
            }
            Jugador jugador1 = jugadores.get(0);
            Jugador jugador2 = jugadores.get(1);
            Juego juego = new Juego(jugador1, jugador2);
            Seccion seccionSimulada1 = new Seccion("CuerpoACuerpo", 0);
            Seccion seccionSimulada2 = new Seccion("CuerpoACuerpo", 1);
            CartaUnidadMock carta1 = new CartaUnidadMock("Aldeano1", java.util.Arrays.asList("Asedio"), 8);
            CartaUnidadMock carta2 = new CartaUnidadMock("Aldeano2", java.util.Arrays.asList("Asedio"), 8);

            CartaUnidadMock carta3 = new CartaUnidadMock("Aldeano3", java.util.Arrays.asList("Asedio"), 8);
            CartaUnidadMock carta4 = new CartaUnidadMock("Aldeano4", java.util.Arrays.asList("Asedio"), 8);

            juego.definirQuienEmpieza(0);

            juego.jugarCarta(carta1, seccionSimulada1);
            juego.jugarCarta(carta2, seccionSimulada1);

            juego.siguienteJugador();

            juego.jugarCarta(carta3, seccionSimulada2);
            juego.jugarCarta(carta4, seccionSimulada2);

            int actualDelJugador1 = juego.puntajeEnSeccion(seccionSimulada1);
            int actualDelJugador2 = juego.puntajeEnSeccion(seccionSimulada2);

            assertEquals(16, actualDelJugador1);
            assertEquals(16, actualDelJugador2);

            EscarchaMordaz cartaClima = new EscarchaMordaz();

            //La juega el jugador 2
            juego.jugarCartaEspecial(cartaClima);

            actualDelJugador1 = juego.puntajeEnSeccion(seccionSimulada1);
            actualDelJugador2 = juego.puntajeEnSeccion(seccionSimulada2);

            assertEquals(2, actualDelJugador1);
            assertEquals(2, actualDelJugador2);

            TiempoDespejado despejadora = new TiempoDespejado();

            juego.jugarCartaEspecial(despejadora);

            actualDelJugador1 = juego.puntajeEnSeccion(seccionSimulada1);
            actualDelJugador2 = juego.puntajeEnSeccion(seccionSimulada2);

            assertEquals(16, actualDelJugador1);
            assertEquals(16, actualDelJugador2);

        } catch (TipoDeSeccionInvalidaError | CartaNoJugable e) {
            fail("Excepción de sección inválida: " + e.getMessage());
        } catch (Exception e) {
            fail("Excepción inesperada: " + e.getMessage());
        }
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
