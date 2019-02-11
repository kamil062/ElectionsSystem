package main;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import net.Net;

public class Vote {
	public static void canUserVote(){
		int userId = Integer.parseInt(Net.receiveData());
		
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = Config.getDbConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery("SELECT count(*) FROM parliamentaryVotes WHERE usr = " + userId);
			
			rs.next();
			Net.sendData(Integer.toString(rs.getInt(1)));
			
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
	
	public static void getPresidentialElectionsCandidates(){
		int electionsId = Integer.parseInt(Net.receiveData());
		int electionsType = Integer.parseInt(Net.receiveData());
		
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = Config.getDbConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery("SELECT pc.id, u.name, u.surname, p.name FROM presidentialCandidates pc, candidates c, users u, parties p " +
								"WHERE pc.candidate = c.id AND c.usr = u.id AND c.party = p.id AND pc.elections = " + electionsId);
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
				Net.sendData(rs.getString(2));
				Net.sendData(rs.getString(3));
				Net.sendData(rs.getString(4));
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
	
	public static void getParliamentaryElectionsCandidates(){
		int electionsId = Integer.parseInt(Net.receiveData());
		int electionsType = Integer.parseInt(Net.receiveData());
		
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = Config.getDbConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery("SELECT pc.id, u.name, u.surname, p.name FROM parliamentaryCandidates pc, candidates c, users u, parties p " +
								"WHERE pc.candidate = c.id AND c.usr = u.id AND c.party = p.id AND pc.elections = " + electionsId);
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
				Net.sendData(rs.getString(2));
				Net.sendData(rs.getString(3));
				Net.sendData(rs.getString(4));
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
	
	public static void getSenateElectionsCandidates(){
		int electionsId = Integer.parseInt(Net.receiveData());
		int electionsType = Integer.parseInt(Net.receiveData());
		
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = Config.getDbConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery("SELECT pc.id, u.name, u.surname, p.name FROM sentateCandidates pc, candidates c, users u, parties p " +
								"WHERE pc.candidate = c.id AND c.usr = u.id AND c.party = p.id AND pc.elections = " + electionsId);
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
				Net.sendData(rs.getString(2));
				Net.sendData(rs.getString(3));
				Net.sendData(rs.getString(4));
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
	
	public static void voteInPresidentialElections(){
		int electionsId = Integer.parseInt(Net.receiveData());
		int userId = Integer.parseInt(Net.receiveData());
		int candidateId = Integer.parseInt(Net.receiveData());
		
		CallableStatement cs = null;
		
		try {
			cs = Config.getDbConnection().prepareCall("{call addPresidentialVote(?,?,?)}");
			cs.setInt(1, electionsId);
			cs.setInt(2, userId);
			cs.setInt(3, candidateId);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void voteInParliamentaryElections(){
		int electionsId = Integer.parseInt(Net.receiveData());
		int userId = Integer.parseInt(Net.receiveData());
		int candidateId = Integer.parseInt(Net.receiveData());
		
		CallableStatement cs = null;
		
		try {
			cs = Config.getDbConnection().prepareCall("{call addParliamentaryVote(?,?,?)}");
			cs.setInt(1, electionsId);
			cs.setInt(2, userId);
			cs.setInt(3, candidateId);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void voteInSenateElections(){
		int electionsId = Integer.parseInt(Net.receiveData());
		int userId = Integer.parseInt(Net.receiveData());
		int candidateId = Integer.parseInt(Net.receiveData());
		
		CallableStatement cs = null;
		
		try {
			cs = Config.getDbConnection().prepareCall("{call addSenateVote(?,?,?)}");
			cs.setInt(1, electionsId);
			cs.setInt(2, userId);
			cs.setInt(3, candidateId);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
