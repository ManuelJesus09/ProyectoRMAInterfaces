/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import modelo.ModeloTablaProductos;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Manuel Jesus Sanchez Vega
 */
public class tablaProductos extends JScrollPane {

    private static JTable tabla;
    private static ModeloTablaProductos modelo;

    public tablaProductos() {

        tabla = new JTable();
        modelo = new ModeloTablaProductos();
        tabla.setModel(modelo);

        this.setViewportView(tabla);
        getViewport().setBackground(new Color(213, 229, 247));
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    }

    public void annadirColumnas(String[] columnas) {

        modelo.setColumnIdentifiers(columnas);

    }

    public static void annadirFilas(Object[] fila) {

        modelo.addRow(fila);

    }

    public static void borrarFilaSeleccionada() {

        modelo.removeRow(tabla.getSelectedRow());
    }

    public static int devolverEANSeleccionada() {

        int ean = -1;

        try {
            ean = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());

        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showConfirmDialog(null, "No has seleccionado ningun producto", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
        }

        return ean;
    }

}
