package main_classes;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.UUID;

public class User {


  // constructor
  public User(Socket client, String name) throws IOException {

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


}



 