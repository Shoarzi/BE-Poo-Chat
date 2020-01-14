package main_classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class UserList {
	
	private static ArrayList<String> listUserName ; 
	private static File log; 
	
	public static int getPort(String name) {
		int port = 0; 
		BufferedReader lecteurAvecBuffer = null;
		String ligne;
		try
		{
			lecteurAvecBuffer = new BufferedReader(new FileReader(log));
			boolean notfound = true ; 
			while (notfound && (ligne = lecteurAvecBuffer.readLine()) != null)
			{ 
				if (ligne.split(";")[0] == name) {
					port = Integer.parseInt(ligne.split(";")[1]) ; 
					notfound = false ; 
				}
			}
			lecteurAvecBuffer.close();
		}
		catch(FileNotFoundException exc)
		{
			System.out.println("Erreur d'ouverture");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return port;
		
	}
	
	public static void NewUserDetected(String str, Client main_user) {
		String pseudo = str.split(";;")[0]; 
		String uuid = str.split(";;")[1]; 
		String ipAdd = str.split(";;")[2]; 
		/*if (pseudo == main_user.getNickname()) {
			try {
				main_user.Reply_Message("!", InetAddress.getByName(ipAdd));
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}*/
		BufferedReader lecteurAvecBuffer = null;
		String fileLigne;
		boolean found = false ; 
		int ligne = 0 ; 
		try {
			lecteurAvecBuffer = new BufferedReader(new FileReader(log));
			while (!found && (fileLigne = lecteurAvecBuffer.readLine()) != null) { 
				ligne += 1 ;  
				/*if (newUserId == fileLigne.split(";")[3]) {
					found = true ; 

				}*/
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (found) { 
			
		}
	}
	
	public static void addUser(Client us) {
		UserList.listUserName.add(us.getNickname()) ;  
		String str = us.getNickname()+";;"+us.getUserId()+";;"+us.getHost();
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(log));
			writer.write(str);
			writer.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public static void addUser(String nickname, String uuid, String host) {
		UserList.listUserName.add(nickname) ;  
		String str = nickname+";;"+uuid+";;"+host;
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(log));
			writer.write(str);
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	public void removeUser(String us) {
		listUserName.remove(us) ; 
	}
	
	public boolean isUserPresent(String us) {
		return listUserName.contains(us) ; 
	}
	 
	public boolean isEmpty() {
		return listUserName.isEmpty() ; 
	}
	
	public static ArrayList<String> getlistUserName() {
		return listUserName ; 
	}

	public static File getLog() {
		return log;
	}

	public static void setLog(File log) {
		UserList.log = log;
	}

	public static InetAddress getAddress(String name) {
		
		BufferedReader lecteurAvecBuffer = null;
		String ligne="";
		boolean found = false ; 
		try
		{
			lecteurAvecBuffer = new BufferedReader(new FileReader(log));
			
			while (!found && (ligne = lecteurAvecBuffer.readLine()) != null)
			{ 
				if (ligne.split(";;")[0] == name) {
					found = true ; 
				}
			}
			lecteurAvecBuffer.close();
			
			if (found) 
				return InetAddress.getByName(ligne.split(";;")[2]) ; 
		}
		catch(FileNotFoundException exc)
		{
			System.out.println("Erreur d'ouverture");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null ; 
	}


}
