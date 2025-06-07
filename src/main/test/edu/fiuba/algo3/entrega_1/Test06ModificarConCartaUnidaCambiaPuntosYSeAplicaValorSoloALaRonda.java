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
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import org.junit.jupiter.api.Test;

public class Test06ModificarConCartaUnidaCambiaPuntosYSeAplicaValorSoloALaRonda {
    @Test
    public void modificarConCartaUnidaCambiaPuntosYSeAplicaValorSoloALaRonda() throws TipoDeSeccionInvalidaError, CartaNoJugable {

        Unidas unidad = new Unidas(new Base());

        Tablero secciones = Tablero.getInstancia();

        ArrayList<String> seccionesCarta = new ArrayList<String>();
        seccionesCarta.add("Asedio");

        CartaUnidad primeraCarta = new CartaUnidad("TipoTest", seccionesCarta, 8, unidad);
        CartaUnidad segundaCarta = new CartaUnidad("TipoTest",seccionesCarta, 8, unidad);

        secciones.agregarCarta("Asedio0", primeraCarta);

        // agrego la o las unidades iguales y le duplico el puntaje a todas
        secciones.agregarCarta("Asedio0", segundaCarta);
        int puntajeCon2Unidad = secciones.getPuntaje("Asedio0");
        assertTrue( (puntajeCon2Unidad == 32) );

    }
}
