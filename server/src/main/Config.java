package main;

import java.sql.Connection;

/**
 * Klasa konfiguracyjna serwera
 * 
 * @author "Kamil Piec"
 *
 */
public class Config {
	/** Port, na kt�rym b�dzie dzia�a� serwer */
	private static int serverPort = 1234;

	/** Adres URL do po��czenia z baz� danych */
	private static String DBurl = "jdbc:oracle:thin:@localhost:1521/xe";
	/** Nazwa u�ytkownika bazy danych */
	private static String DBuser = "projektip";
	/** Has�o u�ytkownika bazy danych */
	private static String DBpassword = "root";
	/** Uchwyt po��czenia z baz� danych */
	private static Connection dbConnection;

	/**
	 * Metoda zwraca port serwera
	 * @return Porst serwera
	 */
	public static int getServerPort() {
		return serverPort;
	}

	/**
	 * Metoda pozwala zmieni� port serwera
	 * @param serverPort Port serwera
	 */
	public static void setServerPort(int serverPort) {
		Config.serverPort = serverPort;
	}
	
	/**
	 * Metoda zwraca adres URL do po��czenia z baz� danych
	 * @return Adres URL
	 */
	public static String getDBurl() {
		return DBurl;
	}

	/**
	 * Metoda pozwala zmieni� adres URL do po��czenia z baz� danych
	 * @param dBurl Nowy adres URL
	 */
	public static void setDBurl(String dBurl) {
		DBurl = dBurl;
	}

	/**
	 * Metoda zwraca nazw� u�ytkownika bazy danych
	 * @return Nazwa u�ytkownika bazy danych
	 */
	public static String getDBuser() {
		return DBuser;
	}

	/**
	 * Metoda pozwala zmieni� nazw� u�ytkownika bazy danych
	 * @param dBuser Nowa nazwa u�ytkownika bazy danych
	 */
	public static void setDBuser(String dBuser) {
		DBuser = dBuser;
	}

	/**
	 * Metoda zwraca has�o u�ytkownika bazy danych
	 * @return Has�o uzytkownika bazy danych
	 */
	public static String getDBpassword() {
		return DBpassword;
	}

	/**
	 * Metoda pozwala zmieni� has�o u�ytkownika bazy danych
	 * @param dBpassword Nowe has�o u�ytkownika bazy danych
	 */
	public static void setDBpassword(String dBpassword) {
		DBpassword = dBpassword;
	}

	/**
	 * Metoda zwraca uchwyt po��czenia z baz� danych
	 * @return Uchwyt po��czenia z baz� danych
	 */
	public static Connection getDbConnection() {
		return dbConnection;
	}

	/**
	 * Metoda zmienia uchwyt po��czenia z baz� danych
	 * @param dbConnection Nowy uchwyt po��czenia z baz� danych
	 */
	public static void setDbConnection(Connection dbConnection) {
		Config.dbConnection = dbConnection;
	}
}
