package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.vistas.CartasView.CartaUnidadVisual;
import edu.fiuba.algo3.vistas.CartasView.CartaEspecialVisual;
import edu.fiuba.algo3.vistas.CartasView.CartaVisual;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.util.List;

public class ManoView {

    private final List<Carta> cartas;

    public ManoView(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public Region construir() {
        HBox contenedor = new HBox(10); // Espaciado entre cartas
        contenedor.setPadding(new Insets(10));
        contenedor.setAlignment(Pos.CENTER);

        for (Carta carta : cartas) {
            CartaVisual visual;

            if (carta instanceof CartaUnidad unidad) {
                visual = new CartaUnidadVisual(unidad);
            } else {
                visual = new CartaEspecialVisual(carta);
            }

            contenedor.getChildren().add(visual);
        }

        return contenedor;
    }
}
