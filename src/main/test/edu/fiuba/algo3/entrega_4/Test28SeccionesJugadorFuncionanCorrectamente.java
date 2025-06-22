package edu.fiuba.algo3.entrega_4;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.secciones.jugador.Descarte;
import edu.fiuba.algo3.modelo.secciones.jugador.Mano;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test28SeccionesJugadorFuncionanCorrectamente {

    @Test
    public void testAgregarYRemoverCarta() {
        Mano mano = new Mano();
        CartaUnidad carta1 = new CartaUnidad("A", Collections.singletonList("CuerpoACuerpo"), 8, new Unidas(new Base()));
        CartaUnidad carta2 = new CartaUnidad("B", Collections.singletonList("CuerpoACuerpo"), 5, new Unidas(new Base()));
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        assertEquals(2, mano.cartasRestantes());
        mano.removerCarta(carta1);
        assertEquals(1, mano.cartasRestantes());
        mano.removerCarta(carta2);
        assertEquals(0, mano.cartasRestantes());
    }

    @Test
    public void testRemoverCartasLista() {
        Mano mano = new Mano();
        CartaUnidad carta1 = new CartaUnidad("A", Collections.singletonList("CuerpoACuerpo"), 8, new Unidas(new Base()));
        CartaUnidad carta2 = new CartaUnidad("B", Collections.singletonList("CuerpoACuerpo"), 5, new Unidas(new Base()));
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        java.util.ArrayList<CartaUnidad> lista = new java.util.ArrayList<>();
        lista.add(carta1);
        lista.add(carta2);
        mano.removerCartas(new java.util.ArrayList<>(lista));
        assertEquals(0, mano.cartasRestantes());
    }

    @Test
    public void testRemoverCartaNoExistenteLanzaError() {
        Mano mano = new Mano();
        CartaUnidad carta1 = new CartaUnidad("A", Collections.singletonList("CuerpoACuerpo"), 8, new Unidas(new Base()));
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> mano.removerCarta(carta1));
    }

    @Test
    public void testAgregarYRemoverCartaEnMazoYPilaDeDescarte() {
        // Prueba Mazo
        CartaUnidad carta1 = new CartaUnidad("A", Collections.singletonList("CuerpoACuerpo"), 8, new Unidas(new Base()));
        CartaUnidad carta2 = new CartaUnidad("B", Collections.singletonList("CuerpoACuerpo"), 5, new Unidas(new Base()));
        Mazo mazo = new Mazo(new java.util.ArrayList<>());
        mazo.agregarCarta(carta1);
        mazo.agregarCarta(carta2);
        assertEquals(2, mazo.cartasRestantes());
        mazo.removerCarta(carta1);
        assertEquals(1, mazo.cartasRestantes());
        mazo.removerCarta(carta2);
        assertEquals(0, mazo.cartasRestantes());

        // Prueba PilaDeDescarte
        Descarte pila = new Descarte();
        pila.agregarCarta(carta1);
        pila.agregarCarta(carta2);
        assertEquals(2, pila.cartasRestantes());
        pila.removerCarta(carta1);
        assertEquals(1, pila.cartasRestantes());
        pila.removerCarta(carta2);
        assertEquals(0, pila.cartasRestantes());
    }

    @Test
    public void testAgregarYRemoverCartaEnManoMazoYDescarte() {
        // Mano
        Mano mano = new Mano();
        CartaUnidad carta1 = new CartaUnidad("A", Collections.singletonList("CuerpoACuerpo"), 8, new Unidas(new Base()));
        CartaUnidad carta2 = new CartaUnidad("B", Collections.singletonList("CuerpoACuerpo"), 5, new Unidas(new Base()));
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        assertEquals(2, mano.cartasRestantes());
        mano.removerCarta(carta1);
        assertEquals(1, mano.cartasRestantes());
        mano.removerCarta(carta2);
        assertEquals(0, mano.cartasRestantes());

        // Mazo
        Mazo mazo = new Mazo(new java.util.ArrayList<>());
        mazo.agregarCarta(carta1);
        mazo.agregarCarta(carta2);
        assertEquals(2, mazo.cartasRestantes());
        mazo.removerCarta(carta1);
        assertEquals(1, mazo.cartasRestantes());
        mazo.removerCarta(carta2);
        assertEquals(0, mazo.cartasRestantes());

        // Descarte
        Descarte descarte = new Descarte();
        descarte.agregarCarta(carta1);
        descarte.agregarCarta(carta2);
        assertEquals(2, descarte.cartasRestantes());
        descarte.removerCarta(carta1);
        assertEquals(1, descarte.cartasRestantes());
        descarte.removerCarta(carta2);
        assertEquals(0, descarte.cartasRestantes());
    }

    @Test
    public void testMetodosBasicosSeccionJugador() {
        // Usamos Mano, Mazo y Descarte para cubrir SeccionJugador
        Mano mano = new Mano();
        Mazo mazo = new Mazo(new java.util.ArrayList<>());
        Descarte descarte = new Descarte();
        CartaUnidad carta = new CartaUnidad("A", Collections.singletonList("CuerpoACuerpo"), 8, new Unidas(new Base()));
        // agregarCarta y cartasRestantes
        mano.agregarCarta(carta);
        mazo.agregarCarta(carta);
        descarte.agregarCarta(carta);
        assertEquals(1, mano.cartasRestantes());
        assertEquals(1, mazo.cartasRestantes());
        assertEquals(1, descarte.cartasRestantes());
        // removerCarta
        mano.removerCarta(carta);
        mazo.removerCarta(carta);
        descarte.removerCarta(carta);
        assertEquals(0, mano.cartasRestantes());
        assertEquals(0, mazo.cartasRestantes());
        assertEquals(0, descarte.cartasRestantes());
        // agregarCartas
        java.util.List<CartaUnidad> lista = new java.util.ArrayList<>();
        lista.add(carta);
        mano.agregarCartas(new java.util.ArrayList<>(lista));
        assertEquals(1, mano.cartasRestantes());
        mano.removerCartas(new java.util.ArrayList<>(lista));
        assertEquals(0, mano.cartasRestantes());
    }
}
