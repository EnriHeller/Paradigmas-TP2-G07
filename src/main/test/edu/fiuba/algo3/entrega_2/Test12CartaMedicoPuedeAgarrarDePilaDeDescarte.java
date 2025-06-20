package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Espias;
import edu.fiuba.algo3.modelo.modificadores.Medico;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.Errores.*;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test12CartaMedicoPuedeAgarrarDePilaDeDescarte {

    @Test
    public void Test12CartaMedicoPuedeAgarrarDePilaDeDescarte() throws Exception {
        Medico medico = new Medico(new Base());
        ArrayList<Carta> cartasDelMazo = new ArrayList<>();
        ArrayList<String> secciones = new ArrayList<>();
        secciones.add("Rango");

        // Crear jugadores y mazos
        Jugador jugador1 = new Jugador("JugadorTest1");
        Jugador jugador2 = new Jugador("JugadorTest2");
        Mazo mazo1 = new Mazo(cartasDelMazo);
        Mazo mazo2 = new Mazo(cartasDelMazo);
        try {
            jugador1.agregarMazo(mazo1);
            jugador2.agregarMazo(mazo2);
        } catch (NoSePuedeCumplirSolicitudDeCartas e) {
            throw new RuntimeException("No se pudo agregar el mazo: " + e.getMessage(), e);
        }
        Juego juego;
        try {
            juego = new Juego(jugador1, jugador2);
        } catch (TipoDeSeccionInvalidaError e) {
            throw new RuntimeException("No se pudo crear el juego: " + e.getMessage(), e);
        }
        try {
            juego.repartirManoInicial(jugador1);
            juego.repartirManoInicial(jugador2);
        } catch (TipoDeSeccionInvalidaError | NoSePuedeCumplirSolicitudDeCartas e) {
            throw new RuntimeException("No se pudo repartir la mano inicial: " + e.getMessage(), e);
        }

        // Forzar algunas cartas en el descarte del jugador1
        jugador1.DescartarCarta(new CartaUnidad("Aldeano1", secciones, 8, new Base()));
        jugador1.DescartarCarta(new CartaUnidad("Aldeano2", secciones, 8, new Base()));
        jugador1.DescartarCarta(new CartaUnidad("Aldeano3", secciones, 8, new Base()));

        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("LaPeorCarta", secciones, 8, medico);
            cartasDelMazo.add(carta);
        }

        // Buscar la sección "Rango" para jugar la carta médico
        final Seccion seccionRango;
        List<Seccion> seccionesTableroJugadorActual = juego.mostrarTableroActual();
        {
            Seccion seccionEncontrada = null;
            for (Seccion seccion : seccionesTableroJugadorActual) {
                if (seccion.getNombre().equals("Rango")) {
                    seccionEncontrada = seccion;
                    break;
                }
            }
            seccionRango = seccionEncontrada;
        }
        assertDoesNotThrow(() -> juego.jugarCarta(new CartaUnidad("LaPeorCarta", secciones, 8, medico), seccionRango));
    }
}
