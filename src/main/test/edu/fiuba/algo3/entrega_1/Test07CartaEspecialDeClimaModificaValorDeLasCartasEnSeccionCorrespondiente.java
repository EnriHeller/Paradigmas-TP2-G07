package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
public class Test07CartaEspecialDeClimaModificaValorDeLasCartasEnSeccionCorrespondiente {

    //
    @Test
    public void cartaEspecialDeClimaModificaValorDeLasCartasEnSeccionCorrespondiente() throws TipoDeSeccionInvalidaError, CartaNoJugable {

        Seccion seccionConClima = new Seccion("CuerpoACuerpo");

        ArrayList<String> secciones = new ArrayList<>();
        secciones.add("CuerpoACuerpo");

        Nevada cartaEspecialClima = new Nevada();

        Clima climaNevado = cartaEspecialClima.CrearClima();

        CartaUnidad primeraCartaPuntajeUno = new CartaUnidad("CartaTest1",secciones, 3);
        CartaUnidad segundaCartaPuntajeUno = new CartaUnidad("CartaTest2",secciones, 3);


        seccionConClima.agregarCarta(primeraCartaPuntajeUno);
        seccionConClima.agregarCarta(segundaCartaPuntajeUno);

        int puntajeSinClima = seccionConClima.getPuntajeTotal();

        seccionConClima.afectarClima(climaNevado);

        assertTrue((puntajeSinClima > seccionConClima.getPuntajeTotal()));

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
    * */
}
