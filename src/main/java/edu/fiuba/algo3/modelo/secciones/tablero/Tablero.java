package edu.fiuba.algo3.modelo.secciones.tablero;

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

        secciones.put("Rango0", new Seccion("Rango"));
        secciones.put("Asedio0", new Seccion("Asedio"));
        secciones.put("CuerpoACuerpo0", new Seccion("CuerpoACuerpo"));

        secciones.put("Rango1", new Seccion("Rango"));
        secciones.put("Asedio1", new Seccion("Asedio"));
        secciones.put("CuerpoACuerpo1", new Seccion("CuerpoACuerpo"));
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

    public void afectarClima(String clave, Clima nuevoClima) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        Seccion seccion = seccion(clave);
        seccion.afectarClima(nuevoClima);
    }

    public int PuntajeTotalSecciones() {
        int total = 0;
        for (Seccion seccion : secciones.values()) {
            total += seccion.getPuntajeTotal();
        }
        return total;
    }

    public int PuntajeSeccion(String clave) throws TipoDeSeccionInvalidaError {
        Seccion seccion = seccion(clave);
        return seccion.getPuntajeTotal();
    }

    public boolean afectadaClima(String clave) throws TipoDeSeccionInvalidaError {
        Seccion seccion = seccion(clave);
        return  seccion.hayClima();
    }

    //Obtengo todas las cartas del tablero
    public List<CartaUnidad> getCartas() {
        List<CartaUnidad> cartasTotales = new ArrayList<>();

        for (Seccion seccion : secciones.values()) {
            cartasTotales.addAll(seccion.getCartas());
        }

        return cartasTotales;
    }

    public List<CartaUnidad> getCartasSeccion(String clave) throws TipoDeSeccionInvalidaError {
        Seccion seccion = seccion(clave);
        return seccion.getCartas();
    }

    public Map<String, Seccion> getSecciones() {
        return secciones;
}

    public boolean contiene(String clave, Carta carta) throws TipoDeSeccionInvalidaError{
        Seccion seccion = seccion(clave);
        return seccion.contiene(carta);
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

    public void removerCartasDeValorMaximo() throws TipoDeSeccionInvalidaError{
        int max = calcularValorMaximoEnTablero();
        removerCartasDeValorN(max);
    }

    private void removerCartasDeValorN(int n) throws TipoDeSeccionInvalidaError {
        for (String claveSeccion : getSecciones().keySet()) {
            List<CartaUnidad> cartas = getCartasSeccion(claveSeccion);

            // Usar removeIf sobre la lista de cartas de la sección
            cartas.removeIf(carta ->
                    carta.ValorActual() == n &&
                            !carta.mostrarCarta().contains("Legendaria")
            );
        }
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

