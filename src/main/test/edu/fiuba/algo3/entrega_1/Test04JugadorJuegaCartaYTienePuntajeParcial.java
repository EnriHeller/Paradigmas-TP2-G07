package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.mocks.CartaUnidadMock;
import edu.fiuba.algo3.mocks.ConstructorDeMazoMock;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;

public class Test04JugadorJuegaCartaYTienePuntajeParcial {
    @Test
    public void jugadorJuegaCartaYTienePuntajeParcial() {
        try {
            List<Mazo> mazos = ConstructorDeMazoMock.crearDosMazosDeUnidades().construirMazos(null);
            Jugador jugador1 = new Jugador("Jugador1");
            Jugador jugador2 = new Jugador("Jugador2");
            jugador1.agregarMazo(mazos.get(0));
            jugador2.agregarMazo(mazos.get(1));
            Juego juego = new Juego(jugador1, jugador2);
            Seccion seccionSimulada = new Seccion("Rango", 0);
            CartaUnidadMock carta = new CartaUnidadMock("Cualesquiera", java.util.Arrays.asList("Rango"), 8);
            juego.jugarCarta(carta, seccionSimulada);
            assertEquals(8, juego.puntajeEnSeccion(seccionSimulada));
        } catch (TipoDeSeccionInvalidaError | CartaNoJugable e) {
            fail("Excepción de sección inválida: " + e.getMessage());
        } catch (Exception e) {
            fail("Excepción inesperada: " + e.getMessage());
        }
    }
}
