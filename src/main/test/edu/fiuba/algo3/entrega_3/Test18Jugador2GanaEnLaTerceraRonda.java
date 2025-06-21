package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Test18Jugador2GanaEnLaTerceraRonda {

    @Test
    public void Test18Jugador2GanaEnLaTerceraRonda() throws TipoDeSeccionInvalidaError, UnoDeLosMazosNoCumpleRequitos {
        Base base = new Base();
        ArrayList<Carta> cartasDelMazo = new ArrayList<Carta>();
        ArrayList<String> secciones = new ArrayList<String>();

        Seccion seccionSimulada = new Seccion("Rango", 0);

        secciones.add("Rango");
        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("Cualesquiera",secciones, 8 , base);
            cartasDelMazo.add(carta);
        }

        Juego juego = new Juego("JugadorTest1", "JugadorTest2", new Mazo(cartasDelMazo), new Mazo(cartasDelMazo));
        juego.definirQuienEmpieza(0);

        juego.jugarCarta(0, new CartaUnidad("Cualesquiera",secciones, 8 , base), seccionSimulada);
        juego.jugarCarta(0, new CartaUnidad("Cualesquiera",secciones, 8 , base), seccionSimulada);

        juego.siguienteJugador();
        juego.jugarCarta(1, new CartaUnidad("Cualesquiera",secciones, 8 , base), seccionSimulada);
        juego.finalizarRonda();

        assertFalse(juego.juegoTerminado());

        juego.jugarCarta(1, new CartaUnidad("Cualesquiera",secciones, 8 , base), seccionSimulada);
        juego.jugarCarta(1, new CartaUnidad("Cualesquiera",secciones, 8 , base), seccionSimulada);

        juego.siguienteJugador();
        juego.jugarCarta(0, new CartaUnidad("Cualesquiera",secciones, 8 , base), seccionSimulada);
        juego.finalizarRonda();

        assertFalse(juego.juegoTerminado());

        juego.siguienteJugador();
        juego.jugarCarta(1, new CartaUnidad("Cualesquiera",secciones, 8 , base), seccionSimulada);
        juego.jugarCarta(1, new CartaUnidad("Cualesquiera",secciones, 8 , base), seccionSimulada);
        juego.jugarCarta(1, new CartaUnidad("Cualesquiera",secciones, 8 , base), seccionSimulada);

        juego.siguienteJugador();
        juego.jugarCarta(0, new CartaUnidad("Cualesquiera",secciones, 8 , base), seccionSimulada);
        juego.finalizarRonda();

        assertTrue(juego.juegoTerminado());
        assertEquals("JugadorTest2", juego.mostrarGanador(), "Gano el jugador incorrecto.\nSe esperaba: " + "JugadorTest2" + " y se obtuvo " + juego.mostrarGanador());
    }
}
