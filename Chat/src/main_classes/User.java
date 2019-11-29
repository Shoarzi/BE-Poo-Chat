package main_classes;

import java.util.Scanner ; 
//import java.util.EventListenerProxy;
import Paquets.Message; 

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
	
	public Message write_message(User dest) {
		Scanner content = new Scanner(System.in);
	    String msg = content.nextLine();
	    Message m = new Message(msg, this, dest); 
	    content.close();
	    return m ; 
	}
	
	public void read_message(Message M) {
		System.out.println("Author : "+ M.getAuthor() +"\n");
		System.out.println("Destinatory : "+ M.getDestinatory() +"\n");
		System.out.println("Date : "+ M.getDate() +"\n");
		System.out.println("Message : "+ M.getMessage_body()+"\n");}
}
 