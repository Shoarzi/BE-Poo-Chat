package main_classes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Scanner;
import swing.chat;
import swing.list_users;

public class Client implements Runnable {

	private InputStream streamIn ;
	private String userId;
	private String nickname;
	private InetAddress host;
	private int port; 
	private DatagramSocket socket_env;
	private DatagramSocket socket_recudp;
	private HashMap<String,chat> ChatList = new HashMap<String,chat>() ;

	// Client who automatically find his own IP address
	public Client(int port, short mask){
		this.port = port ; 
		Enumeration<NetworkInterface> enumNet;	  
		try {
			enumNet = NetworkInterface.getNetworkInterfaces();
			
			//boolean to see if we have found an interface
			boolean f = false ;
			
			//boolean to see if we have found the right address 
			boolean g = false ;
			
			NetworkInterface net = null ;
			InterfaceAddress inter = null ;
			while (!f && enumNet.hasMoreElements()) {
				net = enumNet.nextElement() ;
				List<InterfaceAddress> l_add = net.getInterfaceAddresses() ;
				Iterator<InterfaceAddress> iterator = l_add.iterator();
				//iterate on all the addresses of the interface
				while (!g && iterator.hasNext()) {
					inter = iterator.next() ;
					short test = inter.getNetworkPrefixLength() ; 
					if (test == mask) {
						this.host = inter.getAddress() ;
						g = true ;
						f = true ;
					}
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public void run() {

		while (true) {
			try {
				socket_recudp = new DatagramSocket(port) ; 
				byte buffer[] = new byte[1024];
				DatagramPacket p = new DatagramPacket(buffer,buffer.length); 
				socket_recudp.receive(p);
				streamIn = new ByteArrayInputStream(p.getData());

				// create a new thread for UDP messages handling
				new Thread(new ReceivedMessagesHandler(streamIn, this)).start(); 
			} catch (IOException e) {
				e.printStackTrace();
			}	
			socket_recudp.close();
		}
	}

	//Method for Broadcasting a message on local network
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
		catch (IOException e) { 
			e.printStackTrace();
		} 
	}

	//method to reply a message to an InetAddress when we don't know the nickname. 
	public void Reply_Message(String mes, InetAddress add) {
		try {
			socket_env = new DatagramSocket();
			byte[] buffer = mes.getBytes();
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, add, port);
			socket_env.send(packet);
			socket_env.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//main method to send messages. 
	public void Send_Message(String mes, String name, String add) {
		try {
			socket_env = new DatagramSocket();
			byte[] buffer = (this.getHost()+"§§§"+this.getPseudo()+"§§§"+this.getUserId()+"§§§"+mes+"§§§").getBytes();
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(add), port);
			socket_env.send(packet);
			socket_env.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InetAddress getHost() {
		return host ; 
	}

	public int getPort() { 
		return port ; 
	}

	public String getUserId() {
		return userId;
	}

	public DatagramSocket getSocket_env() {
		return socket_env ; 
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPseudo() {
		return nickname;
	}

	public void setPseudo(String nickname) {
		this.nickname = nickname;
	}

	public Map<String,chat> getChatList() {
		return ChatList;
	}

	public void setChatList(HashMap<String,chat> chatList) {
		this.ChatList = chatList;
	}
}

//Class to implement a thread for message handling
class ReceivedMessagesHandler implements Runnable {

	private InputStream server;
	private Client main_user; 

	public ReceivedMessagesHandler(InputStream server, Client main_user) {
		this.server = server;
		this.main_user = main_user; 
	}

	public void run() {
		Scanner s = new Scanner(server);
		String tmp = "" ; 
		if(s.hasNextLine()) {
			tmp = s.nextLine();
			InetAddress add_away;
			try {
				String st = tmp.split("[βα>§;]")[0].substring(1);
				add_away = InetAddress.getByName(st);
				if (!add_away.equals(main_user.getHost())) { 
					char c=tmp.charAt(st.length()+1); 
					//Someone asked us to send our coordinates. 
					if (c == 'β' && main_user.getPseudo() != null) {
						main_user.Reply_Message(main_user.getHost()+"α"+main_user.getPseudo()+"α"+main_user.getUserId()+"α", add_away ) ;
					}

					//Someone sent us their coordinates. 
					if (c == 'α') { 
						String add = tmp.split("α")[0];
						String pseudo = tmp.split("α")[1]; 
						String uuid = tmp.split("α")[2]; 
						UserList.addUser(pseudo, uuid, add.substring(1));
					}

					//Someone sent a new message
					if (c == '§') { 
						String foreign_nickname = tmp.split("§§§")[1] ; 
						String foreign_user = tmp.split("§§§")[2] ; 
						int i = UserList.listUserName.indexOf(foreign_nickname); 
						//Update of an opened window
						while (main_user.getChatList().containsKey(foreign_user)) { 
							main_user.getChatList().remove(foreign_user).dispose();
						}
						chat newChat= new chat(foreign_nickname, main_user);
						main_user.getChatList().put(foreign_user, newChat) ; 
						list_users.setTrueStateButton(i);
					}

					// TODO : The user changed his name 
					if (c == ';') { 
						String newpseudo = tmp.split(";")[1]; 
						String uuid = tmp.split(";")[2]; 
						UserList.ChangePseudo(newpseudo, uuid) ; 
						UserList.setChange(true);
					}

					//The user left 
					if (c == '>') { 
						String pseudo = tmp.split(">")[1]; 
						UserList.removeUser(pseudo); 
					}
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} 
		}
		s.close();
	}
}