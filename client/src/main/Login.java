package main;

import java.util.Random;

import javax.swing.JOptionPane;

import gui.WindowsTypes;
import net.Net;

public class Login {
	public static boolean login(String userID, char[] password){
		Net.sendData("login");
		Net.sendData(userID);
		Net.sendData(new String(password));
		String ret = Net.receiveData();
		if(ret.equals("true")){
			JOptionPane.showMessageDialog(null, "Uda³o siê zalogowaæ");
			Main.changeWindow(WindowsTypes.VOTER_WINDOW);
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Nie uda³o siê zalogowaæ, spróbuj ponownie");
			return false;
		}
	}
}
