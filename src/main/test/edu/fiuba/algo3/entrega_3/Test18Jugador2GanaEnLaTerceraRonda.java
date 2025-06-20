package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.Errores.*;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.principal.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test18Jugador2GanaEnLaTerceraRonda {

    @Test
    public void Test18Jugador2GanaEnLaTerceraRonda() {
        Base base = new Base();
        ArrayList<Carta> cartasDelMazo = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            ArrayList<String> seccionesCarta = new ArrayList<>();
            seccionesCarta.add("Rango");
            CartaUnidad carta = new CartaUnidad("Cualesquiera", seccionesCarta, 8, base);
            cartasDelMazo.add(carta);
        }

        Jugador j1 = new Jugador("JugadorTest1");
        Jugador j2 = new Jugador("JugadorTest2");
        Mazo m1 = new Mazo(cartasDelMazo);
        Mazo m2 = new Mazo(cartasDelMazo);
        try {
            j1.agregarMazo(m1);
            j2.agregarMazo(m2);
        } catch (NoSePuedeCumplirSolicitudDeCartas e) {
            fail("No se pudo agregar el mazo: " + e.getMessage());
        }
        Juego juego;
        try {
            juego = new Juego(j1, j2);
        } catch (TipoDeSeccionInvalidaError | UnoDeLosMazosNoCumpleRequitos e) {
            fail("No se pudo crear el juego: " + e.getMessage());
            return;
        }
        try {
            juego.repartirManoInicial(j1);
            juego.repartirManoInicial(j2);
        } catch (TipoDeSeccionInvalidaError | NoSePuedeCumplirSolicitudDeCartas e) {
            fail("No se pudo repartir la mano inicial: " + e.getMessage());
        }
        juego.tirarMoneda();

        // Ronda 1
        for (int i = 0; i < 2; i++) {
            List<Carta> manoActual = juego.mostrarManoActual();
            List<edu.fiuba.algo3.modelo.secciones.tablero.Seccion> tableroActual = juego.mostrarTableroActual();
            CartaUnidad cartaElegida = (CartaUnidad) manoActual.get(0);
            edu.fiuba.algo3.modelo.secciones.tablero.Seccion seccionRango = null;
            for (edu.fiuba.algo3.modelo.secciones.tablero.Seccion s : tableroActual) {
                if (s.getNombre().equals("Rango")) {
                    seccionRango = s;
                    break;
                }
            }
            assertNotNull(seccionRango, "No se encontró la sección Rango en el tablero");
            try {
                juego.jugarCarta(cartaElegida, seccionRango);
            } catch (TipoDeSeccionInvalidaError | CartaNoJugable e) {
                fail("No se pudo jugar la carta: " + e.getMessage());
            }
            juego.siguienteJugador();
        }
        List<Carta> manoActual = juego.mostrarManoActual();
        List<edu.fiuba.algo3.modelo.secciones.tablero.Seccion> tableroActual = juego.mostrarTableroActual();
        CartaUnidad cartaElegida = (CartaUnidad) manoActual.get(0);
        edu.fiuba.algo3.modelo.secciones.tablero.Seccion seccionRango = null;
        for (edu.fiuba.algo3.modelo.secciones.tablero.Seccion s : tableroActual) {
            if (s.getNombre().equals("Rango")) {
                seccionRango = s;
                break;
            }
        }
        assertNotNull(seccionRango, "No se encontró la sección Rango en el tablero");
        try {
            juego.jugarCarta(cartaElegida, seccionRango);
        } catch (TipoDeSeccionInvalidaError | CartaNoJugable e) {
            fail("No se pudo jugar la carta: " + e.getMessage());
        }
        juego.siguienteJugador();
        juego.finalizarRonda();

        assertFalse(juego.juegoTerminado());

        // Ronda 2
        for (int i = 0; i < 2; i++) {
            manoActual = juego.mostrarManoActual();
            tableroActual = juego.mostrarTableroActual();
            cartaElegida = (CartaUnidad) manoActual.get(0);
            seccionRango = null;
            for (edu.fiuba.algo3.modelo.secciones.tablero.Seccion s : tableroActual) {
                if (s.getNombre().equals("Rango")) {
                    seccionRango = s;
                    break;
                }
            }
            assertNotNull(seccionRango, "No se encontró la sección Rango en el tablero");
            try {
                juego.jugarCarta(cartaElegida, seccionRango);
            } catch (TipoDeSeccionInvalidaError | CartaNoJugable e) {
                fail("No se pudo jugar la carta: " + e.getMessage());
            }
            juego.siguienteJugador();
        }
        manoActual = juego.mostrarManoActual();
        tableroActual = juego.mostrarTableroActual();
        cartaElegida = (CartaUnidad) manoActual.get(0);
        seccionRango = null;
        for (edu.fiuba.algo3.modelo.secciones.tablero.Seccion s : tableroActual) {
            if (s.getNombre().equals("Rango")) {
                seccionRango = s;
                break;
            }
        }
        assertNotNull(seccionRango, "No se encontró la sección Rango en el tablero");
        try {
            juego.jugarCarta(cartaElegida, seccionRango);
        } catch (TipoDeSeccionInvalidaError | CartaNoJugable e) {
            fail("No se pudo jugar la carta: " + e.getMessage());
        }
        juego.siguienteJugador();
        juego.finalizarRonda();

        assertFalse(juego.juegoTerminado());

        // Ronda 3
        for (int i = 0; i < 3; i++) {
            manoActual = juego.mostrarManoActual();
            tableroActual = juego.mostrarTableroActual();
            cartaElegida = (CartaUnidad) manoActual.get(0);
            seccionRango = null;
            for (edu.fiuba.algo3.modelo.secciones.tablero.Seccion s : tableroActual) {
                if (s.getNombre().equals("Rango")) {
                    seccionRango = s;
                    break;
                }
            }
            assertNotNull(seccionRango, "No se encontró la sección Rango en el tablero");
            try {
                juego.jugarCarta(cartaElegida, seccionRango);
            } catch (TipoDeSeccionInvalidaError | CartaNoJugable e) {
                fail("No se pudo jugar la carta: " + e.getMessage());
            }
            juego.siguienteJugador();
        }
        manoActual = juego.mostrarManoActual();
        tableroActual = juego.mostrarTableroActual();
        cartaElegida = (CartaUnidad) manoActual.get(0);
        seccionRango = null;
        for (edu.fiuba.algo3.modelo.secciones.tablero.Seccion s : tableroActual) {
            if (s.getNombre().equals("Rango")) {
                seccionRango = s;
                break;
            }
        }
        assertNotNull(seccionRango, "No se encontró la sección Rango en el tablero");
        try {
            juego.jugarCarta(cartaElegida, seccionRango);
        } catch (TipoDeSeccionInvalidaError | CartaNoJugable e) {
            fail("No se pudo jugar la carta: " + e.getMessage());
        }
        juego.siguienteJugador();
        juego.finalizarRonda();

        assertTrue(juego.juegoTerminado());
        assertEquals("JugadorTest2", juego.mostrarGanador(), "Gano el jugador incorrecto.\nSe esperaba: " + "JugadorTest2" + " y se obtuvo " + juego.mostrarGanador());
    }
}
