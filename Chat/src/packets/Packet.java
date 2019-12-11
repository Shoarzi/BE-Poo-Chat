package packets;

import java.time.LocalDateTime;

import main_classes.User;

public abstract class Packet {
	
	protected protocol prot;
	protected type packetype ; 
	protected LocalDateTime date ;
	protected User author ; 
	protected User destinatory ; 
	
	public enum type {
		notifin,
		notifinback,
		notifout,
		msg
	}	
	
	public enum protocol {
		tcp,
		udp
	}
	public void setProt(protocol prot) {
		this.prot= prot; 
	}
	
	public protocol getProt() {
		return this.prot ; 
	}
	
	public void setPacketype(type packetype) {
		this.packetype=packetype  ; 
	}
	
	public type getPacketype() {
		return this.packetype ; 
	}
	
	public void setDate(LocalDateTime time) {
		this.date= time ; 
	}
	public LocalDateTime getDate(){
		return this.date ; 
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

	public abstract String getMessage_body();
	// only defined if the type is msg
	
}

