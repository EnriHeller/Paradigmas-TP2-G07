package edu.fiuba.algo3.entrega_4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Medico;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;

public class Test21MedicoPruebaMetodos {
    @Test
    public void testMedicoCubreMetodosConFlujoReal() throws NoSePuedeCumplirSolicitudDeCartas, edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos, TipoDeSeccionInvalidaError {
        Medico medico = new Medico(new Base());
        Base base = new Base();
        List<String> secciones = new ArrayList<>();
        secciones.add("CuerpoACuerpo");
        List<CartaUnidad> cartas = new ArrayList<>();
        cartas.add(new CartaUnidad("Base'e", secciones, 8, base));
        for (int i = 0; i < 19; i++) {
            cartas.add(new CartaUnidad("Medico'e", secciones, 8, medico));
        }
        cartas.add(new CartaUnidad("Base'e2", secciones, 8, base));
        Mazo mazoMock1 = new Mazo(new ArrayList<>(cartas));
        Mazo mazoMock2 = new Mazo(new ArrayList<>(cartas));
        Jugador jugador1 = new Jugador("JugadorTest1");
        jugador1.agregarMazo(mazoMock1);
        Jugador jugador2 = new Jugador("JugadorTest2");
        jugador2.agregarMazo(mazoMock2);
        Juego juego = new Juego(jugador1, jugador2);
        jugador1.agregarCartasAMano(10);
        // Asegurar que la mano tenga la carta base y la de médico
        jugador1.cartasEnMano().clear();
        CartaUnidad cartaBase = new CartaUnidad("Base'e", secciones, 8, base);
        CartaUnidad cartaMedico = new CartaUnidad("Medico'e", secciones, 8, medico);
        jugador1.cartasEnMano().add(cartaBase);
        jugador1.cartasEnMano().add(cartaMedico);
        assertDoesNotThrow(() -> {
            try {
                juego.jugarCarta(cartaBase, new Seccion("CuerpoACuerpo", 0));
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        });
        juego.finalizarRonda();
        juego.definirQuienEmpieza(0);
        // No hace falta agregar más cartas, ya está la de médico
        assertDoesNotThrow(() -> {
            try {
                juego.jugarCarta(cartaMedico, new Seccion("CuerpoACuerpo", 0));
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        });
    }
}
