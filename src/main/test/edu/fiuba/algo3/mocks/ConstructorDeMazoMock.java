package edu.fiuba.algo3.mocks;

import edu.fiuba.algo3.controller.ConstructorMazo;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import java.util.ArrayList;
import java.util.List;

public class ConstructorDeMazoMock {
    public static ConstructorMazo crearDosMazosEstandar() {
        List<Carta> cartasMazo1 = new ArrayList<>();
        List<Carta> cartasMazo2 = new ArrayList<>();

        // 15 unidades (CartaUnidad) y 6 especiales (Carta) para cada mazo
        for (int i = 0; i < 15; i++) {
            cartasMazo1.add(new CartaUnidadMock());
            cartasMazo2.add(new CartaUnidadMock());
        }
        for (int i = 0; i < 6; i++) {
            cartasMazo1.add(new CartaEspecialMock("Especial1"));
            cartasMazo2.add(new CartaEspecialMock("Especial2"));
        }
        List<Mazo> mazos = new ArrayList<>();
        mazos.add(new Mazo(cartasMazo1));
        mazos.add(new Mazo(cartasMazo2));
        return new ConstructorMazo(null, null, null) {
            @Override
            public List<Mazo> construirMazos(String tipo) {
                return mazos;
            }
        };
    }

    public static ConstructorMazo crearDosMazosDeUnidades() {
        List<Carta> cartasMazo1 = new ArrayList<>();
        List<Carta> cartasMazo2 = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            cartasMazo1.add(new CartaUnidadMock());
            cartasMazo2.add(new CartaUnidadMock());
        }
        List<Mazo> mazos = new ArrayList<>();
        mazos.add(new Mazo(cartasMazo1));
        mazos.add(new Mazo(cartasMazo2));
        return new ConstructorMazo(null, null, null) {
            @Override
            public List<Mazo> construirMazos(String tipo) {
                return mazos;
            }
        };
    }
}
