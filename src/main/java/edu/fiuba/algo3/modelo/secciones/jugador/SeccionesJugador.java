package edu.fiuba.algo3.modelo.secciones.jugador;

import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.cartas.Carta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeccionesJugador {

    private static final Map<String, SeccionesJugador> instancias = new HashMap<>();
    private final Map<String, SeccionJugador> seccionesDelJugador;

    private SeccionesJugador() throws TipoDeSeccionInvalidaError {
        seccionesDelJugador = new HashMap<>();
        seccionesDelJugador.put("Mano", new SeccionJugador());
        seccionesDelJugador.put("Descarte", new SeccionJugador());
    }

    public static SeccionesJugador seccionesDelJugador(String jugadorId) throws TipoDeSeccionInvalidaError {
        if (!instancias.containsKey(jugadorId)) {
            instancias.put(jugadorId, new SeccionesJugador());
        }
        return instancias.get(jugadorId);
    }

    private SeccionJugador seccion(String clave) {
        SeccionJugador seccion = seccionesDelJugador.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }
        return seccion;
    }

    public int cartasRestantes(String clave) {
        return seccion(clave).cartasRestantes();
    }

    public Carta removerCarta(String clave, Carta carta) {
        return seccion(clave).removerCarta(carta);
    }
    public List<Carta> removerCartas(String clave, List<Carta> cartas) {
        return seccion(clave).removerCartas(cartas);
    }

    public void agregarCarta(String clave, Carta carta) {
        seccion(clave).agregarCarta(carta);
    }

    public void agregarCartas(String clave, List<Carta> cartas) {
        seccion(clave).agregarCartas(cartas);
    }



}
