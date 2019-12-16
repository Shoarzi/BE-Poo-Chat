package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.BoxLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import java.awt.* ;
public class log extends JFrame implements ActionListener {

	private JFrame frmSignIn;
	private JTextField textField ;

	private JPanel buildPanel() {
		frmSignIn = new JFrame();
		frmSignIn.setTitle("Sign in");
		frmSignIn.setBounds(100, 100, 352, 67);
		frmSignIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmSignIn.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblLogin = new JLabel("Login :");
		panel.add(lblLogin);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		panel.add(btnConnect);
		
		return()
		
	}
	

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
	
		if(source == btnConnect){
			System.out.println("Vous avez cliqué ici.");
		} else if(source == btnConnect){
			System.out.println("Vous avez cliqué là.");	
		}
	}
}

	
	

