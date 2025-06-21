package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.mocks.CartaUnidadMock;
import edu.fiuba.algo3.mocks.ConstructorDeMazoMock;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolcitudDeCartas;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class Test03JugadorPuedeColocarCartaEnSeccionDelTablero {
    // Verificar que un jugador pueda colocar una carta en una sección del tablero

    @Test
    public void jugadorPuedeColocarCartaEnSeccion() throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolcitudDeCartas, UnoDeLosMazosNoCumpleRequitos, IOException, ParseException {
        Seccion seccionSimulada = new Seccion("Rango", 0);
        CartaUnidadMock cartaUnidad = new CartaUnidadMock("CartaTest", java.util.Arrays.asList("Rango"), 8);
        List<Jugador> jugadores = new ArrayList<>();
        for (var mazo : ConstructorDeMazoMock.crearDosMazosDeUnidades().construirMazos("")) {
            jugadores.add(new Jugador("Jugador", mazo));
        }
        Jugador jugador1 = jugadores.get(0);
        Jugador jugador2 = jugadores.get(1);
        Juego juego = new Juego(jugador1, jugador2);
        juego.darMano(0, 10);
        assertDoesNotThrow(() -> juego.jugarCarta(cartaUnidad, seccionSimulada));
    }
}
