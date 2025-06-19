package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.controller.ConstructorMazo;
import edu.fiuba.algo3.mocks.ConstructorMazoMock;

import edu.fiuba.algo3.modelo.Errores.CartaNoJugable;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.Errores.UnoDeLosMazosNoCumpleRequitos;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.especiales.*;
import edu.fiuba.algo3.modelo.cartas.unidades.*;


import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Unidas;

import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;

import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test07CartaEspecialDeClimaModificaValorDeLasCartasEnSeccionCorrespondiente {

    @Test
    public void cartaEspecialDeClimaModificaValorDeLasCartasEnSeccionCorrespondiente() throws Exception, NoSePuedeCumplirSolicitudDeCartas, UnoDeLosMazosNoCumpleRequitos, TipoDeSeccionInvalidaError, CartaNoJugable {

        // Creamos las cartas que queremos en la mano 
        
        ArrayList<String> secciones = new ArrayList<>();
        secciones.add("Cuerpo a Cuerpo");
        
        ArrayList<Carta> cartasMano = new ArrayList<>();

        CartaNevada cartaEspecialClima = new CartaNevada();
        Clima climaNevado = cartaEspecialClima.crearClima();

        CartaUnidad primeraCartaPuntajeUno = new CartaUnidad("CartaTest1",secciones, 3);
        CartaUnidad segundaCartaPuntajeUno = new CartaUnidad("CartaTest2",secciones, 3);

        cartasMano.add(cartaEspecialClima);
        cartasMano.add(primeraCartaPuntajeUno);
        cartasMano.add(segundaCartaPuntajeUno);
        

        // Creamos jugador espiado y mockeamos su mano para el test
        Jugador j1 = spy(new Jugador("Pepito"));
        doReturn(cartasMano).when(j1).cartasEnMano();

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
        
        //Se juega primer carta estandar
        List<Seccion> tableroActual = juego.mostrarTableroActual();
        List<Carta> manoActual = juego.mostrarManoActual();

        CartaUnidad cartaElegida = (CartaUnidad) manoActual.get(0);
        Seccion seccionElegida = tableroActual.get(0);
        juego.jugarCarta(cartaElegida, seccionElegida);
        juego.siguienteJugador();

        //Se juega segunda carta estandar
        tableroActual = juego.mostrarTableroActual();
        manoActual = juego.mostrarManoActual();

        cartaElegida = (CartaUnidad) manoActual.get(0);
        seccionElegida = tableroActual.get(0);
        juego.jugarCarta(cartaElegida, seccionElegida);

        //Guardamos puntaje sin clima
        int puntajeSinClima = seccionElegida.getPuntaje();

        juego.siguienteJugador();

        //Se juega carta climatica
        tableroActual = juego.mostrarTableroActual();
        manoActual = juego.mostrarManoActual();

        cartaElegida = (CartaUnidad) manoActual.get(0);
        seccionElegida = tableroActual.get(0);
        juego.jugarCarta(cartaElegida, seccionElegida);

        //Guardamos puntaje con clima
        int puntajeConClima = seccionElegida.getPuntaje();

        assertTrue((puntajeSinClima > puntajeConClima));

    }

    /*
    * AHORA:
    * especial carta recibe List<Carta>, luego las modifica una por una.
    *VENTAJAS: Tiempo y menos dolor de cabeza.
    * DESVENTAJAS: vamos a tener q modificarlo.
    *
    *
    * DESPUES:
    * El usuari elige una SECCION para modificar.
    * y se envia (por atras) un especial.modificarCartas(seccionElegida.listaDeCartas())
    *
    * */
    /* 1- el jugador juega la nevada - (Jugador)
    *  2- la recibe el juego
    *  3- juego usa el metodo aplicar clima de SU tablero
    *  4- y el tablero lo delega a la seccion correspondiente
    *  5- la seccion aplica el clima que le llega por parametro
    *
    * inst seccion cuerpo a cuerpo y carta especial (clima)
    * usas metodo especial y retorna un clima
    * agregas cartas en algun momento a la seccion
    * le tiras un puntaje
    * aplicas clima a seccion
    * le tiras un puntaje
    * assert tiraspunateje2 > tiraspuntaje1
    *
    * 
    */
}
