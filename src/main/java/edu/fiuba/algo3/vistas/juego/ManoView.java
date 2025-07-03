package edu.fiuba.algo3.vistas.juego;

import java.util.List;

import edu.fiuba.algo3.controller.EspecialController;
import edu.fiuba.algo3.controller.HandlerSeleccionarCarta;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.vistas.juego.cartas.CartaEspecialVisual;
import edu.fiuba.algo3.vistas.juego.cartas.CartaMoralBoostVisual;
import edu.fiuba.algo3.vistas.juego.cartas.CartaUnidadVisual;
import edu.fiuba.algo3.vistas.juego.cartas.CartaVisual;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;


public class ManoView {
    private List<Carta> cartas;
    private final HandlerSeleccionarCarta handler;
    private final EspecialController especialController;
    private final HBox contenedor;
    private CartaVisual cartaSeleccionada = null;

    public ManoView(List<Carta> cartas, HandlerSeleccionarCarta handler, EspecialController especialController) {
        this.cartas = cartas;
        this.handler = handler;
        this.especialController = especialController;
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
                boolean esEspecial = carta.esEspecial();
                System.out.println("Es Especial: " + esEspecial);
                System.out.println("Carta: " + carta.mostrarCarta() + " es especial: " + esEspecial);
                boolean esMoralBoost = esEspecial && carta.mostrarCarta().contains("MoraleBoost");
                System.out.println(esMoralBoost);
                boolean esUnidad = !esEspecial;

                CartaVisual visual = null;

                if (esMoralBoost){
                    visual = new CartaMoralBoostVisual(carta, handler);
                } else if (esEspecial){
                    visual = new CartaEspecialVisual(carta, handler, especialController);
                } else if (esUnidad){
                    visual = new CartaUnidadVisual((CartaUnidad) carta, handler);
                }

                visual.construirVista();
                visual.setManoView(this);
                contenedor.getChildren().add(visual);
            } catch (Exception e) {
                Label errorLabel = new Label("Error\n" + carta.mostrarCarta());
                errorLabel.setPrefSize(80, 100);
                errorLabel.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-alignment: center;");
                contenedor.getChildren().add(errorLabel);
            }
        }
        contenedor.applyCss();
        contenedor.layout();
    }

    // Método para actualizar la lista de cartas y refrescar la vista
    public void actualizarCartas(List<Carta> nuevasCartas) {
        this.cartas = new java.util.ArrayList<>(nuevasCartas);
        deseleccionarCarta();
        handler.limpiarSeleccion();
        refrescar();
    }

    public void seleccionarCarta(CartaVisual carta) {
        if (cartaSeleccionada != null && cartaSeleccionada != carta) {
            cartaSeleccionada.animarDeseleccion();
        }
        if (cartaSeleccionada == carta) {
            cartaSeleccionada.animarDeseleccion();
            cartaSeleccionada = null;
        } else {
            carta.animarSeleccion();
            cartaSeleccionada = carta;
        }
    }

    public void deseleccionarCarta() {
        if (cartaSeleccionada != null) {
            cartaSeleccionada.animarDeseleccion();
            cartaSeleccionada = null;
        }
    }

    public HBox getContenedor() {
        return contenedor;
    }
}