package controlador;

import adicional.ConexionBD;
import adicional.Producto;
import adicional.RMAException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import vista.VistaCrearProducto;
import vista.VistaCrearRma;
import vista.tablaProductos;

/**
 *
 * @author Manuel Jesus Sanchez Vega
 */
public class Controlador2 implements ActionListener {

    VistaCrearRma panel;
    JDialog dialog1;

    public Controlador2(VistaCrearRma vista, JDialog dialogo) {

        panel = vista;
        dialog1 = dialogo;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String comando = ae.getActionCommand();

        switch (comando) {

            case "annadir":

                //Crea una ventana para introducir los datos del producto
                JDialog dialogoProductos = new JDialog(dialog1, true);
                dialogoProductos.setTitle("Datos del producto");
                VistaCrearProducto panelProducto = new VistaCrearProducto();
                dialogoProductos.setContentPane(panelProducto);

                //Le asigna un controlador
                Controlador3 controladorVentanaProductos = new Controlador3(panelProducto);
                panelProducto.addManejador(controladorVentanaProductos);

                dialogoProductos.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialogoProductos.setSize(450, 350);
                dialogoProductos.setLocationRelativeTo(null);
                dialogoProductos.setResizable(false);
                dialogoProductos.setVisible(true);

                break;

            case "eliminar":

                if (Controlador1.productos.size() == 0) {//Si no hay productos muestra el mensaje correspondiente
                    
                    JOptionPane.showConfirmDialog(panel, "No existen productos para eliminar", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
                    
                } else {
                    //Recojo el codigo EAN del producto que se ha seleccionado 
                    int codigoEANSeleccionado = tablaProductos.devolverEANSeleccionada();
                    if (codigoEANSeleccionado != -1) {

                        try {

                            //Creo un producto temporal con el EAN seleccionado, ya que el equals se rige por el EAN
                            Controlador1.productos.remove(new Producto("aaa", codigoEANSeleccionado, 000, "aaa"));

                        } catch (RMAException e) {
                            JOptionPane.showConfirmDialog(panel, e.getMessage(), "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
                        }

                        //Borra el elemento de la tabla que se muestra en la vistaCrearRma
                        tablaProductos.borrarFilaSeleccionada();
                    }
                }

                break;

            case "cancelar":

                dialog1.dispose();

                break;

            case "enviar":

                if (Controlador1.productos.size() == 0) {
                    JOptionPane.showConfirmDialog(panel, "No se puede enviar una solicitud sin productos", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
                } else {
                    //meter en base de datos
                    insertarDatos();
                    dialog1.dispose();
                }
                break;

            default:
                break;

        }

    }

    private void insertarDatos() {

        try (Statement insertarRma = ConexionBD.getConexion().createStatement()) {

            //ResultSet rmaExistente = insertarRma.executeQuery("SELECT * FROM rma WHERE id=" + VistaCrearRma.getReferencia());
            //Inserta el rma
            insertarRma.executeUpdate("INSERT INTO rma (id, fecha) VALUES('" + VistaCrearRma.getReferencia() + "', '" + VistaCrearRma.getFecha() + "')");

            for (Producto prod : Controlador1.productos) {

                insertarRma.executeUpdate("INSERT INTO producto (id, nombre, numero_factura) VALUES('" + prod.getEan() + "', '" + prod.getNombre() + "', '" + prod.getNumeroFactura() + "')");
                insertarRma.executeUpdate("INSERT INTO problema (descripcion, id_rma, id_producto) VALUES('" + prod.getProblema() + "', '" + VistaCrearRma.getReferencia() + "', '" + prod.getEan() + "')");

            }

            ConexionBD.hacerCommit();
            ConexionBD.cerrarConexion();

        } catch (SQLException | ClassNotFoundException ex) {
            //Si lanza excepcion, no se hace ningun insert
            try {
                ConexionBD.hacerRollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
        }
    }

}
