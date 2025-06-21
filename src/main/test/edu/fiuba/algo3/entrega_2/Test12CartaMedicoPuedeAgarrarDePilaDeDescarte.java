package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Espias;
import edu.fiuba.algo3.modelo.modificadores.Medico;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolcitudDeCartas;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesJugador;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test12CartaMedicoPuedeAgarrarDePilaDeDescarte {

    @Test
    public void Test12CartaMedicoPuedeAgarrarDePilaDeDescarte() throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolcitudDeCartas, UnoDeLosMazosNoCumpleRequitos {

        Medico medico = new Medico(new Base());
        ArrayList<Carta> cartasDelMazo = new ArrayList<Carta>();
        ArrayList<String> secciones = new ArrayList<String>();
        secciones.add("Rango");

        Seccion seccionSimulada = new Seccion("Rango", 0);

        //Forzar algunas cartas en el descarte
        SeccionesJugador descarteForzado = SeccionesJugador.seccionesDelJugador("0");

        descarteForzado.agregarCarta("Descarte", new CartaUnidad("Aldeano1",secciones, 8 , new Base()));
        descarteForzado.agregarCarta("Descarte", new CartaUnidad("Aldeano2",secciones, 8 , new Base()));
        descarteForzado.agregarCarta("Descarte", new CartaUnidad("Aldeano3",secciones, 8 , new Base()));
        descarteForzado.agregarCarta("Mano", new CartaUnidad("LaPeorCarta",secciones, 8 , medico));

        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("LaPeorCarta",secciones, 8 , medico);
            cartasDelMazo.add(carta);
        }

        Juego juego = new Juego("JugadorTest1", "JugadorTest2", new Mazo(cartasDelMazo), new Mazo(cartasDelMazo));

        assertDoesNotThrow(() -> juego.jugarCarta(new CartaUnidad("LaPeorCarta",secciones, 8 , medico), seccionSimulada));
    }
}
