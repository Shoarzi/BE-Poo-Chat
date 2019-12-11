package packets;

import main_classes.User;

import java.time.LocalDateTime;

//TODO Un message doit pouvoir être sauvegardé et historisé une fois qu'il a été créé 

public class Message extends Packet{
	private String message_body ; 

	public Message (protocol prot,String contenu, User auth, User dest ) {
		this.prot=prot;
		this.packetype = type.msg; 
		this.date = LocalDateTime.now() ; 
		this.message_body = contenu ; 
		this.author = auth ;
		this.destinatory = dest ; 
	}
	
	public void setcontent(String message_b) {
		this.message_body = message_b ; 
	}
	public String getMessage_body(){
		return this.message_body ; 
	}
	
}