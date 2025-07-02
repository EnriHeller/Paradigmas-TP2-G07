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
                        return new TierraArrasada();
                    case "morale boost":
                        return new MoraleBoost();
                    case "clima":
                        switch (nombre.toLowerCase()) {
                            case "escarcha mordaz":
                                return new EscarchaMordaz();
                            case "lluvia torrencial":
                                return new LluviaTorrencial();
                            case "tormeta de skellige":
                                return new TormentaDeSkellige();
                            case "tiempo despejado":
                                return new TiempoDespejado();
                        }
                    default:
                        return null;
                }
            }
            return null;
        }
        return null;
    }
}

