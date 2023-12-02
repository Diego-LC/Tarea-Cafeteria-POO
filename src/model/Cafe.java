package model;

import java.util.ArrayList;

public class Cafe {
	private String nombre;
	private int gramosCafe;
	private int mililitrosAgua;
	private Tamaño tamaño;
	private IngredientesOpcionales ingredientesOpcionales;
	public Cafeteria cafeteria;

	public Cafe(String nombre, int gramosCafe, int mililitrosAgua, Tamaño tamaño) {
		this.nombre = nombre;
		this.gramosCafe = gramosCafe;
		this.mililitrosAgua = mililitrosAgua;
		this.tamaño = tamaño;
	}

	public Cafe(String nombre, int gramosCafe, int mililitrosAgua, Tamaño tamaño, IngredientesOpcionales ingredientesOpcionales) {
		this.nombre = nombre;
		this.gramosCafe = gramosCafe;
		this.mililitrosAgua = mililitrosAgua;
		this.tamaño = tamaño;
		this.ingredientesOpcionales = ingredientesOpcionales;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getGramosCafe() {
		return gramosCafe;
	}

	public void setGramosCafe(int gramosCafe) {
		this.gramosCafe = gramosCafe;
	}

	public int getMililitrosAgua() {
		return mililitrosAgua;
	}

	public void setMililitrosAgua(int mililitrosAgua) {
		this.mililitrosAgua = mililitrosAgua;
	}

	public Tamaño getTamaño() {
		return tamaño;
	}

	public void setTamaño(Tamaño tamaño) {
		this.tamaño = tamaño;
	}

	public IngredientesOpcionales getIngredientesOpcionales() {
		return ingredientesOpcionales;
	}

	public void setIngredientesOpcionales(IngredientesOpcionales ingredientesOpcionales) {
		this.ingredientesOpcionales = ingredientesOpcionales;
	}

	@Override
	public String toString() {
		String result = this.nombre + "," + this.gramosCafe + "," + this.mililitrosAgua+ "," + this.tamaño.getCategoria();
		if (this.ingredientesOpcionales != null) {
			result = result + "," + this.ingredientesOpcionales.getIngrediente();
		}else{
			result = result + "," + "null";
		}
		return result;
	}
}