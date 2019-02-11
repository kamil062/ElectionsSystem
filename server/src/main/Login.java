package main;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.Net;

public class Login {
	private String login = null;
	private String password = null;
	private int state = -1;
	
	private int[] executeDbQuery(){
		CallableStatement cs = null;
		int[] ret = {-1, 0};
		
		try {
			cs = Config.getDbConnection().prepareCall("{call loginUser(?,?,?,?)}");
			cs.setString(1, this.login);
			cs.setString(2, this.password);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.registerOutParameter(4, Types.INTEGER);
			cs.executeUpdate();

			ret[0] = cs.getInt(3);
			ret[1] = cs.getInt(4);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ret;
	}
	
	public void login(){
		this.login = Net.receiveData();
		this.password = Net.receiveData();
		
		int[] ret = executeDbQuery();
		
		setState(ret[0]);

		Net.sendData(Integer.toString(getState()));
		if(getState() > 0)
			Net.sendData(Integer.toString(ret[1]));
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
