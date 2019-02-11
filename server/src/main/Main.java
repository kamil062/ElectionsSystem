package main;

import db.DatabaseConnect;
import net.Net;
import net.Receiver;

/**
 * G³ówna klasa serwera
 * 
 * @author "Kamil Piec"
 *
 */
public class Main {
	public static void main(String[] args){
		Net.init();
		
		Thread receiverThread = new Thread(new Receiver());
		Thread databaseConnectThread = new Thread(new DatabaseConnect());
		
		databaseConnectThread.start();
		
		try {
			databaseConnectThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		receiverThread.start();
		
		try {
			receiverThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		DatabaseConnect.endConnection();
	}
}
