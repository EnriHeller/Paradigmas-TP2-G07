package edu.fiuba.algo3.modelo.secciones.tablero;

import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.cartas.especiales.Clima;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tablero {
    private static Tablero instancia = null;
    private Map<String, Seccion> secciones;

    private Tablero() throws TipoDeSeccionInvalidaError {
        secciones = new HashMap<>();

        secciones.put("Rango0", new Seccion("Rango"));
        secciones.put("Asedio0", new Seccion("Asedio"));
        secciones.put("CuerpoACuerpo0", new Seccion("CuerpoACuerpo"));

        secciones.put("Rango1", new Seccion("Rango"));
        secciones.put("Asedio1", new Seccion("Asedio"));
        secciones.put("Cuerpo1", new Seccion("CuerpoACuerpo"));
    }

    public static Tablero getInstancia() throws TipoDeSeccionInvalidaError {
        if (instancia == null) {
            instancia = new Tablero();
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

    private Seccion seccion(String clave) throws TipoDeSeccionInvalidaError{
        Seccion seccion = secciones.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }
        return seccion;
    }

    public void afectarClima(String clave, Clima nuevoClima) throws TipoDeSeccionInvalidaError{
        Seccion seccion = seccion(clave);
        seccion.afectarClima(nuevoClima);
    }

    public int getPuntajeTotal(String clave) throws TipoDeSeccionInvalidaError {
        Seccion seccion = seccion(clave);
        return seccion.getPuntajeTotal();
    }

    public List<CartaUnidad> getCartas(String clave) throws TipoDeSeccionInvalidaError {
        Seccion seccion = seccion(clave);
        return  seccion.getCartas();
    }

    public boolean contiene(String clave, Carta carta) throws TipoDeSeccionInvalidaError{
        Seccion seccion = seccion(clave);
        return seccion.contiene(carta);
    }

    public List<CartaUnidad> cartasMasFuertesDelTablero(int cantidadCartas) {
        List<CartaUnidad> cartasTotales = new ArrayList<>();

        //Recorro todas las secciones para unirlas a una lista
        for(Seccion seccion : secciones.values()) {
            cartasTotales.addAll(seccion.getCartas());
        }
        //Ordeno las cartas de mayor a menor
        Collections.sort(cartasTotales, (c1, c2) -> Integer.compare(c2.ValorActual(), c1.ValorActual()));

        return cartasTotales.subList(0, cantidadCartas);
    }

    public CartaUnidad removerCarta(String clave, CartaUnidad carta) {
        Seccion seccion = secciones.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }

        return seccion.removerCarta(carta);
    }
    public List<CartaUnidad> removerCartas(String clave, List<CartaUnidad> cartas) {
        Seccion seccion = secciones.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }
        return seccion.removerCartas(cartas);
    }

    public void agregarCarta(String clave, CartaUnidad carta) {

        Seccion seccion = secciones.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }

        seccion.agregarCarta(carta);
    }

    public void agregarCartas(String clave, List<CartaUnidad> cartas) {

        Seccion seccion = secciones.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }
        seccion.agregarCartas(cartas);
    }

}

