package swing;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class Window extends JFrame{
	private JPanel panel=new JPanel();
	private JPanel panel2=new JPanel();
	private JPanel panel3=new JPanel();
	private JPanel panel4=new JPanel();
	private JTextField text=new JTextField();
	private JLabel label;
	private JButton bouton;
	
	//sans argument -> fenêtre de connexion
	public Window() {
		
		//settings'frame
		this.setTitle("Connection");
		this.setSize(400,75);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//settings'content
		label= new JLabel(" Pseudo : ");
		bouton=new JButton(" Se connecter ");
		label.setForeground(Color.BLACK);
		text.setMaximumSize(new Dimension(200,50));
		
		
		//add content
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
	
	//deux arguments -> fenêtre de chat , String OneToTwo[], String TwoToOne[]
	public Window(String pseudo1, String pseudo2) {
		
		
		//settings'frame
		this.setTitle("Chat : "+pseudo1+" et "+pseudo2);
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 // Define new buttons with different width on help of the ---
        JLabel jL1 =new JLabel("Message 1 de pierre vers paul ")  ;      
        JLabel jL2 =new JLabel("Message 2 de paul vers pierre ");
        JTextField jT =new JTextField();
        JButton jB=new JButton("send");
        
        // Define the panel 
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        
        JPanel panelNewMessage=new JPanel();
        JPanel panelMessages=new JPanel();
        JPanel panelGlobal=new JPanel();
         
        // Set up the title for different panels
        panel1.setBorder(BorderFactory.createTitledBorder(pseudo1));
        panel2.setBorder(BorderFactory.createTitledBorder(pseudo2));
        
        panelNewMessage.setBorder(BorderFactory.createTitledBorder("new message"));
        // Set up the BoxLayout
        BoxLayout layout1 = new BoxLayout(panel1, BoxLayout.Y_AXIS);
        BoxLayout layout2 = new BoxLayout(panel2, BoxLayout.Y_AXIS);
        
        BoxLayout LayoutNewMessage = new BoxLayout(panelNewMessage, BoxLayout.X_AXIS);
        BoxLayout LayoutMessages= new BoxLayout(panelMessages, BoxLayout.X_AXIS);
        BoxLayout layoutGlobal= new BoxLayout(panelGlobal, BoxLayout.Y_AXIS);
        panel1.setLayout(layout1);
        //panel2.setLayout(layout2);
        panel2.setLayout(layout2);
        
        panelNewMessage.setLayout(LayoutNewMessage);
        panelMessages.setLayout(LayoutMessages);
        panelGlobal.setLayout(layoutGlobal);
         
        // Add the buttons into the panel with three different alignment options
        jL1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel1.add(jL1);
      
         
        jL2.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel2.add(jL2);
        
        panelMessages.add(panel1);
        panelMessages.add(panel2);
        
        jB.setAlignmentX(Component.RIGHT_ALIGNMENT);
        jT.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNewMessage.add(jT);
        panelNewMessage.add(jB);
        
        panelGlobal.add(panelMessages);
        panelGlobal.add(panelNewMessage);
        
        this.setLayout(new FlowLayout());
        this.add(panelGlobal);
         
        // Set the window to be visible as the default to be false
        this.pack();
        this.setVisible(true);
		
	}
}       
