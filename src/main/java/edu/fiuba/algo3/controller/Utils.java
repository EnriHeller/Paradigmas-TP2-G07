package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class Utils {
    public static void cambiarEscena(Scene nuevaEscena) {
        App.getStage().setScene(nuevaEscena);
    }


    // --- Popup animado de error ---
    public static void mostrarPopupError(StackPane rootStackPane, String mensaje) {
        javafx.scene.control.Label label = new javafx.scene.control.Label(mensaje);
        label.setStyle("-fx-background-color: rgba(30,30,30,0.95); -fx-text-fill: white; -fx-font-size: 18px; -fx-padding: 18 32; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-color: gold; -fx-border-width: 2;");
        label.setOpacity(0);
        StackPane.setAlignment(label, javafx.geometry.Pos.CENTER);
        if (rootStackPane != null) {
            rootStackPane.getChildren().add(label);
            javafx.animation.FadeTransition fadeIn = new javafx.animation.FadeTransition(javafx.util.Duration.millis(350), label);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setOnFinished(e -> {
                javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(1.7));
                pause.setOnFinished(ev -> {
                    javafx.animation.FadeTransition fadeOut = new javafx.animation.FadeTransition(javafx.util.Duration.millis(400), label);
                    fadeOut.setFromValue(1);
                    fadeOut.setToValue(0);
                    fadeOut.setOnFinished(ev2 -> rootStackPane.getChildren().remove(label));
                    fadeOut.play();
                });
                pause.play();
            });
            fadeIn.play();
        }
    }
}
