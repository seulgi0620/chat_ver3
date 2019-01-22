package chat.client.method;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import chat.client.view.TalkClient;
import chat.util.DBConnectionMgr;

public class SelectLog {

	Connection con;
	CallableStatement cstmt1 = null;
	int resInt = 0;
	ResultSet srs = null;
	
	public void sel_log(TalkClient tc) {
		
		DBConnectionMgr dbc = new DBConnectionMgr();
		
		try {
			
			con = dbc.getConnection("chat_ver2");
			cstmt1 = con.prepareCall("{call proc_select_log(?,?,?)}");
			cstmt1.setString(1, tc.chatroom_code);
			cstmt1.setString(2, tc.user_id);
			cstmt1.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
			cstmt1.execute();
			srs = (ResultSet) cstmt1.getObject(3);
			while(srs.next()) {
				tc.jta_display.append(srs.getString("user_id")+" : ");
				tc.jta_display.append(srs.getString("msg")+" (");
				tc.jta_display.append(srs.getString("tt")+")\n");
			}
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
