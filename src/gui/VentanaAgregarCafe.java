package gui;

import controller.Controlador;
import model.Cafeteria;
import model.Cafe;
import model.IngredientesOpcionales;
import model.Tamaño;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgregarCafe extends VentanaGeneral implements ActionListener {
    private Controlador controlador;
    private VentanaPrincipal ventanaPrincipal;
    private JTextField textFieldNombreCafe;
    private JTextField textFieldMililitrosAgua;
    private JTextField textFieldGramosCafe;
    private JComboBox comboBoxTamaño;
    private JComboBox comboBoxIngredientesOpcionales;
    private JButton botonAgregarCafe;
    private JButton botonVolver;

    public VentanaAgregarCafe(VentanaPrincipal ventanaPrincipal,Controlador controlador) {
        super("Ventana Café");
        super.setSize(500, 580);
        this.ventanaPrincipal = ventanaPrincipal;
        this.controlador = controlador;
        this.generarElementos();
    }

    public void setVisible(boolean b) {
        super.setVisible(b);
        this.textFieldNombreCafe.requestFocus();
    }

    private void generarElementos() {
        super.generarJLabelEncabezado(null, "Agregar Café", 100, 20, 300, 30);
        super.generarJLabel(null, "Ingrese el nombre del café", 100, 60, 300, 30);
        this.generarInputTextFieldNombreCafe();
        super.generarJLabel(null, "Ingrese los mililitros de agua", 100, 120, 300, 30);
        this.generarInputTextFieldMililitrosAgua();
        super.generarJLabel(null, "Ingrese los gramos de café", 100, 180, 300, 30);
        this.generarInputTextFielGramosCafe();
        super.generarJLabel(null, "Seleccione el tamaño del café", 100, 240, 300, 30);
        this.generarListaDesplegableTamaño();
        super.generarJLabel(null, "Seleccione un ingrediente opcional", 100, 300, 300, 30);
        this.generarListaDesplegableIngredientesOpcionales();
        this.generarBotonAgregarCafe();
        this.generarBotonVolver();
    }

    private void generarInputTextFieldNombreCafe() {
        JTextField textField = super.generarJTextField(100, 90, 300, 30);
        this.textFieldNombreCafe = textField;
        this.add(textField);
    }

    private void generarInputTextFieldMililitrosAgua() {
        JTextField textField = super.generarEntradaNumero(100, 150, 300, 30);
        this.textFieldMililitrosAgua = textField;
        this.add(textField);
    }

    private void generarInputTextFielGramosCafe() {
        JTextField textField = super.generarEntradaNumero(100, 210, 300, 30);
        this.textFieldGramosCafe = textField;
        this.add(textField);
    }

    private void generarListaDesplegableTamaño() {
        this.comboBoxTamaño = super.generarListaDesplegable(100, 270, 300, 30);
        this.add(this.comboBoxTamaño);
        for (Tamaño tamaño : Tamaño.values()) {
            this.comboBoxTamaño.addItem(tamaño);
        }
        this.comboBoxTamaño.addActionListener(this);
    }

    private void generarListaDesplegableIngredientesOpcionales() {
        JComboBox comboBox = super.generarListaDesplegable(100, 330, 300, 30);
        this.comboBoxIngredientesOpcionales = comboBox;
        this.add(comboBox);
        comboBox.addItem("Ninguno");
        for (IngredientesOpcionales ingredientesOpcionales : IngredientesOpcionales.values()) {
            comboBox.addItem(ingredientesOpcionales);
        }
        comboBox.addActionListener(this);
    }

    private void generarBotonAgregarCafe() {
        JButton jbAgregarCafe = super.generarBoton("Agregar café", 100, 390, 300, 50);
        this.add(jbAgregarCafe);
        this.botonAgregarCafe = jbAgregarCafe;
        this.botonAgregarCafe.addActionListener(this);
    }

    private void generarBotonVolver() {
        JButton volver = super.generarBoton("Volver", 100, 450, 300, 50);
        this.botonVolver = volver;
        this.add(volver);
        this.botonVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.botonAgregarCafe == e.getSource()) {
            try {
                String nombreCafe = textFieldNombreCafe.getText();
                int mililitrosAgua = textFieldMililitrosAgua.getText().isEmpty() ? 0 : Integer.parseInt(textFieldMililitrosAgua.getText());
                int gramosCafe = textFieldGramosCafe.getText().isEmpty() ? 0 : Integer.parseInt(textFieldGramosCafe.getText());
                Tamaño tamaño = (Tamaño) comboBoxTamaño.getSelectedItem();
                String ingrediente = comboBoxIngredientesOpcionales.getSelectedItem().toString();

                if (!ingrediente.equalsIgnoreCase("Ninguno")) {
                    this.controlador.cafeteria.agregarNuevoCafe(nombreCafe, gramosCafe, mililitrosAgua, tamaño, (IngredientesOpcionales)comboBoxIngredientesOpcionales.getSelectedItem());
                } else {
                    this.controlador.cafeteria.agregarNuevoCafe(nombreCafe, gramosCafe, mililitrosAgua, tamaño, null);
                }
            }catch (Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
            controlador.guardarDatos();
            ventanaPrincipal.setVisible(true);
            // Opcional: oculta la ventana agregar café
            setVisible(false);
        }

        if (e.getSource() == this.botonVolver) {
            // Hace visible la ventana principal
            ventanaPrincipal.setVisible(true);
            // Hace invisible la ventana actual
            setVisible(false);
        }
    }
}
