package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.vistas.juego.ManoView;
import edu.fiuba.algo3.vistas.juego.TableroView;
import javafx.animation.FadeTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class EspecialController {
    private Juego juego;
    private TableroView tableroView;
    private ManoView manoView;

    public EspecialController(Juego juego) {
        this.juego = juego;
    }

    public void setTableroView(TableroView tablero){
        this.tableroView = tablero;
    }

    public void setManoView(ManoView manoView){
        this.manoView = manoView;
    }

    public void activarEspecial(Carta carta, StackPane contenedorCarta) throws TipoDeSeccionInvalidaError, CartaNoJugable{
        FadeTransition fade = new FadeTransition(Duration.seconds(1), contenedorCarta);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(event -> {
            try {
                juego.jugarCartaEspecial(carta);
                juego.removerCartaEnMano(carta);
                tableroView.refrescar();
                manoView.actualizarCartas(juego.mostrarManoActual());
            } catch (TipoDeSeccionInvalidaError | CartaNoJugable ex) {
                ex.printStackTrace();
            }
        });
        fade.play();
    }
}
