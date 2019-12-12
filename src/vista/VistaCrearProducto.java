/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import adicional.RMAException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Manuel Jesus Sanchez Vega
 */
public class VistaCrearProducto extends JPanel {

    private JPanel panelBotones, panelDatos;
    private JLabel titulo, nombre, referencia, nFactura, problema;
    private JTextField tNombre, tReferencia, tNFactura;
    private JTextArea tProblema;
    private botonbeanproyecto.BotonBeanProyecto btAdd, btLimpiar;

    public VistaCrearProducto() {

        iniciarComponentes();

    }

    private void iniciarComponentes() {

        setLayout(new BorderLayout());
        setBackground(new Color(213, 229, 247));

        //Inicializo los JLabel
        titulo = new JLabel("Introduzca los datos del producto", JLabel.CENTER);
        Font negrita = new Font("Arial", Font.BOLD, 15);
        titulo.setFont(negrita);
        titulo.setForeground(Color.blue);

        nombre = new JLabel("Nombre del producto:");
        nombre.setForeground(Color.blue);
        referencia = new JLabel("Numero de referencia(EAN):");
        referencia.setForeground(Color.blue);
        nFactura = new JLabel("Número de factura:");
        nFactura.setForeground(Color.blue);
        problema = new JLabel("Descripcion del problema:");
        problema.setForeground(Color.blue);

        //Inicializo los textField
        tNombre = new JTextField();
        tNombre.setPreferredSize(new Dimension(200, 30));
        tReferencia = new JTextField();
        tReferencia.setPreferredSize(new Dimension(200, 30));
        tNFactura = new JTextField();
        tNFactura.setPreferredSize(new Dimension(200, 30));
        tProblema = new JTextArea();
        tProblema.setPreferredSize(new Dimension(200, 100));
        tProblema.setLineWrap(true);

        //Inicializo los botones
        btAdd = new botonbeanproyecto.BotonBeanProyecto("Añadir");
        btLimpiar = new botonbeanproyecto.BotonBeanProyecto("Limpiar datos");

        add(titulo, BorderLayout.NORTH);

        //Panel central
        panelDatos = new JPanel();
        panelDatos.setLayout(new GridBagLayout());
        panelDatos.setBackground(new Color(213, 229, 247));

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridwidth = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy = 0;
        panelDatos.add(nombre, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        panelDatos.add(tNombre, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        panelDatos.add(referencia, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        panelDatos.add(tReferencia, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        panelDatos.add(nFactura, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        panelDatos.add(tNFactura, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        panelDatos.add(problema, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        panelDatos.add(tProblema, gc);

        add(panelDatos, BorderLayout.CENTER);

        //Panel inferior
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        panelBotones.setBackground(new Color(213, 229, 247));

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        panelBotones.add(btLimpiar, c);

        c.gridx = 2;
        c.gridy = 0;
        panelBotones.add(btAdd, c);

        add(panelBotones, BorderLayout.SOUTH);

    }

    /**
     * Metodo que pone los JTextField vacios
     */
    public void limpiar() {

        tNombre.setText("");
        tNFactura.setText("");
        tProblema.setText("");
        tReferencia.setText("");

    }

    public void addManejador(ActionListener ae) {

        btLimpiar.addActionListener(ae);
        btLimpiar.setActionCommand("limpiar");

        btAdd.addActionListener(ae);
        btAdd.setActionCommand("add");

    }

    public String getNombreProducto() {

        return tNombre.getText();

    }

    public int getReferencia() throws RMAException {
        int ref = 0;

        //Si la referencia esta vacia, la pone a -1, ya que la referencia 0 puede existir
        if (tReferencia.getText().equals("")) {
            ref = -1;
        } else {
            try {
                ref = Integer.parseInt(tReferencia.getText());
            } catch (NumberFormatException e) {
                //lanza un rma exception para capturarla a la hora de crear el producto y asi que no se cree
                throw new RMAException("En la referencia no se aceptan letras");
            }
        }
        return ref;

    }

    public int getNumeroFact() throws RMAException {
        int ref = 0;
        //Si el numero de factura esta vacio, lo pone a -1, ya que el numero 0 puede existir
        if (tNFactura.getText().equals("")) {
            ref = -1;
        } else {
            try {
                ref = Integer.parseInt(tNFactura.getText());
            } catch (NumberFormatException e) {
                //lanza un rma exception para capturarla a la hora de crear el producto y asi que no se cree
                throw new RMAException("El numero de factura solo acepta numeros");
            }
        }
        return ref;
    }

    public String getProblema() {

        return tProblema.getText();

    }
}
