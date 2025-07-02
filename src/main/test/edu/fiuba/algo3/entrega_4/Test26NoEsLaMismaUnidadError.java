package edu.fiuba.algo3.entrega_4;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.cartas.unidades.NoEsLaMismaUnidad;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.modificadores.Base;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;

public class Test26NoEsLaMismaUnidadError {


    @Test
    public void testNoEsLaMismaUnidadError() throws TipoDeSeccionInvalidaError, UnoDeLosMazosNoCumpleRequitos, CartaNoJugable {
        // Caso borde: 3 cartas con distinto nombre o modificadores, intentar aplicar Unidas
        CartaUnidad carta1 = new CartaUnidad("A", Arrays.asList("CuerpoACuerpo"), 8, new Unidas(new Base()));
        CartaUnidad carta2 = new CartaUnidad("B", Arrays.asList("CuerpoACuerpo"), 8, new Unidas(new Base()));
        CartaUnidad carta3 = new CartaUnidad("A", Arrays.asList("CuerpoACuerpo"), 8, new Base());

        Seccion seccion = new Seccion("CuerpoACuerpo", 0);

        List<Carta> cartasDelMazo = new ArrayList<Carta>();
        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("Cualesquiera",List.of("CuerpoACuerpo"), 8 , new Base());
            cartasDelMazo.add(carta);
        }

        Jugador jugador1 = new Jugador("JugadorTest1");
        jugador1.agregarMazo(new Mazo(cartasDelMazo));
        Jugador jugador2 = new Jugador("JugadorTest2");
        jugador2.agregarMazo(new Mazo(cartasDelMazo));
        Juego juego = new Juego(jugador1, jugador2);

        juego.jugarCarta(carta1,seccion);
        juego.jugarCarta(carta2,seccion);
        juego.jugarCarta(carta3,seccion);

        assertEquals(8, carta1.ValorActual());
        assertEquals(8, carta2.ValorActual());
        assertEquals(8, carta3.ValorActual());
    }
}
