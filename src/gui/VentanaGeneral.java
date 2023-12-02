package gui;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class VentanaGeneral extends JFrame implements ActionListener{
    private final Font fuenteTitulo;
    private final Font fuenteTexto;
    protected VentanaGeneral(String nombre){
        super(nombre);
        super.setVisible(true);
        super.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        this.setLayout(null);
        this.fuenteTitulo = new Font("Calibri", Font.BOLD | Font.ITALIC, 20);
        this.fuenteTexto = new Font("Calibri", Font.BOLD, 14);

    }
    protected void generarJLabelEncabezado(JLabel label, String texto, int posicionX, int posicionY, int largoX, int largoY){
        label= new JLabel(texto);
        label.setBounds(posicionX, posicionY, largoX, largoY);
        label.setFont(this.fuenteTitulo);
        this.add(label);

    }
    protected JButton generarBoton(String texto, int posicionX, int posicionY, int largoX, int largoY){
        JButton boton= new JButton(texto);
        boton.setBounds(posicionX, posicionY, largoX, largoY);
        boton.setFont(this.fuenteTexto);
        return boton;
    }
    protected void generarJLabel(JLabel label, String texto, int posicionX, int posicionY, int largoX, int largoY) {
        label= new JLabel(texto);
        label.setBounds(posicionX, posicionY, largoX, largoY);
        label.setFont(this.fuenteTexto);
        this.add(label);
    }

    protected JFormattedTextField generarJFormattedTextField(InternationalFormatter formato, int posicionX, int posicionY, int largoX, int largoY){
        JFormattedTextField textField=  new JFormattedTextField(formato);
        textField.setBounds(posicionX, posicionY, largoX, largoY);
        return textField;
    }

    protected InternationalFormatter generarFormato(int minimo){
        InternationalFormatter formato=new InternationalFormatter();
        formato.setMinimum(minimo);
        return formato;
    }

    protected InternationalFormatter generarFormato(int minimo, int maximo){
        InternationalFormatter formato=new InternationalFormatter();
        formato.setMinimum(minimo);
        formato.setMaximum(maximo);
        return formato;
    }

    protected JTextField generarEntradaNumero(int posicionX, int posicionY, int largoX, int largoY){
        JTextField textField = new JTextField();
        textField.setBounds(posicionX, posicionY,largoX,largoY);
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("\\d*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("\\d*")) {
                    super.replace(fb, offset, length, string, attr);
                }
            }
        });
        return textField;
    }

    protected JTextField generarJTextField(int posicionX, int posicionY, int largoX, int largoY){
        JTextField textField= new JTextField();
        textField.setBounds(posicionX, posicionY, largoX, largoY);
        return textField;
    }

    protected JRadioButton generarJRadioButton(String texto, int posicionX, int posicionY, int largoX, int largoY){
        JRadioButton boton= new JRadioButton(texto);
        boton.setBounds(posicionX, posicionY, largoX, largoY);
        return boton;
    }

    protected JComboBox generarListaDesplegable(int posicionX, int posicionY, int largoX, int largoY){
        JComboBox lista= new JComboBox();
        lista.setBounds(posicionX, posicionY, largoX, largoY);
        return lista;
    }

    protected Object[] generarTabla(){
        JTable tabla = new JTable();
        tabla.setFillsViewportHeight(true);
        JScrollPane scrollPane= new JScrollPane(tabla);
        JPanel panel= new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        return new Object[]{tabla, panel};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
