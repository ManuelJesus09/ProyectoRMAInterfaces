/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import adicional.ConexionBD;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Manuel Jesus Sanchez Vega
 */
public class VistaCrearRma extends JPanel {

    private JPanel panel1, panel2, panel3, panel4, panel5;
    private JScrollPane panelArea;
    private JLabel titulo, referenciaSolicitud, fecha, productos;
    private static JTextField tReferencia, tFecha;
    private botonbeanproyecto.BotonBeanProyecto btAnnadir, btEliminar, btCancelar, btEnviarSolicitud;
    private tablaProductos panelConTabla;

    public VistaCrearRma() {

        iniciarComponentes();

    }

    private void iniciarComponentes() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Panel del titulo
        panel1 = new JPanel();
        titulo = new JLabel("Introduzca los datos para crear su solicitud", JLabel.CENTER);
        Font negrita = new Font("Arial", Font.BOLD, 15);
        titulo.setFont(negrita);
        titulo.setForeground(Color.blue);
        panel1.add(titulo);
        panel1.setBackground(new Color(213, 229, 247));

        //Panel datos
        panel2 = new JPanel();
        panel2.setBackground(new Color(213, 229, 247));

        referenciaSolicitud = new JLabel("Número de referencia de la solicitud:");
        referenciaSolicitud.setForeground(Color.blue);
        fecha = new JLabel("Fecha:");
        fecha.setForeground(Color.blue);

        tReferencia = new JTextField();
        tReferencia.setPreferredSize(new Dimension(200, 30));
        tReferencia.setEditable(false);
        tReferencia.setText(String.valueOf(crearReferencia()));

        //Creo la fecha en formato
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        tFecha = new JTextField(format.format(new Date()));
        tFecha.setPreferredSize(new Dimension(200, 30));
        tFecha.setEditable(false);

        panel2.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        panel2.add(referenciaSolicitud, c);

        c.gridx = 1;
        c.gridy = 0;
        panel2.add(tReferencia, c);

        c.gridx = 0;
        c.gridy = 1;
        panel2.add(fecha, c);

        c.gridx = 1;
        c.gridy = 1;
        panel2.add(tFecha, c);

        //Panel central con productos
        panel3 = new JPanel();
        panel3.setBackground(new Color(213, 229, 247));
        panel3.setLayout(new BorderLayout());

        productos = new JLabel("Productos");
        productos.setForeground(Color.blue);
        panel3.add(productos, BorderLayout.NORTH);

        //Inserta la tabla
        panelConTabla = new tablaProductos();
        String[] nombreColumna = {"Nombre", "Problema", "Codigo EAN", "Numero de factura"};
        panelConTabla.annadirColumnas(nombreColumna);
        panelConTabla.setPreferredSize(new Dimension(550, 200));
        panel3.add(panelConTabla, BorderLayout.CENTER);

        //panel botones textArea
        panel4 = new JPanel();
        panel4.setBackground(new Color(213, 229, 247));
        btAnnadir = new botonbeanproyecto.BotonBeanProyecto("Añadir");
        btEliminar = new botonbeanproyecto.BotonBeanProyecto("Eliminar");
        panel4.add(btAnnadir);
        panel4.add(btEliminar);

        panel3.add(panel4, BorderLayout.SOUTH);

        //panel botones inferiores
        panel5 = new JPanel();
        panel5.setBackground(new Color(213, 229, 247));
        panel5.setLayout(new GridBagLayout());
        btCancelar = new botonbeanproyecto.BotonBeanProyecto("Cancelar");
        btEnviarSolicitud = new botonbeanproyecto.BotonBeanProyecto("Enviar solicitud");
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridwidth = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy = 0;
        panel5.add(btCancelar, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        panel5.add(btEnviarSolicitud, gc);

        //Añadimos los paneles a la ventana
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel5);
    }

    public void addManejador(ActionListener ae) {

        btAnnadir.addActionListener(ae);
        btAnnadir.setActionCommand("annadir");

        btEliminar.addActionListener(ae);
        btEliminar.setActionCommand("eliminar");

        btCancelar.addActionListener(ae);
        btCancelar.setActionCommand("cancelar");

        btEnviarSolicitud.addActionListener(ae);
        btEnviarSolicitud.setActionCommand("enviar");
    }

    private int crearReferencia() {

        int referencia = -1;
        boolean rmaExiste;

        try (Statement rmaExistente = ConexionBD.getConexion().createStatement()) {

            //Crea una referencia nueva, sigue dando vueltas en el bucle si la referencia existe en la base de datos
            do {
                referencia = (int) (100000 * Math.random());
                ResultSet resulRma = rmaExistente.executeQuery("SELECT * FROM rma WHERE id=" + referencia);
                rmaExiste = resulRma.next();
            } while (rmaExiste);

            ConexionBD.hacerCommit();
            ConexionBD.cerrarConexion();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return referencia;
    }

    public static int getReferencia() {

        return Integer.parseInt(tReferencia.getText());

    }

    public static String getFecha() {

        return tFecha.getText();

    }
}
