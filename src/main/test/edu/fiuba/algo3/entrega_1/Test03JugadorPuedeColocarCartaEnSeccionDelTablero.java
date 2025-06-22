package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.mocks.CartaUnidadMock;
import edu.fiuba.algo3.mocks.ConstructorDeMazoMock;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import java.util.List;

public class Test03JugadorPuedeColocarCartaEnSeccionDelTablero {
    // Verificar que un jugador pueda colocar una carta en una sección del tablero

    @Test
    public void jugadorPuedeColocarCartaEnSeccion() throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolicitudDeCartas, UnoDeLosMazosNoCumpleRequitos, IOException, ParseException {
        Seccion seccionSimulada = new Seccion("Rango", 0);
        CartaUnidadMock cartaUnidad = new CartaUnidadMock("CartaTest", java.util.Arrays.asList("Rango"), 8);
        List<Mazo> mazos = ConstructorDeMazoMock.crearDosMazosDeUnidades().construirMazos(null);
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");
        jugador1.agregarMazo(mazos.get(0));
        jugador2.agregarMazo(mazos.get(1));
        Juego juego = new Juego(jugador1, jugador2);
        juego.darMano(0, 10);
        assertDoesNotThrow(() -> juego.jugarCarta(cartaUnidad, seccionSimulada));
    }
}
