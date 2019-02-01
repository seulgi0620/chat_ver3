package chat.server.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import chat.client.view.FriendsPage;
import chat.util.DBConnectionMgr;
import chat.util.SearchTableVO;

public class SearchTable {

	DBConnectionMgr dbMgr = new DBConnectionMgr();
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	FriendsPage fp = null;
	
	public Vector<SearchTableVO> search (String user_id, String s_ID) {
//		this.fp = fp;
//		
//		fp.remove(fp.myfriendsScroll);
//		fp.add(fp.searchScroll);
//		
//		int row = fp.dtm_search.getRowCount();
//		if (row > 0) {
//			for (int i = row - 1; i >= 0; i--) {
//				fp.dtm_search.removeRow(i);
//			}
//		}
		Vector<String> v_s = new Vector<String>();
		SearchTableVO stbvo = new SearchTableVO();
		Vector<SearchTableVO> v_stbvo = new Vector<SearchTableVO>();
		try {

			conn = dbMgr.getConnection("chat_ver2");

			String sql = "  select user_id, user_name from chat_user  " + "  where user_id like '%' || ? || '%'  "
					+ "  and user_id != ?  " + "  minus  " + "  select friend_id , friend_name from chat_friend "
					+ "  where user_id= ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, s_ID);
			pstm.setString(2, user_id);
			pstm.setString(3, user_id);
			rs = pstm.executeQuery();

			while (rs.next()) {
				String s_user_id = rs.getString("user_id");
				String user_name = rs.getString("user_name");
				v_s.add(s_user_id);
				v_s.add(user_name);
				//v_s.add("|");
				//stbvo.setUser_id(s_user_id);
				//stbvo.setUser_name(user_name);
				
				//fp.dtm_search.addRow(v_s);
			}
			stbvo.setV_s(v_s);
			v_stbvo.add(stbvo);

		} catch (SQLException sqle) {
			System.out.println("SELECT문에서 예외 발생");
			sqle.printStackTrace();
		}
		return v_stbvo;
		
	}
	
	
}
