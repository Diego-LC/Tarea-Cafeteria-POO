package principal;

import controller.Controlador;

public class Main {

	public static void main(String[] args) {
		System.out.println("corriendo...");
		Controlador controller = new Controlador();
		controller.iniciarSistema();
	}
}