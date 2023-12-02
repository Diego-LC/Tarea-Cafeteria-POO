package controller;

import model.*;
import gui.VentanaPrincipal;
import data.GestorDeDatos;

public class Controlador {
	public Cafeteria cafeteria;
	public IngredientesOpcionales ingredientesOpcionales;
	public RedesSociales redesSociales;
	private VentanaPrincipal ventanaPrincipal;
	private GestorDeDatos gestorDeDatos;

	public Controlador() {
		this.cafeteria = new Cafeteria("Cafetería", "Calle 123");
		this.ventanaPrincipal = new VentanaPrincipal(this);
		this.gestorDeDatos = new GestorDeDatos();
	}

	public void iniciarSistema() {
		this.cafeteria.setRedesSociales(new RedesSociales[] {RedesSociales.INSTAGRAM, RedesSociales.FACEBOOK, RedesSociales.TWITTER});
		this.cafeteria.agregarNuevoCafe("Capuchino", 30, 150, Tamaño.CHICO, null);
		this.cafeteria.agregarNuevoCafe("Capuchino", 30, 150, Tamaño.CHICO, IngredientesOpcionales.LECHE);
		this.cafeteria.agregarNuevoCafe("Capuchino", 30, 200, Tamaño.MEDIANO, IngredientesOpcionales.CREMA);
		//this.cafeteria = this.gestorDeDatos.leerArchivoCafeteria(caf,"cafeteria.txt", "cafes.txt");
		this.guardarDatos();
		//this.ventanaPrincipal.setVisible(true);
		System.out.println(" ================================= ");
	}

	public void guardarDatos() {
		this.gestorDeDatos.registrarDato(this.cafeteria, "cafeteria.txt");
		for (Cafe cafe : this.cafeteria.getCafes()) {
			this.gestorDeDatos.registrarDato(cafe, "cafes.txt");
		}
	}

	public void mostrarCafes() {
		System.out.println(" cafes: ---");
	}
}