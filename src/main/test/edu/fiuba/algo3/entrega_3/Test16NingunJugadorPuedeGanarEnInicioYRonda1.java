package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;

public class Test16NingunJugadorPuedeGanarEnInicioYRonda1 {
    @Test
    public void Test16NingunJugadorPuedeGanarEnInicioYRonda1() throws TipoDeSeccionInvalidaError, UnoDeLosMazosNoCumpleRequitos, CartaNoJugable {
        Base base = new Base();
        ArrayList<Carta> cartasDelMazo = new ArrayList<Carta>();
        ArrayList<String> secciones = new ArrayList<String>();
        secciones.add("Rango");

        Seccion seccionSimulada = new Seccion("Rango", 0);

        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("Cualesquiera",secciones, 8 , base);
            cartasDelMazo.add(carta);
        }

        Jugador jugador1 = new Jugador("JugadorTest1");
        jugador1.agregarMazo(new Mazo(cartasDelMazo));
        Jugador jugador2 = new Jugador("JugadorTest2");
        jugador2.agregarMazo(new Mazo(cartasDelMazo));
        Juego juego = new Juego(jugador1, jugador2);

        assertFalse(juego.juegoTerminado());
        juego.definirQuienEmpieza(0);
        juego.jugarCarta(new CartaUnidad("Vengador",secciones, 8 , base), seccionSimulada);
        juego.jugarCarta(new CartaUnidad("Vengador",secciones, 8 , base), seccionSimulada);

        juego.siguienteJugador();
        juego.jugarCarta(new CartaUnidad("Vengador",secciones, 8 , base), seccionSimulada);
        juego.finalizarRonda();

        assertFalse(juego.juegoTerminado());
    }
}
