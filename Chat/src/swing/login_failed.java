package swing;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class login_failed {

	private JFrame frmSignIn;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_failed window = new login_failed();
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
	public login_failed() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSignIn = new JFrame();
		frmSignIn.setTitle("Sign in");
		frmSignIn.setBounds(100, 100, 418, 94);
		frmSignIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setForeground(Color.LIGHT_GRAY);
		frmSignIn.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblLoginAlreadyUsed = new JLabel("Login already used");
		panel.add(lblLoginAlreadyUsed);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		frmSignIn.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JLabel lblLogin = new JLabel("login");
		panel_1.add(lblLogin);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnConnect = new JButton("connect");
		panel_1.add(btnConnect);
	}

}
