package edu.fiuba.algo3.modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeccionesSinPuntaje {

    private static SeccionesSinPuntaje instancia = null;
    Map<String, SeccionSinPuntaje> seccionesDelJugador;

    private SeccionesSinPuntaje() throws TipoDeSeccionInvalidaError {
        seccionesDelJugador = new HashMap<>();

        seccionesDelJugador.put("Mano", new SeccionSinPuntaje());
        seccionesDelJugador.put("Descarte", new SeccionSinPuntaje());
    }

    private SeccionSinPuntaje seccionSinPuntaje(String clave) throws TipoDeSeccionInvalidaError{
        SeccionSinPuntaje seccion = seccionesDelJugador.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }
        return seccion;
    }

    private SeccionSinPuntaje seccion(String clave) throws TipoDeSeccionInvalidaError{
        SeccionSinPuntaje seccion = seccionesDelJugador.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }
        return seccion;
    }

    public static SeccionesSinPuntaje seccionesDelJugador() throws TipoDeSeccionInvalidaError {
        if (instancia == null) {
            instancia = new SeccionesSinPuntaje();
        }
        return instancia;
    }

    public int cartasRestantes(String clave) throws TipoDeSeccionInvalidaError {
        SeccionSinPuntaje seccion = seccion(clave);
        return seccion.cartasRestantes();
    }

    public Carta removerCarta(String clave, int index) throws TipoDeSeccionInvalidaError {
        SeccionSinPuntaje seccion = seccion(clave);
        return seccion.removerCarta(index);
    }

    public Carta removerCarta(String clave, Carta carta) throws TipoDeSeccionInvalidaError {
        SeccionSinPuntaje seccion = seccion(clave);
        seccion.removerCarta(carta);
        return carta;
    }

    public void agregarCarta(String clave, Carta carta) throws TipoDeSeccionInvalidaError {
        SeccionSinPuntaje seccion = seccion(clave);
        seccion.agregarCarta(carta);
    }

    public void agregarCartas(String clave, List<Carta> cartas) throws TipoDeSeccionInvalidaError {
        SeccionSinPuntaje seccion = seccion(clave);
        seccion.agregarCartas(cartas);
    }



}
