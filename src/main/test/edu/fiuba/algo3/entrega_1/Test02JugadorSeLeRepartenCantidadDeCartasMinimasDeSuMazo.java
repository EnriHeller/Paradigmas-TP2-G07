package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Errores.*;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test02JugadorSeLeRepartenCantidadDeCartasMinimasDeSuMazo {

    @Test
    public void jugadorRecibe10CartasInicialesEnSuMano() throws NoSePuedeCumplirSolicitudDeCartas, UnoDeLosMazosNoCumpleRequitos, TipoDeSeccionInvalidaError  {

        // Crear 21 Cartas
        List<Carta> cartas = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            cartas.add(new CartaUnidad());
        }

        Mazo m1 = new Mazo(cartas);
        Mazo m2 = new Mazo(cartas);
        
        Jugador j1 = new Jugador("Pepito");
        Jugador j2 = new Jugador("Juanito");

        j1.agregarMazo(m1);
        j2.agregarMazo(m2);

        Juego juego = new Juego(j1, j2);
        
        juego.repartirManoInicial(j1);
        juego.repartirManoInicial(j2);

        assertTrue(j1.TengoCartasSuficientesEnMano());
        assertTrue(j2.TengoCartasSuficientesEnMano());

    }
}