package chat.server.method;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chat.util.DBConnectionMgr;

public class ExitChatroom {
	
	Connection con;
	CallableStatement cstmt1 = null;
	int resInt = 0;
	ResultSet srs = null;
	
	Connection con2;
	PreparedStatement cstmt2 = null;
	int resInt2 = 10;
	ResultSet srs2 = null;
	
	Connection con3;
	CallableStatement cstmt3 = null;
	int resInt3 = 0;
	ResultSet srs3 = null;
	
	Connection con4;
	CallableStatement cstmt4 = null;
	int resInt4 = 0;
	ResultSet srs4 = null;
	
	public int exit_chatroom(String user_id, String chatroom_code) {
	
		DBConnectionMgr dbc = new DBConnectionMgr();
		DBConnectionMgr dbc2 = new DBConnectionMgr();
		
		try {
			con = dbc.getConnection("chat_ver2");
			cstmt1 = con.prepareCall("{call proc_leave_chatroom(?,?)}");
			cstmt1.setString(1, user_id);
			cstmt1.setString(2, chatroom_code);
			cstmt1.executeUpdate();
			
			con.close();
			
			con2  = dbc2.getConnection("chat_ver2");
			cstmt2 = con2.prepareStatement("select count(*) from participent_list where chatroom_code = ?");
			cstmt2.setString(1, chatroom_code);
			srs2 = cstmt2.executeQuery();
			srs2.next();
			resInt2 = srs2.getInt(1);
					
			con2.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resInt2;
	}
	
	public void delete_chatroom(String chatroom_code) {
		
		DBConnectionMgr dbc3 = new DBConnectionMgr();
		DBConnectionMgr dbc4 = new DBConnectionMgr();
		
		try {
	
			if(resInt2 == 0) {
				
				con4 = dbc4.getConnection("chat_ver2");
				cstmt4 = con4.prepareCall("{call proc_delete_chat_log(?)}");
				cstmt4.setString(1, chatroom_code);
				cstmt4.executeUpdate();
				
				con4.close();
				
				con3 = dbc3.getConnection("chat_ver2");
				cstmt3 = con3.prepareCall("{call proc_delete_chatroom(?)}");
				cstmt3.setString(1, chatroom_code);
				cstmt3.executeUpdate();
				
				con3.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}