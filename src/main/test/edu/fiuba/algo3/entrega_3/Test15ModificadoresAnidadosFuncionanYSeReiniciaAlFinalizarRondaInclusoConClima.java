package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.especiales.CartaNevada;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.modificadores.SumaValorBase;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.Errores.*;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test15ModificadoresAnidadosFuncionanYSeReiniciaAlFinalizarRondaInclusoConClima {
    @Test
    public void Test15ModificadoresAnidadosFuncionanYSeReiniciaAlFinalizarRondaInclusoConClima() {
        Modificador superModificador = new Unidas(new SumaValorBase(new Base()));
        ArrayList<Carta> cartasDelMazo = new ArrayList<>();
        ArrayList<String> secciones = new ArrayList<>();
        secciones.add("Cuerpo a Cuerpo");
        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("SuperCarta", secciones, 8, superModificador);
            cartasDelMazo.add(carta);
        }

        edu.fiuba.algo3.modelo.principal.Jugador jugador1 = new edu.fiuba.algo3.modelo.principal.Jugador("JugadorTest1");
        edu.fiuba.algo3.modelo.principal.Jugador jugador2 = new edu.fiuba.algo3.modelo.principal.Jugador("JugadorTest2");
        assertDoesNotThrow(() -> jugador1.agregarMazo(new Mazo(cartasDelMazo)));
        assertDoesNotThrow(() -> jugador2.agregarMazo(new Mazo(cartasDelMazo)));
        final Juego juego = assertDoesNotThrow(() -> new Juego(jugador1, jugador2));
        assertDoesNotThrow(() -> juego.repartirManoInicial(jugador1));
        assertDoesNotThrow(() -> juego.repartirManoInicial(jugador2));

        CartaUnidad carta1 = new CartaUnidad("SuperCarta", secciones, 8, superModificador);
        CartaUnidad carta2 = new CartaUnidad("SuperCarta", secciones, 8, superModificador);

        // Buscar la sección "Cuerpo a Cuerpo" del jugador actual
        final edu.fiuba.algo3.modelo.secciones.tablero.Seccion seccionCuerpo;
        {
            edu.fiuba.algo3.modelo.secciones.tablero.Seccion encontrada = null;
            for (edu.fiuba.algo3.modelo.secciones.tablero.Seccion s : juego.mostrarTableroActual()) {
                if (s.getNombre().equals("Cuerpo a Cuerpo")) {
                    encontrada = s;
                    break;
                }
            }
            seccionCuerpo = encontrada;
        }
        assert seccionCuerpo != null;
        assertDoesNotThrow(() -> juego.jugarCarta(carta1, seccionCuerpo));
        assertDoesNotThrow(() -> juego.jugarCarta(carta2, seccionCuerpo));

        int actual = seccionCuerpo.getCartas().stream().mapToInt(CartaUnidad::ValorActual).sum();
        assertTrue(actual == 38);

        // Jugar carta de clima
        ArrayList<String> seccionesClima = new ArrayList<>();
        seccionesClima.add("Cuerpo a Cuerpo");
        assertDoesNotThrow(() -> juego.jugarCarta(new CartaNevada("Nevada", "Reduce el valor de las cartas a 1", seccionesClima), seccionCuerpo));
        actual = seccionCuerpo.getCartas().stream().mapToInt(CartaUnidad::ValorActual).sum();
        assertTrue(actual == 2);

        juego.finalizarRonda();
        int cartasEnDescarte = jugador1.cartasEnDescarte().size();
        assertEquals(2, cartasEnDescarte, "No coincide numero de cartas en el Descarte");
        int puntajeEnElDescarte = jugador1.cartasEnDescarte().stream().mapToInt(c -> ((CartaUnidad)c).ValorActual()).sum();
        assertEquals(16, puntajeEnElDescarte, "No Se reinicio el puntaje al finalizar la ronda");
    }
}
