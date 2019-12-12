package controlador;

import adicional.BarraMenu;
import adicional.ConexionBD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import adicional.Producto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import vista.VistaCrearRma;
import vista.VistaMostrarRma;
import vista.VistaPrincipal;

/**
 *
 * @author Manuel Jesus Sanchez Vega
 */
public class Controlador1 implements ActionListener {

    private VistaPrincipal vista;
    private JFrame ventana;
    public static LinkedList<Producto> productos = new LinkedList<Producto>();

    public Controlador1(JFrame ventana, VistaPrincipal panel) {

        vista = panel;
        this.ventana = ventana;

    }

    /**
     * Metodo que annade un producto a la collecion de productos
     *
     * @param temporal, producto que se va a annadir
     */
    public static void addProducto(Producto temporal) {

        productos.add(temporal);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String comando = ae.getActionCommand();

        if (comando.equals("add")) {  //Añadir

            //Crea una ventana para annadir producto
            VistaCrearRma panelRma = new VistaCrearRma();
            JDialog dialog1 = new JDialog(ventana, true);
            dialog1.setTitle("Solicitud RMA");

            //Le asigna un controlador
            Controlador2 controladorProductos = new Controlador2(panelRma, dialog1);
            panelRma.addManejador(controladorProductos);

            //Crea barra de menu y se le asigna un controlador
            BarraMenu barra = new BarraMenu();
            barra.addManejador(controladorProductos);
            dialog1.setJMenuBar(barra);

            dialog1.setContentPane(panelRma);
            dialog1.setSize(550, 600);
            dialog1.setLocationRelativeTo(null);//Hace que la ventana sea en el centro
            dialog1.setResizable(false);
            dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog1.setVisible(true);

        } else {  //Mostrar
            //Crea una ventana para mostrar los rmas
            VistaMostrarRma mostrarRma = new VistaMostrarRma();

            //Rellena la tabla con los datos de la base de datos
            rellenarTablaVistaRma(mostrarRma);

            JDialog dialog2 = new JDialog(ventana, true);
            dialog2.setTitle("Lista de solicitudes enviadas");
            dialog2.setContentPane(mostrarRma);
            dialog2.setSize(400, 200);
            dialog2.setLocationRelativeTo(null);
            dialog2.setResizable(false);
            dialog2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog2.setVisible(true);

        }

    }

    /**
     * Metodo que accede a la base de datos, recoge los datos necesarios para la
     * tabla y los muestra en ella
     *
     * @param vista , panel que contiene la tabla
     */
    private void rellenarTablaVistaRma(VistaMostrarRma vista) {

        //Pone los nombres a las columnas
        String[] nombreColumnas = {"Referencia de la solicitud", "Fecha de solicitud"};
        vista.annadirColumnas(nombreColumnas);

        vista.centrarColumnas();

        try (Statement sentencia = ConexionBD.getConexion().createStatement()) {

            ResultSet resul = sentencia.executeQuery("SELECT * FROM rma");

            while (resul.next()) {

                int referencia = resul.getInt("id");
                String fecha = resul.getString("fecha");

                Object[] fila = {referencia, fecha};
                vista.annadirFilas(fila);
            }

            ConexionBD.cerrarConexion();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
