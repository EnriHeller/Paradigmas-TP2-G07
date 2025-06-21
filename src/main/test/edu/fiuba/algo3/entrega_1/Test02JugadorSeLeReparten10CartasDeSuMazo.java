package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
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
            cartas.add(new CartaUnidad());
        }
        Mazo mazo = new Mazo(cartas);
        try {
            Juego juego = new Juego("JugadorTest1", "JugadorTest2", mazo, mazo);
            assertDoesNotThrow(() -> juego.darMano(0, 10));
        } catch (TipoDeSeccionInvalidaError | edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos e) {
            fail("No se esperaba excepción: " + e.getMessage());
        }
    }
}