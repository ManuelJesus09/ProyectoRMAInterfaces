/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adicional;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 *
 * @author DAM-2
 */
public class BarraDeHerramientas extends JToolBar{
        
    private JButton annadir, eliminar, enviar;
    
    public BarraDeHerramientas(){
        
        iniciarComponentes();        
        
    }

    private void iniciarComponentes() {
        
        this.setBackground(new Color(213, 229, 247));
        //Crea la barra de  herramientas y los botones de esta
        annadir = new JButton(new ImageIcon("src/icons/add.png"));  //Cambiar ruta, no poner src, poner getResources, get class
        annadir.setToolTipText("Añadir producto");
        eliminar = new JButton(new ImageIcon("src/icons/delete.png"));
        eliminar.setToolTipText("Eliminar de la lista");
        enviar = new JButton(new ImageIcon("src/icons/send.png"));
        enviar.setToolTipText("Enviar solicitud");
        
        add(annadir);
        add(eliminar);        
        add(enviar);
    }
    
    
    public void addManejador(ActionListener e) {

        annadir.addActionListener(e);
        annadir.setActionCommand("annadir");

        eliminar.addActionListener(e);
        eliminar.setActionCommand("eliminar");
        
        enviar.addActionListener(e);
        enviar.setActionCommand("enviar");

    }
}
