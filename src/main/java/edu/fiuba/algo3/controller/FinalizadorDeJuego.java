package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.modelo.principal.Juego;
import javafx.scene.control.Alert;

public class FinalizadorDeJuego {
    private final Juego juego;

    public FinalizadorDeJuego(Juego juego) {
        this.juego = juego;
    }

    public void verificarFinDeJuego() throws Exception {
        if (juego.juegoTerminado()) {
            Audio audio = Audio.getInstance();
            audio.stop();
            String ganador = juego.mostrarGanador();
            mostrarDialogoGanador(ganador);
            reiniciarJuego();
        }
    }

    private void mostrarDialogoGanador(String ganador) {
        // Detener la música antes de mostrar el diálogo
        try {
            edu.fiuba.algo3.controller.Audio.getInstance().stop();
        } catch (Exception ignored) {}
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Fin del juego!");
        alert.setHeaderText("¡Tenemos un ganador!");
        alert.setContentText("El ganador es: " + ganador);

        // Esperar hasta que el usuario cierre el diálogo
        alert.showAndWait();
    }

    private void reiniciarJuego() throws Exception  {
        Bienvenida.mostrarBienvenida();
    }
}