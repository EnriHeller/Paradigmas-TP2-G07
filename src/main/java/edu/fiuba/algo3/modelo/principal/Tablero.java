package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.secciones.Asedio;
import edu.fiuba.algo3.modelo.secciones.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.secciones.Rango;
import edu.fiuba.algo3.modelo.secciones.Seccion;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

    private final List<Seccion> seccionesJugador1;
    private final List<Seccion> seccionesJugador2;

    public Tablero() {
        // Crea las filas espejadas para ambos jugadores
        this.seccionesJugador1 = new ArrayList<>();
        this.seccionesJugador2 = new ArrayList<>();


        Seccion asedioJ1 = new Asedio();
        Seccion rangoJ1 = new Rango();
        Seccion cuerpoACuerpoJ1 = new CuerpoACuerpo();

        Seccion cuerpoACuerpoJ2 = new CuerpoACuerpo();
        Seccion rangoJ2 = new Rango();
        Seccion asedioJ2 = new Asedio();

        // Las agregamos de forma espejada
        seccionesJugador1.add(asedioJ1);
        seccionesJugador1.add(rangoJ1);
        seccionesJugador1.add(cuerpoACuerpoJ1);

        seccionesJugador2.add(cuerpoACuerpoJ2);
        seccionesJugador2.add(rangoJ2);
        seccionesJugador2.add(asedioJ2);
    }

    public List<Seccion> getSeccionesJugador1() {
        return new ArrayList<>(this.seccionesJugador1);
    }

    public List<Seccion> getSeccionesJugador2() {
        return new ArrayList<>(this.seccionesJugador2);
    }


    public void limpiar() {
        for (Seccion seccion : seccionesJugador1) {
            seccion.limpiar();
        }
        for (Seccion seccion : seccionesJugador2) {
            seccion.limpiar();
        }
    }

}
