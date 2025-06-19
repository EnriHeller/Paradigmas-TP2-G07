package edu.fiuba.algo3.modelo;

public class Errores {
    // Error: Sección inválida
    public static class TipoDeSeccionInvalidaError extends Throwable {
        public TipoDeSeccionInvalidaError() { super(); }
    }

    // Error: No se puede cumplir solicitud de cartas
    public static class NoSePuedeCumplirSolicitudDeCartas extends Throwable {
        public NoSePuedeCumplirSolicitudDeCartas() { super(); }
    }

    // Error: No se puede eliminar clima si no hay clima
    public static class NoSePuedeEliminarClimaSiNoHayClima extends Throwable {
        public NoSePuedeEliminarClimaSiNoHayClima() { super(); }
    }

    // Error: Carta no jugable
    public static class CartaNoJugable extends Throwable {
        public CartaNoJugable() { super(); }
    }

    // Error: No es la misma unidad
    public static class NoEsLaMismaUnidad extends Exception {
        public NoEsLaMismaUnidad(String mensaje) { super(mensaje); }
    }

    // Error: Uno de los mazos no cumple requisitos
    public static class UnoDeLosMazosNoCumpleRequitos extends Exception {
        public UnoDeLosMazosNoCumpleRequitos() { super(); }
    }

    // Error: Cantidad máxima de descarte alcanzada
    public static class CantidadMaximaDeDescarteAlcanzadaError extends RuntimeException {
        public CantidadMaximaDeDescarteAlcanzadaError(String message) { super(message); }
    }

    // Error: No se encontró la carta buscada
    public static class CartaNoEncontradaError extends RuntimeException {
        public CartaNoEncontradaError(String message) { super(message); }
    }
}
