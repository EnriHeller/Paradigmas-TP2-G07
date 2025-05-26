package edu.fiuba.algo3.modelo;

import java.util.Objects;

public class Localize {
    protected final String esContent;
    protected final String usContent;

    public Localize(String esContent, String usContent) {
        this.esContent = esContent;
        this.usContent = usContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Localize localize = (Localize) o;
        return Objects.equals(esContent, localize.esContent) && Objects.equals(usContent, localize.usContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(esContent, usContent);
    }

    public String greet(String lang) {
        if (lang.equals("us")) {
            return usContent;
        }

        return esContent;
    }

}
