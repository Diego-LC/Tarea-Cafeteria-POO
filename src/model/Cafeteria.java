package model;

import java.util.ArrayList;

public class Cafeteria {
	private String nombreCafeteria;
	private String direccion;
	private RedesSociales[] redesSociales;
	private ArrayList<Cafe> cafes;

	public Cafeteria(String nombreCafeteria, String direccion) {
		this.nombreCafeteria = nombreCafeteria;
		this.direccion = direccion;
		this.cafes = new ArrayList<Cafe>();
	}

	public Cafeteria() {
	}

	public String getNombreCafeteria() {
		return this.nombreCafeteria;
	}

	public void setNombreCafeteria(String nombreCafeteria) {
		this.nombreCafeteria = nombreCafeteria;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public RedesSociales[] getRedesSociales() {
		return redesSociales;
	}

	public void setRedesSociales(RedesSociales[] redesSociales) {
		this.redesSociales = redesSociales;
	}

	public ArrayList<Cafe> getCafes() {
		return this.cafes;
	}

	public void setCafes(Cafe cafes) {
		this.cafes.add(cafes);
	}

	public void agregarNuevoCafe(String nombre, int gramos, int agua, Tamaño tamaño, IngredientesOpcionales ingredientes) {
		Cafe cafe = new Cafe(nombre, gramos, agua, tamaño);
		if (ingredientes != null) {
			cafe.setIngredientesOpcionales(ingredientes);
		}
		this.cafes.add(cafe);
	}

	public ArrayList<Cafe> obtenerCafesPorTamanio(String tamanio) {
		ArrayList<Cafe> cafesPorTamaño = new ArrayList<Cafe>();
		for (Cafe cafe : this.cafes) {
			if (cafe.getTamaño().getCategoria().equalsIgnoreCase(tamanio)) {
				cafesPorTamaño.add(cafe);
			}
		}
		return cafesPorTamaño;
	}

	public boolean descontinuarCafe(Cafe cafe) {
		return this.cafes.remove(cafe);
	}

	@Override
	public String toString() {
		return this.nombreCafeteria + "," + this.direccion + "," + this.redesSociales[0].getCategoria() + "," + this.redesSociales[1].getCategoria() + "," + this.redesSociales[2].getCategoria();
	}
}