package edu.fiuba.algo3.vistas.juego;

import java.util.List;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.vistas.juego.cartas.CartaEspecialVisual;
import edu.fiuba.algo3.vistas.juego.cartas.CartaUnidadVisual;
import edu.fiuba.algo3.vistas.juego.cartas.CartaVisual;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class ManoView {

    private final List<Carta> cartas;

    public ManoView(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public Region construir() {
        HBox contenedor = new HBox(10); // Espaciado entre cartas
        //contenedor.setPadding(new Insets(10));
        contenedor.setAlignment(Pos.CENTER);

        for (Carta carta : cartas) {
            CartaVisual visual;

            if (!carta.esEspecial()) {
                visual = new CartaUnidadVisual((CartaUnidad) carta);
                visual.construirVista();
            } else{
                visual = new CartaEspecialVisual(carta);
                visual.construirVista();
            } 
            contenedor.getChildren().add(visual);
        }

        return contenedor;
    }
}
