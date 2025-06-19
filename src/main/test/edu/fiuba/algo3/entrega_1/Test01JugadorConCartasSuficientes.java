package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Errores.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.Errores.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList; // o import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class Test01JugadorConCartasSuficientes {

    @Test
    public void jugadorTieneCartasSuficientesAlEmpezar() throws UnoDeLosMazosNoCumpleRequitos, TipoDeSeccionInvalidaError, NoSePuedeCumplirSolicitudDeCartas {

        // Crear 21 Cartas
        List<Carta> cartas = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            cartas.add(new CartaUnidad());
        }

        Mazo m1 = new Mazo(cartas);

        Jugador j1 = new Jugador("Pepito");

        j1.agregarMazo(m1);
        assertTrue(j1.HayCartasSuficientesEnMazo(m1));

        

    }


}