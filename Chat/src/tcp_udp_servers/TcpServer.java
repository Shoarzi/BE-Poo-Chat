package tcp_udp_servers;
import java.io.*;
import java.net.*;
// ref : https://systembash.com/a-simple-java-tcp-server-and-tcp-client/

public class TcpServer {

	public static void main(String argv[]) throws Exception {
		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(6789);

	while (true) {
		Socket connectionSocket = welcomeSocket.accept();
		BufferedReader inFromClient =
	    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		clientSentence = inFromClient.readLine();
		System.out.println("Received: " + clientSentence);
		capitalizedSentence = clientSentence.toUpperCase() + 'n';
		outToClient.writeBytes(capitalizedSentence);
	  }
	}
}

