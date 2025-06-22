package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.mocks.CartaUnidadMock;
import edu.fiuba.algo3.mocks.CartaEspecialMock;
import edu.fiuba.algo3.mocks.ConstructorDeMazoMock;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.cartas.especiales.CartaNevada;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.ArrayList;

public class Test07CartaEspecialDeClimaModificaValorDeLasCartasEnSeccionCorrespondiente {
    @Test
    public void cartaEspecialDeClimaModificaValorDeLasCartasEnSeccionCorrespondiente() {
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
            Seccion seccionSimulada = new Seccion("CuerpoACuerpo", 0);
            CartaUnidadMock carta1 = new CartaUnidadMock("Aldeano", java.util.Arrays.asList("CuerpoACuerpo"), 8);
            CartaUnidadMock carta2 = new CartaUnidadMock("Aldeano", java.util.Arrays.asList("CuerpoACuerpo"), 8);
            juego.jugarCarta(carta1, seccionSimulada);
            juego.jugarCarta(carta2, seccionSimulada);
            int actual = juego.puntajeEnSeccion(seccionSimulada);
            assertEquals(16, actual);
            CartaNevada cartaClima = new CartaNevada();
            juego.jugarCarta(cartaClima, seccionSimulada);
            actual = juego.puntajeEnSeccion(seccionSimulada);
            assertEquals(2, actual);
        } catch (TipoDeSeccionInvalidaError e) {
            fail("Excepción de sección inválida: " + e.getMessage());
        } catch (Exception e) {
            fail("Excepción inesperada: " + e.getMessage());
        }
    }
}