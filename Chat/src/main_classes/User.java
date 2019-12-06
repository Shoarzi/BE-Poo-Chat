package main_classes;

import java.util.Scanner ; 
//import java.util.EventListenerProxy;
import packets.Message; 

// TODO implémenter une méthode qui vérifie si 2 utilisateurs sont les mêmes 

public class User {

	private String pseudo; 
	private int port ; 
	
	public User(String pse) {
		this.setPort(4242) ;  ; 
		this.setPseudo(pse) ; 
		//récupération de la liste d'utilisateurs connectés. 
	}
	
	public void setPseudo(String pse) {
		this.pseudo = pse ; 
	}
	
	public String getPseudo() {
		return this.pseudo ; 
	}
	
	public Message send_message(User dest) {
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

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
 