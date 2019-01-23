package chat.server.method;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import chat.util.DBConnectionMgr;

public class SelectChatList {
	
	Connection con;
	CallableStatement cstmt1 = null;
	int resInt = 0;
	ResultSet srs = null;
	
	public Vector<String> selectChatList(String user_id) {
		
		DBConnectionMgr dbc = new DBConnectionMgr();
		Vector<String> vc   = new Vector<String>();
		
		try {
			
			con = dbc.getConnection("chat_ver2");
			cstmt1 = con.prepareCall("{call proc_select_chatroom_list(?,?)}");
			cstmt1.setString(1, user_id);
			cstmt1.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cstmt1.execute();
			srs = (ResultSet) cstmt1.getObject(2);
			while(srs.next()) {
				vc.addElement(srs.getString("chatroom_code"));
			}
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vc;
		
	}
	
}
