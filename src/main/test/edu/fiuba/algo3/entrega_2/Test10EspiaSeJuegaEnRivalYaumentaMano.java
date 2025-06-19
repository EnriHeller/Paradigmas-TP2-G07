package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.especiales.CartaNevada;
import edu.fiuba.algo3.modelo.cartas.especiales.DestructoraDeClima;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Espias;
import edu.fiuba.algo3.modelo.principal.*;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesJugador;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.TableroSingleton;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test10EspiaSeJuegaEnRivalYaumentaMano {

    @Test
    public void Test10EspiaSeJuegaEnRivalYaumentaMano() throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolcitudDeCartas, UnoDeLosMazosNoCumpleRequitos {

        Espias spyBlack = new Espias(new Base());
        ArrayList<Carta> cartasDelMazo = new ArrayList<Carta>();
        ArrayList<String> secciones = new ArrayList<String>();
        secciones.add("Rango");
        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("SpyBlack",secciones, 8 , spyBlack);
            cartasDelMazo.add(carta);
        }

        Juego juego = new Juego("JugadorTest1", "JugadorTest2", new Mazo(cartasDelMazo), new Mazo(cartasDelMazo));
        juego.jugarCarta(0, new CartaUnidad("SpyBlack",secciones, 8 , spyBlack), "Rango");

        

        int cartasEnMano = juego.cartasRestantesJugador(0);
        int puntajeDelOtroJugador = juego.puntajeEnSeccion("Rango1");
        assertTrue((cartasEnMano == 2) && (puntajeDelOtroJugador == 8));
    }

}
