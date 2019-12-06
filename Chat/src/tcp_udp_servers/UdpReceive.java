package tcp_udp_servers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import main_classes.User;

public  class UdpReceive {
	
	private static DatagramSocket dsock ;  
    private static byte[] buffer;
    private User dest; 
   
	public static void main(String[] args){
	
	    Thread t = new Thread(new Runnable(){
	        private DatagramPacket dpacket ;  
	        
			public void run() {
		    	//this.setDest(dest) ;
		    	
		        //Création de la connexion côté serveur, en spécifiant un port d'écoute
		    	try {
					dsock = new DatagramSocket();
			
		    	//Objet Paquet 
					buffer = new byte[8192] ; 
					dpacket = new DatagramPacket(buffer,buffer.length) ;  
		    	
					dsock.receive(dpacket);
	                  
	                  //nous récupérons le contenu de celui-ci et nous l'affichons
	                  String str = new String(dpacket.getData());
	                  System.out.print("Reçu de la part de " + dpacket.getAddress() 
	                                    + " sur le port " + dpacket.getPort() + " : ");
	                  System.err.println(str);
	                  
		    	
		    	} catch (SocketException e) {
					e.printStackTrace();
				}
		    }
	    });
	}
	
    
    public void Receive_message() {
    	
    	try {
			dsock.receive(dpacket) ;
			  //nous récupérons le contenu de celui-ci et nous l'affichons
            String str = new String(dpacket.getData());
            System.out.print("Reçu de la part de " + dpacket.getAddress() 
                              + " sur le port " + dpacket.getPort() + " : ");
            System.out.println(str);
            
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
