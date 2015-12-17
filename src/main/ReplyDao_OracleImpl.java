package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReplyDao_OracleImpl implements ReplyDao{

	@Override
	public List<ReplyVO> findbyBn(int board_no) throws Exception {
		List<ReplyVO> ls = new ArrayList<ReplyVO>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"hr","oracle");
			stmt = conn.createStatement();
			System.out.println("test12"+board_no);
			String sql = "SELECT * from reply where board_no="+board_no;
			
			rs = stmt.executeQuery(sql);
			while( rs.next() ){
				ReplyVO vo = new ReplyVO();
				vo.setNo( rs.getInt(1) );
				vo.setBang_no(rs.getInt(2));
				vo.setUserId( rs.getString(3) );
				vo.setText(rs.getString(4));
				ls.add( vo );
			}
		}
		catch( Exception e ){ throw e; }
		finally{
			if( rs != null ){ rs.close(); } 
			if( stmt != null ){ stmt.close(); } 
			if( conn != null ){ conn.close(); }
		}
		return ls;
	}

	@Override
	public int add(ReplyVO pvo) throws Exception {
		
			Connection conn = null;
			Statement stmt = null;
			int rc = 0;
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"hr","oracle");
				stmt = conn.createStatement();
			
				
				
				String sql = "INSERT INTO reply VALUES "+
					"(SEQ_REP.NEXTVAL,'"+pvo.getBang_no() +"','"+ pvo.getUserId() +"','"+ 
					pvo.getText() +"')";
				rc = stmt.executeUpdate(sql);
			}
			catch( Exception e ){ throw e; }
			finally{
				if( stmt != null ){ stmt.close(); } 
				if( conn != null ){ conn.close(); }
			}
		return rc;
	}



	@Override
	public int delete(int number) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		int rc = 0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:XE",
				"hr","oracle");
			stmt = conn.createStatement();
			
			String sql="delete from reply where no="+number;
			
			rc = stmt.executeUpdate(sql);
		}
		catch( Exception e ){ throw e; }
		finally{
			if( stmt != null ){ stmt.close(); } 
			if( conn != null ){ conn.close(); }
		}
		return rc;
	}

	public int updateReply(int number, String text) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		int rc = 0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:XE",
				"hr","oracle");
			stmt = conn.createStatement();
			String sql="update reply set text='"+text+"' where no="+number;
			
	
			rc = stmt.executeUpdate(sql);
		}
		catch( Exception e ){ throw e; }
		finally{
			if( stmt != null ){ stmt.close(); } 
			if( conn != null ){ conn.close(); }
		}
		return rc;
	}

}












