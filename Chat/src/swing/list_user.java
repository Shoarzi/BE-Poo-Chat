package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.SwingConstants;

public class list_user extends chat_box {

	private JFrame frmUsersConnected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					list_user window = new list_user();
					window.frmUsersConnected.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public list_user() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUsersConnected = new JFrame();
		frmUsersConnected.setTitle("Users connected");
		frmUsersConnected.setBounds(100, 100, 346, 354);
		frmUsersConnected.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUsersConnected.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Box verticalBox = Box.createVerticalBox();
		frmUsersConnected.getContentPane().add(verticalBox, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("User1");
		verticalBox.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("User2");
		verticalBox.add(btnNewButton_1);
	}

}
