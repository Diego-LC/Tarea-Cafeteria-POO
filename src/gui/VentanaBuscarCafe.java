package gui;

import controller.Controlador;
import model.Cafe;
import model.Tamaño;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaBuscarCafe extends VentanaGeneral implements ActionListener {

    private Controlador controlador;
    private VentanaPrincipal ventanaPrincipal;
    private JComboBox<String> comboBoxBuscar;
    private JButton botonBuscar;
    private JTextArea textAreaResultado;
    private JButton botonVolver;

    public VentanaBuscarCafe(Controlador controlador, VentanaPrincipal ventanaPrincipal) {
        super("Buscar Cafés");
        this.controlador = controlador;
        this.ventanaPrincipal = ventanaPrincipal;
        this.generarElementos();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setVisible(true);
        setSize(400, 350);
    }

    private void generarElementos() {
        setLayout(null);  // Establecer un null layout para la ventana
    
        generarEncabezado();
        generarComboBoxBuscar();
        generarBotonBuscar();
        generarTextArea();
        generarBotonVolver();
    }

    private void generarEncabezado() {
        JLabel label = new JLabel("Seleccione el tamaño del café");
        add(label);
    }

    private void generarComboBoxBuscar() {
        this.comboBoxBuscar = new JComboBox<>();
        for (Tamaño tamaño : Tamaño.values()) {
            this.comboBoxBuscar.addItem(tamaño.name());
        }
        this.comboBoxBuscar.setBounds(50, 50, 100, 20);  // Establecer la posición y el tamaño del comboBox
        add(this.comboBoxBuscar);
    }

    private void generarBotonBuscar() {
        this.botonBuscar = new JButton("Buscar");
        this.botonBuscar.setBounds(200, 50, 100, 20);  // Establecer la posición y el tamaño del botón
        add(this.botonBuscar);
        this.botonBuscar.addActionListener(this);
    }

    private void generarTextArea() {
        this.textAreaResultado = new JTextArea(10, 30);
        this.textAreaResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(this.textAreaResultado);
        scrollPane.setBounds(50, 100, 300, 100);  // Establecer la posición y el tamaño del scrollPane
        add(scrollPane);
    }

    private void generarBotonVolver() {
        this.botonVolver = super.generarBoton("Volver", 100, 240, 200, 30);
        add(this.botonVolver);
        botonVolver.addActionListener(this);
    }

    public void setVisible(boolean b) {
        super.setVisible(b);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == this.botonBuscar){
            String nombreCafe = (String) this.comboBoxBuscar.getSelectedItem();
            ArrayList<Cafe> cafesEncontrados = this.controlador.cafeteria.obtenerCafesPorTamanio(nombreCafe);
            this.textAreaResultado.setText("");
            if (!cafesEncontrados.isEmpty()) {
                for (Cafe cafe : cafesEncontrados) {
                    this.textAreaResultado.append(cafe.toString() + "\n");
                }
            } else {
                this.textAreaResultado.setText("Café no encontrado");
            }
        }
        if (e.getSource() == this.botonVolver){
            this.ventanaPrincipal.setVisible(true);
            setVisible(false);
        }
    }
}