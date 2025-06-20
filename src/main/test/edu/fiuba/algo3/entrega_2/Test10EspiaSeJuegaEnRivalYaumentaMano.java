package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.especiales.CartaNevada;
import edu.fiuba.algo3.modelo.cartas.especiales.DestructoraDeClima;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Espias;
import edu.fiuba.algo3.modelo.principal.*;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.Errores.CartaNoJugable;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test10EspiaSeJuegaEnRivalYaumentaMano {

    @Test
    public void Test10EspiaSeJuegaEnRivalYaumentaMano() throws Exception {
        Espias spyBlack = new Espias(new Base());
        ArrayList<Carta> cartasDelMazo = new ArrayList<>();
        ArrayList<String> secciones = new ArrayList<>();
        secciones.add("Rango");
        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("SpyBlack", secciones, 8, spyBlack);
            cartasDelMazo.add(carta);
        }

        // Crear jugadores y mazos
        Jugador j1 = new Jugador("JugadorTest1");
        Jugador j2 = new Jugador("JugadorTest2");
        Mazo m1 = new Mazo(cartasDelMazo);
        Mazo m2 = new Mazo(cartasDelMazo);
        try {
            j1.agregarMazo(m1);
            j2.agregarMazo(m2);
        } catch (NoSePuedeCumplirSolicitudDeCartas e) {
            throw new RuntimeException("No se pudo agregar el mazo: " + e.getMessage(), e);
        }
        Juego juego;
        try {
            juego = new Juego(j1, j2);
        } catch (TipoDeSeccionInvalidaError e) {
            throw new RuntimeException("No se pudo crear el juego: " + e.getMessage(), e);
        }
        try {
            juego.repartirManoInicial(j1);
            juego.repartirManoInicial(j2);
        } catch (TipoDeSeccionInvalidaError | NoSePuedeCumplirSolicitudDeCartas e) {
            throw new RuntimeException("No se pudo repartir la mano inicial: " + e.getMessage(), e);
        }

        // Buscar la sección "Rango" del rival (j2)
        List<Seccion> tableroJ2 = juego.mostrarTableroActual();
        Seccion seccionRango = null;
        for (Seccion s : tableroJ2) {
            if (s.getNombre().equals("Rango")) {
                seccionRango = s;
                break;
            }
        }
        assertTrue(seccionRango != null);

        // Jugar la carta espía en la sección del rival
        CartaUnidad cartaEspia = new CartaUnidad("SpyBlack", secciones, 8, spyBlack);
        try {
            juego.jugarCarta(cartaEspia, seccionRango);
        } catch (TipoDeSeccionInvalidaError | CartaNoJugable e) {
            throw new RuntimeException("No se pudo jugar la carta de espía: " + e.getMessage(), e);
        }

        // Verificar que la mano del jugador aumentó y el puntaje del rival es correcto
        int cartasEnMano = j1.cartasEnMano().size();
        int puntajeDelOtroJugador = seccionRango.getPuntaje();
        assertTrue((cartasEnMano == 2) && (puntajeDelOtroJugador == 8));
    }

}
