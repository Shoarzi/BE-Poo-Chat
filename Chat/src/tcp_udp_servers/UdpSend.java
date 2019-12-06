package tcp_udp_servers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

import main_classes.User;
import packets.Message;
import packets.Packet.protocol;
import test.TestUdp.UDPClient;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpSend{
     
	public static void Send_message(User se, User re, String mes){

		//Thread t = new Thread(new Runnable() {	      
	  	    
		//	public void run(){
		
				String envoi = se.getPseudo() + " : " + mes;
	            byte[] buffer = envoi.getBytes();
	   
	            try {
	               //On initialise la connexion côté client
	               DatagramSocket client = new DatagramSocket();
	               
	               //On crée notre datagramme
	               DatagramPacket packet = new DatagramPacket(buffer, buffer.length, re.getAddress(), re.getPort());
	               
	               //On lui affecte les données à envoyer
	               packet.setData(buffer);
	               
	               //On envoie au serveur
	               client.send(packet);
	                        
	            } catch (SocketException e) {
	               e.printStackTrace();
	            } catch (UnknownHostException e) {
	               e.printStackTrace();
	            } catch (IOException e) {
	               e.printStackTrace();
	            }
	         }
      	//});
		//t.start() ; 
	}

