package chat.server.method;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import chat.server.method.TalkServerThread;
import chat.util.ChatLogVO;
import chat.util.DBConnectionMgr;

public class SelectLog {

	Connection con;
	CallableStatement cstmt1 = null;
	int resInt = 0;
	ResultSet srs = null;
	
	public Vector<ChatLogVO> sel_log(TalkServerThread tst) {
		
		DBConnectionMgr dbc = new DBConnectionMgr();
		
		Vector<ChatLogVO> vc = new Vector<ChatLogVO>();
		
		try {
			
			con = dbc.getConnection("chat_ver2");
			cstmt1 = con.prepareCall("{call proc_select_log(?,?,?)}");
			cstmt1.setString(1, tst.chatroom_code);
			cstmt1.setString(2, tst.user_id);
			cstmt1.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
			cstmt1.execute();
			srs = (ResultSet) cstmt1.getObject(3);

			while(srs.next()) {
				
				ChatLogVO cl = new ChatLogVO();
				
				cl.setChatroom_code(tst.chatroom_code);
				cl.setUser_id(srs.getString("user_id"));
				cl.setMsg(srs.getString("msg"));
				cl.setTt(srs.getString("tt"));
				
				vc.add(cl);
				
			}
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vc;
		
	}
	
}
