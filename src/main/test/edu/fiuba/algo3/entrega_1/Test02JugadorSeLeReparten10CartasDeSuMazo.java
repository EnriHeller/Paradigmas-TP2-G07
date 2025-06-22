package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.mocks.CartaUnidadMock;
import edu.fiuba.algo3.mocks.ConstructorDeMazoMock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test02JugadorSeLeReparten10CartasDeSuMazo {

    @Test
    public void jugadorRecibe10CartasInicialesEnSuMano() {
        try {
            List<Mazo> mazos = ConstructorDeMazoMock.crearDosMazosDeUnidades().construirMazos(null);
            Jugador jugador1 = new Jugador("Jugador1");
            Jugador jugador2 = new Jugador("Jugador2");
            jugador1.agregarMazo(mazos.get(0));
            jugador2.agregarMazo(mazos.get(1));
            Juego juego = new Juego(jugador1, jugador2);
            assertDoesNotThrow(() -> juego.darMano(0, 10));
        } catch (TipoDeSeccionInvalidaError | edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos | java.io.IOException | org.json.simple.parser.ParseException e) {
            fail("No se esperaba excepción: " + e.getMessage());
        }
    }
}