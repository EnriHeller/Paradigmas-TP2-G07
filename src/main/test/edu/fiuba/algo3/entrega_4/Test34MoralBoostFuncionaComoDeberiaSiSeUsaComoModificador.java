package edu.fiuba.algo3.entrega_4;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.especiales.MoraleBoost;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.modificadores.SumaValorBase;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test34MoralBoostFuncionaComoDeberiaSiSeUsaComoModificador {

    @Test
    public void Test34MoralBoostFuncionaComoDeberiaSiSeUsaComoModificador() throws TipoDeSeccionInvalidaError, UnoDeLosMazosNoCumpleRequitos {
        Modificador moralBoostOnly = new MoraleBoost(new Base());
        ArrayList<Carta> cartasDelMazo = new ArrayList<Carta>();
        ArrayList<String> secciones = new ArrayList<String>();

        Seccion seccionSimulada = new Seccion("Rango", 0);

        secciones.add("Rango");
        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("MoralOrel",secciones, 2 , moralBoostOnly);
            cartasDelMazo.add(carta);
        }

        Jugador jugador1 = new Jugador("JugadorTest1");
        jugador1.agregarMazo(new Mazo(cartasDelMazo));
        Jugador jugador2 = new Jugador("JugadorTest2");
        jugador2.agregarMazo(new Mazo(cartasDelMazo));
        Juego juego = new Juego(jugador1, jugador2);

        CartaUnidad carta1 = new CartaUnidad("MoralOrel",secciones, 2 , moralBoostOnly);

        CartaUnidad carta2 = new CartaUnidad("MoralOrel",secciones, 2 , moralBoostOnly);

        juego.jugarCarta(carta1, seccionSimulada);
        juego.jugarCarta(carta2, seccionSimulada);

        int actual = juego.puntajeEnSeccion(seccionSimulada);
        assertTrue(actual == 12);
        juego.finalizarRonda();
        int cartasEnDesacarte = juego.cartasRestantesJugador("Descarte", 0);
        assertEquals(2, cartasEnDesacarte, "No coincide numero de cartas en el Descarte");
        int puntajeEnElDescarte = carta1.ValorActual() + carta2.ValorActual();
        assertEquals(4, puntajeEnElDescarte, "No Se reinicio el puntaje al finalizar la ronda");
    }

}
