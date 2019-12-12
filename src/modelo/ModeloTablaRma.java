/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel Jesus Sanchez Vega
 */
public class ModeloTablaRma extends DefaultTableModel{
     
    //referenciaRma, fecha
    private Class[] tipoColumna = {Integer.class, String.class};

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Class getColumnClass(int columIndex) {
        return tipoColumna[columIndex];
    }
}
