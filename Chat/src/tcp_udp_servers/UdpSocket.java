package tcp_udp_servers;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.DatagramSocket;

public class UdpSocket {
	; 
	private InetAddress s_ip_address ; 
	private int port ;  
	private DatagramSocket s_socket_address ; 
	
	public UdpSocket(int p, InetAddress add) {
		setS_ip_address(add) ; 
		setPort(p) ;
		try {
			setS_socket_address(new DatagramSocket(p,add));
		} catch (SocketException e) {
			System.out.println("Le socket n'a pas put être créé");
		} 
	}

	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}

	public InetAddress getS_ip_address() {
		return s_ip_address;
	}


	public void setS_ip_address(InetAddress s_ip_address) {
		this.s_ip_address = s_ip_address;
	}

	public DatagramSocket getS_socket_address() {
		return s_socket_address;
	}

	public void setS_socket_address(DatagramSocket s_socket_address) {
		this.s_socket_address = s_socket_address;
	}

	

}