/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import modelo.ModeloTablaRma;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Manuel Jesus Sanchez Vega
 */
public class VistaMostrarRma extends JScrollPane {

    private JTable tabla;
    private ModeloTablaRma modelo;

    public VistaMostrarRma() {

        initComponents();

    }

    private void initComponents() {

        tabla = new JTable();
        modelo = new ModeloTablaRma();
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
