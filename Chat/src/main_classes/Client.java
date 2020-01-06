package main_classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.UUID;
import java.io.StringReader;



public class Client implements Runnable {

	private PrintStream streamOut ; //
	private InputStream streamIn ; //
	private UUID userId;
	private String nickname;
	private Socket client; //
	private InetAddress host;
	private int port; 
	private DatagramSocket socket_env;
	private ServerSocket socket_rec;

	// Client qui trouve automatiquement son adresse IP 
  public Client(int port, short mask, String name) {
	  
	  this.setUserId(java.util.UUID.randomUUID());
	  this.port = port ; 
	  this.setNickname(name);
      Enumeration<NetworkInterface> enumNet;	  
      try {
		enumNet = NetworkInterface.getNetworkInterfaces();
	      boolean find = false ;
	      boolean right = false ;
	      NetworkInterface net = null ;
	      InterfaceAddress inter = null ;
	      while (!find && enumNet.hasMoreElements()) {
	          net = enumNet.nextElement() ;
	          List<InterfaceAddress> l_add = net.getInterfaceAddresses() ;
	          Iterator<InterfaceAddress> iterator = l_add.iterator();
	          while (!right && iterator.hasNext()) {
	              inter = iterator.next() ;
	              short test = inter.getNetworkPrefixLength() ; 
	              if (test == mask) {
	                  this.host = inter.getAddress() ;
	                  right = true ;
	                  find = true ;
	              }
	          }
	      }
		
      } 
      catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
      } 
      
      Broadcast((";" + nickname + ";" + getUserId() + ";" + getHost())) ; 
  }
  
  public Client(String host, int port) {
    try {
		this.host = InetAddress.getByName(host);
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    this.port = port;
  }

  public void run() {
	  System.out.println("successfully connected");
	  
	    try {
			socket_rec = new ServerSocket(port) {
			    protected void finalize() throws IOException {
			      this.close();
			    }
			};
			client = socket_rec.accept();
			//wait for new messages 
			
			streamIn = client.getInputStream();
			Scanner sc = new Scanner(streamIn);
			// create a new thread for server messages handling
			new Thread(new ReceivedMessagesHandler(streamIn)).start();
	
			// read messages from keyboard and send to server
			System.out.println("Messages: \n");
	
			// while new messages
			while (sc.hasNextLine()) {
				String str = sc.nextLine() ; 
				streamOut.println(str);
			}
		
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
  	}
  
  public void Broadcast(String broadcastMessage){
      try {
	  socket_env = new DatagramSocket();
      socket_env.setBroadcast(true);
      byte[] buffer = broadcastMessage.getBytes();
      InetAddress add = InetAddress.getByName("255.255.255.255");
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length, add, port);
      socket_env.send(packet);
      socket_env.close();
      } 
      catch (SocketException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
      }
      catch (UnknownHostException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
      } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
      } 
  }
  
  public void Send_Message(String mes, String name) {
	  try {
		  socket_env = new DatagramSocket();
	      byte[] buffer = mes.getBytes();
	      InetAddress add = UserList.getAddress(name) ; 
	      DatagramPacket packet = new DatagramPacket(buffer, buffer.length, add, port);
	      socket_env.send(packet);
	      socket_env.close();
	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
  }

  	public InetAddress getHost() {
  		return host ; 
  	}
  
  	public int getPort() { 
  		return port ; 
  	}
  	
	public UUID getUserId() {
		return userId;
	}
	
	public DatagramSocket getSocket_env() {
		return socket_env ; 
	}
	
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}

class ReceivedMessagesHandler implements Runnable {

  private InputStream server;

  public ReceivedMessagesHandler(InputStream server) {
    this.server = server;
  }

  public void run() {
    // receive server messages and print out to screen
    Scanner s = new Scanner(server);
    String tmp = "" ; 
   // if(s.hasNextLine()) {
    	tmp = s.nextLine(); 
    	if (tmp.charAt(0) == ';') { 
    		UserList.NewUserDetected(tmp.substring(1)) ; 
    	}
    	while (s.hasNextLine()) {
    		tmp = s.nextLine();
    		System.out.println(tmp);
    	} 
    
    /*  if (tmp.charAt(0) == '[') {
        tmp = tmp.substring(1, tmp.length()-1);
        System.out.println(
            "\nUSERS LIST: " +
            new ArrayList<String>(Arrays.asList(tmp.split(","))) + "\n"
            );
      }else{
        try {
          System.out.println("\n" + getTagValue(tmp));
          // System.out.println(tmp);
        } catch(Exception ignore){}
      }
    }*/
    //s.close();
  }

  public static String getTagValue(String xml){
    return  xml.split(">")[2].split("<")[0] + xml.split("<span>")[1].split("</span>")[0];
  }
  
}
