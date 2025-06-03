package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

public class Test06ModificarConCartaUnidaCambiaPuntosYSeAplicaValorSoloALaRonda {
    @Test
    public void modificarConCartaUnidaCambiaPuntosYSeAplicaValorSoloALaRonda() throws TipoDeSeccionInvalidaError {
        //new seccion
        // creamos 2 carta con modificador unidad
        // la metemos en seccion
        // dame el puntaje de seccion (8)
        // meto la segunda carta
        // dame el puntaje de seccion y deberia ser (32)

        Unidad unidad = new Unidad();

        Seccion nuevaSeccion = new Seccion("Asedio");

        ArrayList<String> secciones = new ArrayList<String>();
        secciones.add("Asedio");

        CartaUnidad primeraCarta = new CartaUnidad(secciones, 8, unidad);
        CartaUnidad segundaCarta = new CartaUnidad(secciones, 8, unidad);

        nuevaSeccion.agregarCarta(primeraCarta);

        // agrego la o las unidades iguales y le duplico el puntaje a todas

        int puntajeCon1Unidad = nuevaSeccion.getPuntajeTotal();
        nuevaSeccion.agregarCarta(segundaCarta);
        int puntajeCon2Unidad = nuevaSeccion.getPuntajeTotal();
        assertTrue( (puntajeCon2Unidad > puntajeCon1Unidad ));

    }
}
