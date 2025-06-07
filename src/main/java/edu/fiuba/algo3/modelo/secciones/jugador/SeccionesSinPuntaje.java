package edu.fiuba.algo3.modelo.secciones.jugador;

import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.cartas.Carta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeccionesSinPuntaje {

    private static final Map<String, SeccionesSinPuntaje> instancias = new HashMap<>();

    private final Map<String, SeccionSinPuntaje> seccionesDelJugador;

    private SeccionesSinPuntaje() throws TipoDeSeccionInvalidaError {
        seccionesDelJugador = new HashMap<>();
        seccionesDelJugador.put("Mano", new SeccionSinPuntaje());
        seccionesDelJugador.put("Descarte", new SeccionSinPuntaje());
    }

    public static SeccionesSinPuntaje seccionesDelJugador(String jugadorId) throws TipoDeSeccionInvalidaError {
        if (!instancias.containsKey(jugadorId)) {
            instancias.put(jugadorId, new SeccionesSinPuntaje());
        }
        return instancias.get(jugadorId);
    }

    private SeccionSinPuntaje seccion(String clave) {
        SeccionSinPuntaje seccion = seccionesDelJugador.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }
        return seccion;
    }

    public int cartasRestantes(String clave) {
        return seccion(clave).cartasRestantes();
    }

    public Carta removerCarta(String clave, int index) {
        return seccion(clave).removerCarta(index);
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

