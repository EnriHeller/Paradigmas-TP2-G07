package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.TableroSingleton;

import java.util.Arrays;
import java.util.List;

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
    public String mostrarModificadores(){
        return "DestructorDeClima";
    }

    @Override
    public void modificar(Contexto modificadorContexto) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        TableroSingleton tablero = modificadorContexto.getTablero();

        List<String> claves = Arrays.asList(
                "Rango0", "Asedio0", "CuerpoACuerpo0",
                "Rango1", "Asedio1", "CuerpoACuerpo1"
        );
        int seccionesSinClima = 0;
        for (String clave : claves) {
            if (!tablero.afectadaClima(clave)) {
                seccionesSinClima++;
            } else{
                tablero.afectarClima(clave, new SinClima());
            }
        }
        if (seccionesSinClima == 6) {
            throw new NoSePuedeEliminarClimaSiNoHayClima();
        }
    }


}
