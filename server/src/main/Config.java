package main;

import java.sql.Connection;

/**
 * Klasa konfiguracyjna serwera
 * 
 * @author "Kamil Piec"
 *
 */
public class Config {
	/** Port, na którym bêdzie dzia³a³ serwer */
	private static int serverPort = 1234;

	/** Adres URL do po³¹czenia z baz¹ danych */
	private static String DBurl = "jdbc:oracle:thin:@localhost:1521/xe";
	/** Nazwa u¿ytkownika bazy danych */
	private static String DBuser = "projektip";
	/** Has³o u¿ytkownika bazy danych */
	private static String DBpassword = "root";
	/** Uchwyt po³¹czenia z baz¹ danych */
	private static Connection dbConnection;

	/**
	 * Metoda zwraca port serwera
	 * @return Porst serwera
	 */
	public static int getServerPort() {
		return serverPort;
	}

	/**
	 * Metoda pozwala zmieniæ port serwera
	 * @param serverPort Port serwera
	 */
	public static void setServerPort(int serverPort) {
		Config.serverPort = serverPort;
	}
	
	/**
	 * Metoda zwraca adres URL do po³¹czenia z baz¹ danych
	 * @return Adres URL
	 */
	public static String getDBurl() {
		return DBurl;
	}

	/**
	 * Metoda pozwala zmieniæ adres URL do po³¹czenia z baz¹ danych
	 * @param dBurl Nowy adres URL
	 */
	public static void setDBurl(String dBurl) {
		DBurl = dBurl;
	}

	/**
	 * Metoda zwraca nazwê u¿ytkownika bazy danych
	 * @return Nazwa u¿ytkownika bazy danych
	 */
	public static String getDBuser() {
		return DBuser;
	}

	/**
	 * Metoda pozwala zmieniæ nazwê u¿ytkownika bazy danych
	 * @param dBuser Nowa nazwa u¿ytkownika bazy danych
	 */
	public static void setDBuser(String dBuser) {
		DBuser = dBuser;
	}

	/**
	 * Metoda zwraca has³o u¿ytkownika bazy danych
	 * @return Has³o uzytkownika bazy danych
	 */
	public static String getDBpassword() {
		return DBpassword;
	}

	/**
	 * Metoda pozwala zmieniæ has³o u¿ytkownika bazy danych
	 * @param dBpassword Nowe has³o u¿ytkownika bazy danych
	 */
	public static void setDBpassword(String dBpassword) {
		DBpassword = dBpassword;
	}

	/**
	 * Metoda zwraca uchwyt po³¹czenia z baz¹ danych
	 * @return Uchwyt po³¹czenia z baz¹ danych
	 */
	public static Connection getDbConnection() {
		return dbConnection;
	}

	/**
	 * Metoda zmienia uchwyt po³¹czenia z baz¹ danych
	 * @param dbConnection Nowy uchwyt po³¹czenia z baz¹ danych
	 */
	public static void setDbConnection(Connection dbConnection) {
		Config.dbConnection = dbConnection;
	}
}
