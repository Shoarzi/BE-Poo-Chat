package packets;

import java.time.LocalDateTime;

import main_classes.User;

public class NotifOut extends Packet {
	
	public NotifOut (protocol prot,User auth, User dest ) {
		this.prot=prot;
		this.packetype=type.notifout;
		this.setDate(LocalDateTime.now()) ; 
		this.setAuthor(auth) ;
		this.setDestinatory(dest) ; 
	}

	@Override
	public String getMessage_body() {
		// only useful if the Packet is a Message 
		return null;
	}

}
