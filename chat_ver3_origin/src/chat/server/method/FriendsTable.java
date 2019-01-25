package chat.server.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import chat.client.view.FriendsPage;
import chat.util.DBConnectionMgr;
import chat.util.MyfriendsListVO;

public class FriendsTable {
	DBConnectionMgr dbMgr = new DBConnectionMgr();
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

	FriendsPage fp = null;

	public Vector<MyfriendsListVO> search (String m_ID) {
//		this.fp = fp;
		//fp.remove(fp.searchScroll);
//		fp.add(fp.jp_myfriends);
//		
//		int row = fp.dtm_myfriends.getRowCount();
//		
//		if(row>0) {
//			for (int i = row- 1; i >= 0; i--) {
//				fp.dtm_myfriends.removeRow(i);
//			}}
		//MyfriendsListVO mflvo = new MyfriendsListVO();
		Vector<MyfriendsListVO> v_mflvo = new Vector<MyfriendsListVO>();
		try {
			
			conn = dbMgr.getConnection("chat_ver2");
			
			String sql = "select friend_id from chat_friend where user_id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, m_ID);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				MyfriendsListVO mflvo = new MyfriendsListVO();
				String friend_name = rs.getString("friend_id");
				mflvo.setFriendName(friend_name);
				v_mflvo.add(mflvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v_mflvo;
	}

	
}
