package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AbajoPanel extends JPanel{
	
	private JTextField clicks;
	private JTextField jugador;
	
	public AbajoPanel()
	{
		setLayout(new GridLayout(1,4));
		add(new JLabel("Clicks:"));
		clicks = new JTextField();
		add(clicks);
    
		add(new JLabel("Jugador:"));
		jugador = new JTextField();
		add(jugador);
		
	}

	
	public void setJugadasText(String text) {
		clicks.setText(text);
	}
	
	public String getJugadorText() {
		return jugador.getText();
	}
	
	public void setJugadorText(String text) {
		jugador.setText(text);
	}
}
