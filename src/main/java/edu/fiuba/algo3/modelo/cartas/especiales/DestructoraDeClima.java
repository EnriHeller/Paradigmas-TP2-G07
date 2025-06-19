package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

public class DestructoraDeClima implements Carta, Modificador {

    @Override
    public boolean esEspecial() {
        return true;
    }

    @Override
    public String getNombre() {
        return "DestructoraDeClima";
    }

    @Override
    public String mostrarModificadores() {
        return "DestructoraDeClima";
    }

    @Override
    public void modificar(Contexto modificadorContexto) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        Tablero tablero = modificadorContexto.getTablero();
        int seccionesSinClima = 0;
        for (edu.fiuba.algo3.modelo.secciones.tablero.Seccion seccion : tablero.todasLasSecciones()) {
            if (seccion.hayClima()) {
                tablero.afectarClima(seccion, new SinClima());
            } else {
                seccionesSinClima++;
            }
        }
        if (seccionesSinClima == tablero.todasLasSecciones().size()) {
            throw new NoSePuedeEliminarClimaSiNoHayClima();
        }
    }


}
