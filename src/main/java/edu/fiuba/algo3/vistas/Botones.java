package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.App;
import javafx.scene.control.Button;

import java.util.function.Function;

public class Botones {

    public static Button Boton_1(String nombre, Runnable funcion){
        Button boton = new Button(nombre);
        boton.setStyle("-fx-background-color: rgba(255,255,255,0.7); -fx-background-radius: 10;");
        boton.setOnAction(e -> funcion.run());

        return boton;
    }
}