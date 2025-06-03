package edu.fiuba.algo3.modelo;

public class Unidad implements Modificador {

    private final Modificador modificador;

    public Unidad() {
        this.modificador = new Base();
    }

    public Unidad(Modificador modificador) {
        this.modificador = modificador;
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Unidad";
    }

    @Override
    public void modificarComportamiento(CartaUnidad carta) {
        carta.modificarValor(2 * carta.ValorActual());
    }

    @Override
    public void modificarComportamientoSeccion(Seccion seccion) {

    }

}
