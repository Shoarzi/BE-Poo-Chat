package main_classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Server {

  private int port;
  private List<User> clients;
  private ServerSocket server;

  public static void main(String[] args) throws IOException {
    new Server(12345).run();
  }

  public Server(int port) {
    this.port = port;
    this.clients = new ArrayList<User>();
  }

  public void run() throws IOException {
    server = new ServerSocket(port) {
      protected void finalize() throws IOException {
        this.close();
      }
    };
    System.out.println("Port 12345 is now open.");

    while (true) {
      // accepts a new client
      Socket client = server.accept();

      // get nickname of newUser
      InputStream testIS = client.getInputStream() ; 
      Scanner testSC = new Scanner ( testIS ) ; 
      String nickname = (testSC.nextLine());
      nickname = nickname.replace(",", ""); //  ',' use for serialisation
      nickname = nickname.replace(" ", "_");
      System.out.println("New Client: \"" + nickname + "\"\n\t     Host:" + client.getInetAddress().getHostAddress());

      // create new User
      User newUser = new User(client, nickname); 

      // add newUser message to list
      this.clients.add(newUser);

      // Welcome msg
      newUser.getOutStream().println("<b>Welcome</b> " + newUser.toString());

      // create a new thread for newUser incoming messages handling
      new Thread(new UserHandler(this, newUser)).start();
    }
  }

// delete a user from the list
  public void removeUser(User user){
    this.clients.remove(user);
  }

  // send incoming msg to all Users
  public void broadcastMessages(String msg, Client userSender) {
    for (User client : this.clients) {
      client.getOutStream().println(
          userSender.toString() + "<span>: " + msg+"</span>");
    }
  }

  // send list of clients to all Users
  public void broadcastAllUsers(){
    for (User client : this.clients) {
      client.getOutStream().println(this.clients);
    }
  }

  // send message to a User (String)
  public void sendMessageToUser(String msg, User userSender, String user){
    boolean find = false;
    for (User client : this.clients) {
      if (client.getNickname().equals(user) && client != userSender) {
        find = true;
        userSender.getOutStream().println(userSender.toString() + " -> " + client.toString() +": " + msg);
        client.getOutStream().println(
            "(<b>Private</b>)" + userSender.toString() + "<span>: " + msg+"</span>");
      }
    }
    if (!find) {
      userSender.getOutStream().println(userSender.toString() + " -> (<b>no one!</b>): " + msg);
    }
  }
  
  public void sendHistoricMsgToUser() {
	  
  }
  
  public void historizeMessage (File dirtosearch, String msg, UUID idsender, UUID idreceiver) {
			File[] listOfFiles = dirtosearch.listFiles();
			Vector<File> listOfSimpleFiles = new Vector<File>();
			for (File file : listOfFiles) {
				listOfSimpleFiles.add(file);		
			}
			String name1 ; 
			if (idsender.toString().compareTo(idreceiver.toString()) > 0) {
				name1 = idsender.toString()+idreceiver.toString() ;
			}
			else {
				name1 = idreceiver.toString()+idsender.toString() ; 
			}
			boolean find = false;
			File filewanted ; 
			
			int i = 0;
			while (i < listOfSimpleFiles.size() && !find) {
				if (listOfSimpleFiles.get(i).getName()
						.equals(name1)) {
					find = true ; 
					filewanted = listOfSimpleFiles.get(i) ; 
				} else
					i++;
			}
			if (find) { 
				// ajouter la conv à l'historique
				FileWriter fr;
				try {
					fr = new FileWriter(filewanted, true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fr.write("data");
				fr.close();
		
			}
			else {
				// créer l'historique 
			}
  }
}

class UserHandler implements Runnable {

	  private Server server;
	  private User user;

	  public UserHandler(Server server, User user) {
	    this.server = server;
	    this.user = user;
	    this.server.broadcastAllUsers();
	  }

	  public void run() {
	    String message;

	    // when there is a new message, broadcast to all
	    Scanner sc = new Scanner(this.user.getInputStream());
	    while (sc.hasNextLine()) {
	      message = sc.nextLine();

	 
	      // Gestion des messages private
	    	  if (!message.isEmpty()) { 
			      if (message.charAt(0) == '@'){
			        if(message.contains(" ")){
			          System.out.println("private msg : " + message);
			          int firstSpace = message.indexOf(" ");
			          String msg = message.substring(
				                firstSpace+1, message.length()
				                );
			          String userPrivate= message.substring(1, firstSpace);
			          server.sendMessageToUser(msg, user, userPrivate);
			        
			        }
			      
		      }
	      }
	        else {
	        	System.out.print("You have to send your message to a specific user using \"@user\" ");
	        }
	    }
	    // end of Thread
	    server.removeUser(user);
	    this.server.broadcastAllUsers();
	    sc.close();
	  }
	}
	
