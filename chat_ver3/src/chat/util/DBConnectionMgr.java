package chat.util;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectionMgr {
	
	public final String _URL = "jdbc:oracle:thin:@192.168.0.234:1521/orcl11";
	public final String _DRIVER = "oracle.jdbc.driver.OracleDriver";
	public final String _PW = "tiger";
	
	public Connection con = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	
	public Connection getConnection(String user) throws SQLException {
		
		try {
			Class.forName(_DRIVER);
			con = DriverManager.getConnection(_URL, user, _PW);
		} catch (ClassNotFoundException e) {
			System.out.println("DB 연결 실패"+e);
		}
		
		return con;
	}
	
}
