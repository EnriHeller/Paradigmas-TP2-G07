package edu.fiuba.algo3.controller;

import javafx.scene.Scene;
import edu.fiuba.algo3.App;

public class Utils {
    public static void cambiarEscena(Scene nuevaEscena) {
        App.getStage().setScene(nuevaEscena);
    }

}
