package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import main_classes.Client;
import main_classes.UserList;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class changeLoginWindow extends JFrame implements ActionListener {
	
	private JTextField textfieldPseudo ;

	public changeLoginWindow(Client user, String fieldPseudo){
			this.setTitle("Change Login");
			this.setBounds(100, 100, 300, 150);
			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel panel = new JPanel();
			this.getContentPane().add(panel, BorderLayout.CENTER);
			
			JLabel lblLogin = new JLabel(fieldPseudo);
			panel.add(lblLogin);

			textfieldPseudo = new JTextField();
			panel.add(textfieldPseudo);
			textfieldPseudo.setColumns(10);
			
			JButton btnConnect = new JButton("Change");
			btnConnect.addActionListener(new changeListener(this,user));
			panel.add(btnConnect);
			this.setVisible(true);
	}
	//Connect again on chat service 
	class changeListener implements ActionListener{
		changeLoginWindow thisFrame;
		Client thisUser;
		public changeListener(changeLoginWindow frame,Client user) {
			thisFrame=frame;
			thisUser=user;
		}
		public void actionPerformed(ActionEvent e) {
			//check if newLogin is not already used
			String newLogin=textfieldPseudo.getText();
			if(!UserList.listUserName.contains(newLogin)){
				//change the Login in listUserName of UserList
				UserList.listUserName.set(UserList.getListUserName().indexOf(thisUser.getPseudo()), newLogin);
				
				thisUser.setPseudo(newLogin);
				//open list_users
				new  list_users(thisUser);
				
				//broadcast
				thisUser.Reply_Message(thisUser.getHost()+";"+thisUser.getPseudo()+";"+thisUser.getUserId()+";", thisUser.getHost()) ;
				//close this window
				thisFrame.dispose();
			}
			else {
				//close and reopen this window
				new changeLoginWindow(thisUser,"New Login (Login already used) : ");
				thisFrame.dispose();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Auto-generated method stub
	}
}