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
import javax.swing.Action;
import java.awt.* ;
public class login {

	private JFrame frmSignIn;
	private JTextField textField;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frmSignIn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		btnConnect.setAction(action);
		panel.add(btnConnect);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
 
		if(source == btnConnect){
			System.out.println("Vous avez cliqué ici.");
		} else if(source == btnConnect){
			System.out.println("Vous avez cliqué là.");	
		}
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(ACTION_COMMAND_KEY, "get.Text(textField)");
			putValue(NAME, "Connect");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
