package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class JuegoView {

    private String jugador1;
    private String jugador2;

    public JuegoView(String jugador1, String jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public VBox construir() {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);

        Text texto = new Text("Partida entre " + jugador1 + " y " + jugador2);
        Button volver = new Button("Volver al menú");
        volver.setOnAction(e -> App.cambiarEscena(new Scene(new MenuView().construir(), 600, 400)));

        layout.getChildren().addAll(texto, volver);
        return layout;
    }
}
