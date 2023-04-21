package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ButPanel extends JPanel implements ActionListener {
	
	private JComboBox<String> boardSizeComboBox;
	private ButtonGroup difficultyButtonGroup;
    private JRadioButton bfacil;
    private JRadioButton bmedio;
    private JRadioButton bhard;
	
	public ButPanel()
	{
		// Create the top menu panel
	    setLayout(new FlowLayout());
	    setBackground(Color.CYAN);
	    
	    JLabel sizeLabel = new JLabel("Tamaño:");
	    add(sizeLabel);

	    String[] boardSizeOptions = {"5x5", "7x7", "10x10"};
	    boardSizeComboBox = new JComboBox<>(boardSizeOptions);
	    boardSizeComboBox.setPreferredSize(new Dimension(80, 20));
	    add(boardSizeComboBox);
	    boardSizeComboBox.addActionListener(this);
	    
	    JLabel dificultadLabel = new JLabel("Dificultad:");
	    add(dificultadLabel);
	    
        difficultyButtonGroup = new ButtonGroup();
        bfacil = new JRadioButton("Fácil", true);
        bfacil.setBackground(Color.CYAN);
        bfacil.setActionCommand("Fácil");
        add(bfacil);
        difficultyButtonGroup.add(bfacil);
        bfacil.addActionListener(this);
        
        bmedio = new JRadioButton("Medio");
        bmedio.setBackground(Color.CYAN);
        bmedio.setActionCommand("Medio");
        add(bmedio);
        difficultyButtonGroup.add(bmedio);
        bmedio.addActionListener(this);
        
        bhard = new JRadioButton("Difícil");
        bhard.setBackground(Color.CYAN);
        bhard.setActionCommand("Difícil");
        add(bhard);
        difficultyButtonGroup.add(bhard);
        bhard.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	if (e.getSource() == boardSizeComboBox) {
    		String selectedSize = (String) boardSizeComboBox.getSelectedItem();
            int size = Integer.parseInt(selectedSize.split("x")[0]);
            Ventana.getBoardPanel().setBoardSize(size);
            Ventana.cambiarTamaño();
	    } else if (e.getSource() == bfacil) {
	    	Ventana.getBoardPanel().setDificultad(1);
	    } else if (e.getSource() == bmedio) {
	    	Ventana.getBoardPanel().setDificultad(2);
	    } else if (e.getSource() == bhard) {
	    	Ventana.getBoardPanel().setDificultad(3);
	    }
    	
    	
    }
}