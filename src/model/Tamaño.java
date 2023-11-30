package model;

public enum Tamaño {
    CHICO("Chico"),
    MEDIANO("Mediano"),
    GRANDE("Grande");
    private String tamaño;

    Tamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getCategoria() {
        return this.tamaño;
    }
}
