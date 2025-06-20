package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Agil;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Espias;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.Errores.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class Test11CartaAgilSePuedeUbicarEnSeccionCorrespondiente {

    @Test
    public void Test11CartaAgilSePuedeUbicarEnSeccionCorrespondiente() throws Exception, NoSePuedeCumplirSolicitudDeCartas {
        Agil agil = new Agil(new Base());
        ArrayList<Carta> cartasDelMazo = new ArrayList<>();
        ArrayList<String> secciones = new ArrayList<>();
        secciones.add("CuerpoACuerpo");
        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("Agil'e", secciones, 8, agil);
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

        // Buscar la sección "Rango" para probar la carta ágil
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
        assertDoesNotThrow(() -> juego.jugarCarta(new CartaUnidad("Agil'e", secciones, 8, agil), seccionRango));
    }
}
