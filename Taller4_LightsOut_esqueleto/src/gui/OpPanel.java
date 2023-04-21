package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OpPanel extends JPanel implements ActionListener{
	
	JButton nuevo;
	JButton restart;
	JButton top;
	JButton jugador;
	
	public OpPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				
		nuevo = new JButton("NUEVO");
		nuevo.setAlignmentX(Component.CENTER_ALIGNMENT);
        nuevo.addActionListener(this);
        nuevo.setBackground(Color.CYAN);
		nuevo.setMaximumSize(new Dimension(500, 50));
        add(nuevo);
        add(Box.createRigidArea(new Dimension(0, 15)));

        restart = new JButton("REINICIAR");
        restart.setAlignmentX(Component.CENTER_ALIGNMENT);
        restart.addActionListener(this);
        restart.setBackground(Color.CYAN);
		restart.setMaximumSize(new Dimension(500, 50));
        add(restart);
        add(Box.createRigidArea(new Dimension(0, 15)));

        top = new JButton("TOP");
        top.setAlignmentX(Component.CENTER_ALIGNMENT);
        top.addActionListener(this);
        top.setBackground(Color.CYAN);
		top.setMaximumSize(new Dimension(500, 50));
        add(top);
        add(Box.createRigidArea(new Dimension(0, 15)));

        jugador = new JButton("CAMBIAR JUGADOR");
        jugador.setAlignmentX(Component.CENTER_ALIGNMENT);
        jugador.addActionListener(this);
        jugador.setBackground(Color.CYAN);
        jugador.setMaximumSize(new Dimension(500, 50));
        add(jugador);
        
        add(Box.createVerticalGlue());
        
       
	}
	
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == nuevo) {
	    	Ventana.cambiarTamaño();
	    } else if (e.getSource() == restart) {
	    	Ventana.reiniciar();
	    } else if (e.getSource() == top) {
	        Ventana.mostrarTop();
	    } else if (e.getSource() == jugador) {
	        Ventana.changePlayer();
	    }
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(150, super.getPreferredSize().height);
	}
}
