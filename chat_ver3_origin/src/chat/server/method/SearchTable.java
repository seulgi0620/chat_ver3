package chat.server.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import chat.client.view.FriendsPage;
import chat.util.DBConnectionMgr;

public class SearchTable {

	DBConnectionMgr dbMgr = new DBConnectionMgr();
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

	FriendsPage fp = null;
	
	public SearchTable(FriendsPage fp) {
		this.fp = fp;
		
		fp.remove(fp.myfriendsScroll);
		fp.add(fp.searchScroll);
		
		int row = fp.dtm_search.getRowCount();
		if (row > 0) {
			for (int i = row - 1; i >= 0; i--) {
				fp.dtm_search.removeRow(i);
			}
		}
				
		
		try {

			conn = dbMgr.getConnection("chat_ver2");

			String sql = "  select user_id, user_name from chat_user  " + "  where user_id like '%' || ? || '%'  "
					+ "  and user_id != ?  " + "  minus  " + "  select friend_id , friend_name from chat_friend "
					+ "  where user_id= ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, fp.tf_search.getText());
			pstm.setString(2, fp.user_id);
			pstm.setString(3, fp.user_id);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Vector<String> v_s = new Vector<String>();
				String fuser_id = rs.getString("user_id");
				String user_name = rs.getString("user_name");
				v_s.add(fuser_id);
				v_s.add(user_name);
				fp.dtm_search.addRow(v_s);
				
			}
			

		} catch (SQLException sqle) {
			System.out.println("SELECT문에서 예외 발생");
			sqle.printStackTrace();
		}
	}
}
