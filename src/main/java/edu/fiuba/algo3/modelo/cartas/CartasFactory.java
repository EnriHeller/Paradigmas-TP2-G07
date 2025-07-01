package edu.fiuba.algo3.modelo.cartas;

import java.util.List;

import edu.fiuba.algo3.modelo.cartas.especiales.*;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Modificador;

public class CartasFactory {
    public Carta crearCarta(String tipo ,String nombre, List<String> seccionesOAfectados ,long cantidad, Modificador modificador, String descripcion, String tipoEspecial) {
        if (tipo.equals("u")) {
            return new CartaUnidad(nombre, seccionesOAfectados, (int) cantidad, modificador);
        } else if (tipo.equals("e")) {
            if (tipoEspecial != null) {
                switch (tipoEspecial.toLowerCase()) {
                    case "tierra arrasada":
                        return new TierraArrasada(nombre, descripcion, tipoEspecial, seccionesOAfectados);
                    case "morale boost":
                        return new MoraleBoost(nombre, descripcion, tipoEspecial, seccionesOAfectados);
                    case "clima":
                        switch (nombre.toLowerCase()) {
                            case "escarcha mordaz":
                                return new EscarchaMordaz(nombre, descripcion, tipoEspecial, seccionesOAfectados);
                            case "lluvia torrencial":
                                return new LluviaTorrencial(nombre, descripcion, tipoEspecial, seccionesOAfectados);
                            case "tormeta de skellige":
                                return new TormentaDeSkellige(nombre, descripcion, tipoEspecial, seccionesOAfectados);
                            case "tiempo despejado":
                                return new DestructoraDeClima(nombre, descripcion, tipoEspecial, seccionesOAfectados);
                        }
                    default:
                        return null;
                }
            }

        }
        return null;
    }
}

