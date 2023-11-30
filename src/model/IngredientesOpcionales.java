package model;

public enum IngredientesOpcionales {
    CREMA("Crema"),
    LECHE("Leche"),
    CHOCOLATE("Chocolate");

    private String ingrediente;

    private IngredientesOpcionales(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public String getIngrediente(){
        return this.ingrediente;
    }
}
