package main_classes;

import java.net.InetAddress;
import java.net.UnknownHostException;

import tcp_udp_servers.UdpReceive;
import tcp_udp_servers.UdpSend;


public class Tester implements Runnable {

	public static InetAddress add1;
	public static InetAddress add2; 
	private static User user1 ;
	private static User user2 ; 
	private static UdpSend sendUser1;
	private static UdpReceive recUser2 ; 
	
	public static void main(String[] args) {
	
		try {
			add1 = InetAddress.getByName("195.83.11.69") ;
			add2 = InetAddress.getByName("195.83.11.70") ;
			user1 = new User("Benoit" , 4242, add1 );
			user2 = new User("José", 5656, add2 ); 
			sendUser1 = new UdpSend(user1); 
			recUser2= new UdpReceive(user2);
		} catch (UnknownHostException e) {
			System.out.print("Error : bad address"); 
			e.printStackTrace();
		} 
		run(0); 
		run(1); 
	}
	
	public static void run(int side) { 
		if (side == 0) {
			runserver() ; 
		}
		else {
			runclient();
		}
	}
	
	public static void runserver() { 
		Thread serv = new Thread (new Runnable(){
			public void run() {
				while(true) {
					recUser2.Receive_message();
				}
			}
		});
		
		serv.start();
		
	}
	
	public static void runclient() {
		Thread client = new Thread(new Runnable () { 
			public void run () {
				while (true) {
					sendUser1.send_message("Bonjour",user1,user2);
					try { 
					Thread.sleep(1000) ; 
					}
					catch (InterruptedException e) {
		                  e.printStackTrace();
		               }
						
				}
			}
		});
		
		client.start() ; 
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
