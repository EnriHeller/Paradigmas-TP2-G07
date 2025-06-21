package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.especiales.CartaNevada;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.cartas.especiales.Clima;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.modificadores.SumaValorBase;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class Test07CartaEspecialDeClimaModificaValorDeLasCartasEnSeccionCorrespondiente {

    //
    @Test
    public void cartaEspecialDeClimaModificaValorDeLasCartasEnSeccionCorrespondiente() throws TipoDeSeccionInvalidaError, UnoDeLosMazosNoCumpleRequitos {
        Base base = new Base();
        ArrayList<Carta> cartasDelMazo = new ArrayList<Carta>();
        ArrayList<String> secciones = new ArrayList<String>();
        secciones.add("CuerpoACuerpo");
        for (int i = 0; i < 21; i++) {
            CartaUnidad carta = new CartaUnidad("Aldeano",secciones, 8 , base);
            cartasDelMazo.add(carta);
        }

        Seccion seccionSimulada = new Seccion("CuerpoACuerpo", 0);

        Juego juego = new Juego("JugadorTest1", "JugadorTest2", new Mazo(cartasDelMazo), new Mazo(cartasDelMazo));

        CartaUnidad carta1 = new CartaUnidad("Aldeano",secciones, 8 , base);

        CartaUnidad carta2 = new CartaUnidad("Aldeano",secciones, 8 , base);

        juego.jugarCarta(carta1, seccionSimulada);
        juego.jugarCarta(carta2, seccionSimulada);

        int actual = juego.puntajeEnSeccion(seccionSimulada);
        assertEquals(16, actual);

        juego.jugarCarta(new CartaNevada(), seccionSimulada);

        actual = juego.puntajeEnSeccion(seccionSimulada);
        assertEquals(2, actual);
    }
}