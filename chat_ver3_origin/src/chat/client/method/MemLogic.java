package chat.client.method;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import chat.util.DBConnectionMgr;
import chat.util.MemVO;

public class MemLogic {

	public static MemVO mvo=new MemVO();
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;	
	DBConnectionMgr dbMgr = new DBConnectionMgr();
	CallableStatement cstmt=null;
	PreparedStatement pstmt =null;

	public int Login(String p_Id, String p_Pw) {
		
		MemVO tempMvo = new MemVO();
		
		try {
			
			con = dbMgr.getConnection("chat_ver2");
			String sql = "Select USER_ID, USER_PW, USER_NAME, USER_EMAIL, USER_PHONE, USER_PROFILE From chat_user where USER_ID =?";
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, p_Id);
			rs = cstmt.executeQuery();
			
			while(rs.next()) {
				tempMvo.setM_ID(rs.getString("USER_ID"));
				tempMvo.setM_pw(rs.getString("USER_PW"));
				tempMvo.setM_name(rs.getString("USER_NAME"));
				tempMvo.setM_email(rs.getString("USER_EMAIL"));
				tempMvo.setM_phone(rs.getString("USER_PHONE"));
				tempMvo.setPath(rs.getString("USER_PROFILE"));
			}
			
			if(tempMvo.getM_ID()==null) {
				return 0;
			} else if(tempMvo.getM_pw().equals(p_Pw)) {
				MemLogic.mvo = tempMvo;
				return 1;
			}
			else {	
				return 2;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	
	}
	
	public int MemJoin(String m_ID,String m_pw,String m_name, String m_phone, String m_email, String m_profile) {
		
		int result=0;
		
		try {
			
			con = dbMgr.getConnection("chat_ver2");
			
			cstmt = con.prepareCall("{call proc_join(?,?,?,?,?,?)}");
			cstmt.setString(1, m_ID);
			cstmt.setString(2, m_pw);
			cstmt.setString(3, m_name);
			cstmt.setString(4, m_phone);
			cstmt.setString(5, m_email);
			cstmt.setString(6, m_profile);
			result = cstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean memIdCheck(String p_Id) {
			
		try {
			
			con = dbMgr.getConnection("chat_ver2");
			
			String sql = "Select USER_ID From chat_user where USER_ID =?";
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, p_Id);
			rs = cstmt.executeQuery();
			rs.next();
			
			if(rs.getString("USER_ID")!=null)
				return true;
			else
				return false;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}
	
	public int ProfileUpd(String p_id,String path)  {
		
		int result=0;
		
		try {
			
			con = dbMgr.getConnection("chat_ver2");
			
			cstmt = con.prepareCall("{call proc_profile(?,?)}");
			cstmt.setString(1, p_id);
			cstmt.setString(2, path);
			result = cstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	
	}
	
}
