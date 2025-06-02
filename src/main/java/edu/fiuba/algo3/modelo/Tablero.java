package edu.fiuba.algo3.modelo;
import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.modelo.Seccion;

public class Tablero {

    private List<Seccion> secciones;
    private List<SeccionSinPuntaje> seccionesSinPuntaje;

    public Tablero() {
        this.secciones = new ArrayList<Seccion>(6);
        this.seccionesSinPuntaje = new ArrayList<SeccionSinPuntaje>(4);
    }

    public Tablero(List<Seccion> secciones, List<SeccionSinPuntaje> seccionesSinPuntaje) {
        // Constructor stub: puedes implementar la lógica luego
        this.secciones = secciones;
        this.seccionesSinPuntaje = seccionesSinPuntaje;
    }

    public boolean jugarCarta(Carta carta, String dondeJugarla){
        // Stub para compilar
        return false;
    }


}