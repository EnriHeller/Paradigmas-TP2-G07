package edu.fiuba.algo3.controller;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public final class Audio {
    private static Audio instancia;
    private Clip clip;
    private String musicaActual;
    private static boolean globalActivo = true;
    private static float globalVolumen = 0.7f;

    private Audio() {}

    public void play(String ubicacionAudio) throws Exception {
        if (!globalActivo) {
            return;
        }
        URL soundURL = Bienvenida.class.getResource(ubicacionAudio);
        if (soundURL == null) {
            System.out.println("No se encontró el archivo en el classpath.");
            return;
        }
        this.musicaActual = ubicacionAudio;
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        setVolume(globalVolumen); // Usar siempre el volumen global
        clip.start();
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public static void escuchar() {
        globalActivo = true;
        globalVolumen = 0.7f;
        if (instancia != null) {
            instancia.setVolume(globalVolumen);
        }
    }

    public static void silenciar() {
        globalActivo = false;
        globalVolumen = 0f;
        if (instancia != null) {
            instancia.setVolume(globalVolumen);
        }
    }

    public void setVolume(float volumen) {
        // El volumen ahora es global, no se usa la variable de instancia
        if (clip != null) {
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float min = control.getMinimum();
            float max = control.getMaximum();
            float gain = (globalVolumen == 0.0f) ? min : (min + (max - min) * globalVolumen);
            control.setValue(gain);
        }
    }

    public static Audio getInstance() {
        if (instancia == null) {
            instancia = new Audio();
        }
        return instancia;
    }

    public static Audio getInstanceEffect() {

        Audio audio = new Audio();
        audio.setVolume(0.7f);
        return audio;
    }

    public static boolean estaActivo() {
        return globalActivo;
    }

    public String getMusicaActual() {
        return musicaActual;
    }
}
