package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import pkg.Jugador;
import pkg.CartaPuntuada;

import java.util.ArrayList;
import java.util.List;

//Verificar que un jugador posea cartas suficientes para empezar el juego en su mazo.

//Inicializo una instancia de Mazo (usando el constructor de mazos del juego) y ese mazo se lo paso a Jugador (inicializando una instancia de jugador). Tras haber inicializado el jugador con su mazo, uso un metodo de jugador para chequear que tenga un mazo con x cartas
public class Test01JugadorTieneMazoConCartasSuficientes {

    @Test
    public void testJugadorPuedeCrearseCon21Cartas() {

        List<CartaPuntuada> cartas = new ArrayList<>();
        
        for (int i = 0; i < 21; i++) {
            cartas.add(new CartaPuntuada());
        }
        assertDoesNotThrow(() -> {
            new Jugador("Jugador1", cartas);
        });
    }
}
