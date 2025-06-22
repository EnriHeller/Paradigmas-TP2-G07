package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.mocks.CartaUnidadMock;
import edu.fiuba.algo3.mocks.ConstructorDeMazoMock;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Test05LasCartasPasanALaPilaDeDescarte {
    @Test
    public void pilaDescarteRecibeCartasJugadas() {
        try {
            int cartasJugadasEsperadas = 8;
            List<Jugador> jugadores = new ArrayList<>();
            for (var mazo : ConstructorDeMazoMock.crearDosMazosDeUnidades().construirMazos(null)) {
                Jugador jugador = new Jugador("Jugador");
                jugador.agregarMazo(mazo);
                jugadores.add(jugador);
            }
            Jugador jugador1 = jugadores.get(0);
            Jugador jugador2 = jugadores.get(1);
            Juego juego = new Juego(jugador1, jugador2);
            Seccion seccionSimulada = new Seccion("Rango", 0);
            for (int i = 1; i <= cartasJugadasEsperadas; i++) {
                CartaUnidadMock carta = new CartaUnidadMock("Cualesquiera"+i, java.util.Arrays.asList("Rango"), 8);
                juego.jugarCarta(carta, seccionSimulada);
            }
            juego.finalizarRonda();
            assertEquals(cartasJugadasEsperadas, jugador1.cartasRestantesEnSeccion("Descarte"));
        } catch (TipoDeSeccionInvalidaError e) {
            fail("Excepción de sección inválida: " + e.getMessage());
        } catch (Exception e) {
            fail("Excepción inesperada: " + e.getMessage());
        }
    }
}
