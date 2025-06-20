package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.principal.Jugador;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class JuegoView {

    private Jugador jugador1;
    private Jugador jugador2;

    public JuegoView(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }


    public VBox construir() {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);

        Text texto = new Text("Partida entre " + jugador1.getNombre() + " y " + jugador2.getNombre());
        Button volver = new Button("Volver al menú");
        volver.setOnAction(e -> App.cambiarEscena(new Scene(new BienvenidaView().construir(), 600, 400)));

        layout.getChildren().addAll(texto, volver);
        return layout;
    }
}
