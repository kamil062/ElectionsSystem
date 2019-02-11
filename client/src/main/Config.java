package main;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Config {
	/*private static Rectangle windowSize = new Rectangle(
			(int)(Toolkit.getDefaultToolkit().getScreenSize().width * 0.8),
			(int)(Toolkit.getDefaultToolkit().getScreenSize().height * 0.8));*/
	private static Rectangle windowSize = new Rectangle(800, 600);
	private static Rectangle windowOffset = new Rectangle(
			(int)((Toolkit.getDefaultToolkit().getScreenSize().width - windowSize.width) / 2),
			(int)((Toolkit.getDefaultToolkit().getScreenSize().height - windowSize.height) / 2));
	
	private static int serverPort = 1234;
	private static InetAddress serverAddress;

	public static void init() {
		try {
			setServerAddress(InetAddress.getByName("localhost"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static Rectangle getWindowSize() {
		return windowSize;
	}

	public static void setWindowSize(Rectangle windowSize) {
		Config.windowSize = windowSize;
	}

	public static Rectangle getWindowOffset() {
		return windowOffset;
	}

	public static void setWindowOffset(Rectangle windowOffset) {
		Config.windowOffset = windowOffset;
	}

	public static int getServerPort() {
		return serverPort;
	}

	public static void setServerPort(int serverPort) {
		Config.serverPort = serverPort;
	}

	public static InetAddress getServerAddress() {
		return serverAddress;
	}

	public static void setServerAddress(InetAddress serverAddress) {
		Config.serverAddress = serverAddress;
	}

}
