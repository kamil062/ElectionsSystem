package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import javax.swing.JOptionPane;

import main.Config;

public class Net {
	private static DatagramSocket clientSocket;
	private static byte[] receiveBuffer;
	private static byte[] sendBuffer;

	public static void init(){
		try {
			setClientSocket(new DatagramSocket());
		} catch (SocketException e) {
			JOptionPane.showMessageDialog(null, "Blad tworzenia socketa");
			e.printStackTrace();
		}
	}
	
	public static void sendData(String msg){ 
		try {
			byte sendBuffer[] = msg.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, Config.getServerAddress(), Config.getServerPort());      
			clientSocket.send(sendPacket);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Blad wysylania datagramu");
			e.printStackTrace();
		}
	}
	
	public static String receiveData() throws SocketTimeoutException {          
		try {
			setReceiveBuffer(new byte[10240]);
			DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length); 
			clientSocket.setSoTimeout(3000);
			clientSocket.receive(receivePacket);
			return new String(receivePacket.getData()).substring(0, receivePacket.getLength());
		} catch(SocketTimeoutException e){
			JOptionPane.showMessageDialog(null, "B³¹d odbioru danych z serwera");
			return "";
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "B³¹d odbioru danych z serwera");
			e1.printStackTrace();
			return "";
		}            
	}
	
	public static DatagramSocket getClientSocket() {
		return clientSocket;
	}

	public static void setClientSocket(DatagramSocket clientSocket) {
		Net.clientSocket = clientSocket;
	}

	public static byte[] getSendBuffer() {
		return sendBuffer;
	}

	public static void setSendBuffer(byte[] sendBuffer) {
		Net.sendBuffer = sendBuffer;
	}
	
	public static byte[] getReceiveBuffer() {
		return receiveBuffer;
	}

	public static void setReceiveBuffer(byte[] receiveBuffer) {
		Net.receiveBuffer = receiveBuffer;
	}
}
