package edu.fiuba.algo3.modelo.cartas.especiales;

import java.util.List;

public interface CartaEspecial{

    String getNombre();

    String getDescripcion();
    
    String getTipo();
    
    List<String> getAfectado();
}