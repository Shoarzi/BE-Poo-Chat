package tcp_udp_servers;

import main_classes.Message;
import java.net.InetAddress;

public interface MessageListener {
	void onMessage(InetAddress address, Message message) ; 
}
