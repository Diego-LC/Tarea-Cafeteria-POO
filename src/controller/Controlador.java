package controller;

import model.Cafeteria;
import model.IngredientesOpcionales;
import model.Cafe;
import gui.VentanaPrincipal;
import data.GestorDeDatos;
import model.RedesSociales;

public class Controlador {
	public Cafeteria cafeteria;
	public IngredientesOpcionales ingredientesOpcionales;
	public RedesSociales redesSociales;
	private VentanaPrincipal ventanaPrincipal;
	private GestorDeDatos gestorDeDatos;

	public Controlador() {
		this.cafeteria = new Cafeteria("Cafetería", "Calle 123", redesSociales);
		this.ventanaPrincipal = new VentanaPrincipal(this);
		this.gestorDeDatos = new GestorDeDatos();
	}

	public void iniciarSistema() {
		//this.cafeteria = gestorDatos.leerArchivoCafeteria("cafeteria.txt");
		this.ventanaPrincipal.setVisible(true);
		System.out.println(" ================================= ");
	}

	public void guardarDatos() {
		gestorDeDatos.registrarDato(this.cafeteria, "cafeteria.txt");
	}

	public void mostrarCafes() {
		System.out.println(" cafes: ---");
	}
}