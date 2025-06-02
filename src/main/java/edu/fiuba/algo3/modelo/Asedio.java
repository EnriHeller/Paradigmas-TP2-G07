package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Asedio extends Seccion{
    public Asedio(String claveSeccion) throws TipoDeSeccionInvalidaError {
        super(claveSeccion);
        this.cartasActuales = new ArrayList<>();
    }
}
