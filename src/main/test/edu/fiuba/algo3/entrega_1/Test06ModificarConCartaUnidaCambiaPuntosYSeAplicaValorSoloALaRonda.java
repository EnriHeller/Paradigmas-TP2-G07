package edu.fiuba.algo3.entrega_1;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.fiuba.algo3.controller.ConstructorMazo;
import edu.fiuba.algo3.mocks.ConstructorMazoMock;
import edu.fiuba.algo3.modelo.Errores.CartaNoJugable;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.Errores.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import org.junit.jupiter.api.Test;

public class Test06ModificarConCartaUnidaCambiaPuntosYSeAplicaValorSoloALaRonda {
    @Test
    public void modificarConCartaUnidaCambiaPuntosYSeAplicaValorSoloALaRonda() throws Exception, NoSePuedeCumplirSolicitudDeCartas, UnoDeLosMazosNoCumpleRequitos, TipoDeSeccionInvalidaError, CartaNoJugable {

        // Creamos las cartas que queremos en la mano
        List<String> secciones = new ArrayList<>();
        secciones.add("Asedio");

        CartaUnidad catapultaUnida = new CartaUnidad("Catapulta", secciones, 8, new Unidas(new Base()));
        CartaUnidad catapultaNormal = new CartaUnidad("Catapulta", secciones, 8, new Base());

        // Creamos jugador espiado y mockeamos su mano para el test
        Jugador j1 = spy(new Jugador("Pepito"));
        doReturn(Arrays.asList(catapultaUnida, catapultaNormal)).when(j1).cartasEnMano();

        // j2 normal
        Jugador j2 = new Jugador("Fulano");

        // Mazos (no se usan realmente porque la mano está mockeada)
        ConstructorMazo constructorMazo = ConstructorMazoMock.crearDosMazosEstandar();
        List<Mazo> mazos = constructorMazo.construirMazos("mock");

        Mazo m1 = mazos.get(0);
        Mazo m2 = mazos.get(1);

        j1.agregarMazo(m1);
        j2.agregarMazo(m2);

        Juego juego = new Juego(j1, j2);
        juego.tirarMoneda();

        // FASE DE JUEGO

        // Primera jugada
        List<Seccion> tableroActual = juego.mostrarTableroActual();
        List<Carta> manoActual = juego.mostrarManoActual();

        CartaUnidad cartaElegida = (CartaUnidad) manoActual.get(0);
        Seccion seccionElegida = tableroActual.get(0);
        juego.jugarCarta(cartaElegida, seccionElegida);

        // Segunda jugada
        manoActual = juego.mostrarManoActual();
        tableroActual = juego.mostrarTableroActual();

        cartaElegida = (CartaUnidad) manoActual.get(0);
        seccionElegida = tableroActual.get(0);

        juego.jugarCarta(cartaElegida, seccionElegida);

        int puntajeEsperado = 2 * (2 * cartaElegida.getPuntaje());
        int puntajeObtenido = seccionElegida.getPuntaje();

        assertEquals(puntajeEsperado, puntajeObtenido);
    }
}
