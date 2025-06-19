package edu.fiuba.algo3.mocks;

import edu.fiuba.algo3.controller.ConstructorMazo;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ConstructorMazoMock {
    public static ConstructorMazo crearDosMazosEstandar() throws Exception{
        ConstructorMazo mock = Mockito.mock(ConstructorMazo.class);
        List<Carta> cartasMazo1 = new ArrayList<>();
        List<Carta> cartasMazo2 = new ArrayList<>();

        // 15 unidades (CartaUnidad) y 6 especiales (Carta) para cada mazo
        for (int i = 0; i < 15; i++) {
            cartasMazo1.add(Mockito.mock(CartaUnidad.class));
            cartasMazo2.add(Mockito.mock(CartaUnidad.class));
        }
        for (int i = 0; i < 6; i++) {
            // Cartas especiales: mocks de Carta, pero NO de CartaUnidad
            Carta especial1 = Mockito.mock(Carta.class);
            Carta especial2 = Mockito.mock(Carta.class);

            // Aseguramos que no sean instancia de CartaUnidad
            Mockito.when(especial1.esEspecial()).thenReturn(true);
            Mockito.when(especial2.esEspecial()).thenReturn(true);

            cartasMazo1.add(especial1);
            cartasMazo2.add(especial2);
        }
        List<Mazo> mazos = new ArrayList<>();
        mazos.add(new Mazo(cartasMazo1));
        mazos.add(new Mazo(cartasMazo2));
        Mockito.when(mock.construirMazos(Mockito.anyString())).thenReturn(mazos);
        return mock;
    }

    public static ConstructorMazo crearDosMazosDeUnidades() throws Exception{
        ConstructorMazo mock = Mockito.mock(ConstructorMazo.class);
        List<Carta> cartasMazo1 = new ArrayList<>();
        List<Carta> cartasMazo2 = new ArrayList<>();
        
        // 21 unidades (CartaUnidad) para cada mazo
        for (int i = 0; i < 21; i++) {
            cartasMazo1.add(Mockito.mock(CartaUnidad.class));
            cartasMazo2.add(Mockito.mock(CartaUnidad.class));
        }
        
        List<Mazo> mazos = new ArrayList<>();
        mazos.add(new Mazo(cartasMazo1));
        mazos.add(new Mazo(cartasMazo2));
        Mockito.when(mock.construirMazos(Mockito.anyString())).thenReturn(mazos);
        return mock;
    }
}
