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
		this.gestorDeDatos = new GestorDeDatos();
		this.cafeteria = new Cafeteria();
		this.iniciarSistema();
	}

	public void iniciarSistema() {
		this.cafeteria = this.gestorDeDatos.leerArchivoCafeteria(this.cafeteria,"cafeteria.txt", "cafes.txt");
		this.ventanaPrincipal = new VentanaPrincipal(this);
		this.guardarDatos();

	}

	public void guardarDatos() {
		System.out.println(" ================================= ");
		this.gestorDeDatos.borrarDatosArchivo("cafeteria.txt");
		this.gestorDeDatos.borrarDatosArchivo("cafes.txt");
		this.gestorDeDatos.registrarDato(this.cafeteria, "cafeteria.txt");
		if (this.cafeteria.getCafes() != null) {
			for (Cafe cafe : this.cafeteria.getCafes()) {
				this.gestorDeDatos.registrarDato(cafe, "cafes.txt");
			}
		}
	}

	public void mostrarCafes() {
		System.out.println(" cafes: ---");
	}
}