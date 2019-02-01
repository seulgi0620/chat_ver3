package chat.server.method;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import chat.util.DBConnectionMgr;
import chat.util.ParticipentVO;

public class SelectParticipentList {

	Connection con;
	CallableStatement cstmt1 = null;
	int resInt = 0;
	ResultSet srs = null;
	
	public Vector<ParticipentVO> selectChatList(String chatroom_code) {
		
		DBConnectionMgr dbc = new DBConnectionMgr();

		Vector<String> vc = new Vector<String>();
		ParticipentVO ptvo = new ParticipentVO();
		Vector<ParticipentVO> vc_ptvo = new Vector<ParticipentVO>();
		
		try {
			
			con = dbc.getConnection("chat_ver2");
			cstmt1 = con.prepareCall("{call proc_select_participent_list(?,?)}");
			cstmt1.setString(1, chatroom_code);
			cstmt1.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cstmt1.execute();
			srs = (ResultSet) cstmt1.getObject(2);
			while(srs.next()) {
				vc.add(srs.getString("user_id"));
			}
			
			ptvo.setChatroom_code(chatroom_code);
			ptvo.setVc(vc);
			vc_ptvo.add(ptvo);
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vc_ptvo;
		
	}
	
}