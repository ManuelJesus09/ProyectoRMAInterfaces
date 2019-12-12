package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Manuel Jesus Sanchez Vega
 */
public class VistaPrincipal extends JPanel {

    private JLabel textoBienvenida;
    private JButton addRMA, showRMA;

    public VistaPrincipal() {

        iniciarComponentes();

    }

    private void iniciarComponentes() {

        setLayout(new GridBagLayout());
        this.setBackground(new Color(213, 229, 247));   //Le pone un color de fondo al panel

        textoBienvenida = new JLabel("Panel de gestión de solicitudes RMA", JLabel.CENTER);
        Font negrita = new Font("Arial", Font.BOLD, 15);
        textoBienvenida.setFont(negrita);
        textoBienvenida.setForeground(Color.blue);

        addRMA = new JButton("Crear solicitud RMA");
        addRMA.setForeground(Color.blue);
        addRMA.setPreferredSize(new Dimension(300, 30));
        addRMA.setBorder(new BotonEnCirculo(40));
        addRMA.setBackground(new Color(213, 229, 247));
        
        showRMA = new JButton("Ver solicitudes RMA enviadas");
        showRMA.setForeground(Color.blue);
        showRMA.setPreferredSize(new Dimension(300, 30));
        showRMA.setBorder(new BotonEnCirculo(40));
        showRMA.setBackground(new Color(213, 229, 247));

        add(textoBienvenida);
        add(addRMA);
        add(showRMA);

        //Coloca los componentes en el panel
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        add(textoBienvenida, c);

        c.gridx = 0;
        c.gridy = 1;
        add(addRMA, c);

        c.gridx = 0;
        c.gridy = 2;
        add(showRMA, c);

    }

    public void addManejador(ActionListener ae) {

        addRMA.addActionListener(ae);
        addRMA.setActionCommand("add");
        showRMA.addActionListener(ae);
        showRMA.setActionCommand("show");

    }

}
