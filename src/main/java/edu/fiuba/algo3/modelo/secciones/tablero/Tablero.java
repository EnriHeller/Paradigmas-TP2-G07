package edu.fiuba.algo3.modelo.secciones.tablero;

import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.cartas.especiales.Clima;

import java.util.*;

public class Tablero {
    private Map<String, Seccion> secciones;

    public Tablero() throws TipoDeSeccionInvalidaError {
        secciones = new HashMap<>();

        secciones.put("Rango0", new Seccion("Rango", 0));
        secciones.put("Asedio0", new Seccion("Asedio", 0));
        secciones.put("CuerpoACuerpo0", new Seccion("CuerpoACuerpo", 0));

        secciones.put("Rango1", new Seccion("Rango", 1));
        secciones.put("Asedio1", new Seccion("Asedio", 1));
        secciones.put("CuerpoACuerpo1", new Seccion("CuerpoACuerpo", 1));
    }

    public boolean contieneSeccion(Seccion seccionBuscada) {
        return secciones.containsValue(seccionBuscada);
    }

    public int getPuntaje(String clave) {
        Seccion seccion = secciones.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }
        return seccion.getPuntajeTotal();
    }

    public void existeSeccion(Seccion seccion) throws TipoDeSeccionInvalidaError {
        if (!contieneSeccion(seccion)) {
            throw new IllegalArgumentException("Clave inválida: " + seccion.getClave());
        }
    }

    public void afectarClima(Seccion seccion, Clima nuevoClima) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        existeSeccion(seccion);
        obtenerSeccion(seccion).afectarClima(nuevoClima);
    }

    public void afectarClimas() {
        for (Seccion seccion : secciones.values()) {
            seccion.afectarClima();
        }
    }

    public int PuntajeTotalSecciones() {
        int total = 0;
        for (Seccion seccion : secciones.values()) {
            total += seccion.getPuntajeTotal();
        }
        return total;
    }

    public int PuntajeSeccion(Seccion seccion) throws TipoDeSeccionInvalidaError {
        existeSeccion(seccion);
        return obtenerSeccion(seccion).getPuntajeTotal();
    }

    public Seccion obtenerSeccion(Seccion seccion) {
        String clave = seccion.getClave() + seccion.getJugadorId();
        return secciones.get(clave);
    }

    public boolean afectadaClima(Seccion seccion) throws TipoDeSeccionInvalidaError {
        existeSeccion(seccion);
        return obtenerSeccion(seccion).hayClima();
    }

    private List<CartaUnidad> getCartas() {
        List<CartaUnidad> cartasTotales = new ArrayList<>();
        for (Seccion seccion : secciones.values()) {
            cartasTotales.addAll(seccion.getCartas());
        }
        return cartasTotales;
    }

    public List<CartaUnidad> getCartasSeccion(Seccion seccion) throws TipoDeSeccionInvalidaError {
        existeSeccion(seccion);
        return obtenerSeccion(seccion).getCartas();
    }

    public boolean contiene(Seccion seccion, Carta carta) throws TipoDeSeccionInvalidaError {
        existeSeccion(seccion);
        return obtenerSeccion(seccion).contiene(carta);
    }

    public CartaUnidad removerCarta(Seccion seccion, CartaUnidad carta) throws TipoDeSeccionInvalidaError {
        existeSeccion(seccion);
        return obtenerSeccion(seccion).removerCarta(carta);
    }

    public void removerCartasDeValorMaximo() throws TipoDeSeccionInvalidaError {
        int max = calcularValorMaximoEnTablero();
        removerCartasDeValorN(max);
    }

    public Seccion seccionContraria(Seccion seccion) throws TipoDeSeccionInvalidaError {
        existeSeccion(seccion);
        String clave = seccion.getClave();
        int jugadorId = seccion.getJugadorId();
        int jugadorContrario = 1 - jugadorId;
        return secciones.get(clave + jugadorContrario);
    }

    private void removerCartasDeValorN(int n) throws TipoDeSeccionInvalidaError {
        for (Seccion seccion : secciones.values()) {
            List<CartaUnidad> cartas = getCartasSeccion(seccion);
            cartas.removeIf(carta -> carta.ValorActual() == n && !carta.mostrarCarta().contains("Legendaria"));
        }
    }

    public List<CartaUnidad> removerCartasDeJugador(int jugadorID) {
        List<CartaUnidad> cartasJugador = new ArrayList<>();
        List<Seccion> secciones = new ArrayList<>();

        try {
            secciones.add(new Seccion("Rango", jugadorID));
            secciones.add(new Seccion("Asedio", jugadorID));
            secciones.add(new Seccion("CuerpoACuerpo", jugadorID));

            for (Seccion seccion : secciones) {
                cartasJugador.addAll(removerCartas(seccion));
            }
        } catch (TipoDeSeccionInvalidaError ignored) {
        }

        return cartasJugador;
    }

    private List<CartaUnidad> removerCartas(Seccion seccion) {

        return obtenerSeccion(seccion).removerCartas();
    }

    private int calcularValorMaximoEnTablero() {
        List<CartaUnidad> cartas = getCartas();
        int max = 0;
        for (CartaUnidad carta : cartas) {
            if (!carta.mostrarCarta().contains("Legendaria")) {
                max = Math.max(max, carta.ValorActual());
            }
        }
        return max;
    }

    public void agregarCarta(Seccion seccion, CartaUnidad carta) throws TipoDeSeccionInvalidaError {
        existeSeccion(seccion);
        obtenerSeccion(seccion).agregarCarta(carta);
    }

    public Seccion obtenerSeccionPorClave(String clave) {
        return secciones.get(clave);
    }

    public List<Seccion> obtenerSeccionesDelJugador(int jugadorID) {
        List<Seccion> secciones = new ArrayList<>();

        try {
            secciones.add(obtenerSeccion(new Seccion("Rango", jugadorID)));
            secciones.add(obtenerSeccion(new Seccion("Asedio", jugadorID)));
            secciones.add(obtenerSeccion(new Seccion("CuerpoACuerpo", jugadorID)));
        } catch (TipoDeSeccionInvalidaError ignored) {
        }

        return secciones;
    }
}
