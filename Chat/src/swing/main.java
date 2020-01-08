package swing;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.UIManager;

import main_classes.Client;

public class main {
	public static void main(String[] args) {
	
		//(@MAC + Pseudo) + (infos pour envoyer msg (@ip) 
		ArrayList<String> ListUser= new ArrayList<String>();
		ListUser.add("user1");
		ListUser.add("user2");
		ListUser.add("user3");
		
		//First tentative to connect
		log first_window=new log();
		String ID=first_window.returnId();
		first_window.dispose();
		//Loop tentative to connect
		while(ListUser.contains(ID)) {
				log_failed loop_window=new log_failed();
				ID=loop_window.returnId();
				loop_window.dispose();
		}
		ListUser.add(ID);
		Client this_user = new Client(12345,(short) 16, ID); 
		Thread th = new Thread(this_user);
		th.start(); 		
		//The user is connected
		
		list_users list_users_window= new list_users(ListUser, ID);
		while(true) {
			try {
		        Thread.sleep(3);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }  
			int indexUser=list_users_window.windowToOpen();
			if(indexUser!=-9000)
			{
				chat newChat= new chat(ListUser.get(indexUser), this_user);
				list_users_window.ListStateButton.set(indexUser, false);
			}
				
			
		}
		//ouvrir chat_box
	}
		
}
