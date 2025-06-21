package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.mocks.CartaUnidadMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test02JugadorSeLeReparten10CartasDeSuMazo {

    @Test
    public void jugadorRecibe10CartasInicialesEnSuMano() {
        // Crear 21 Cartas
        List<Carta> cartas = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            cartas.add(new CartaUnidadMock());
        }
        Mazo mazo = new Mazo(cartas);
        Jugador jugador1 = new Jugador("Jugador1", mazo);
        Jugador jugador2 = new Jugador("Jugador2", mazo);
        try {
            Juego juego = new Juego(jugador1, jugador2);
            assertDoesNotThrow(() -> juego.darMano(0, 10));
        } catch (TipoDeSeccionInvalidaError | edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos e) {
            fail("No se esperaba excepción: " + e.getMessage());
        }
    }
}