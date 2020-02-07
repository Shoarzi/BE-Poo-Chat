package main_classes;

import java.io.File;
import java.util.ArrayList;

public class UserList {

	public static ArrayList<String> listUserName = new ArrayList<String>() ;
	public static ArrayList<String> listUserId = new ArrayList<String>() ;
	public static ArrayList<String> listUserAddress = new ArrayList<String>() ;
	public static int nb_user = 0 ;  
	private static File log = new File("log.txt"); 
	private static Boolean change = false ; 
	private static Boolean disconnect = false ; 

	public static void addUser(String pseudo, String uuid, String host) {
		int i = listUserId.indexOf(uuid) ; 
		if (i == -1) { 
			UserList.listUserName.add(pseudo);
			UserList.listUserId.add(uuid);
			UserList.listUserAddress.add(host);
			nb_user +=1 ; 
		}
		else { 
			UserList.listUserName.set(i, pseudo);
			UserList.listUserAddress.set(i, host);
		}
		setChange(true);
	}

	public static void removeUser(String us) {
		int i = UserList.listUserName.indexOf(us);
		UserList.listUserName.remove(us) ;  
		UserList.listUserId.remove(i);
		UserList.listUserAddress.remove(i);
		nb_user -= 1 ; 
		setChange(true);
	}

	public boolean isEmpty() {
		return nb_user == 0  ; 
	}

	public static ArrayList<String> getlistUserName() {
		return getListUserName() ; 
	}

	public static File getLog() {
		return log;
	}

	public static void setLog(File log) {
		UserList.log = log;
	}

	public static Boolean getChange() {
		return change;
	}

	public static void setChange(Boolean change) {
		UserList.change = change;
	}

	public static ArrayList<String> getListUserName() {
		return listUserName;
	}

	//TODO implements the dynamic change of pseudo
	public static void ChangePseudo(String newpseudo, String uuid) {
		int i = listUserId.indexOf(uuid) ; 
		listUserName.set(i, newpseudo) ;
		setChange(true);
	}

	public static ArrayList<String> getListUserAddress() {
		return listUserAddress;
	}

	public static Boolean getDisconnect() {
		return disconnect;
	}

	public static void setDisconnect(Boolean disconnect) {
		UserList.disconnect = disconnect;
	}
}