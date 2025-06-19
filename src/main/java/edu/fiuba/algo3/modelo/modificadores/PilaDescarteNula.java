package edu.fiuba.algo3.modelo.modificadores;

public class PilaDescarteNula extends RuntimeException {
    public PilaDescarteNula() {
        super("La pila de descarte está vacía o nula.");
    }
}
