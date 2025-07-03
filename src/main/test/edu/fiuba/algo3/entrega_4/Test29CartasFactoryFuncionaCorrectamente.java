package edu.fiuba.algo3.entrega_4;

import edu.fiuba.algo3.modelo.cartas.CartasFactory;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.secciones.jugador.Mano;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class Test29CartasFactoryFuncionaCorrectamente {

    @Test
    public void testCrearCartaUnidad() {
        CartasFactory factory = new CartasFactory();
        Modificador mod = new Base();
        Carta carta = factory.crearCarta(
            "u",
            "UnidadTest",
            Arrays.asList("CuerpoACuerpo"),
            10L,
            mod,
            "desc",
            null
        );
        assertNotNull(carta);
        assertEquals("UnidadTest", ((CartaUnidad)carta).mostrarCarta());
    }

    @Test
    public void testCrearCartaEspecialTierraArrasada() {
        CartasFactory factory = new CartasFactory();
        Carta carta = factory.crearCarta(
            "e",
            "Tierra arrasada",
            Arrays.asList("CuerpoACuerpo"),
            0L,
            null,
            "desc",
            "Tierra arrasada"
        );
        assertNotNull(carta);
        assertEquals("TierraArrasada", carta.mostrarCarta());
    }

    @Test
    public void testCrearCartaEspecialClima() {
        CartasFactory factory = new CartasFactory();
        Carta carta = factory.crearCarta(
            "e",
            "Escarcha Mordaz",
            Arrays.asList("CuerpoACuerpo"),
            0L,
            null,
            "desc",
            "Clima"
        );
        assertNotNull(carta);
        assertEquals("Nevada", carta.mostrarCarta());
    }

    @Test
    public void testCrearCartaEspecialTipoDesconocidoDevuelveNull() {
        CartasFactory factory = new CartasFactory();
        Carta carta = factory.crearCarta(
            "e",
            "Desconocida",
            Arrays.asList("CuerpoACuerpo"),
            0L,
            null,
            "desc",
            "TipoInexistente"
        );
        assertNull(carta);
    }

    @Test
    public void testCrearCartaEspecialTipoMoraleBoost() {
        CartasFactory factory = new CartasFactory();
        Carta carta = factory.crearCarta(
                "e",
                "morale boost",
                Arrays.asList("CuerpoACuerpo"),
                0L,
                null,
                "desc",
                "morale boost"
        );
        assertEquals("MoraleBoost", carta.mostrarCarta());

    }

    @Test
    public void testCrearCartaEspecialTipoLluviaTorrencial() {
        CartasFactory factory = new CartasFactory();
        Carta carta = factory.crearCarta(
                "e",
                "lluvia torrencial",
                Arrays.asList("CuerpoACuerpo"),
                0L,
                null,
                "desc",
                "clima"
        );
        assertEquals("Lluvia Torrencial", carta.mostrarCarta());

    }

    @Test
    public void testCrearCartaEspecialTipoTormentaDeSkelligel() {
        CartasFactory factory = new CartasFactory();
        Carta carta = factory.crearCarta(
                "e",
                "tormeta de skellige",
                Arrays.asList("CuerpoACuerpo"),
                0L,
                null,
                "desc",
                "clima"
        );
        assertEquals("TormentaDeSkellige", carta.mostrarCarta());

    }

    @Test
    public void testCrearCartaEspecialTipoTiempoDespejado() {
        CartasFactory factory = new CartasFactory();
        Carta carta = factory.crearCarta(
                "e",
                "tiempo despejado",
                Arrays.asList("CuerpoACuerpo"),
                0L,
                null,
                "desc",
                "clima"
        );
        assertEquals("tiempoDespejado", carta.mostrarCarta());

    }

    @Test
    public void testCrearCartaEspecialTipoNullRetornaNull() {
        CartasFactory factory = new CartasFactory();
        Carta carta = factory.crearCarta(
                "e",
                "tiempo despejado",
                Arrays.asList("CuerpoACuerpo"),
                0L,
                null,
                "desc",
                null
        );
        assertNull(carta);

    }
    @Test
    public void testCrearCartaInvalidaRetornaNull() {
        CartasFactory factory = new CartasFactory();
        Carta carta = factory.crearCarta(
                "s",
                "tiempo despejado",
                Arrays.asList("CuerpoACuerpo"),
                0L,
                null,
                "desc",
                null
        );
        assertNull(carta);

    }
}
