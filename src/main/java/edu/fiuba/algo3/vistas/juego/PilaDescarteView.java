package edu.fiuba.algo3.vistas.juego;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.vistas.juego.cartas.CartaEspecialVisual;
import edu.fiuba.algo3.vistas.juego.cartas.CartaReversoVisual;
import edu.fiuba.algo3.vistas.juego.cartas.CartaUnidadVisual;
import edu.fiuba.algo3.vistas.juego.cartas.CartaVisual;

import javafx.geometry.Pos;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class PilaDescarteView extends StackPane {

    private Carta carta;

    public PilaDescarteView(Carta carta) {
        this.carta = carta;
    }

    //recibe esto juego.getUltimaCartaDeLaPilaDeDescarte()
    public void actualizarPila(Carta nuevaCarta) {
        this.carta = nuevaCarta;
        this.getChildren().clear(); // Limpiar lo anterior
        Region nuevaVista = construir();
        this.getChildren().add(nuevaVista);
    }


    public Region construir() {
        HBox contenedor = new HBox(10);
        contenedor.setAlignment(Pos.CENTER);

        if (carta != null) {
            CartaVisual visual;
            if (!carta.esEspecial()) {
                visual = new CartaUnidadVisual((CartaUnidad) carta);
            } else {
                visual = new CartaEspecialVisual(carta);
            }
            visual.construirVista();
            contenedor.getChildren().add(visual);
        } else {
            // Si no hay carta, mostrar una carta vacía o reverso
            CartaVisual reverso = new CartaReversoVisual(null);
            reverso.construirVista();
            contenedor.getChildren().add(reverso);
        }

        return contenedor;
    }

}

