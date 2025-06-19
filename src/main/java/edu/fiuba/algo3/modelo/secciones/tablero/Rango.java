package edu.fiuba.algo3.modelo.secciones.tablero;

import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;

import java.util.ArrayList;

public class Rango extends Seccion {
    public Rango(String claveSeccion) throws TipoDeSeccionInvalidaError {
        super(claveSeccion);
        this.cartasActuales = new ArrayList<>();
    }
}
