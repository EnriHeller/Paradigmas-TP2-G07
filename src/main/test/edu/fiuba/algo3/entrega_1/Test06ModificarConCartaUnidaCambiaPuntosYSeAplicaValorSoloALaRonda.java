package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.mocks.CartaUnidadUnidasMock;
import org.junit.jupiter.api.Test;

public class Test06ModificarConCartaUnidaCambiaPuntosYSeAplicaValorSoloALaRonda {
    @Test
    public void modificarConCartaUnidaCambiaPuntosYSeAplicaValorSoloALaRonda() throws TipoDeSeccionInvalidaError, UnoDeLosMazosNoCumpleRequitos {
        ArrayList<Carta> cartasDelMazo = new ArrayList<Carta>();
        ArrayList<String> secciones = new ArrayList<String>();
        secciones.add("Rango");
        Seccion seccionSimulada = new Seccion("Rango", 0);
        for (int i = 0; i < 21; i++) {
            cartasDelMazo.add(new CartaUnidadUnidasMock("Vengador", secciones, 8));
        }
        Jugador jugador1 = new Jugador("Jugador1");
        jugador1.agregarMazo(new Mazo(cartasDelMazo));
        Jugador jugador2 = new Jugador("Jugador2");
        jugador2.agregarMazo(new Mazo(cartasDelMazo));
        Juego juego = new Juego(jugador1,jugador2);
        juego.jugarCarta(new CartaUnidadUnidasMock("Vengador", secciones, 8), seccionSimulada);
        juego.jugarCarta(new CartaUnidadUnidasMock("Vengador", secciones, 8), seccionSimulada);
        int puntaje = juego.puntajeEnSeccion(seccionSimulada);
        assertEquals(32, puntaje, "Cantidad incorrecta puntos al sumar el poder de las cratas con el modificadorAplicado\nEn realidad se esperaban " + 32 + "Puntos y se obtuvo " + puntaje);
        juego.finalizarRonda();
        int actual = juego.cartasRestantesJugador("Descarte", 0);
        assertEquals(2, actual, "Cantidad incorrecta de cartas en el descarte: se esperaban " + 2 + " y se obtuvo " + actual);
    }
}
