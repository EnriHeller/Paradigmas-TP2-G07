package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.*;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.Errores.*;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test14ModificadoresAnidadosFuncionanYSeReiniciaAlFinalizarRonda {

    @Test
    public void Test14ModificadoresAnidadosFuncionanYSeReiniciaAlFinalizarRonda() {
        Modificador superModificador = new Unidas(new SumaValorBase(new Base()));
        ArrayList<Carta> cartasDelMazo = new ArrayList<>();
        ArrayList<String> secciones = new ArrayList<>();
        secciones.add("Rango");
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

        // Buscar la sección "Rango" del jugador actual
        final edu.fiuba.algo3.modelo.secciones.tablero.Seccion seccionRango;
        {
            edu.fiuba.algo3.modelo.secciones.tablero.Seccion encontrada = null;
            for (edu.fiuba.algo3.modelo.secciones.tablero.Seccion s : juego.mostrarTableroActual()) {
                if (s.getNombre().equals("Rango")) {
                    encontrada = s;
                    break;
                }
            }
            seccionRango = encontrada;
        }
        assert seccionRango != null;
        assertDoesNotThrow(() -> juego.jugarCarta(carta1, seccionRango));
        assertDoesNotThrow(() -> juego.jugarCarta(carta2, seccionRango));

        // Sumar el puntaje de las cartas en la sección
        int actual = seccionRango.getCartas().stream().mapToInt(CartaUnidad::ValorActual).sum();
        assertTrue(actual == 38);
        juego.finalizarRonda();
        // Verificar cartas en el descarte del jugador actual
        int cartasEnDescarte = jugador1.cartasEnDescarte().size();
        assertEquals(2, cartasEnDescarte, "No coincide numero de cartas en el Descarte");
        int puntajeEnElDescarte = jugador1.cartasEnDescarte().stream().mapToInt(c -> ((CartaUnidad)c).ValorActual()).sum();
        assertEquals(16, puntajeEnElDescarte, "No Se reinicio el puntaje al finalizar la ronda");
    }
}
