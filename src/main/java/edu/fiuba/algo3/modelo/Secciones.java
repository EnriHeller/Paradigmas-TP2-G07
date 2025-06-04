package edu.fiuba.algo3.modelo;

import java.util.HashMap;
import java.util.Map;

public class Secciones {
    private static Secciones instancia = null;
    private Map<String, Seccion> secciones;

    private Secciones() throws TipoDeSeccionInvalidaError {
        secciones = new HashMap<>();

        secciones.put("RangoJugador1", new Seccion("Rango"));
        secciones.put("AsedioJugador1", new Seccion("Asedio"));
        secciones.put("CuerpoACuerpoJugador1", new Seccion("CuerpoACuerpo"));

        secciones.put("RangoJugador2", new Seccion("Rango"));
        secciones.put("AsedioJugador2", new Seccion("Asedio"));
        secciones.put("CuerpoACuerpoJugador2", new Seccion("CuerpoACuerpo"));
    }

    public static Secciones getInstancia() throws TipoDeSeccionInvalidaError {
        if (instancia == null) {
            instancia = new Secciones();
        }
        return instancia;
    }

    public int getPuntaje(String clave) {
        Seccion seccion = secciones.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }
        return seccion.getPuntajeTotal();
    }

    public Seccion getSeccion(String clave) {
        return secciones.get(clave);
    }

    private Seccion existeSeccion(String clave) throws TipoDeSeccionInvalidaError{
        Seccion seccion = secciones.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }
        return seccion;
    }

    public void agregarCarta(String clave, CartaUnidad carta) throws TipoDeSeccionInvalidaError {
        Seccion seccion = existeSeccion(clave);
        seccion.agregarCarta(carta);
    }

    public void afectarClima(String clave, Clima nuevoClima) throws TipoDeSeccionInvalidaError{
        Seccion seccion = existeSeccion(clave);
        seccion.afectarClima(nuevoClima);
    }

    public int getPuntajeTotal(String clave) throws TipoDeSeccionInvalidaError {
        Seccion seccion = existeSeccion(clave);
        return seccion.getPuntajeTotal();
    }
}

