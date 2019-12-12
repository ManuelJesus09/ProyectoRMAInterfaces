package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import adicional.RMAException;
import adicional.Producto;
import vista.VistaCrearProducto;
import vista.tablaProductos;

/**
 *
 * @author Manuel Jesus Sanchez Vega
 */
public class Controlador3 implements ActionListener {

    private VistaCrearProducto panel;

    public Controlador3(VistaCrearProducto vista) {

        panel = vista;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String comando = ae.getActionCommand();

        if (comando.equals("limpiar")) { //Limpiar

            panel.limpiar();

        } else { //Aceptar

            try {

                Producto temporal = new Producto(panel.getNombreProducto(), panel.getReferencia(), panel.getNumeroFact(), panel.getProblema().replaceAll("\n", " "));
                
                if(Controlador1.productos.contains(temporal)){
                    throw new RMAException("Ese producto ya lo has introducido.");
                }
                
                Controlador1.addProducto(temporal);

                Object[] objetos = new Object[4];      //Crea un array de Objetos para ir rellenando con el nombre y edad y despues annadir fila     

                objetos[0] = temporal.getNombre();
                objetos[1] = temporal.getProblema();
                objetos[2] = temporal.getEan();
                objetos[3] = temporal.getNumeroFactura();
                tablaProductos.annadirFilas(objetos);

                JOptionPane.showConfirmDialog(panel, "Producto añadido", "Confirmación", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
                panel.limpiar();

            } catch (RMAException e) {
                JOptionPane.showConfirmDialog(panel, e.getMessage(), "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
            }

        }

    }

}
