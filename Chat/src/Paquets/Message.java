package Paquets;

import main_classes.User; 
import java.time.LocalDateTime;

//TODO Un message doit pouvoir être sauvegardé et historisé une fois qu'il a été créé 

public class Message implements Paquet {
	
		private content paquetype ; 
		private LocalDateTime date ; 
		private String message_body ; 
		private User author ; 
		private User destinatory ; 


	public Message (String contenu, User auth, User dest ) {
		this.paquetype = content.msg; 
		this.date = LocalDateTime.now() ; 
		this.message_body = contenu ; 
		this.author = auth ;
		this.destinatory = dest ; 
	}
	
	public void setDate(LocalDateTime time) {
		this.date= time ; 
	}
	public LocalDateTime getDate(){
		return this.date ; 
	}
	
	public void setcontent(String message_b) {
		this.message_body = message_b ; 
	}
	public String getMessage_body(){
		return this.message_body ; 
	}
	
	public void setAuthor(User auth) {
		this.author = auth ; 
	}
	public User getAuthor(){
		return this.author ; 
	}
	
	public void setDestinatory(User dest) {
		this.destinatory = dest ; 
	}
	public User getDestinatory(){
		return this.destinatory ; 
	}
}