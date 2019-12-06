package swing;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class Window extends JFrame{
	private JPanel panel=new JPanel();
	private JPanel panel2=new JPanel();
	private JTextField text=new JTextField();
	private JLabel label= new JLabel(" Pseudo : ");
	private JButton bouton=new JButton(" Se connecter ");
	
	
	public Window() {
		//settings'frame
		this.setTitle("Connection");
		this.setSize(400,100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//settings'label & text
		label.setForeground(Color.BLACK);

		text.setMaximumSize(new Dimension(200,100));
		
		
		//add components
		panel.add(label);
		panel.add(text);
		panel.add(bouton);
		panel2.add(panel);
		
		//set layout
		panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
		
		this.setContentPane(panel2);
	    this.setVisible(true);
	}
	   
}       
