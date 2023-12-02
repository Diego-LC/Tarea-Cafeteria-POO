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
        super("Ventana Café", 500, 500);
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
        super.generarJLabel(null, "Ingrese el tipo de café", 100, 120, 300, 30);
        this.generarInputTextFieldMililitrosAgua();
        super.generarJLabel(null, "Ingrese el precio", 100, 180, 300, 30);
        this.generarListaDesplegableTamaño();
        this.generarListaDesplegableIngredientesOpcionales();
        this.generarInputTextFielGramosCafe();
        this.generarBotonAgregarCafe();
        this.generarBotonVolver();
    }

    private void generarInputTextFieldNombreCafe() {
        JTextField textField = super.generarJTextField(100, 90, 300, 30);
        this.textFieldNombreCafe = textField;
        this.add(textField);
    }

    private void generarInputTextFieldMililitrosAgua() {
        JTextField textField = super.generarJTextField(100, 150, 300, 30);
        this.textFieldMililitrosAgua = textField;
        this.add(textField);
    }

    private void generarInputTextFielGramosCafe() {
        JTextField textField = super.generarJTextField(100, 210, 300, 30);
        this.textFieldGramosCafe = textField;
        this.add(textField);
    }

    private void generarListaDesplegableTamaño() {
        JComboBox comboBox = super.generarListaDesplegable(100, 270, 300, 30);
        this.comboBoxTamaño = comboBox;
        this.add(comboBox);
        for (Tamaño tamaño : Tamaño.values()) {
            comboBox.addItem(tamaño);
        }
        comboBox.addActionListener(this);
    }

    private void generarListaDesplegableIngredientesOpcionales() {
        JComboBox comboBox = super.generarListaDesplegable(100, 330, 300, 30);
        this.comboBoxIngredientesOpcionales = comboBox;
        this.add(comboBox);
        for (IngredientesOpcionales ingredientesOpcionales : IngredientesOpcionales.values()) {
            comboBox.addItem(ingredientesOpcionales);
        }
        comboBox.addActionListener(this);
    }

    private void generarBotonAgregarCafe() {
        JButton jbAgregarCafe = super.generarBoton("Agregar café", 100, 350, 300, 50);
        this.add(jbAgregarCafe);
        this.botonAgregarCafe = jbAgregarCafe;
        this.botonAgregarCafe.addActionListener(this);
    }

    private void generarBotonVolver() {
        JButton volver = super.generarBoton("Volver", 100, 370, 300, 50);
        this.botonVolver = volver;
        this.add(volver);
        this.botonVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.botonAgregarCafe == e.getSource()) {
            // Código para agregar café
            String nombreCafe = textFieldNombreCafe.getText();
            String mililitros = textFieldMililitrosAgua.getText();
            int mililitrosAgua = mililitros.isEmpty() ? 0 : Integer.parseInt(mililitros);
            String gramos = textFieldGramosCafe.getText();
            int gramosCafe = gramos.isEmpty() ? 0 : Integer.parseInt(gramos);
            Tamaño tamaño = (Tamaño) comboBoxTamaño.getSelectedItem();
            IngredientesOpcionales ingredientesOpcionales = (IngredientesOpcionales) comboBoxIngredientesOpcionales.getSelectedItem();

            Cafe cafe = new Cafe(nombreCafe, gramosCafe, mililitrosAgua, tamaño, ingredientesOpcionales);
            this.controlador.cafeteria.agregarNuevoCafe(nombreCafe, gramosCafe, mililitrosAgua, tamaño, ingredientesOpcionales);
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
