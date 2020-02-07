package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.* ;

public class logwindow extends JFrame implements ActionListener {

	private JTextField textField ;
	private JTextField textFieldId ;
	private boolean no_click_button=true;

	public logwindow(String pseudoField, String idField) {
		this.setTitle("Sign in");
		this.setBounds(100, 100, 400, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		this.getContentPane().add(panel1,BorderLayout.NORTH);
		this.getContentPane().add(panel2,BorderLayout.CENTER);

		JLabel label1 = new JLabel();
		JLabel label2 = new JLabel();

		label1 = new JLabel(pseudoField); 
		label2 = new JLabel(idField);

		panel1.add(label1);
		panel2.add(label2);

		textField = new JTextField();
		panel1.add(textField);
		textField.setColumns(10);

		textFieldId = new JTextField();
		panel2.add(textFieldId);
		textFieldId.setColumns(10);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new buttonListener());
		panel2.add(btnConnect);

		this.getRootPane().setDefaultButton(btnConnect);
		this.setVisible(true);
	}

	public String[] returnFields()
	{
		while(this.no_click_button)
		{
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}  
		} 
		String[] tab = new String[2] ; 
		tab[0] = textField.getText();
		tab[1] = textFieldId.getText();
		return tab ;
	}

	class buttonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			no_click_button=false;
		}

	}
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}