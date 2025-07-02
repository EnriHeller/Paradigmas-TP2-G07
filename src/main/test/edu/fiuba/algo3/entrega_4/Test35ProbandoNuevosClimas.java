package edu.fiuba.algo3.entrega_4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.mocks.CartaUnidadMock;
import edu.fiuba.algo3.mocks.ConstructorDeMazoMock;
import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;
import edu.fiuba.algo3.modelo.cartas.especiales.LluviaTorrencial;
import edu.fiuba.algo3.modelo.cartas.especiales.TormentaDeSkellige;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;

public class Test35ProbandoNuevosClimas {

    @Test
    public void testLluviaTorrencial() {
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
            Seccion seccionSimulada1 = new Seccion("Asedio", 0);
            Seccion seccionSimulada2 = new Seccion("Asedio", 1);
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

            LluviaTorrencial cartaClima = new LluviaTorrencial();

            //La juega el jugador 2
            juego.jugarCartaEspecial(cartaClima);

            actualDelJugador1 = juego.puntajeEnSeccion(seccionSimulada1);
            actualDelJugador2 = juego.puntajeEnSeccion(seccionSimulada2);

            assertEquals(2, actualDelJugador1);
            assertEquals(2, actualDelJugador2);

        } catch (TipoDeSeccionInvalidaError | CartaNoJugable e) {
            fail("Excepción de sección inválida: " + e.getMessage());
        } catch (Exception e) {
            fail("Excepción inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testTormentaDESkellige() {
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
            Seccion seccionSimulada1_1 = new Seccion("Rango", 0);
            Seccion seccionSimulada1_2 = new Seccion("CuerpoACuerpo", 0);
            Seccion seccionSimulada2_1 = new Seccion("Rango", 1);
            Seccion seccionSimulada2_2 = new Seccion("CuerpoACuerpo", 1);

            CartaUnidadMock carta1 = new CartaUnidadMock("Aldeano1", java.util.Arrays.asList("Rango"), 8);
            CartaUnidadMock carta2 = new CartaUnidadMock("Aldeano2", java.util.Arrays.asList("CuerpoACuerpo"), 8);

            CartaUnidadMock carta3 = new CartaUnidadMock("Aldeano3", java.util.Arrays.asList("Rango"), 8);
            CartaUnidadMock carta4 = new CartaUnidadMock("Aldeano4", java.util.Arrays.asList("CuerpoACuerpo"), 8);

            juego.definirQuienEmpieza(1);

            juego.jugarCarta(carta1, seccionSimulada1_1);
            juego.jugarCarta(carta2, seccionSimulada1_2);

            juego.siguienteJugador();

            juego.jugarCarta(carta3, seccionSimulada2_1);
            juego.jugarCarta(carta4, seccionSimulada2_2);

            int actualDelJugador1 = juego.puntajeEnSeccion(seccionSimulada1_1) + juego.puntajeEnSeccion(seccionSimulada1_2);
            int actualDelJugador2 = juego.puntajeEnSeccion(seccionSimulada2_1) + juego.puntajeEnSeccion(seccionSimulada2_2);

            assertEquals(16, actualDelJugador1);
            assertEquals(16, actualDelJugador2);

            TormentaDeSkellige cartaClima = new TormentaDeSkellige();

            //La juega el jugador 1
            juego.jugarCartaEspecial(cartaClima);

            actualDelJugador1 = juego.puntajeEnSeccion(seccionSimulada1_1) + juego.puntajeEnSeccion(seccionSimulada1_2);
            actualDelJugador2 = juego.puntajeEnSeccion(seccionSimulada2_1) + juego.puntajeEnSeccion(seccionSimulada2_2);

            assertEquals(2, actualDelJugador1);
            assertEquals(2, actualDelJugador2);

        } catch (TipoDeSeccionInvalidaError | CartaNoJugable e) {
            fail("Excepción de sección inválida: " + e.getMessage());
        } catch (Exception e) {
            fail("Excepción inesperada: " + e.getMessage());
        }
    }
}
