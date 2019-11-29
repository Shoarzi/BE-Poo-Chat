package Paquets;

import java.util.Date;

import main_classes.User; 

//TODO Un message doit pouvoir être sauvegardé et historisé une fois qu'il a été créé 

public class Message implements Paquet {
	
		private content paquetype ; 
		private Date time_sent ; 
		private String message_body ; 
		private User author ; 
		private User destinatory ; 


	public Message (String content, User auth, User dest ) {
		this.paquetype = content.msg; 
		this.time_sent = Date.Date() ; 
		this.message_body = content ; 
		this.author = auth ;
		this.destinatory = dest ; 
	}
	
	public void setTime_sent(Date time) {
		this.time_sent = time ; 
	}
	public Date getTime_sent(){
		return this.time_sent ; 
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