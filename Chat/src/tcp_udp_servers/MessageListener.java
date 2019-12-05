package tcp_udp_servers;

import java.net.InetAddress;

import packets.Message;

public interface MessageListener {
	void onMessage(InetAddress address, Message message) ; 
}
