package adicional;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Manuel Jesus Sanchez Vega
 */
public class BarraMenu extends JMenuBar{
    
    private JMenu archivo, operaciones;
    private JMenuItem salir, annadirProducto, eliminarProducto, enviarSolicitud;
    
    public BarraMenu(){
        
        iniciarComponentes();
        
    }

    private void iniciarComponentes() {
       
        //Crea los JMenus y les annade los JMenuItem
        //Archivo
        archivo=new JMenu("Archivo");
        archivo.setMnemonic('A');
        salir=new JMenuItem("Salir");
        salir.setMnemonic('S');
        archivo.add(salir);
        
        //Operaciones
        operaciones=new JMenu("Operaciones");
        operaciones.setMnemonic('O');
        annadirProducto=new JMenuItem("Añadir producto");
        annadirProducto.setMnemonic('A');
        eliminarProducto=new JMenuItem("Eliminar producto");
        eliminarProducto.setMnemonic('E');
        enviarSolicitud=new JMenuItem("Enviar solicitud");
        enviarSolicitud.setMnemonic('S');
        operaciones.add(annadirProducto);
        operaciones.add(eliminarProducto);
        operaciones.add(enviarSolicitud);
        
        //Annade los componentes a la barra de menu
        add(archivo);
        add(operaciones);
        
    }
    
    public void addManejador(ActionListener ae){
        
        //Asigna los actionCommand a los JMenuItems
        salir.addActionListener(ae);
        salir.setActionCommand("cancelar");
        annadirProducto.addActionListener(ae);
        annadirProducto.setActionCommand("annadir");
        eliminarProducto.addActionListener(ae);
        eliminarProducto.setActionCommand("eliminar");
        enviarSolicitud.addActionListener(ae);
        enviarSolicitud.setActionCommand("enviar");  
        
    }
    
}
