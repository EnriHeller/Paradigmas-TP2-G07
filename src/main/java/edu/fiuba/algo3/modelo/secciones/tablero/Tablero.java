package edu.fiuba.algo3.modelo.secciones.tablero;

import edu.fiuba.algo3.modelo.cartas.unidades.Puntuable;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.cartas.especiales.Clima;
import java.util.*;

public class Tablero {
    private static Tablero instancia = null;
    private Map<String, Seccion> secciones;

    private Tablero() throws TipoDeSeccionInvalidaError {
        secciones = new HashMap<>();

        secciones.put("Rango0", new Seccion("Rango",0));
        secciones.put("Asedio0", new Seccion("Asedio",0));
        secciones.put("CuerpoACuerpo0", new Seccion("CuerpoACuerpo",0));

        secciones.put("Rango1", new Seccion("Rango",1));
        secciones.put("Asedio1", new Seccion("Asedio",1));
        secciones.put("CuerpoACuerpo1", new Seccion("CuerpoACuerpo",1));
    }

    public static Tablero getInstancia() throws TipoDeSeccionInvalidaError {
        if (instancia == null) {
            instancia = new Tablero();
        }
        return instancia;
    }

    public boolean contieneSeccion(Seccion seccionBuscada) {
        return secciones.values().contains(seccionBuscada);
    }

    public int getPuntaje(String clave) {
        Seccion seccion = secciones.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }
        return seccion.getPuntajeTotal();
    }

    public void existeSeccion(Seccion seccion) throws TipoDeSeccionInvalidaError{

        if (!contieneSeccion(seccion)) {
            throw new IllegalArgumentException("Clave inválida: " + seccion);
        }
    }

    public void afectarClima(Seccion seccion, Clima nuevoClima) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        existeSeccion(seccion);
        obtenerSeccion(seccion).afectarClima(nuevoClima);
    }

    public void afectarClimas(){
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
        Seccion seccionReal = secciones.get(clave);

        return seccionReal;
    }

    public boolean afectadaClima(Seccion seccion) throws TipoDeSeccionInvalidaError {
        existeSeccion(seccion);
        return  obtenerSeccion(seccion).hayClima();
    }

    //Obtengo todas las cartas del tablero
    public List<CartaUnidad> getCartas() {
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

    public boolean contiene(Seccion seccion, Carta carta) throws TipoDeSeccionInvalidaError{
        existeSeccion(seccion);
        return obtenerSeccion(seccion).contiene(carta);
    }

    public CartaUnidad removerCarta(Seccion seccion, CartaUnidad carta) throws TipoDeSeccionInvalidaError {
        existeSeccion(seccion);

        return obtenerSeccion(seccion).removerCarta(carta);
    }
    
    public List<CartaUnidad> removerCartas(Seccion seccion, List<CartaUnidad> cartas) throws TipoDeSeccionInvalidaError {
        existeSeccion(seccion);
        return obtenerSeccion(seccion).removerCartas(cartas);
    }

    public void removerCartasDeValorMaximo() throws TipoDeSeccionInvalidaError{
        int max = calcularValorMaximoEnTablero();
        removerCartasDeValorN(max);
    }

    public Seccion seccionContraria(Seccion seccion) throws TipoDeSeccionInvalidaError {
        existeSeccion(seccion);
        String clave = seccion.getClave();
        int jugadorId = seccion.getJugadorId();
        int jugadorContrario = 1 - jugadorId;

        String claveContraria = clave + jugadorContrario;

        Seccion seccionContraria = secciones.get(claveContraria);

        return seccionContraria;

    }

    private void removerCartasDeValorN(int n) throws TipoDeSeccionInvalidaError {
        for (Seccion seccion : secciones.values()) {
            List<CartaUnidad> cartas = getCartasSeccion(seccion);

            // Usar removeIf sobre la lista de cartas de la sección
            cartas.removeIf(carta ->
                    carta.ValorActual() == n &&
                            !carta.mostrarCarta().contains("Legendaria")
            );
        }
    }

    public List<CartaUnidad> removerCartasDeJugador(int jugadorID){
        List<CartaUnidad> cartasJugador = new ArrayList<>();

        String[] seccionesJugador = {
                "Rango" + jugadorID,
                "Asedio" + jugadorID,
                "CuerpoACuerpo" + jugadorID
        };

        for (String claveSeccion : seccionesJugador) {
            cartasJugador.addAll(removerCartas(claveSeccion));
        }

        return cartasJugador;
    }

    private List<CartaUnidad> removerCartas(String clave) {
        Seccion seccion = secciones.get(clave);
        if (seccion == null) {
            throw new IllegalArgumentException("Clave inválida: " + clave);
        }
        return seccion.removerCartas();
    }


    private int calcularValorMaximoEnTablero(){
        List<CartaUnidad> cartas = getCartas();
        if (cartas.isEmpty()) return 0;

        // Buscar el valor máximo entre cartas NO legendarias
        int max = 0;

        for (CartaUnidad carta : cartas) {
            // Usar mostrarCarta() para detectar si tiene el modificador Legendaria
            if (!carta.mostrarCarta().contains("Legendaria")) {
                int valor = carta.ValorActual();
                if (valor > max) max = valor;
            }
        }
        return max;
    }

    public void agregarCarta(Seccion seccion, CartaUnidad carta) throws TipoDeSeccionInvalidaError {
        existeSeccion(seccion);
        seccion.agregarCarta(carta);
    }
    
    public void agregarCartas(Seccion seccion, List<CartaUnidad> cartas) throws TipoDeSeccionInvalidaError {

        existeSeccion(seccion);
        seccion.agregarCartas(cartas);
    }

}

