package packets;

import java.time.LocalDateTime;

import main_classes.User;

public class NotifInBack extends Packet {
	public NotifInBack (protocol prot,User auth, User dest ) {
		this.prot=prot;
		this.packetype=type.notifinback;
		this.setDate(LocalDateTime.now()) ; 
		this.setAuthor(auth) ;
		this.setDestinatory(dest) ; 
	}
}
