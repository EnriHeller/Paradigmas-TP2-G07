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

public final class Audio {
    private static Audio instancia;
    Clip clip;
    private Audio (){
    }
    public void play(String ubicacionAudio) {
        try {
            URL soundURL = Bienvenida.class.getResource(ubicacionAudio);

            if (soundURL == null) {
                System.out.println("No se encontró el archivo cs16.wav en el classpath.");
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static Audio getInstance() {
        if (instancia == null) {
            instancia = new Audio();
        }
        return instancia;
    }
    public void stop() {
        clip.stop();
    }
}
