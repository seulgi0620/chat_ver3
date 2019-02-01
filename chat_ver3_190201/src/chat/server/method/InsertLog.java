package chat.server.method;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import chat.util.DBConnectionMgr;

public class InsertLog {

	Connection con;
	CallableStatement cstmt1 = null;
	int resInt = 0;
	ResultSet srs = null;
	
	public void ins_log(String chatroom_code, String protocol, String user_id, String message, String log_time) {
		
		DBConnectionMgr dbc = new DBConnectionMgr();
		
		try {
			
			con = dbc.getConnection("chat_ver2");
			cstmt1 = con.prepareCall("{call proc_insert_log(?,?,?,?,?)}");
			cstmt1.setString(1, protocol);
			cstmt1.setString(2, message);
			cstmt1.setString(3, user_id);
			cstmt1.setString(4, chatroom_code);
			cstmt1.setString(5, log_time);

			cstmt1.executeUpdate();
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
