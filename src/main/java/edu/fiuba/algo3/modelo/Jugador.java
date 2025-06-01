public class Jugador {
    private String nombre;
    private Mazo mazo;
    private Mano mano;
    private PilaDescarte pilaDescarte;


    public Jugador(String nombre, Mazo mazo) {}

    public void jugarCarta() {
        // jugador le pide a Su mano mostrar todas las cartas disponibles sus cartas y elige 1
        // Dentro: cada Carta se muestra abstractamente como string
        // el jugador elije uno de esos abtractos y se le da la carta
        // si la puede jugar se descartaCarta (mano piede su referencia) y se le da a tablero
        // sino puede vuelve a intentarlo (manejo de errores)

    }

    public void descartarCarta(Carta unaCarta) {}
}