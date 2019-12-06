package main_classes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner ; 
import packets.Message;
import packets.Packet.protocol;
//import java.util.EventListenerProxy;
<<<<<<< HEAD
import packets.Message; 
=======
>>>>>>> f29faa98db9fa97a27a0008aac965174a4963935

// TODO implémenter une méthode qui vérifie si 2 utilisateurs sont les mêmes 

public class User {

	private String pseudo; 
<<<<<<< HEAD
	private int port ; 
	
	public User(String pse) {
		this.setPort(4242) ;  ; 
		this.setPseudo(pse) ; 
		//récupération de la liste d'utilisateurs connectés. 
=======
	//on affecte un port par défaut
	private int port=1997;
	private InetAddress address ; 
	
	public User(String pse, int port) {
		this.setPseudo(pse); 
		this.setPort(port);
		this.setAddress(); 
	}
	
	public User(String pse, int port, InetAddress ad) {
		this.setPseudo(pse); 
		this.setPort(port);
		this.address= ad ; 
	}
	
	
	public void read_message(Message M) {
		System.out.println("Author : "+ M.getAuthor() +"\n");
		System.out.println("Destinatory : "+ M.getDestinatory() +"\n");
		System.out.println("Date : "+ M.getDate() +"\n");
		System.out.println("Message : "+ M.getMessage_body()+"\n");
>>>>>>> f29faa98db9fa97a27a0008aac965174a4963935
	}
	
	public void setPseudo(String pse) {
		this.pseudo = pse ; 
	}
	
	public String getPseudo() {
		return this.pseudo ; 
	}
<<<<<<< HEAD
	
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
=======

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress() {
		try {
			this.address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.out.print("Error, can't find IP address") ; 
			e.printStackTrace();
		}
	}
>>>>>>> f29faa98db9fa97a27a0008aac965174a4963935
}
 