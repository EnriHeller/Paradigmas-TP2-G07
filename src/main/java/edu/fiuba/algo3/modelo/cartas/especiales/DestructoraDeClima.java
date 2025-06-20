package edu.fiuba.algo3.modelo.cartas.especiales;

import java.util.List;
import java.util.Arrays;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

public class DestructoraDeClima implements Carta, Modificador {

    @Override
    public  boolean esEspecial(){
        return true;
    }

    @Override
    public String mostrarCarta() {
        return "DestructorDeClima";
    }

    @Override
    public void aplicarModificador(Contexto contexto) {
        try {
            modificar(contexto);
        } catch (TipoDeSeccionInvalidaError | NoSePuedeEliminarClimaSiNoHayClima e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String mostrarModificadores(){
        return "DestructorDeClima";
    }

    @Override
    public void modificar(Contexto modificadorContexto) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        Tablero tablero = modificadorContexto.getTablero();
        int seccionesSinClima = 0;
        for (var seccion : tablero.todasLasSecciones()) {
            // Si la sección ya está sin clima, la contamos
            if (!seccion.hayClima()) {
                seccionesSinClima++;
            } else {
                tablero.afectarClima(seccion, new SinClima());
            }
        }
        if (seccionesSinClima == tablero.todasLasSecciones().size()) {
            throw new NoSePuedeEliminarClimaSiNoHayClima();
        }
    }


}
