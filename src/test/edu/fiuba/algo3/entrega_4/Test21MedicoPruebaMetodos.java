//package edu.fiuba.algo3.entrega_4;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import org.junit.jupiter.api.Test;
//
//import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
//import edu.fiuba.algo3.modelo.modificadores.Base;
//import edu.fiuba.algo3.modelo.modificadores.Medico;
//import edu.fiuba.algo3.modelo.principal.Juego;
//import edu.fiuba.algo3.modelo.principal.Jugador;
//import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;
//import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
//import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
//import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
//
//public class Test21MedicoPruebaMetodos {
//    @Test
//    public void testMedicoCubreMetodosConFlujoReal() throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolicitudDeCartas, edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos {
//        Medico medico = new Medico(new Base());
//        Base base = new Base();
//        List<String> secciones = new ArrayList<>();
//        secciones.add("CuerpoACuerpo");
//        List<CartaUnidad> cartas = new ArrayList<>();
//        // Una carta base para poder descartar
//        cartas.add(new CartaUnidad("Base'e", secciones, 8, base));
//        // 19 cartas Medico
//        for (int i = 0; i < 19; i++) {
//            cartas.add(new CartaUnidad("Medico'e", secciones, 8, medico));
//        }
//        // Otra carta base para asegurar 21
//        cartas.add(new CartaUnidad("Base'e2", secciones, 8, base));
//        Mazo mazoMock1 = new Mazo(new ArrayList<>(cartas));
//        Mazo mazoMock2 = new Mazo(new ArrayList<>(cartas));
//        Jugador jugador1 = new Jugador("JugadorTest1");
//        jugador1.agregarMazo(mazoMock1);
//        Jugador jugador2 = new Jugador("JugadorTest2");
//        jugador2.agregarMazo(mazoMock2);
//        Juego juego = new Juego(jugador1, jugador2);
//        jugador1.agregarCartasAMano(10);
//        // Buscar y jugar una carta base para llenar el descarte
//        CartaUnidad cartaBase = null;
//        for (var carta : jugador1.cartasEnMano()) {
//            if (carta instanceof CartaUnidad && ((CartaUnidad) carta).getModificadores().isEmpty()) {
//                cartaBase = (CartaUnidad) carta;
//                break;
//            }
//        }
//        if (cartaBase == null) {
//            throw new RuntimeException("No se encontró una carta base en la mano para el test");
//        }
//        CartaUnidad finalCartaBase = cartaBase;
//        assertDoesNotThrow(() -> {
//            try {
//                juego.jugarCarta(finalCartaBase, new Seccion("CuerpoACuerpo", 0));
//            } catch (Exception e) {
//                throw new RuntimeException(e.getMessage(), e);
//            }
//        });
//        // Finalizar la ronda para que la carta vaya al descarte
//        juego.finalizarRonda();
//        // Asegurarse que el jugador activo es jugador1
//        juego.definirQuienEmpieza(0);
//        // Repartir cartas a la mano para la siguiente jugada
//        jugador1.agregarCartasAMano(1);
//        // Buscar y jugar una carta Medico
//        CartaUnidad cartaMedico = null;
//        for (var carta : jugador1.cartasEnMano()) {
//            if (carta instanceof CartaUnidad && ((CartaUnidad) carta).getModificadores().contains("Medico")) {
//                cartaMedico = (CartaUnidad) carta;
//                break;
//            }
//        }
//        if (cartaMedico == null) {
//            throw new RuntimeException("No se encontró una carta Médico en la mano para el test");
//        }
//        CartaUnidad finalCartaMedico = cartaMedico;
//        assertDoesNotThrow(() -> {
//            try {
//                juego.jugarCarta(finalCartaMedico, new Seccion("CuerpoACuerpo", 0));
//            } catch (Exception e) {
//                throw new RuntimeException(e.getMessage(), e);
//            }
//        });
//    }
//}
