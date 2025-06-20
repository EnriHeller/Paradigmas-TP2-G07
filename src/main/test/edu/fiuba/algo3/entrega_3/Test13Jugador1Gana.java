package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.Errores.*;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class Test13Jugador1Gana {

    @Test
    public void Test13Jugador1Gana() throws Exception {
        Base base = new Base();
        ArrayList<Carta> cartasDelMazo = new ArrayList<>();
        ArrayList<String> secciones = new ArrayList<>();
        secciones.add("Rango");
        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("Cualesquiera", secciones, 8, base);
            cartasDelMazo.add(carta);
        }

        Jugador jugador1 = new Jugador("JugadorTest1");
        Jugador jugador2 = new Jugador("JugadorTest2");
        Mazo mazo1 = new Mazo(cartasDelMazo);
        Mazo mazo2 = new Mazo(cartasDelMazo);
        assertDoesNotThrow(() -> jugador1.agregarMazo(mazo1));
        assertDoesNotThrow(() -> jugador2.agregarMazo(mazo2));
        final Juego juego = assertDoesNotThrow(() -> new Juego(jugador1, jugador2));
        assertDoesNotThrow(() -> juego.repartirManoInicial(jugador1));
        assertDoesNotThrow(() -> juego.repartirManoInicial(jugador2));
        // Ronda 1
        assertDoesNotThrow(() -> juego.jugarCarta(new CartaUnidad("Cualesquiera", secciones, 8, base), buscarSeccion(juego, "Rango")));
        assertDoesNotThrow(() -> juego.jugarCarta(new CartaUnidad("Cualesquiera", secciones, 8, base), buscarSeccion(juego, "Rango")));
        juego.siguienteJugador();
        assertDoesNotThrow(() -> juego.jugarCarta(new CartaUnidad("Cualesquiera", secciones, 8, base), buscarSeccion(juego, "Rango")));
        juego.finalizarRonda();
        // Ronda 2
        juego.siguienteJugador();
        for (int i = 0; i < 7; i++) {
            assertDoesNotThrow(() -> juego.jugarCarta(new CartaUnidad("Cualesquiera", secciones, 8, base), buscarSeccion(juego, "Rango")));
        }
        juego.siguienteJugador();
        assertDoesNotThrow(() -> juego.jugarCarta(new CartaUnidad("Cualesquiera", secciones, 8, base), buscarSeccion(juego, "Rango")));
        juego.finalizarRonda();
        // Ronda 3
        assertDoesNotThrow(() -> juego.jugarCarta(new CartaUnidad("Cualesquiera", secciones, 8, base), buscarSeccion(juego, "Rango")));
        assertDoesNotThrow(() -> juego.jugarCarta(new CartaUnidad("Cualesquiera", secciones, 8, base), buscarSeccion(juego, "Rango")));
        juego.siguienteJugador();
        assertDoesNotThrow(() -> juego.jugarCarta(new CartaUnidad("Cualesquiera", secciones, 8, base), buscarSeccion(juego, "Rango")));
        juego.finalizarRonda();
        assertEquals("JugadorTest1", juego.mostrarGanador(), "Gano el jugador incorrecto.\nSe esperaba: JugadorTest1 y se obtuvo " + juego.mostrarGanador());
    }

    private Seccion buscarSeccion(Juego juego, String nombreSeccion) throws Exception {
        for (Seccion seccion : juego.mostrarTableroActual()) {
            if (seccion.getNombre().equals(nombreSeccion)) {
                return seccion;
            }
        }
        throw new RuntimeException("No se encontró la sección: " + nombreSeccion);
    }
}
