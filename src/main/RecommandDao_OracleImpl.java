package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecommandDao_OracleImpl implements RecommandDao{

	@Override
	public int findbyID(int board_no, String userId) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int rc=0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"hr","oracle");
			stmt = conn.createStatement();
			System.out.println("test12"+board_no);
			String sql = "SELECT count(*) from recommand where board_no="+board_no +" and user_id='"+userId+"'";
			
			rs = stmt.executeQuery(sql);
			if( rs.next() ){
				rc= rs.getInt(1);
			}
		}
		catch( Exception e ){ throw e; }
		finally{
			if( rs != null ){ rs.close(); } 
			if( stmt != null ){ stmt.close(); } 
			if( conn != null ){ conn.close(); }
		}
		return rc;
	}


}












