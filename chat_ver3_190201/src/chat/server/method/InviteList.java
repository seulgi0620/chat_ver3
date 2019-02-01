package chat.server.method;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import chat.util.DBConnectionMgr;
import chat.util.InviteListVO;

public class InviteList {

	CallableStatement cstmt = null;
	Connection con = null;
	DBConnectionMgr dbMgr = new DBConnectionMgr();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void invite_friend(String choice, String chatroom_code) {
		
		try {
			
			con = dbMgr.getConnection("chat_ver2");
			
			cstmt = con.prepareCall("{Call proc_chatinv2(?,?)}");
			cstmt.setString(1, choice);
			cstmt.setString(2, chatroom_code);

			cstmt.executeUpdate();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
	public Vector<InviteListVO> invite_friend_list(String user_id, String chatroom_code) {
		
		Vector<String> v = new Vector<String>();
		InviteListVO ilvo = new InviteListVO();
		Vector<InviteListVO> vc_ilvo = new Vector<InviteListVO>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select friend_id from chat_friend where user_id = ? ");
		sql.append("  minus  "); 
		sql.append("  select user_id from participent_list where chatroom_code = ? ");

		try {
			
			con = dbMgr.getConnection("chat_ver2");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			pstmt.setString(2, chatroom_code);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				v.add(rs.getString("friend_id"));
			}
			
			ilvo.setChatroom_code(chatroom_code);
			ilvo.setVc(v);
			vc_ilvo.add(ilvo);
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vc_ilvo;
		
	}
	
}
