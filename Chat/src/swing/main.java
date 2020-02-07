package swing;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import main_classes.Client;
import main_classes.UserList;

public class main {
	public static void main(String[] args) {

		//(Port + Mask) Thread Client <=> Message Handling
		Client this_user = new Client(12345,(short) 16);
		Thread th = new Thread(this_user);
		th.start();
		this_user.Broadcast(this_user.getHost()+"β"+"new"+"β"+"UUPseudo_Not_Defined") ; 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try { 
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel"); 
		} 
		catch (InstantiationException | ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException e) { 
			e.printStackTrace();
		}

		boolean failureAttemptConnection = true;

		String idField="Id : ";
		String pseudoField="Login : ";
		String pseudo="";
		String id="";

		//While Attempt Fail
		while(failureAttemptConnection) {
			logwindow window=new logwindow(pseudoField, idField);

			//pseudo Field
			pseudo=window.returnFields()[0];
			if(pseudo.contentEquals(""))
				pseudoField="Login Field Empty : ";
			else if(UserList.listUserName.contains(pseudo))
				pseudoField="Login Already Used : ";
			else
				pseudoField="Login : ";

			//Id Field
			id=window.returnFields()[1];
			if(id.contentEquals(""))
				idField="Id Field Empty : ";
			else
				idField="Id : ";

			window.dispose();
			failureAttemptConnection = pseudo.contentEquals("") || id.contentEquals("") || UserList.listUserName.contains(pseudo); 
		}

		UserList.addUser(pseudo,id,this_user.getHost().getHostAddress());
		this_user.setPseudo(pseudo);
		this_user.setUserId(id);
		this_user.Broadcast(this_user.getHost()+"α"+this_user.getPseudo()+"α"+this_user.getUserId()+"α");
		list_users list_users_window= new list_users(this_user);

		//main loop to check if foreign_user button had been clicked
		while(!UserList.getDisconnect()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}  

			//Update the foreign user List
			if (UserList.getChange()) {
				list_users_window.dispose(); 
				list_users_window = new list_users(this_user);
				UserList.setChange(false);
			}
			
			//selector of a button pressed
			int j=-1;
			//iterator on the buttons
			int i=0;

			//loop on every buttons			
			for(i=0;i< list_users_window.list_button.size();i++)
			{
				 ArrayList<Boolean> testList = list_users.ListStateButton ; 
				//return the index of the button that has been clicked
				if(list_users.ListStateButton.get(i))
				{
					j=i;
					break;
				}
			}

			//open new chatbox if a button has been clicked
			Map<String, chat> testclist = this_user.getChatList() ; 
			if(j!=-1 ) { 
				while (this_user.getChatList().containsKey((UserList.listUserId.get(j)))) { 
					this_user.getChatList().remove(UserList.listUserId.get(j)).dispose() ; 
				}
				chat newChat= new chat(UserList.listUserName.get(j), this_user);
				this_user.getChatList().put(UserList.listUserId.get(j), newChat) ; 
				list_users.setFalseStateButton(j) ; 
			}
		}
	}
}