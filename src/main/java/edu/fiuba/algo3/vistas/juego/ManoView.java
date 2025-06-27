package edu.fiuba.algo3.vistas.juego;

import java.util.List;

import edu.fiuba.algo3.controller.CartaClickHandler;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.vistas.juego.cartas.CartaEspecialVisual;
import edu.fiuba.algo3.vistas.juego.cartas.CartaUnidadVisual;
import edu.fiuba.algo3.vistas.juego.cartas.CartaVisual;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Duration;

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
                Carta cartaActual = carta; // Necesario para lambda final
                CartaVisual visual;
                if (!carta.esEspecial()) {
                    visual = new CartaUnidadVisual((CartaUnidad) carta);
                    visual.setStyle("-fx-border-color: blue; -fx-background-color: #e0e0e0; -fx-border-width: 2px;");
                    visual.construirVista();

                    visual.setOnMouseClicked(e -> handler.alClic(cartaActual));

                } else {
                    visual = new CartaEspecialVisual(carta);
                    visual.setStyle("-fx-border-color: green; -fx-background-color: #f0fff0; -fx-border-width: 2px;");

                    visual.construirVista();

                    visual.setOnMouseClicked(e -> {
                        // Mostrar botón de confirmación una sola vez
                        if (visual.lookup("#activarBtn") == null) {
                            Button activarBtn = new Button("Activar");
                            activarBtn.setId("activarBtn");
                            activarBtn.setOnAction(ev -> {
                                FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), visual);
                                fadeOut.setFromValue(1.0);
                                fadeOut.setToValue(0.0);
                                fadeOut.setOnFinished(fadeEv -> {
                                    handler.alClic(cartaActual);
                                    contenedor.getChildren().remove(visual);
                                });
                                fadeOut.play();
                            });

                            activarBtn.setTranslateY(40); // Posicionarlo debajo de la carta
                            visual.getChildren().add(activarBtn); // visual debe ser un VBox o StackPane
                        }
                    });
                }


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
