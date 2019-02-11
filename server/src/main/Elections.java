package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.Net;

public class Elections {
	public static void getElections(){
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = Config.getDbConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			           ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery("SELECT id, type, dateStarted, dateEnded, sysdate FROM elections ORDER BY dateStarted DESC");
			
			int size= 0;
			if (rs != null) {  
			  rs.beforeFirst();  
			  rs.last();  
			  size = rs.getRow(); 
			  rs.beforeFirst(); 
			}  
			
			Net.sendData(Integer.toString(size));
			while(rs.next()){
				Net.sendData(Integer.toString(rs.getInt(1)));
				Net.sendData(Integer.toString(rs.getInt(2)));
				Net.sendData(rs.getString(3));
				Net.sendData(rs.getString(4));
				Net.sendData(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void getInfoAboutElections(){
		int electionsId = Integer.parseInt(Net.receiveData());
		
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = Config.getDbConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery("SELECT id, type, dateStarted, dateEnded, sysdate FROM elections WHERE id = " + electionsId);
			
			while(rs.next()){
				Net.sendData(Integer.toString(rs.getInt(1)));
				Net.sendData(Integer.toString(rs.getInt(2)));
				Net.sendData(rs.getString(3));
				Net.sendData(rs.getString(4));
				Net.sendData(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
