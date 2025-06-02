package edu.fiuba.algo3.modelo.secciones;

import edu.fiuba.algo3.modelo.errores.TipoDeSeccionInvalidaError;

import java.util.ArrayList;

public class Asedio extends Seccion{
    public Asedio(String claveSeccion) throws TipoDeSeccionInvalidaError {
        super(claveSeccion);
        this.cartasActuales = new ArrayList<>();
    }
}
