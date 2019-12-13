package main_classes;

import java.net.InetAddress;
import java.net.UnknownHostException;

import packets.*;
import tcp_udp_servers.UdpReceive;


public class Tester2 {

	public static InetAddress add1;
	public static InetAddress add2; 
	private static User user1 = new User("José",4242) ;
	private static User user2 ; 
	private static Packet pa ;
	
	public static void main(String[] args) {
		try {
			user2 = new User("Henry", 4242, InetAddress.getByName("10.1.5.153")) ;
		} catch (UnknownHostException e) {
			System.out.print("addresse receiver inconnue"); 
		} 
		UdpReceive.Receive(user2); 
		//UdpSend.CloseSocket(); 
	}
	
	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		Tester2.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		Tester2.user2 = user2;
	}

}
