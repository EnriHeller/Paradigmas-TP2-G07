package edu.fiuba.algo3.entrega_4;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;
import edu.fiuba.algo3.modelo.cartas.CartasFactory;
import edu.fiuba.algo3.modelo.cartas.especiales.CartaEspecial;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.jugador.Mano;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test30CartaEspecialFuncionaCorrectamente {

    static class CartaEspecialBase extends CartaEspecial {
        public CartaEspecialBase(String nombre, String descripcion, String tipo, List<String> afectado) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.tipo = tipo;
            this.afectado = afectado;
        }
    }

    @org.junit.jupiter.api.Test
    public void testGettersDevuelvenValoresCorrectos() {
        List<String> afectado = Arrays.asList("CuerpoACuerpo", "Rango");
        CartaEspecial carta = new CartaEspecialBase("EspecialTest", "Desc test", "TipoTest", afectado);
        assertEquals("EspecialTest", carta.getNombre());
        assertEquals("Desc test", carta.getDescripcion());
        assertEquals("TipoTest", carta.getTipo());
        assertEquals(afectado, carta.getAfectado());
    }

    @org.junit.jupiter.api.Test
    public void testJugarCartaEspecialNoMoralBoost() throws TipoDeSeccionInvalidaError, UnoDeLosMazosNoCumpleRequitos, CartaNoJugable {
        //inicializacion de juego
        Base base = new Base();
        ArrayList<Carta> cartasDelMazo = new ArrayList<Carta>();
        ArrayList<String> secciones = new ArrayList<String>();

        Seccion seccionSimulada = new Seccion("Rango", 0);

        List<Carta> manoSimulada = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            CartaUnidad carta = new CartaUnidad("Cualesquiera",secciones, 8 , base);
            manoSimulada.add(carta);
        }

        secciones.add("Rango");
        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("Cualesquiera",secciones, 8 , base);
            cartasDelMazo.add(carta);
        }

        Jugador jugador1 = new Jugador("JugadorTest1");
        jugador1.agregarMazo(new Mazo(cartasDelMazo));
        Jugador jugador2 = new Jugador("JugadorTest2");
        jugador2.agregarMazo(new Mazo(cartasDelMazo));
        Juego juego = new Juego(jugador1, jugador2);


        //carta a jugar
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

        assertThrows(CartaNoJugable.class, ()->juego.jugarCarta(carta, seccionSimulada));

    }
}
