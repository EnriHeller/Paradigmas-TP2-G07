package edu.fiuba.algo3.modelo.cartas;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.cartas.especiales.CartaNevada;
import edu.fiuba.algo3.modelo.cartas.especiales.Clima;
import edu.fiuba.algo3.modelo.cartas.especiales.ClimaNevado;
import edu.fiuba.algo3.modelo.cartas.especiales.TierraArrasada;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.*;

public class CartasFactory {
    public Carta crearCarta(String tipo ,String nombre, List<String> seccionesOAfectados ,long cantidad, Modificador modificador, String descripcion, String tipoEspecial) {
        Carta carta = null;
        if (tipo.equals("u")) {
            carta = new CartaUnidad(nombre, seccionesOAfectados, (int) cantidad, modificador);
        } else if (tipo.equals("e")) {
            switch (tipoEspecial) {
                case "Tierra arrasada":
                    carta = new TierraArrasada(nombre, descripcion, seccionesOAfectados);
                case "Morale boost":
                    //carta = new MoraleBoost(new Base(), nombre);
                case "Clima":
                    carta = new CartaNevada(nombre, descripcion, seccionesOAfectados);
            }
        }
        return carta;
    }
}
