package edu.fiuba.algo3.vistas.juego;

import java.util.List;

import edu.fiuba.algo3.controller.CartaClickHandler;
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
    private final CartaClickHandler handler;
    private HBox contenedor;

    public ManoView(List<Carta> cartas, CartaClickHandler handler) {
        this.cartas = cartas;
        this.handler = handler;
    }

    public Region construir() {
        contenedor = new HBox(10);
        contenedor.setAlignment(Pos.CENTER);

        for (Carta carta : cartas) {
            try {
                CartaVisual visual;
                if (!carta.esEspecial()) {
                    visual = new CartaUnidadVisual((CartaUnidad) carta);
                    visual.setStyle("-fx-border-color: blue; -fx-background-color: #e0e0e0; -fx-border-width: 2px;");
                    visual.construirVista();
                } else {
                    visual = new CartaEspecialVisual(carta);
                    visual.setStyle("-fx-border-color: green; -fx-background-color: #f0fff0; -fx-border-width: 2px;");
                    visual.construirVista();
                }

                Carta cartaActual = carta; // Necesario para lambda final
                visual.setOnMouseClicked(e -> {
                    handler.alClic(cartaActual);
                    cartas.remove(cartaActual);           // Lógica
                    contenedor.getChildren().remove(visual); // Visual
                });

                contenedor.getChildren().add(visual);

            } catch (Exception e) {
                javafx.scene.control.Label errorLabel = new javafx.scene.control.Label("Error\n" + carta.mostrarCarta());
                errorLabel.setPrefSize(80, 100);
                errorLabel.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-alignment: center;");
                contenedor.getChildren().add(errorLabel);
            }
        }

        return contenedor;
    }
}
