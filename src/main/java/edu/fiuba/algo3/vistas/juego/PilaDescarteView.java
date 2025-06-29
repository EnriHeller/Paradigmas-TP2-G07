package edu.fiuba.algo3.vistas.juego;

import java.util.Objects;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.vistas.juego.cartas.CartaEspecialVisual;
import edu.fiuba.algo3.vistas.juego.cartas.CartaReversoVisual;
import edu.fiuba.algo3.vistas.juego.cartas.CartaUnidadVisual;
import edu.fiuba.algo3.vistas.juego.cartas.CartaVisual;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
        Pane contenedor = new Pane();

        String rutaDorso = "/imagenes/dorso.png";
        Image dorso = new Image(Objects.requireNonNull(getClass().getResourceAsStream(rutaDorso)));
        int cantidadCartas = 8;
        for (int i = 0; i < cantidadCartas; i++) {
            ImageView cartaView = new ImageView(dorso);
            cartaView.setFitWidth(70);
            cartaView.setFitHeight(90);
            cartaView.setLayoutX(i * 1.5); // desplazamiento horizontal leve
            cartaView.setLayoutY(i * 1.5); // desplazamiento vertical leve
            contenedor.getChildren().add(cartaView);
        }

        contenedor.setPrefSize(70 + cantidadCartas * 1.5, 90 + cantidadCartas * 1.5);


        if (carta != null) {
            CartaVisual visual;
            if (!carta.esEspecial()) {
                visual = new CartaUnidadVisual((CartaUnidad) carta, null);
            } else {
                visual = new CartaEspecialVisual(carta, null);
            }
            visual.construirVista();
            visual.setLayoutX((cantidadCartas + 1) * 1.5);
            visual.setLayoutY((cantidadCartas + 1) * 1.5);
            contenedor.getChildren().add(visual);
        } else {
            // Si no hay carta, mostrar una carta vacía o reverso
            CartaReversoVisual reverso = new CartaReversoVisual();
            reverso.setLayoutX((cantidadCartas + 1) * 1.5);
            reverso.setLayoutY((cantidadCartas + 1) * 1.5);
            contenedor.getChildren().add(reverso);
        }

        return contenedor;
    }

}

