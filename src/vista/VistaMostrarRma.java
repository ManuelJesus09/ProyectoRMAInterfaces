/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import adicional.ConexionBD;
import botonbeanproyecto.BotonBeanProyecto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.ModeloTablaRma;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Clase del panel que muestra las solicitudes rma que se han enviado
 *
 * @author Manuel Jesus Sanchez Vega
 */
public class VistaMostrarRma extends JPanel {

    private static JTable tabla;
    private ModeloTablaRma modelo;
    private static JFrame ventanaPrincipal;
    private JScrollPane panelTabla;
    private JPanel panelInfo;
    private JLabel info;
    private static botonbeanproyecto.BotonBeanProyecto detalles;

    public VistaMostrarRma(JFrame ventana) {

        initComponents();
        ventanaPrincipal = ventana;

    }

    private void initComponents() {

        setLayout(new BorderLayout());

        //Inicializa la tabla, le añade el modelo y le añade un MouseAdapter, para ver los detalles de las solicitudes
        panelTabla = new JScrollPane();
        tabla = new JTable();
        modelo = new ModeloTablaRma();
        tabla.setModel(modelo);
        tabla.addMouseListener(new MouseAdapterCambiado());

        panelTabla.setViewportView(tabla);
        panelTabla.getViewport().setBackground(new Color(213, 229, 247));
        panelTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        panelInfo = new JPanel();
        panelInfo.setBackground(new Color(213, 229, 247));
        info = new JLabel("<html><body>Para ver los detalles de una solicitud,<br>puedes hacer doble click en ella o<br>pulsar en detalles </body></html>");
        detalles = new BotonBeanProyecto("Detalles");
        //Le annado un actionListener en una clase anonima
        detalles.setActionCommand("detalles");
        detalles.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getActionCommand().equals("detalles")) {
                    try {
                        MouseAdapterCambiado.crearVentanaDetalles();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        JOptionPane.showConfirmDialog(null, "No has seleccionado ningun Rma", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //Annade los paneles al panel prinicpal
        panelInfo.add(info);
        panelInfo.add(detalles);

        add(panelTabla, BorderLayout.CENTER);
        add(panelInfo, BorderLayout.SOUTH);
    }

    /**
     * Metodo que, dado un array de string, les pone nombre a las columnas de la
     * tabla
     *
     * @param columnas
     */
    public void annadirColumnas(String[] columnas) {

        modelo.setColumnIdentifiers(columnas);

    }

    /**
     * Metodo que añade una fila a la tabla, la que se le pasa por parametros
     *
     * @param fila
     */
    public void annadirFilas(Object[] fila) {

        modelo.addRow(fila);

    }

    /**
     * Metodo que centra el texto de las dos columnas de la tabla
     */
    public void centrarColumnas() {
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
        tabla.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
    }

    /**
     * Metodo que abre una ventana con los datos de un rma, cuando se hace doble
     * click en algun rma
     */
    private static class MouseAdapterCambiado extends MouseAdapter {

        public MouseAdapterCambiado() {
        }

        public void mouseClicked(MouseEvent evnt) {
            if (evnt.getClickCount() == 2) {

                crearVentanaDetalles();
            }
        }

        private static void rellenarTablaVistaRmaClick(VistaMostrarRmaClick mostrarRma) {
            //Pone los nombres a las columnas
            String[] nombreColumnas = {"Nombre del producto", "Problema"};
            mostrarRma.annadirColumnas(nombreColumnas);

            mostrarRma.centrarColumnas();

            int numeroDeRma = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());

            try (Statement sentencia = ConexionBD.getConexion().createStatement()) {

                ResultSet resul = sentencia.executeQuery("SELECT p.nombre, pr.descripcion FROM producto p INNER JOIN problema pr ON p.id=pr.id_producto WHERE pr.id_rma=" + numeroDeRma);

                while (resul.next()) {

                    String nombreProd = resul.getString("nombre");
                    String poema = resul.getString("descripcion");

                    Object[] fila = {nombreProd, poema};
                    mostrarRma.annadirFilas(fila);
                }

                ConexionBD.cerrarConexion();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public static void crearVentanaDetalles() {
            //Abrir ventana con los datos del rma
            VistaMostrarRmaClick mostrarRma = new VistaMostrarRmaClick();

            //Rellena la tabla con los datos de la base de datos
            rellenarTablaVistaRmaClick(mostrarRma);

            JDialog dialog3 = new JDialog(ventanaPrincipal, true);
            dialog3.setTitle("Datos del RMA seleccionado");
            dialog3.setContentPane(mostrarRma);
            dialog3.setSize(600, 200);
            dialog3.setLocationRelativeTo(null);
            dialog3.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog3.setVisible(true);
        }
    }

}
