package main_classes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import tcp_udp_servers.UdpSend;
import tcp_udp_servers.UdpReceive;


public class Tester2 {

	public static InetAddress add1;
	public static InetAddress add2; 
	private static User user1 = new User("José",4242) ;
	private static User user2 =new User("Henry", 5353) ; 
	private static UdpSend sendUser1;
	private static UdpReceive recUser2 ; 
	
	public static void main(String[] args) {
	
		UdpReceive.Receive(user2); 
		UdpSend.Send_message(user1, user2, "coucou");
	
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
