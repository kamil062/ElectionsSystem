package main;

public class User {
	private static int userID;
	private static String userPESEL;
	
	public static int getUserID() {
		return userID;
	}
	
	public static void setUserID(int userID) {
		User.userID = userID;
	}
	
	public static String getUserPESEL() {
		return userPESEL;
	}
	
	public static void setUserPESEL(String userPESEL) {
		User.userPESEL = userPESEL;
	}
}
