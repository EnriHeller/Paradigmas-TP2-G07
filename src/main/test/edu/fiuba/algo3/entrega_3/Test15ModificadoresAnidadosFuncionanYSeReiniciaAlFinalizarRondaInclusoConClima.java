package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.especiales.CartaNevada;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.modificadores.SumaValorBase;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test15ModificadoresAnidadosFuncionanYSeReiniciaAlFinalizarRondaInclusoConClima {
    @Test
    public void Test15ModificadoresAnidadosFuncionanYSeReiniciaAlFinalizarRondaInclusoConClima() throws TipoDeSeccionInvalidaError, UnoDeLosMazosNoCumpleRequitos {
        Modificador superModificador = new Unidas(new SumaValorBase(new Base()));
        ArrayList<Carta> cartasDelMazo = new ArrayList<Carta>();
        ArrayList<String> secciones = new ArrayList<String>();
        secciones.add("CuerpoACuerpo");
        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("SuperCarta",secciones, 8 , superModificador);
            cartasDelMazo.add(carta);
        }

        Seccion seccionSimulada = new Seccion("CuerpoACuerpo", 0);

        Juego juego = new Juego(new Jugador("JugadorTest1", new Mazo(cartasDelMazo)), new Jugador("JugadorTest2", new Mazo(cartasDelMazo)));

        CartaUnidad carta1 = new CartaUnidad("SuperCarta",secciones, 8 , superModificador);

        CartaUnidad carta2 = new CartaUnidad("SuperCarta",secciones, 8 , superModificador);

        juego.jugarCarta(carta1, seccionSimulada);
        juego.jugarCarta(carta2, seccionSimulada);

        int actual = juego.puntajeEnSeccion(seccionSimulada);
        assertTrue(actual == 38);

        juego.jugarCarta(new CartaNevada(), seccionSimulada);

        actual = juego.puntajeEnSeccion(seccionSimulada);
        assertTrue(actual == 2);

        juego.finalizarRonda();
        int cartasEnDesacarte = juego.cartasRestantesJugador("Descarte", 0);
        assertEquals(2, cartasEnDesacarte, "No coincide numero de cartas en el Descarte");
        int puntajeEnElDescarte = carta1.ValorActual() + carta2.ValorActual();
        assertEquals(16, puntajeEnElDescarte, "No Se reinicio el puntaje al finalizar la ronda");

    }
}
