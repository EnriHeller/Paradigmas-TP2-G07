package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.especiales.CartaNevada;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.cartas.especiales.Clima;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class Test07CartaEspecialDeClimaModificaValorDeLasCartasEnSeccionCorrespondiente {

    //
    @Test
    public void cartaEspecialDeClimaModificaValorDeLasCartasEnSeccionCorrespondiente() {
        Seccion seccionConClima = null;
        try {
            seccionConClima = new Seccion("CuerpoACuerpo");
        } catch (edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError e) {
            org.junit.jupiter.api.Assertions.fail("No se esperaba TipoDeSeccionInvalidaError al crear Seccion: " + e.getMessage());
        }
        if (seccionConClima != null) {
            try {
                ArrayList<String> secciones = new ArrayList<>();
                secciones.add("CuerpoACuerpo");
                CartaNevada cartaEspecialClima = new CartaNevada();
                Clima climaNevado = cartaEspecialClima.crearClima();
                CartaUnidad primeraCartaPuntajeUno = new CartaUnidad("CartaTest1",secciones, 3);
                CartaUnidad segundaCartaPuntajeUno = new CartaUnidad("CartaTest2",secciones, 3);
                seccionConClima.agregarCarta(primeraCartaPuntajeUno);
                seccionConClima.agregarCarta(segundaCartaPuntajeUno);
                int puntajeSinClima = seccionConClima.getPuntajeTotal();
                try {
                    seccionConClima.afectarClima(climaNevado);
                } catch (edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima e) {
                    org.junit.jupiter.api.Assertions.fail("No se esperaba NoSePuedeEliminarClimaSiNoHayClima al afectar clima: " + e.getMessage());
                }
                assertTrue((puntajeSinClima > seccionConClima.getPuntajeTotal()));
            } catch (Exception e) {
                org.junit.jupiter.api.Assertions.fail("No se esperaba excepción: " + e.getMessage());
            }
        }
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
