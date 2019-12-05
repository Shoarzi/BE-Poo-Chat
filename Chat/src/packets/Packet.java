package packets;

public abstract class Packet {
	
	public enum content {
		notifin,
		notifinback,
		notifout,
		msg
	}	
	
	public enum protocol {
		tcp,
		udp
	}
	
}

