package tcp_udp_servers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

import main_classes.User;
import packets.Message;
import packets.Packet.protocol;
import test.TestUdp.UDPClient;

public class UdpSend {

	//socket sur lequel on va écouter les réponses
	private UdpSocket sock; 
	private Thread thread_listen ; 
    private byte[] buffer;
		
	public void send_message(String content, User author, User dest) {
		
		// on créé un buffer de réception 
		byte[] buffer_content = content.getBytes() ; 
		InetAddress adresse = dest.getAddress();
		// on créé notre paquet UDP 
        UdpPacket packet = new UdpPacket(buffer, buffer.length, adresse, dest.getPort());
        DatagramPacket Dpacket = packet.toDatagramPacket(); 
        Dpacket.setData(buffer);
        //on envoie le message
        try {
			sock.getS_socket_address().send(Dpacket);
		} catch (IOException e) {
			System.out.print("The message failed sending") ; 
			e.printStackTrace();
		}
	}

	public UdpSocket getSock() {
		return sock;
	}

	public void setSock(User author) {
		this.sock = new UdpSocket(author.getPort(),author.getAddress());
	}

	public Thread getThread_listen() {
		return thread_listen;
	}

	public void setThread_listen() {
		this.thread_listen = new Thread();
	}

	public byte[] getBuffer() {
		return buffer;
	}

	public void setBuffer(User author) {
		this.buffer = new byte[author.getPort()];
	}
	
	
}
