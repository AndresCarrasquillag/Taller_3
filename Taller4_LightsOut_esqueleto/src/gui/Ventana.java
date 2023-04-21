package gui;

import javax.swing.*;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

public class Ventana extends JFrame {
	
	private static Ventana instanciaActual = null;

    private static ButPanel panelBotones;
    private static TableroPanel tabPanel;
    private static AbajoPanel abPanel;
    private static OpPanel opPanel;
    private static Tablero tablero;
    private static Top10 top10;


    private Ventana() {
        setTitle("Lights Out Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        
        tablero = new Tablero(5);
        tablero.desordenar(5);

        panelBotones = new ButPanel();
        add(panelBotones, BorderLayout.NORTH);
        
        tabPanel = new TableroPanel(5, tablero);
        add(tabPanel, BorderLayout.CENTER);
        
        abPanel = new AbajoPanel();
        add(abPanel, BorderLayout.SOUTH);

        opPanel = new OpPanel();
        add(opPanel, BorderLayout.EAST);
        
        top10 = new Top10();
        top10.cargarRecords(new File("./data/top10.csv"));
        
        
        
        setVisible(true);
        changePlayer();

    }
    
    public static Ventana getInstance() {
        if (instanciaActual == null) {
        	instanciaActual = new Ventana();
        }
        return instanciaActual; 
    }
    
    public static TableroPanel getBoardPanel() {
        return tabPanel;
    }
    
    public static void setBoardPanel(TableroPanel panel) {
    	tabPanel = panel;
    }
    
    public static RegistroTop10[] darRegistros() {
        return top10.darRegistros().toArray(new RegistroTop10[top10.darRegistros().size()]);
    }
    
    public static void mostrarTop()
    {
    	new Ventana10(getInstance());
    }
    
    
    public static void cambiarTamaño() {
    	int boardSize = tabPanel.getBoardSize();
    	int dificultad = tabPanel.getDificultad();
    	tablero = new Tablero(boardSize);
    	tablero.desordenar(dificultad*boardSize/2);
    	recarga(boardSize);
       
    }
    
    public static void recarga(int boardSize)
    {
    	 abPanel.setJugadasText(Integer.toString(tablero.darJugadas()));
    	 getInstance().remove(tabPanel);
         tabPanel = new TableroPanel(boardSize, tablero);
         getInstance().add(tabPanel, BorderLayout.CENTER);
         getInstance().revalidate();
         getInstance().repaint();
    }
    
    public static void reiniciar() {
		tablero.reiniciar();
		recarga(tablero.darTablero().length);
	}
    
    public static void jugar(int fila, int columna)
    {
    	tablero.jugar(fila,columna);
    }
    
    public static Boolean tableroIluminado()
    {
    	return tablero.tableroIluminado();
    }
    
    public static void finalizar()
    {
 
    	try {
			top10.salvarRecords(new File("./data/top10.csv"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported coding");
			e.printStackTrace();
		}
    	
    	ganar();
       
    }
    
    public static void ganar() {
    	String message;
        JFrame frame = new JFrame("Congratulations!");
        frame.setSize(300, 100);        
        if (top10.esTop10(tablero.calcularPuntaje()))
        {
        	message = "GANASTE ENTRANDO EN EL TOP!";
        	top10.agregarRegistro(abPanel.getJugadorText(), tablero.calcularPuntaje());
        } else 
        {
        	message = "GANASTE";
        }

        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(480, 50)); 
        JPanel panel = new JPanel();
        panel.add(label);
        frame.add(panel);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                reiniciar();
            }
        });
    }
    
    public static void changePlayer() {
		
		JFrame frame = new JFrame("Cambiar Jugador");
		try {
	        String nuevoJugador = JOptionPane.showInputDialog(frame, "Ingrese su nombre:");
	        abPanel.setJugadorText(nuevoJugador.substring(0, Math.min(nuevoJugador.length(), 3)).toUpperCase());
	    } catch (Exception e) {
	    }
		
	}
    
    
    
    public static void main(String[] args)
    {
    	getInstance();
    }

	

	
}
