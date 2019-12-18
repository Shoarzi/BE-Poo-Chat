package main_classes;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.UUID;

public class User {
  private static int nbUser = 0;
  private UUID userId;
  private PrintStream streamOut;
  private InputStream streamIn;
  private String nickname;
  private Socket client;

  // constructor
  public User(Socket client, String name) throws IOException {
    this.streamOut = new PrintStream(client.getOutputStream());
    this.streamIn = client.getInputStream();
    this.client = client;
    this.nickname = name;
    this.setUserId(java.util.UUID.randomUUID());
    nbUser += 1;
  }
  
  public User(Socket client, String name, UUID id ) throws IOException {
	    this.streamOut = new PrintStream(client.getOutputStream());
	    this.streamIn = client.getInputStream();
	    this.client = client;
	    this.nickname = name;
	    setUserId(id) ; 
	    nbUser += 1;
	  }

  // getteur
  public PrintStream getOutStream(){
    return this.streamOut;
  }

  public InputStream getInputStream(){
    return this.streamIn;
  }

  public String getNickname(){
    return this.nickname;
  }

  // print user with his color
  public String toString(){

    return "<u><span>" + this.getNickname() + "</span></u>";

  }

public UUID getUserId() {
	return userId;
}

public void setUserId(UUID uuid) {
	this.userId = uuid;
}
}



 