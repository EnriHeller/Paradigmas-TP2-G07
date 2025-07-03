package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.mocks.ConstructorDeMazoMock;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test01JugadorConCartasSuficientes {

    @Test
    public void jugadorTieneCartasSuficientesAlEmpezar() {
        try {
            List<Mazo> mazos = ConstructorDeMazoMock.crearDosMazosDeUnidades().construirMazos(null);
            Jugador jugador1 = new Jugador("Jugador1");
            Jugador jugador2 = new Jugador("Jugador2");
            jugador1.agregarMazo(mazos.get(0));
            jugador2.agregarMazo(mazos.get(1));
            
            // Verificar que cada jugador tiene exactamente 21 cartas en su mazo antes de empezar
            assertEquals(21, jugador1.cartasRestantesEnSeccion("Mazo"), 
                        "El jugador 1 debe tener 21 cartas en su mazo al empezar");
            assertEquals(21, jugador2.cartasRestantesEnSeccion("Mazo"), 
                        "El jugador 2 debe tener 21 cartas en su mazo al empezar");
            
            Juego juego = new Juego(jugador1, jugador2);
            
            // Repartir las cartas explícitamente
            juego.darMano(0, 10);
            juego.darMano(1, 10);
            
            // Verificar que después de repartir las cartas, cada jugador tiene:
            // - 10 cartas en la mano
            // - 11 cartas restantes en el mazo
            // Total: 21 cartas
            assertEquals(10, jugador1.cartasRestantesEnSeccion("Mano"), 
                        "El jugador 1 debe tener 10 cartas en su mano después de iniciar el juego");
            assertEquals(11, jugador1.cartasRestantesEnSeccion("Mazo"), 
                        "El jugador 1 debe tener 11 cartas restantes en su mazo después de iniciar el juego");
            
            assertEquals(10, jugador2.cartasRestantesEnSeccion("Mano"), 
                        "El jugador 2 debe tener 10 cartas en su mano después de iniciar el juego");
            assertEquals(11, jugador2.cartasRestantesEnSeccion("Mazo"), 
                        "El jugador 2 debe tener 11 cartas restantes en su mazo después de iniciar el juego");
            
            // Verificar total de 21 cartas por jugador
            int totalJugador1 = jugador1.cartasRestantesEnSeccion("Mano") + jugador1.cartasRestantesEnSeccion("Mazo");
            int totalJugador2 = jugador2.cartasRestantesEnSeccion("Mano") + jugador2.cartasRestantesEnSeccion("Mazo");
            
            assertEquals(21, totalJugador1, 
                        "El jugador 1 debe tener exactamente 21 cartas en total (mano + mazo)");
            assertEquals(21, totalJugador2, 
                        "El jugador 2 debe tener exactamente 21 cartas en total (mano + mazo)");
            
        } catch (Exception e) {
            fail("No se esperaba una excepción al verificar que los jugadores tienen cartas suficientes: " + e.getMessage());
        } catch (TipoDeSeccionInvalidaError | NoSePuedeCumplirSolicitudDeCartas e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testMetodosBasicosJugador() {
        Jugador jugador = new Jugador("TestJugador");
        assertEquals("TestJugador", jugador.getNombre());
        jugador.setOrdenDeJuego(2);
        assertEquals(2, jugador.ordenDeJuego());
        
        // Test agregarCartaAMano y cartasEnMano
        edu.fiuba.algo3.mocks.CartaUnidadMock carta = new edu.fiuba.algo3.mocks.CartaUnidadMock();
        jugador.agregarCartaAMano(carta);
        assertTrue(jugador.cartasEnMano().contains(carta));
    }
}