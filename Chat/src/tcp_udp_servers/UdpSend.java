package tcp_udp_servers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

import main_classes.User;
import packets.Message;
import packets.Packet;
import packets.Packet.protocol;
import test.TestUdp.UDPClient;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpSend{
	
	static DatagramSocket client ; 
	static byte[] buffer ; 
	static String envoi ; 
     
	public static void Send_message(Packet mes){

        try {
            //On initialise la connexion côté client
            client = new DatagramSocket();
            
			switch (mes.getPacketype()) {
				case msg : 
					
					envoi = "Ms" + mes.getAuthor().getPseudo() + " : " + mes.getMessage_body();
		            break ; 
		
				case notifin : 
					
					envoi = "Ni" + mes.getAuthor().getPseudo() + ":" + mes.getAuthor().getAddress() + ":"  + mes.getAuthor().getPort() ;
					break ;
					
				case notifinback : 
					envoi = "Nb" + mes.getAuthor().getPseudo() + " : " + mes.getAuthor().getPort(); 
					break ; 
					
				case notifout : 
					envoi = "No" + mes.getAuthor().getPseudo() + " : " + mes.getAuthor().getPort(); 
					break ; 
	        	}
            
            buffer = envoi.getBytes();        

			//On crée notre datagramme
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, mes.getDestinatory().getAddress(), mes.getDestinatory().getPort());
            
            //On lui affecte les données à envoyer
            packet.setData(buffer);
            
            //On envoie au serveur
            client.send(packet);    
	        }
		catch (SocketException e) {
            e.printStackTrace();
         } catch (UnknownHostException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         }
      	//});
		//t.start() ; 
	}

public static void CloseSocket() { 

		client.close();

	}
}

