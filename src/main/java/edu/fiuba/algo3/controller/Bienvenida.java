package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.vistas.BienvenidaView;
import edu.fiuba.algo3.vistas.MenuView;
import javafx.scene.Scene;

public class Bienvenida {

    public static void mostrarMenu() throws Exception {
        MenuView menuView = new MenuView();
        Scene escenaMenu = new Scene(menuView.construir(), App.WIDTH, App.HEIGHT);
        App.getStage().setScene(escenaMenu);
        Audio efecto = Audio.getInstanceEffect();
        efecto.play("/audio/effects/draw-sword.wav");
    }

    public static void mostrarBienvenida() throws Exception {
        Audio audio = Audio.getInstance();
        audio.setVolume(0.7f);
        audio.play("/audio/cs16.wav");

        BienvenidaView bienvenida = new BienvenidaView();
        Scene escenaBienvenida = new Scene(bienvenida.construir(), App.WIDTH, App.HEIGHT);

        App.getStage().setScene(escenaBienvenida);
    }

}

