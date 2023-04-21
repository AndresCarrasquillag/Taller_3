package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.Tablero;

public class TableroPanel extends JPanel {
    private JButton[][] botones;
    private int size;
    private int dificultad = 5;

    public TableroPanel(int size, Tablero tablero) {
    	this.size = size;

        setLayout(new GridLayout(this.size, this.size));

        botones = new JButton[this.size][this.size];
        ImageIcon icon = new ImageIcon("./data/luz.png");

        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
                button.setBackground(tablero.darTablero()[row][col] ? Color.YELLOW : Color.BLACK);
                button.setIcon(icon);
                botones[row][col] = button;
                add(button);
            }
        }
        addActionListeners();
    }
    
    public void setBoardSize(int size)
    {
    	this.size = size;
    }
    
    public int getBoardSize()
    {
    	return this.size;
    }
    
    public void setDificultad(int dificultad)
    {
    	this.dificultad = dificultad;
    }
    
    public int getDificultad()
    {
    	return this.dificultad;
    }

    private void addActionListeners() {
        for (int fila = 0; fila < size; fila++) {
            for (int col = 0; col < size; col++) {
                final int f = fila;
                final int c = col;
                botones[fila][col].addActionListener(e -> {
                    Ventana.jugar(f,c);
                    if ( Ventana.tableroIluminado() )
                    {
                    	Ventana.finalizar();
                    }
                    Ventana.recarga(size);
                });
            }
        }
    }
}


