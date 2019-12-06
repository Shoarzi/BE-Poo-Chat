package tcp_udp_servers;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketAddress;

public class UdpPacket {
	
	private byte[] buffer;
	private int length;
	private int offset;
	private InetAddress address;
	private int port;
	private SocketAddress socketAddress;
	
	public UdpPacket(byte[] buffer, int length) {
		this.setBuffer(buffer);
		this.setLength(length);
	}
	public UdpPacket(byte[] buffer, int offset, int length) {
		this.setBuffer(buffer);
		this.setLength(length);
		this.setOffset(offset);
	}
	
	public UdpPacket(byte[] buffer, int length, InetAddress address, int port) {
		this.setBuffer(buffer);
		this.setLength(length);
		this.setDestination(address);
		this.setPort(port);
	}
	
	public UdpPacket(byte[] buffer, int offset, int length, SocketAddress socketAddress, int port) {
		this.setBuffer(buffer);
		this.setLength(length);
		this.setOffset(offset);
		this.setSocketAddress(socketAddress);
		this.setPort(port);
	}
	
	public UdpPacket(byte[] buffer, int length, SocketAddress socketAddress, int port) {
		this.setBuffer(buffer);
		this.setLength(length);
		this.setSocketAddress(socketAddress);
		this.setPort(port);
	}
	
	public UdpPacket(byte[] buffer, int offset, int length, InetAddress address, int port) {
		this.setBuffer(buffer);
		this.setLength(length);
		this.setOffset(offset);
		this.setDestination(address);
		this.setPort(port);
	}
	
	public DatagramPacket toDatagramPacket() {
		
		return new DatagramPacket(buffer, length, address, port) ; 
		
	}
	
	public byte[] getBuffer() {
		return buffer;
	}
	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public InetAddress getAddress() {
		return address;
	}
	public void setDestination(InetAddress destination) {
		this.address = destination;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public SocketAddress getSocketAddress() {
		return socketAddress;
	}
	public void setSocketAddress(SocketAddress socketAddress) {
		this.socketAddress = socketAddress;
	}
}
