package tcp_udp_servers;

import java.io.IOException;
import java.net.DatagramPacket;

import main_classes.User;

public class UdpReceive {
	
	private UdpSocket sock; 
	private Thread thread_listen ; 
    private byte[] buffer;
    private User dest; 
    private UdpPacket packet ;
    private DatagramPacket dpacket ;  
    
    public UdpReceive(User dest) {
    	this.setDest(dest) ;
    	sock = new UdpSocket(dest.getPort(),dest.getAddress()); 
    	//le buffer qui recevra le message 
    	buffer = new byte[dest.getPort()] ; 
    	packet = new UdpPacket(buffer, buffer.length) ; 
    	dpacket = packet.toDatagramPacket() ; 
    }
    
    public void Receive_message() {
    	
    	try {
			sock.getS_socket_address().receive(dpacket) ;
			// on recoit le message et on le mets dans le paquet.  
    	} catch (IOException e) {
			System.out.print("Exception with receiving an incoming packet") ; 
			e.printStackTrace();
		}
    	String data = new String (dpacket.getData());
    	// TODO : détailler la data qui ne contiendra pas que des messages à afficher mais aussi des infos pour les fonctions. 
    	System.out.print(data) ; 
    	
    }
    
    
    
	public UdpSocket getSock() {
		return sock;
	}
	public void setSock(UdpSocket sock) {
		this.sock = sock;
	}
	public Thread getThread_listen() {
		return thread_listen;
	}
	public void setThread_listen(Thread thread_listen) {
		this.thread_listen = thread_listen;
	}
	public byte[] getBuffer() {
		return buffer;
	}
	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}



	public User getDest() {
		return dest;
	}



	public void setDest(User dest) {
		this.dest = dest;
	}

}
