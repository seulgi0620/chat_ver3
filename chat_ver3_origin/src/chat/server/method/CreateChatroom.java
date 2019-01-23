package chat.server.method;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import chat.util.DBConnectionMgr;

public class CreateChatroom {

	Boolean flag_user = false;
	String chatroom_code = null;
	
	Connection con;
	CallableStatement cstmt1 = null;
	int resInt = 0;
	ResultSet srs = null;
	
	Connection con2;
	PreparedStatement cstmt2 = null;
	int resInt2 = 0;
	ResultSet srs2 = null;
	
	public String create_chatroom(String user_id, String protocol, String message, String friend_id) {
		
		DBConnectionMgr dbc = new DBConnectionMgr();
		DBConnectionMgr dbc2 = new DBConnectionMgr();
		
		try {
			if(!flag_user) {
				con = dbc.getConnection("chat_ver2");
				cstmt1 = con.prepareCall("{call proc_create_chatroom(?,?,?,?)}");
				cstmt1.setString(1, protocol);
				cstmt1.setString(2, message);
				cstmt1.setString(3, user_id);
				cstmt1.registerOutParameter(4, java.sql.Types.VARCHAR);
				cstmt1.executeUpdate();
				chatroom_code = cstmt1.getString(4);

				con.close();
				flag_user = true;
			} else {

			}
			
			con2  = dbc2.getConnection("chat_ver2");
			cstmt2 = con2.prepareStatement("insert into participent_list(chatroom_code, user_id, last_log) values(?,?,?)");
			cstmt2.setString(1, chatroom_code);
			cstmt2.setString(2, friend_id);
			cstmt2.setInt(3, 0);
			cstmt2.executeUpdate();

			con2.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return chatroom_code;
	}
	
}
