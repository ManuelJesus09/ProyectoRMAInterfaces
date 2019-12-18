package vista;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import modelo.ModeloTablaRmaClick;

/**
 *
 * @author Manuel
 */
public class VistaMostrarRmaClick extends JScrollPane {

    private static JTable tabla;
    private ModeloTablaRmaClick modelo;

    public VistaMostrarRmaClick() {

        initComponents();

    }

    private void initComponents() {

        tabla = new JTable();
        modelo = new ModeloTablaRmaClick();
        tabla.setModel(modelo);

        setViewportView(tabla);
        getViewport().setBackground(new Color(213, 229, 247));
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public void annadirColumnas(String[] columnas) {

        modelo.setColumnIdentifiers(columnas);

    }

    public void annadirFilas(Object[] fila) {

        modelo.addRow(fila);

    }

    public void centrarColumnas() {
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
        tabla.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
    }

}
