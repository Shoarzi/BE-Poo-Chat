package main_classes;

import java.util.Scanner ; 
import java.util.EventListenerProxy;

// TODO implémenter une méthode qui vérifie si 2 utilisateurs sont les mêmes 

public class User {

	private String pseudo; 
	
	public User(String pse) {
		this.pseudo = pse ; 
	}
	
	public void setPseudo(String pse) {
		this.pseudo = pse ; 
	}
	
	public String getPseudo() {
		return this.pseudo ; 
	}
	
	public EventListener receive_msg() {
		
	}
	
	public Message write_message(User dest) {
		Scanner content = new Scanner(System.in);
	    String msg = content.nextLine();
	    Message m = new Message(msg, this, dest); 
	    content.close();
	    return m ; 
	}
}
 