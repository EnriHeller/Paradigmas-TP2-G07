package edu.fiuba.algo3.controller;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public final class Audio {
    private static Audio instancia;
    Clip clip;
    private String musicaActual;
    private boolean activo = true;
    private float volumen = 1.0f;

    

    private Audio (){
    }
    public void play(String ubicacionAudio) throws Exception {
        URL soundURL = Bienvenida.class.getResource(ubicacionAudio);

        if (soundURL == null) {
            System.out.println("No se encontró el archivo cs16.wav en el classpath.");
            return;
        }

        this.musicaActual = ubicacionAudio;

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        setVolume(activo ? 1.0f : volumen); // <-- siempre respeta el mute/volumen
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public void escuchar(){
        activo = true;
        setVolume(1.0f);
    }

    public void silenciar() {
        activo = false;
        setVolume(0);
    }

    public void setVolume(float volumen) {
    this.volumen = volumen;
    if (clip != null) {
        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float min = control.getMinimum();
        float max = control.getMaximum();
        float gain = (volumen == 0.0f) ? min : (min + (max - min) * volumen);
        control.setValue(gain);
    }
}
    public static Audio getInstance() {
        if (instancia == null) {
            instancia = new Audio();
        }
        return instancia;
    }

    public boolean estaActivo() {
        return activo; //musica esta activa?
    }

    public String getMusicaActual() {
        return musicaActual;
    }
}
