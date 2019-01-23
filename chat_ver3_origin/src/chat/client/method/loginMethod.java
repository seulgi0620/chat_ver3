package chat.client.method;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import chat.util.DBConnectionMgr;

public class loginMethod {

	Connection con;
	CallableStatement cstmt1 = null;
	int resInt = 0;
	ResultSet srs = null;
	
	String resName = "";
	
	public String accessMain(String id, String pw) {
		
		DBConnectionMgr dbc = new DBConnectionMgr();
		
		try {
			
			con = dbc.getConnection("chat_ver2");
			cstmt1 = con.prepareCall("{call proc_login(?,?,?)}");
			cstmt1.setString(1, id);
			cstmt1.setString(2, pw);
			cstmt1.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt1.execute();
			
			resName = cstmt1.getString(3);
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resName;
		
	}
	
}
