package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

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
    public void modificar(Contexto modificadorContexto) throws TipoDeSeccionInvalidaError {
        Tablero tablero = modificadorContexto.getTablero();  // obtener el tablero desde el contexto
        //Clima nuevoClima = this.clima;  // asumimos que lo tenés como atributo de tu clase

        // Lista de claves que querés afectar
        List<String> claves = Arrays.asList(
                "Rango0", "Asedio0", "CuerpoACuerpo0",
                "Rango1", "Asedio1", "Cuerpo1"
        );

        for (String clave : claves) {
            //tablero.afectarClima(clave, nuevoClima);
        }
    }


}
