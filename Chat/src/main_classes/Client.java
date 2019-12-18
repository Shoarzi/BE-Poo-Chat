package main_classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.StringReader;

public class Client {

  private InetAddress host;
  private int port; 
	public static void main(String[] args) throws UnknownHostException, IOException {
    new Client(12345, (short) 16).run();
    // mask corresponding to 10.1.255.255
  }

  public Client(int port, short mask) {
	  this.port = port ; 
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

      } catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
      } 
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
    // connect client to server
		  try {
		    Socket client = null;
			
			client = new Socket(host, port);
			
		    System.out.println("Client successfully connected to server!");
		
		    // Get Socket output stream (where the client send her mesg)
		    PrintStream output;
			output = new PrintStream(client.getOutputStream());
		    // ask for a nickname
		    Scanner sc = new Scanner(System.in);
		    System.out.print("Enter a nickname: ");
		    String nickname = sc.nextLine();
		
		    // send nickname to server
		    output.println(nickname);
		
		    // create a new thread for server messages handling
		    new Thread(new ReceivedMessagesHandler(client.getInputStream())).start();
		
		    // read messages from keyboard and send to server
		    System.out.println("Messages: \n");
		
		    // while new messages
		    while (sc.hasNextLine()) {
		      output.println(sc.nextLine());
		    }
		
		    // end ctrl D
		   /* output.close();
		    sc.close();  
			client.close();*/
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
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
    String tmp = "";
    while (s.hasNextLine()) {
      tmp = s.nextLine();
      if (tmp.charAt(0) == '[') {
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
    }
    //s.close();
  }

  public static String getTagValue(String xml){
    return  xml.split(">")[2].split("<")[0] + xml.split("<span>")[1].split("</span>")[0];
  }

}
