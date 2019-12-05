package main_classes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner ; 
import packets.Message;
import packets.Packet.protocol;
//import java.util.EventListenerProxy;

// TODO implémenter une méthode qui vérifie si 2 utilisateurs sont les mêmes 

public class User {

	private String pseudo; 
	//on affecte un port par défaut
	private int port=1997;
	private InetAddress address ; 
	
	public User(String pse, int port) {
		this.setPseudo(pse); 
		this.setPort(port);
		this.setAddress(); 
	}
	
	public void read_message(Message M) {
		System.out.println("Author : "+ M.getAuthor() +"\n");
		System.out.println("Destinatory : "+ M.getDestinatory() +"\n");
		System.out.println("Date : "+ M.getDate() +"\n");
		System.out.println("Message : "+ M.getMessage_body()+"\n");
	}
	
	public void setPseudo(String pse) {
		this.pseudo = pse ; 
	}
	
	public String getPseudo() {
		return this.pseudo ; 
	}

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
}
 