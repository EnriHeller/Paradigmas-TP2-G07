package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Agil;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Espias;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;

public class Test11CartaAgilSePuedeUbicarEnSeccionCorrespondiente {

    @Test
    public void Test11CartaAgilSePuedeUbicarEnSeccionCorrespondiente() throws TipoDeSeccionInvalidaError, UnoDeLosMazosNoCumpleRequitos, CartaNoJugable {
        Agil agil= new Agil(new Base());
        ArrayList<Carta> cartasDelMazo = new ArrayList<Carta>();
        ArrayList<String> secciones = new ArrayList<String>();
        secciones.add("CuerpoACuerpo");

        Seccion seccionSimulada = new Seccion("CuerpoACuerpo", 0);

        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("Agil'e",secciones, 8 , agil);
            cartasDelMazo.add(carta);
        }

        Jugador jugador1 = new Jugador("JugadorTest1");
        jugador1.agregarMazo(new Mazo(cartasDelMazo));
        Jugador jugador2 = new Jugador("JugadorTest2");
        jugador2.agregarMazo(new Mazo(cartasDelMazo));

        Juego juego = new Juego(jugador1, jugador2);

        assertDoesNotThrow(() -> juego.jugarCarta(new CartaUnidad("Agil'e",secciones, 8 , agil), seccionSimulada));
    }
}
