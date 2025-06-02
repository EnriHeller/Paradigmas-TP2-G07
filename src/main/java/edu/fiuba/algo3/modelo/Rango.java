package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Rango extends Seccion{
    public Rango(String claveSeccion) throws TipoDeSeccionInvalidaError {
        super(claveSeccion);
        this.cartasActuales = new ArrayList<>();
    }
}
