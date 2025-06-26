package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.vistas.BienvenidaView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import edu.fiuba.algo3.vistas.MenuView;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import javax.sound.sampled.*;

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

