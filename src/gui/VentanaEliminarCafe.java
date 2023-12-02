package gui;

import controller.Controlador;
import model.Cafe;
import model.Cafeteria;
import model.Tamaño;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaEliminarCafe extends JFrame implements ActionListener {
    private Controlador controlador;
    private VentanaPrincipal ventanaPrincipal;
    private JComboBox<String> comboBoxTamanio;
    private JList<String> listResultado;
    private JButton botonBuscar;
    private JButton botonEliminar;
    private JButton botonVolver;
    private Cafe cafeSeleccionado;

    public VentanaEliminarCafe(Controlador controlador, VentanaPrincipal ventanaPrincipal) {
        this.controlador = controlador;
        this.ventanaPrincipal = ventanaPrincipal;
        setTitle("Eliminar Café");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        generarElementos();
    }

    private void generarElementos() {
        setLayout(null);  // Establecer un null layout para la ventana
    
        generarComboBoxTamanio();
        generarBotonBuscar();
        generarListResultado();
        generarBotonEliminar();
        generarBotonVolver();
    }
    
    private void generarComboBoxTamanio() {
        this.comboBoxTamanio = new JComboBox<>();
        for (Tamaño tamaño : Tamaño.values()) {
            this.comboBoxTamanio.addItem(tamaño.name());
        }
        this.comboBoxTamanio.setBounds(50, 50, 100, 20);  // Establecer la posición y el tamaño del comboBox
        add(this.comboBoxTamanio);
    }
    
    private void generarBotonBuscar() {
        this.botonBuscar = new JButton("Buscar");
        this.botonBuscar.setBounds(200, 50, 100, 20);  // Establecer la posición y el tamaño del botón
        add(this.botonBuscar);
        this.botonBuscar.addActionListener(this);
    }
    
    private void generarListResultado() {
        this.listResultado = new JList<>();
        JScrollPane scrollPane = new JScrollPane(this.listResultado);
        scrollPane.setBounds(50, 100, 300, 100);  // Establecer la posición y el tamaño del scrollPane
        add(scrollPane);
    }
    
    private void generarBotonEliminar() {
        this.botonEliminar = new JButton("Eliminar");
        this.botonEliminar.setBounds(100, 240, 200, 30);  // Establecer la posición y el tamaño del botón
        add(this.botonEliminar);
        botonEliminar.addActionListener(this);
    }
    
    private void generarBotonVolver() {
        this.botonVolver = new JButton("Volver");
        this.botonVolver.setBounds(100, 280, 200, 30);  // Establecer la posición y el tamaño del botón
        add(this.botonVolver);
        botonVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonBuscar) {
            buscarCafe();
        } else if (e.getSource() == botonEliminar) {
            eliminarCafe();
        } else if (e.getSource() == botonVolver) {
            volver();
        }
    }

    private void buscarCafe() {
        String tamanio = (String) comboBoxTamanio.getSelectedItem();
        ArrayList<Cafe> cafesEncontrados = controlador.cafeteria.obtenerCafesPorTamanio(tamanio);
        if (!cafesEncontrados.isEmpty()) {
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Cafe cafe : cafesEncontrados) {
                listModel.addElement(cafe.toString());
            }
            listResultado.setModel(listModel);
            cafeSeleccionado = cafesEncontrados.get(0); // selecciona el primer café por defecto
        } else {
            listResultado.setModel(new DefaultListModel<>());  // Limpiar la lista
        }
    }

    private void eliminarCafe() {
        int selectedIndex = listResultado.getSelectedIndex();
        if (selectedIndex != -1) {
            controlador.cafeteria.descontinuarCafe(cafeSeleccionado);
            ((DefaultListModel<String>) listResultado.getModel()).remove(selectedIndex);
            cafeSeleccionado = null; // resetea la selección
        } else {
            JOptionPane.showMessageDialog(this, "No hay café seleccionado para eliminar");
        }
    }

    private void volver() {
        this.dispose();
        ventanaPrincipal.setVisible(true);
    }
}