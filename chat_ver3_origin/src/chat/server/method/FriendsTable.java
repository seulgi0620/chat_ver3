package chat.server.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import chat.client.view.FriendsPage;
import chat.util.DBConnectionMgr;

public class FriendsTable {
	DBConnectionMgr dbMgr = new DBConnectionMgr();
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

	FriendsPage fp = null;

	public FriendsTable(FriendsPage fp,String m_ID) {
		this.fp = fp;
		fp.remove(fp.searchScroll);
		fp.add(fp.myfriendsScroll);
		
		int row = fp.dtm_myfriends.getRowCount();
		
		if(row>0) {
			for (int i = row- 1; i >= 0; i--) {
				fp.dtm_myfriends.removeRow(i);
			}}
		
		try {
			
			conn = dbMgr.getConnection("chat_ver2");
			
			String sql = "select friend_id from chat_friend where user_id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, m_ID);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				Vector<String> v_f = new Vector<String>();
				String friend_name = rs.getString("friend_id");
				v_f.add(friend_name);
				fp.dtm_myfriends.addRow(v_f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
}
