package edu.fiuba.algo3.modelo.cartas.especiales;

import java.util.List;

public abstract class CartaEspecial {
    protected String nombre;
    protected String descripcion;
    protected String tipo;
    protected List<String> afectado;

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public List<String> getAfectado() {
        return afectado;
    }
}