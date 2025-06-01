package pkg;

public class ConstructorMazo {

    private Carta[] cartas;

    public ConstructorMazo() {
        // Inicialización mínima para stub
        this.cartas = new Carta[30];
    }

    public Mazo CrearMazoAleatorio() {
        // Retorna un mazo vacío o stub para compilar
        return new Mazo();
    }
    public int cantidadCartas() {
        return cartas.length;
    }
    public Mazo ObtenerMazoDeArchivo(String ruta) {
        // Retorna un mazo vacío o stub para compilar
        return new Mazo();
    }
}
