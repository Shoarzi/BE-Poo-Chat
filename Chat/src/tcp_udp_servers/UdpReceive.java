package tcp_udp_servers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import main_classes.User;

public class UdpReceive {
	
	private static DatagramSocket dsock ;  
    private static byte[] buffer;
    private static User dest; 
    private static int long_buf = 10000; 
   
	public static void Receive(User de){
    	
		setDest(de) ;
	    Thread t = new Thread(new Runnable(){
	        
	    	private DatagramPacket dpacket ;  
	        
			public void run() {
				
				try {
					
					//Création de la connexion côté serveur, en spécifiant un port d'écoute
					dsock = new DatagramSocket(dest.getPort());

					
					while (true) {
						
						//Objet Paquet 
						buffer = new byte[long_buf] ; 
						dpacket = new DatagramPacket(buffer,buffer.length) ;  
						
						dsock.receive(dpacket);
		                  
		                  //nous récupérons le contenu de celui-ci et nous l'affichons
		                String str = new String(dpacket.getData());
		                String strtype = str.substring(0, 2);
		                if (strtype.contentEquals("Ms")) {
		                	System.out.print(str.substring(2));
		                }
		                else if (strtype.contentEquals("Ni")) 
		                {
		                	String delim = "[:]";  
		                	String strrep = new String ("Nb" + dest.getPseudo() + " : " + dest.getPort()) ; 
		                	byte[] bufferrep = new String(strrep).getBytes() ; 
		                	String[] strelems = str.split(delim) ; 
		                	//creation of the response packet 
		                	DatagramPacket rep = new DatagramPacket(bufferrep,bufferrep.length, InetAddress.getByName(strelems[1]), Integer.parseInt(strelems[2])); 
		                	dsock.send(rep) ; 
		                	rep.setLength(bufferrep.length);
		                	
		                }
		                else if (strtype.contentEquals("Nb")) { 
		                	System.out.print("trop fort");
		                }
		                else if (strtype.contentEquals("No")) {
		                	
		                }
		                else {
		                	throw new Exception("BadUdpSyntaxException") ; 
		                }
		                
		                dpacket.setLength(buffer.length);
		                  
			    	} 
				}
				catch (SocketException e) {
					e.printStackTrace();
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();	
			    } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
	    });
	    t.start();
	}
	
    
   /* public void Receive_message() {
    	
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
    */


	public static void CloseSocket() {
		dsock.close();
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

	public static void setDest(User de) {
		dest = de;
	}



}
