package pkg;

import java.util.List;

public class CartaPuntuada implements Carta {
    public CartaPuntuada() {
        // Constructor mínimo
    }
    @Override
    public String[] dondePuedenSerColocada() {
        return new String[]{"cualquiera"};
    }
}
