package edu.fiuba.algo3.vistas.juego;

import java.util.List;

import edu.fiuba.algo3.controller.CartaClickHandler;
import edu.fiuba.algo3.controller.HandlerEspecialMano;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.vistas.juego.cartas.CartaEspecialVisual;
import edu.fiuba.algo3.vistas.juego.cartas.CartaUnidadVisual;
import edu.fiuba.algo3.vistas.juego.cartas.CartaVisual;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;


public class ManoView {
    private List<Carta> cartas;
    private final CartaClickHandler handler;
    private final HandlerEspecialMano handlerEspecial;
    private final HBox contenedor;

    public ManoView(List<Carta> cartas, CartaClickHandler handler, HandlerEspecialMano handlerEspecial) {
        this.cartas = cartas;
        this.handler = handler;
        this.handlerEspecial = handlerEspecial;
        this.contenedor = new HBox(10);
        this.contenedor.setAlignment(Pos.CENTER);
    }

    public Region construir() {
        refrescar();
        return contenedor;
    }

    public void refrescar() {
        contenedor.getChildren().clear();
        for (Carta carta : cartas) {
            try {
                CartaVisual visual = carta.esEspecial()
                    ? new CartaEspecialVisual(carta, handlerEspecial)
                    : new CartaUnidadVisual((CartaUnidad) carta, handler);
                visual.construirVista();
                if (carta.esEspecial()) {
                    visual.setOnMouseClicked(e -> {
                        // Mostrar botón de confirmación una sola vez
                        if (visual.lookup("#activarBtn") == null) {
                            javafx.scene.control.Button activarBtn = new javafx.scene.control.Button("Activar");
                            activarBtn.setId("activarBtn");
                            activarBtn.setOnAction(ev -> {
                                javafx.animation.FadeTransition fadeOut = new javafx.animation.FadeTransition(javafx.util.Duration.seconds(1), visual);
                                fadeOut.setFromValue(1.0);
                                fadeOut.setToValue(0.0);
                                fadeOut.setOnFinished(fadeEv -> {
                                    handlerEspecial.alClicEspecial((CartaEspecialVisual) visual);
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
                Label errorLabel = new Label("Error\n" + carta.mostrarCarta());
                errorLabel.setPrefSize(80, 100);
                errorLabel.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-alignment: center;");
                contenedor.getChildren().add(errorLabel);
            }
        }
        // Forzar layout para que las posiciones estén actualizadas tras refrescar
        contenedor.applyCss();
        contenedor.layout();
    }

    // Método para actualizar la lista de cartas y refrescar la vista
    public void actualizarCartas(List<Carta> nuevasCartas) {
        this.cartas = new java.util.ArrayList<>(nuevasCartas);
        refrescar();
    }

    public HBox getContenedor() {
        return contenedor;
    }
}