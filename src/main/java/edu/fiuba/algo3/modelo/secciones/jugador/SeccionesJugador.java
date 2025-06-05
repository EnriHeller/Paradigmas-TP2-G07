package edu.fiuba.algo3.modelo.secciones.jugador;

import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.cartas.Carta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeccionesJugador {

    private static SeccionesJugador instancia = null;
    static Map<String, SeccionJugador> seccionesDelJugador;

    private SeccionesJugador() throws TipoDeSeccionInvalidaError {
        seccionesDelJugador = new HashMap<>();

        seccionesDelJugador.put("Mano", new SeccionJugador());
        seccionesDelJugador.put("Descarte", new SeccionJugador());
    }

    private SeccionJugador SeccionJugador(String clave) throws TipoDeSeccionInvalidaError{
        SeccionJugador seccion = seccionesDelJugador.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }
        return seccion;
    }

    private static SeccionJugador seccion(String clave) throws TipoDeSeccionInvalidaError{
        SeccionJugador seccion = seccionesDelJugador.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }
        return seccion;
    }

    public static SeccionesJugador seccionesDelJugador() throws TipoDeSeccionInvalidaError {
        if (instancia == null) {
            instancia = new SeccionesJugador();
        }
        return instancia;
    }

    public static int cartasRestantes(String clave) throws TipoDeSeccionInvalidaError {
    SeccionesJugador instancia = seccionesDelJugador();
    SeccionJugador seccion = SeccionesJugador.seccion(clave);
    return (seccion.cartasRestantes());
    }


    public Carta removerCarta(String clave, int index) throws TipoDeSeccionInvalidaError {
        SeccionJugador seccion = seccion(clave);
        return seccion.removerCarta(index);
    }

    public void agregarCarta(String clave, Carta carta) throws TipoDeSeccionInvalidaError {
        SeccionJugador seccion = seccion(clave);
        seccion.agregarCarta(carta);
    }

    public static void agregarCartas(String clave, List<Carta> cartas) throws TipoDeSeccionInvalidaError {
        SeccionJugador seccion = seccion(clave);
        seccion.agregarCartas(cartas);
    }



}
