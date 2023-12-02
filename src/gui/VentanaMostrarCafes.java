package gui;

import controller.Controlador;
import model.Cafe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMostrarCafes extends VentanaGeneral implements ActionListener {
    private final VentanaPrincipal VentanaPrincipal;
    private final Controlador controlador;
    private JButton botonOk;

    public VentanaMostrarCafes(VentanaPrincipal ventanaPrincipal, Controlador controlador) {
        super("Cafes", 300,300);
        this.VentanaPrincipal = ventanaPrincipal;
        this.controlador = controlador;
        this.generarElementos();
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
        panel.add(tabla);
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
                modelo.addRow(new Object[]{
                        cafe.getNombre(),
                        cafe.getMililitrosAgua(),
                        cafe.getGramosCafe(),
                        cafe.getTamaño(),
                        cafe.getIngredientesOpcionales()
                });
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void generarBotonOk() {
        JButton buttonOk = super.generarBoton("Ok", 100, 200, 300, 50);
        this.botonOk = buttonOk;
        this.add(buttonOk);
        botonOk.addActionListener(this);
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
