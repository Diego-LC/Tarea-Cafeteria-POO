package gui;

import controller.Controlador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends VentanaGeneral implements ActionListener {
    private Controlador controlador;
    private JButton botonMostrarCafes;
    private JButton botonAgregarCafe;
    private JButton botonBuscarCafe;
    private JButton botonEliminarCafe;

    public VentanaPrincipal(Controlador controlador) {
        super("Ventana Principal", 500, 500);
        this.controlador = controlador;
        this.generarElementos();
    }

    private void generarElementos() {
        super.generarJLabelEncabezado(null, "Cafetería", 100, 20, 300, 30);
        this.generarBotonMostrarCafes();
        this.generarBotonAgregarCafe();
        this.generarBotonBuscarCafe();
        this.generarBotonEliminarCafe();
    }

    private void generarBotonMostrarCafes() {
        JButton btnMostrarCafes = super.generarBoton("Mostrar Cafés", 100, 70, 300, 50);
        this.botonMostrarCafes = btnMostrarCafes;
        this.add(btnMostrarCafes);
        this.botonMostrarCafes.addActionListener(this);
    }

    private void generarBotonAgregarCafe() {
        JButton btnAgregarCafe = super.generarBoton("Agregar Café", 100, 130, 300, 50);
        this.botonAgregarCafe = btnAgregarCafe;
        this.add(btnAgregarCafe);
        this.botonAgregarCafe.addActionListener(this);
    }

    private void generarBotonBuscarCafe() {
        JButton btnBuscarCafe = super.generarBoton("Buscar Café", 100, 190, 300, 50);
        this.botonBuscarCafe = btnBuscarCafe;
        this.add(btnBuscarCafe);
        this.botonBuscarCafe.addActionListener(this);
    }

    private void generarBotonEliminarCafe() {
        JButton btnEliminarCafe = super.generarBoton("Eliminar Café", 100, 250, 300, 50);
        this.botonEliminarCafe = btnEliminarCafe;
        this.add(btnEliminarCafe);
        this.botonEliminarCafe.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonMostrarCafes) {
            // Mostrar la lista de cafés asociados
            controlador.mostrarCafes();
        }

        if (e.getSource() == this.botonAgregarCafe) {
            // Crear una nueva instancia de VentanaAgregarCafe y mostrarla
            VentanaAgregarCafe ventanaAgregarCafe = new VentanaAgregarCafe(VentanaPrincipal.this);
            ventanaAgregarCafe.setVisible(true);
            // Opcional: ocultar la ventana principal
            setVisible(false);
        }

        if (e.getSource() == this.botonBuscarCafe) {
            // Crear una nueva instancia de VentanaBuscarCafe y mostrarla
            VentanaBuscarCafe ventanaBuscarCafe = new VentanaBuscarCafe(controlador, VentanaPrincipal.this);
            ventanaBuscarCafe.setVisible(true);
            // Opcional: ocultar la ventana principal
            setVisible(false);
        }

        if (e.getSource() == this.botonEliminarCafe) {
            // Crear una nueva instancia de VentanaEliminarCafe y mostrarla
            VentanaEliminarCafe ventanaEliminarCafe = new VentanaEliminarCafe(controlador, VentanaPrincipal.this);
            ventanaEliminarCafe.setVisible(true);
            // Opcional: ocultar la ventana principal
            setVisible(false);
        }
    }
}