package edu.fiuba.algo3.modelo;

public class Medico implements Modificador{

    private final Modificador modificador;

    public Medico(Modificador modificador){
        this.modificador = modificador;
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Medico";
    }

    @Override
    public void modificarComportamiento(Carta carta) {

    }

    @Override
    public void modificarComportamientoSeccion(Seccion seccion) {

    }
}
