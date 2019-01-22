package chat.client.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import chat.client.view.FriendsPage;
import chat.util.DBConnectionMgr;

public class SearchAddFriend {
	DBConnectionMgr dbMgr = new DBConnectionMgr();
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	FriendsPage fp = null;
	
	public SearchAddFriend(FriendsPage fp) {
		this.fp = fp;
		
		
		int row = fp.jt_search.getSelectedRow();
		Object value = fp.jt_search.getValueAt(row, 0);
		String svalue = (String) value;
		System.out.println(svalue);
		try {
			conn = dbMgr.getConnection("chat_ver2");
			String sql = "select user_id, user_name from chat_user  " + "  where user_id = ?  ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, svalue);
			rs = pstm.executeQuery();

			Vector<String> v_s = new Vector<String>();
			String user_id = null;
			String user_name = null;

			while (rs.next()) {
				user_id = rs.getString("user_id");
				user_name = rs.getString("user_name");
				v_s.add(user_id);
				v_s.add(user_name);
			}

			String sql1 = "insert into chat_friend (friend_id, friend_name, user_id) " + "values (?,?,?)";
			pstm = conn.prepareStatement(sql1);
			pstm.setString(1, user_id);
			pstm.setString(2, user_name);
			pstm.setString(3, fp.user_id);
			pstm.executeUpdate();

			new FriendsTable(fp,fp.user_id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
