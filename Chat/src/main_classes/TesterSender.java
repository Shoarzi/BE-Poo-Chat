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
	private static User user2 ; 
	private static Packet pa ;
	
	public static void main(String[] args) {
		try {
			user2 = new User("Henry",4242, InetAddress.getByName("10.1.5.153")) ; 
			user1 = new User("José",4242, InetAddress.getByName("10.1.5.154")) ;
		} catch (UnknownHostException e) {
			System.out.print("Addresse Receiver inconnue du sender ") ; 
		}
		pa = new NotifIn(protocol.udp, user2, user1 ); 
		UdpReceive.Receive(user2); 
		UdpSend.Send_message(pa);
		UdpSend.CloseSocket(); 
	}
	
	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		TesterSender.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		TesterSender.user2 = user2;
	}

}
