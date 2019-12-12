package principal;

import controlador.Controlador1;
import javax.swing.JFrame;
import vista.VistaPrincipal;

/**
 * Programa para crear solicitudes RMA y las almacena en una base de datos
 * embebida sqlite
 *
 * @author Manuel Jesus Sanchez Vega
 */
public class Principal {

    public static void main(String[] args) {

        JFrame ventana = new JFrame("Gestor de solicitudes");
        VistaPrincipal panel = new VistaPrincipal();
        ventana.setContentPane(panel);

        Controlador1 manejadorVentanaPrincipal = new Controlador1(ventana, panel);
        panel.addManejador(manejadorVentanaPrincipal);

        ventana.setSize(600, 300);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
