package user;

import java.net.SocketTimeoutException;
import javax.swing.JOptionPane;

import gui.WindowsTypes;
import main.Main;
import net.Net;

public class Login {
	private static void changeWindow(String userType){
		if(userType.equals("1"))
			Main.changeWindow(WindowsTypes.VOTER_WINDOW);
		if(userType.equals("2"))
			Main.changeWindow(WindowsTypes.ELECTORAL_COMMISION_MEMBER_WINDOW);
		if(userType.equals("3"))
			Main.changeWindow(WindowsTypes.OFFICER_WINDOW);
	}
	
	public static boolean login(String userID, char[] password){
		try {
			Net.sendData("login");
			Net.sendData(userID);
			Net.sendData(new String(password));
			String ret = Net.receiveData();
			if(ret.compareTo("0") > 0){
				JOptionPane.showMessageDialog(null, "Uda³o siê zalogowaæ");
				
				changeWindow(ret);
				
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Nie uda³o siê zalogowaæ, spróbuj ponownie");
				return false;
			}
		} catch(SocketTimeoutException e){
			JOptionPane.showMessageDialog(null, "B³¹d odbioru danych z serwera");
			return false;
		}
	}
}
