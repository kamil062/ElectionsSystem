package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.JOptionPane;

import main.Config;

public class Net {
	private static DatagramSocket serverSocket;
	private static byte[] receiveBuffer;
	private static byte[] sendBuffer;
	private static int lastPort;
	private static InetAddress lastAddress;
	
	public static void init() {
		try {
			serverSocket = new DatagramSocket(Config.getServerPort());
			receiveBuffer = new byte[10240];
			sendBuffer = new byte[10240];
			
			System.out.println("Serwer uruchomiony na porcie " + Config.getServerPort());
		} catch (SocketException e) {
			System.out.println("Blad tworzenia socketa");
			e.printStackTrace();
		}
	}
	
	public static void sendData(String msg){ 
		try {
			byte sendBuffer[] = msg.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, getLastAddress(), getLastPort());      
			serverSocket.send(sendPacket);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Blad wysylania datagramu");
			e.printStackTrace();
		}
	}
	
	public static String receiveData(){          
		try {
			setReceiveBuffer(new byte[10240]);
			DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length); 
			serverSocket.receive(receivePacket);
			setLastPort(receivePacket.getPort());
			setLastAddress(receivePacket.getAddress());
			return new String(receivePacket.getData()).substring(0, receivePacket.getLength());
		} catch (IOException e) {
			System.out.println("Blad odbioru datagramu");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static DatagramSocket getServerSocket() {
		return serverSocket;
	}

	public static void setServerSocket(DatagramSocket serverSocket) {
		Net.serverSocket = serverSocket;
	}

	public static byte[] getReceiveBuffer() {
		return receiveBuffer;
	}

	public static void setReceiveBuffer(byte[] receiveBuffer) {
		Net.receiveBuffer = receiveBuffer;
	}

	public static byte[] getSendBuffer() {
		return sendBuffer;
	}

	public static void setSendBuffer(byte[] sendBuffer) {
		Net.sendBuffer = sendBuffer;
	}

	public static int getLastPort() {
		return lastPort;
	}

	public static void setLastPort(int lastPort) {
		Net.lastPort = lastPort;
	}

	public static InetAddress getLastAddress() {
		return lastAddress;
	}

	public static void setLastAddress(InetAddress lastAddress) {
		Net.lastAddress = lastAddress;
	}
}	
