package swing;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import packets.Message;

import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Window.Type;

public class chat_box {

	private JFrame frmChatbox;
	private JTextField txtEef;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chat_box window = new chat_box();
					window.frmChatbox.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public chat_box() {
		initialize();
		//print_message();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChatbox = new JFrame();
		frmChatbox.setTitle("Chat box");
		frmChatbox.setBounds(100, 100, 875, 348);
		frmChatbox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		frmChatbox.getContentPane().add(panel, BorderLayout.SOUTH);
		
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
		frmChatbox.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane();
		textPane.setMaximumSize(new Dimension(10, 10));
		scrollPane.setViewportView(textPane);
	}
	private void print_message(Message message) {
		
	}
}
