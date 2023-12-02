package gui;

import controller.Controlador;
import model.Cafe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class VentanaBuscarCafe extends JFrame {

    private Controlador controlador;
    private VentanaPrincipal ventanaPrincipal;
    private JTextField textFieldBuscar;
    private JButton botonBuscar;
    private JTextArea textAreaResultado;

    public VentanaBuscarCafe(Controlador controlador, VentanaPrincipal ventanaPrincipal) {
        this.controlador = controlador;
        this.ventanaPrincipal = ventanaPrincipal;
        setTitle("Buscar Cafés");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panelBuscar = new JPanel();
        panelBuscar.setLayout(new FlowLayout());
        textFieldBuscar = new JTextField(20);
        botonBuscar = new JButton("Buscar");
        textAreaResultado = new JTextArea(10, 30);
        textAreaResultado.setEditable(false);

        botonBuscar.addActionListener((ActionEvent e) -> {
            String nombreCafe = textFieldBuscar.getText();
            ArrayList<Cafe> cafesEncontrados = controlador.cafeteria.obtenerCafesPorTamanio(nombreCafe);
            if (!cafesEncontrados.isEmpty()) {
                for (Cafe cafe : cafesEncontrados) {
                    textAreaResultado.append(cafe.toString() + "\n");
                }
            } else {
                textAreaResultado.setText("Café no encontrado");
            }
        });

        panelBuscar.add(textFieldBuscar);
        panelBuscar.add(botonBuscar);
        add(panelBuscar, BorderLayout.NORTH);
        add(new JScrollPane(textAreaResultado), BorderLayout.CENTER);
    }

    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b) {
            textFieldBuscar.setText("");
            textAreaResultado.setText("");
        }
    }
}
