package chat.client.method;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import chat.util.DBConnectionMgr;

public class LastRead {
	
	Connection con;
	CallableStatement cstmt1 = null;
	int resInt = 0;
	ResultSet srs = null;
	
	public void updateLastRead(String user_id, String chatroom_code) {
		
		DBConnectionMgr dbc = new DBConnectionMgr();
		
		try {
			
			con = dbc.getConnection("chat_ver2");
			cstmt1 = con.prepareCall("{call proc_lastread_ins(?,?)}");
			cstmt1.setString(1, user_id);
			cstmt1.setString(2, chatroom_code);
			cstmt1.executeUpdate();
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}