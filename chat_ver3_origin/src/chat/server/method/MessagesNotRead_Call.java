package chat.server.method;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import chat.util.ChatroomListVO;
import chat.util.DBConnectionMgr;

public class MessagesNotRead_Call {
	
	Connection con;
	CallableStatement cstmt1 = null;
	int resInt = 0;
	ResultSet srs = null;
	
	public Vector<ChatroomListVO> selectMsgCount(String user_id, Vector<String> chatroom_list) {
		
		Vector<ChatroomListVO> vc = new Vector<ChatroomListVO>();
		DBConnectionMgr dbc = new DBConnectionMgr();
		
		try {
			
			con = dbc.getConnection("chat_ver2");
			cstmt1 = con.prepareCall("{call proc_msg_not_read_cnt_ver2(?,?)}");
			cstmt1.setString(1, user_id);
			cstmt1.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cstmt1.execute();
			srs = (ResultSet) cstmt1.getObject(2);
			
			for(int i=0;i<chatroom_list.size();i++) {
				srs.next();
				ChatroomListVO clvo = new ChatroomListVO();
				clvo.setChatroom_code(srs.getString("chatroom_code"));
				clvo.setMax_msg(srs.getString("LAST_MSG"));
				clvo.setMax_log_time(srs.getString("LAST_LOG_TIME"));
				clvo.setMsg_not_read(srs.getInt("NOT_READ_CNT"));
				clvo.setlast_code(srs.getInt("LAST_MSG_CODE"));
				vc.add(clvo);
			}
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vc;
		
	}
	
}