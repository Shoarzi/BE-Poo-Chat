package main_classes;

public class Session {
	
	private String pseudo ; 
	
	public void Session(String pseu) {
		// TODO : empécher l'utilisateur de se connecter si le pseudo est déjà pris. 
		this.pseudo = pseu ; 
	}
	
	
	
	public void Stop_Session() {
		// Arrête la session. 
	}
}
