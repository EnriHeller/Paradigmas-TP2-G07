package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.vistas.BienvenidaView;
import edu.fiuba.algo3.vistas.MenuView;
import javafx.scene.Scene;

public class Bienvenida {

    public static void mostrarMenu() {
        MenuView menuView = new MenuView();
        Scene escenaMenu = new Scene(menuView.construir(), App.WIDTH, App.HEIGHT);
        App.getStage().setScene(escenaMenu);
    }

    public static void mostrarBienvenida() {
        Audio audio = Audio.getInstance();
        audio.play("/audio/cs16.wav");
        BienvenidaView bienvenida = new BienvenidaView();
        Scene escenaBienvenida = new Scene(bienvenida.construir(), App.WIDTH, App.HEIGHT);

        App.getStage().setScene(escenaBienvenida);
    }

}

