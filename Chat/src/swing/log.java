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
	

	private JTextField textField ;
	private boolean No_Clic_Button=true;

	public log(){
			this.setTitle("Sign in");
			this.setBounds(100, 100, 352, 67);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel panel = new JPanel();
			this.getContentPane().add(panel, BorderLayout.CENTER);
			
			JLabel lblLogin = new JLabel("Login :");
			panel.add(lblLogin);
			
			textField = new JTextField();
			panel.add(textField);
			textField.setColumns(10);
			
			JButton btnConnect = new JButton("Connect");
			btnConnect.addActionListener(new buttonListener());
			panel.add(btnConnect);
			this.setVisible(true);
	}
	
	public String returnId()
	{
		while(this.No_Clic_Button)
		{
			try {
		        Thread.sleep(3);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }  
		} 
		return textField.getText();
	}

	class buttonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			No_Clic_Button=false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
	
	


	
	

