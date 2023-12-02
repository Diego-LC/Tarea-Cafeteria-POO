package gui;

import controller.Controlador;
import model.Cafe;
import model.Cafeteria;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaEliminarCafe extends JFrame implements ActionListener {
    private Controlador controlador;
    private VentanaPrincipal ventanaPrincipal;
    private JTextField textFieldTamanio;
    private JTextArea textAreaResultado;
    private JButton botonBuscar;
    private JButton botonEliminar;
    private Cafe cafeSeleccionado;

    public VentanaEliminarCafe(Controlador controlador, VentanaPrincipal ventanaPrincipal) {
        this.controlador = controlador;
        this.ventanaPrincipal = ventanaPrincipal;
        setTitle("Eliminar Café");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panelEliminar = new JPanel();
        panelEliminar.setLayout(new BoxLayout(panelEliminar, BoxLayout.Y_AXIS));

        textFieldTamanio = new JTextField(20);
        botonBuscar = new JButton("Buscar");
        textAreaResultado = new JTextArea(10, 30);
        textAreaResultado.setEditable(false);
        botonEliminar = new JButton("Eliminar");

        panelEliminar.add(new JLabel("Ingrese el tamaño del café"));
        panelEliminar.add(textFieldTamanio);
        panelEliminar.add(botonBuscar);
        panelEliminar.add(new JScrollPane(textAreaResultado));
        panelEliminar.add(botonEliminar);

        botonBuscar.addActionListener(this);
        botonEliminar.addActionListener(this);

        add(panelEliminar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonBuscar) {
            String tamanio = textFieldTamanio.getText();
            ArrayList<Cafe> cafesEncontrados = controlador.cafeteria.obtenerCafesPorTamanio(tamanio);
            if (!cafesEncontrados.isEmpty()) {
                textAreaResultado.setText("");
                for (Cafe cafe : cafesEncontrados) {
                    textAreaResultado.append(cafe.toString() + "\n");
                }
                cafeSeleccionado = cafesEncontrados.get(0); // selecciona el primer café por defecto
            } else {
                textAreaResultado.setText("Café no encontrado");
            }
        } else if (e.getSource() == botonEliminar) {
            if (cafeSeleccionado != null) {
                controlador.cafeteria.descontinuarCafe(cafeSeleccionado);
                textAreaResultado.setText("Café eliminado: " + cafeSeleccionado.toString());
                cafeSeleccionado = null; // resetea la selección
            } else {
                textAreaResultado.setText("No hay café seleccionado para eliminar");
            }
        }
    }
}
