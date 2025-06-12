package edu.fiuba.algo3.modelo.modificadores;

public class ModificadoresFactory {
    public Modificador crearModificador(String tipoModificador, Modificador siguienteModificador) {
        switch (tipoModificador.toLowerCase()) {
            case "legendaria":
                return new Legendaria();
            case "médico":
                return new Medico();
            case "ágil":
                return new Agil(siguienteModificador);
            case "unidas":
                return new Unidas(siguienteModificador);
            case "espías":
                return new Espias(siguienteModificador);
            case "suma valor base":
                return new SumaValorBase(siguienteModificador);
            default:
                throw new IllegalArgumentException("Tipo de modificador no reconocido: " + tipoModificador);
        }
    }
}
