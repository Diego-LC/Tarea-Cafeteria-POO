package gui;

import controller.Controlador;
import model.Cafe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMostrarCafes extends VentanaGeneral implements ActionListener {
    private final VentanaPrincipal VentanaPrincipal;
    private final Controlador controlador;
    private JButton botonOk;

    public VentanaMostrarCafes(VentanaPrincipal ventanaPrincipal, Controlador controlador) {
        super("Cafes");
        super.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.VentanaPrincipal = ventanaPrincipal;
        this.controlador = controlador;
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.generarElementos();
        //this.setSize(500,400);
        this.pack();
    }

    private void generarElementos() {
        super.generarJLabelEncabezado(null, "Lista de cafés en cafeteria", 100, 20, 300, 30);
        this.generarDatosEnTabla();
        this.generarBotonOk();
    }

    private void generarDatosEnTabla() {
        Object[] items = super.generarTabla();
        JTable tabla = (JTable) items[0];
        JPanel panel = (JPanel) items[1];
        tabla.setModel(this.generarModeloTabla());
        this.add(panel);
        /*tabla.revalidate();
        tabla.repaint();*/
    }

    private DefaultTableModel generarModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Mililitros de Agua");
        modelo.addColumn("Gramos de cafe");
        modelo.addColumn("Tamaño");
        modelo.addColumn("Ingredientes opcionales");
        this.añadirFilas(modelo);
        return modelo;
    }

    private void añadirFilas(DefaultTableModel modelo) {
        try{
            for (Cafe cafe : this.controlador.cafeteria.getCafes()) {
                if (cafe.getIngredientesOpcionales() != null){
                    modelo.addRow(new Object[]{
                            cafe.getNombre(),
                            cafe.getMililitrosAgua(),
                            cafe.getGramosCafe(),
                            cafe.getTamaño().getCategoria(),
                            cafe.getIngredientesOpcionales().getIngrediente()
                    });
                }else {
                    modelo.addRow(new Object[]{
                            cafe.getNombre(),
                            cafe.getMililitrosAgua(),
                            cafe.getGramosCafe(),
                            cafe.getTamaño().getCategoria(),
                            " "});
                }
            }
        }catch (Exception e){
            System.out.println("L76: VentanaMostrarCafes error: " + e.getMessage());
        }
    }

    private void generarBotonOk() {
        this.botonOk = super.generarBoton("Ok", 100, 20, 100, 30);
        this.botonOk.addActionListener(this);
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(botonOk);
        box.add(Box.createHorizontalGlue());
        this.add(Box.createVerticalGlue());
        this.add(box);
        this.add(Box.createVerticalGlue());
    }

    public void setVisible(boolean b) {
        super.setVisible(b);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonOk) {
            this.setVisible(false);
        }
    }
}
