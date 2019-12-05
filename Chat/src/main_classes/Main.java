package main_classes;

import tcp_udp_servers.UdpReceive;
import tcp_udp_servers.UdpSend;

public class Main implements Runnable {

	private User user1;
	private User user2;
	private UdpSend sendUser1;
	private UdpReceive recUser2;
	
	public void run() {
		while(true) {	
			sendUser1.send_message("bonjour", user1, user2);
			recUser2.Receive_message();
			// On va essayer de faire passer un message entre user 1 et user 2 
		}
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}
	
}
