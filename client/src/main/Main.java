package main;

import gui.LoginWindow;
import gui.Window;
import gui.WindowFactory;
import gui.WindowsTypes;
import net.Net;

public class Main {
	private static Window activeWindow = null;
	private static WindowsTypes avtiveWindowType;
	private static WindowFactory windowFactory;
	
	public static void main(String[] args) {
		Config.init();
		Net.init();
		
		windowFactory = new WindowFactory();
		changeWindow(WindowsTypes.LOGIN_WINDOW);
	}

	public static Window getActiveWindow() {
		return activeWindow;
	}

	public static void setActiveWindow(Window activeWindow) {
		Main.activeWindow = activeWindow;
	}
	
	public static void changeWindow(WindowsTypes window){
		if(getActiveWindow() != null){
			getActiveWindow().close();
		}
		setActiveWindow(windowFactory.makeWindow(window));
		setAvtiveWindowType(window);
		getActiveWindow().run();
	}

	public static WindowsTypes getAvtiveWindowType() {
		return avtiveWindowType;
	}

	public static void setAvtiveWindowType(WindowsTypes avtiveWindowType) {
		Main.avtiveWindowType = avtiveWindowType;
	}
}
