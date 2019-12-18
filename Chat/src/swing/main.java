package swing;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.UIManager;

public class main {
	public static void main(String[] args) {
	
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
				chat newChat= new chat(ListUser.get(indexUser));
				list_users_window.ListStateButton.set(indexUser, false);
			}
				
			
		}
		//ouvrir chat_box
	}
		
}
