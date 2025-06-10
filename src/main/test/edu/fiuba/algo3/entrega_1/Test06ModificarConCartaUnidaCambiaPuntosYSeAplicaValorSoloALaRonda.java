package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesJugador;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import org.junit.jupiter.api.Test;

public class Test06ModificarConCartaUnidaCambiaPuntosYSeAplicaValorSoloALaRonda {
    @Test
    public void modificarConCartaUnidaCambiaPuntosYSeAplicaValorSoloALaRonda() {
        try {
            Unidas unidad = new Unidas(new Base());
            Tablero secciones = null;
            try {
                secciones = Tablero.getInstancia();
            } catch (edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError e) {
                org.junit.jupiter.api.Assertions.fail("No se esperaba TipoDeSeccionInvalidaError al obtener Tablero: " + e.getMessage());
            }
            ArrayList<String> seccionesCarta = new ArrayList<String>();
            seccionesCarta.add("Asedio");
            CartaUnidad primeraCarta = new CartaUnidad("TipoTest", seccionesCarta, 8, unidad);
            CartaUnidad segundaCarta = new CartaUnidad("TipoTest",seccionesCarta, 8, unidad);
            if (secciones != null) {
                try {
                    secciones.agregarCartas("Asedio0", java.util.Collections.singletonList(primeraCarta));
                    SeccionesJugador seccionesJugador = null;
                    try {
                        seccionesJugador = SeccionesJugador.seccionesDelJugador("0");
                    } catch (edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError e) {
                        org.junit.jupiter.api.Assertions.fail("No se esperaba TipoDeSeccionInvalidaError al obtener SeccionesJugador: " + e.getMessage());
                    }
                    if (seccionesJugador != null) {
                        Contexto contexto = new Contexto(secciones, "Asedio", segundaCarta,  0, seccionesJugador, new Jugador());
                        secciones.agregarCartas("Asedio0", java.util.Collections.singletonList(segundaCarta));
                        try {
                            segundaCarta.aplicarModificador(contexto);
                        } catch (edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError e) {
                            org.junit.jupiter.api.Assertions.fail("No se esperaba TipoDeSeccionInvalidaError al aplicar modificador: " + e.getMessage());
                        }
                        int puntajeCon2Unidad = secciones.getPuntaje("Asedio0");
                        assertTrue( (puntajeCon2Unidad == 32) );
                    }
                } catch (Exception e) {
                    org.junit.jupiter.api.Assertions.fail("No se esperaba excepción en la lógica principal: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            org.junit.jupiter.api.Assertions.fail("No se esperaba excepción general: " + e.getMessage());
        }
    }
}
