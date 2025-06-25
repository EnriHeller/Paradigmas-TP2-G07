package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class FinalizadorDeJuego {
    private final Juego juego;

    public FinalizadorDeJuego(Juego juego) {
        this.juego = juego;
    }

    public void verificarFinDeJuego() {
        if (juego.juegoTerminado()) {
            String ganador = juego.mostrarGanador();
            mostrarDialogoGanador(ganador);
            reiniciarJuego();
        }
    }

    private void mostrarDialogoGanador(String ganador) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Fin del juego!");
        alert.setHeaderText("¡Tenemos un ganador!");
        alert.setContentText("El ganador es: " + ganador);

        // Esperar hasta que el usuario cierre el diálogo
        alert.showAndWait();
    }

    private void reiniciarJuego() {
        Bienvenida.mostrarBienvenida();
    }
}