package packets;

import java.time.LocalDateTime;

import main_classes.User;
public class NotifIn extends Packet {
	
	public NotifIn (protocol prot,User auth, User dest ) {
		this.prot=prot;
		this.packetype=type.notifin;
		this.setDate(LocalDateTime.now()) ; 
		this.setAuthor(auth) ;
		this.setDestinatory(dest) ; 
	}
}
