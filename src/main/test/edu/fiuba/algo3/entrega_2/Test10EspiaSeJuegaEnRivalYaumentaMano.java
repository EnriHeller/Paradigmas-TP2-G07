package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Espias;
import edu.fiuba.algo3.modelo.principal.*;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test10EspiaSeJuegaEnRivalYaumentaMano {

    @Test
    public void Test10EspiaSeJuegaEnRivalYaumentaMano() throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolicitudDeCartas, UnoDeLosMazosNoCumpleRequitos {

        Espias spyBlack = new Espias(new Base());
        ArrayList<Carta> cartasDelMazo = new ArrayList<Carta>();
        ArrayList<String> secciones = new ArrayList<String>();
        Seccion seccionSimulada = new Seccion("Rango", 0);
        secciones.add("Rango");
        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("SpyBlack",secciones, 8 , spyBlack);
            cartasDelMazo.add(carta);
        }

        Jugador jugador1 = new Jugador("JugadorTest1");
        jugador1.agregarMazo(new Mazo(cartasDelMazo));
        Jugador jugador2 = new Jugador("JugadorTest2");
        jugador2.agregarMazo(new Mazo(cartasDelMazo));

        Juego juego = new Juego(jugador1, jugador2);

        juego.jugarCarta(new CartaUnidad("SpyBlack",secciones, 8 , spyBlack), seccionSimulada);

        

        int cartasEnMano = juego.cartasRestantesJugador("Mano", 0);
        int puntajeDelOtroJugador = juego.puntajeEnSeccion(new Seccion("Rango", 1));
        assertTrue((cartasEnMano == 2) && (puntajeDelOtroJugador == 8));
    }

}
