package main_classes;

import java.util.ArrayList;

public class UserList {

	public User us ; 
	
	private ArrayList<User> users_connected ; 

	public UserList() {
		this.users_connected = new ArrayList<User>() ; 
	}
	
	public void addUser(User us) {
		this.users_connected.add(us) ;  
	}
	
	public void removeUser(User us) {
		this.users_connected.remove(us) ; 
	}
	
	public boolean isUserPresent(User us) {
		return users_connected.contains(us) ; 
	}
	 
	public boolean isEmpty() {
		return this.users_connected.isEmpty() ; 
	}
	
	public ArrayList<User> getUsersConnected() {
		return this.users_connected ; 
	}



}
