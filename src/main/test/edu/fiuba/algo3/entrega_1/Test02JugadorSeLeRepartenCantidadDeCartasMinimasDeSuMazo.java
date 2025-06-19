package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.controller.ConstructorMazo;
import edu.fiuba.algo3.mocks.ConstructorMazoMock;
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
    public void jugadorRecibe10CartasInicialesEnSuMano() throws Exception, NoSePuedeCumplirSolicitudDeCartas, UnoDeLosMazosNoCumpleRequitos, TipoDeSeccionInvalidaError  {

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

        assertTrue(j1.TengoCartasSuficientesEnMano());
        assertTrue(j2.TengoCartasSuficientesEnMano());

    }
}