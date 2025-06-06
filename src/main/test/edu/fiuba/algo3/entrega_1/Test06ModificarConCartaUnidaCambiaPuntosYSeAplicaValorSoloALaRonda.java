package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Secciones;
import org.junit.jupiter.api.Test;

public class Test06ModificarConCartaUnidaCambiaPuntosYSeAplicaValorSoloALaRonda {
    @Test
    public void modificarConCartaUnidaCambiaPuntosYSeAplicaValorSoloALaRonda() throws TipoDeSeccionInvalidaError, CartaNoJugable {
        //new seccion
        // creamos 2 carta con modificador unidad
        // la metemos en seccion
        // dame el puntaje de seccion (8)
        // meto la segunda carta
        // dame el puntaje de seccion y deberia ser (32)

        Unidas unidad = new Unidas(new Base());

        Secciones secciones = Secciones.getInstancia();

        ArrayList<String> seccionesCarta = new ArrayList<String>();
        seccionesCarta.add("Asedio");

        CartaUnidad primeraCarta = new CartaUnidad("TipoTest", seccionesCarta, 8, unidad);
        CartaUnidad segundaCarta = new CartaUnidad("TipoTest",seccionesCarta, 8, unidad);

        secciones.agregarCarta("AsedioJugador1", primeraCarta);

        // agrego la o las unidades iguales y le duplico el puntaje a todas
        secciones.agregarCarta("AsedioJugador1", segundaCarta);
        int puntajeCon2Unidad = secciones.getPuntaje("AsedioJugador1");
        assertTrue( (puntajeCon2Unidad == 32) );

    }
}
