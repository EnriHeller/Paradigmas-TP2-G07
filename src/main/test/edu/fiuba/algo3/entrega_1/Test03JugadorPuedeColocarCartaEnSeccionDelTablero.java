package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.jugador.Mano;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.controller.ConstructorMazo;
import edu.fiuba.algo3.mocks.ConstructorMazoMock;
import edu.fiuba.algo3.modelo.Errores.*;
import edu.fiuba.algo3.modelo.secciones.tablero.*;

import java.util.List;
import java.util.ArrayList;

public class Test03JugadorPuedeColocarCartaEnSeccionDelTablero  {
    // Verificar que un jugador pueda colocar una carta en una sección del tablero

    @Test
    public void jugadorPuedeColocarCartaEnSeccion() throws Exception, NoSePuedeCumplirSolicitudDeCartas, UnoDeLosMazosNoCumpleRequitos, TipoDeSeccionInvalidaError, CartaNoJugable {

        ConstructorMazo constructorMazo = ConstructorMazoMock.crearDosMazosEstandar();

        List<Mazo> mazos = constructorMazo.construirMazos("mock");

        Mazo m1 = mazos.get(0);
        Mazo m2 = mazos.get(1);

        Jugador j1 = new Jugador("Pepito");
        Jugador j2 = new Jugador("Fulano");

        j1.agregarMazo(m1);
        j2.agregarMazo(m2);

        Juego juego = new Juego(j1, j2);
        
        juego.repartirManoInicial(j1);
        juego.repartirManoInicial(j2);
        juego.tirarMoneda();

        //FASE DE JUEGO

        //Mostramos estado actual del juego (Para renderizar, SOLO JUGADOR ACTUAL)

        List<Seccion> tableroActual = juego.mostrarTableroActual();
        List<Carta> manoActual = juego.mostrarManoActual();

        //Elegimos carta y sección
        Carta cartaElegida = manoActual.get(0);
        Seccion seccionElegida = tableroActual.get(0);
        
        juego.jugarCarta(cartaElegida, seccionElegida);
        assertTrue(seccionElegida.contiene(cartaElegida));
        
    }
}
