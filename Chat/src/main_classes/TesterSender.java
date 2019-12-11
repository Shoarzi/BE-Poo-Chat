package main_classes;

import java.net.InetAddress;
import java.net.UnknownHostException;

import packets.*;
import packets.Packet.protocol;
import tcp_udp_servers.UdpSend;
import tcp_udp_servers.UdpReceive;


public class TesterSender {

	public static InetAddress add1;
	public static InetAddress add2; 
	private static User user1 ; 
	private static User user2 =new User("Henry",4445) ; 
	private static Packet pa ;
	
	public static void main(String[] args) {
		try {
			user1 = new User("José",4242, InetAddress.getLocalHost()) ;
		} catch (UnknownHostException e) {
			System.out.print("Addresse Sender inconnue") ; 
		}
		pa = new NotifIn(protocol.udp, user1, user2 ); 
		UdpReceive.Receive(user1); 
		UdpSend.Send_message(pa);
		UdpSend.CloseSocket(); 
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
