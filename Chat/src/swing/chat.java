package swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import main_classes.Client;

public class chat extends JFrame {
	private JTextField txtEef;
	
	//actionListener : send
	//use sendMesssageToUser de Server.java
	
	public chat(String UserName, Client main_user) {
		this.setTitle(UserName);
		this.setBounds(100, 100, 875, 348);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		this.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JLabel lblMessage = new JLabel("Message :");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMessage);
		
		txtEef = new JTextField();
		txtEef.setMaximumSize(new Dimension(50, 20));
		panel.add(txtEef);
		txtEef.setColumns(45);
		
		JButton btnSend = new JButton("Send");
		btnSend.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(btnSend);
		
		JScrollPane scrollPane = new JScrollPane();
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane();
		textPane.setMaximumSize(new Dimension(10, 10));
		scrollPane.setViewportView(textPane);
		
		this.setVisible(true);
		
		btnSend.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		             if(e.getSource() == btnSend) {
		            	 main_user.Send_Message(txtEef.getText(), UserName) ; 		              
		             }
		       }
		 });
	}
	
}
