package pkg;

import java.util.List;

public class Jugador {
    private String identificador;
    private java.util.List<CartaPuntuada> mazo;

    public Jugador(String nombre, java.util.List<CartaPuntuada> mazo) {
        if (mazo == null || mazo.size() < 21) {
            throw new IllegalArgumentException("El mazo debe tener al menos 21 cartas");
        }
        this.identificador = nombre;
        this.mazo = mazo;
    }
    // Métodos stub para que compile
}
