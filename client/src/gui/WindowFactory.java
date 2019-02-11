package gui;

import javax.swing.JFrame;

import main.Main;

public class WindowFactory {
	private Window window;
	
	public Window makeWindow(WindowsTypes type){
		switch(type){
			default:
			case LOGIN_WINDOW:
				window = new LoginWindow();
				break;
			case ELECTORAL_COMMISION_MEMBER_WINDOW:
				window = new ElectoralCommisionMemberWindow();
				break;
			case OFFICER_WINDOW:
				window = new OfficerWindow();
				break;
			case VOTER_WINDOW:
				window = new VoterWindow();
				break;
		}
		
		return window;
	}
}
