package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import java.awt.* ;
public class log_failed extends JFrame implements ActionListener {
	

	private JTextField textField ;
	private boolean No_Clic_Button=true;

	public log_failed() {
		this.setTitle("Sign in");
		this.setBounds(100, 100, 418, 94);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setForeground(Color.LIGHT_GRAY);
		this.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblLoginAlreadyUsed = new JLabel("Login already used");
		panel.add(lblLoginAlreadyUsed);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		this.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JLabel lblLogin = new JLabel("login");
		panel_1.add(lblLogin);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new buttonListener());
		panel_1.add(btnConnect);
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
	
	


	
	

