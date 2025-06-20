package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test13Jugador1Gana {

    @Test
    public void Test13Jugador1Gana() throws TipoDeSeccionInvalidaError, UnoDeLosMazosNoCumpleRequitos {
        Base base = new Base();
        ArrayList<Carta> cartasDelMazo = new ArrayList<Carta>();
        ArrayList<String> secciones = new ArrayList<String>();
        secciones.add("Rango");
        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("Cualesquiera",secciones, 8 , base);
            cartasDelMazo.add(carta);
        }

        Juego juego = new Juego("JugadorTest1", "JugadorTest2", new Mazo(cartasDelMazo), new Mazo(cartasDelMazo));

        juego.definirQuienEmpieza(0);
        juego.jugarCarta(0, new CartaUnidad("Vengador",secciones, 8 , base), "Rango");
        juego.jugarCarta(0, new CartaUnidad("Vengador",secciones, 8 , base), "Rango");

        juego.siguienteJugador();
        juego.jugarCarta(1, new CartaUnidad("Vengador",secciones, 8 , base), "Rango");
        juego.finalizarRonda();
        juego.jugarCarta(1, new CartaUnidad("Vengador",secciones, 8 , base), "Rango");
        juego.jugarCarta(1, new CartaUnidad("Vengador",secciones, 8 , base), "Rango");
        juego.jugarCarta(1, new CartaUnidad("Vengador",secciones, 8 , base), "Rango");
        juego.jugarCarta(1, new CartaUnidad("Vengador",secciones, 8 , base), "Rango");
        juego.jugarCarta(1, new CartaUnidad("Vengador",secciones, 8 , base), "Rango");
        juego.jugarCarta(1, new CartaUnidad("Vengador",secciones, 8 , base), "Rango");
        juego.jugarCarta(1, new CartaUnidad("Vengador",secciones, 8 , base), "Rango");
        juego.jugarCarta(1, new CartaUnidad("Vengador",secciones, 8 , base), "Rango");

        juego.siguienteJugador();
        juego.jugarCarta(0, new CartaUnidad("Vengador",secciones, 8 , base), "Rango");
        juego.finalizarRonda();
        juego.jugarCarta(0, new CartaUnidad("Vengador",secciones, 8 , base), "Rango");
        juego.jugarCarta(0, new CartaUnidad("Vengador",secciones, 8 , base), "Rango");

        juego.siguienteJugador();
        juego.jugarCarta(1, new CartaUnidad("Vengador",secciones, 8 , base), "Rango");
        juego.finalizarRonda();
        assertEquals("JugadorTest1", juego.mostrarGanador(), "Gano el jugador incorrecto.\nSe esperaba: " + "JugadorTest1" + " y se obtuvo " + juego.mostrarGanador());
    }
}
