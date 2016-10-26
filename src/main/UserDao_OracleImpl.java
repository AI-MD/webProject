package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao_OracleImpl implements UserDao{

	/*	�Ʒ��� findByPk �� ���� ��� ROW �� �ϳ��� ������ ���̽�
	 * 	����� �ϳ��� ������ ����� �Լ� ����Ÿ���� List �ƴ� VO
	 * 
	 * 	������ ������ ��� ���ڵ尡 2�� �̻� ? ���ܸ� �߻���Ų��
	 * 	id �� ������ ��� ���ڵ尡 0�� �ε�
	 * 		������ �߻����Ѿ� �ϳ�? - spring ������ �׷��� �Ѵ�.
	 * 		null �� return �ؾ� �ϳ�? - IBatis ������ �׷��� �Ѵ�.
	 * 		��Ȯ�� ��Ģ�� ������ �ٸ� �ϰ����� �־�� �Ѵ�.
	 * 	"�츮�� ��Ģ�� null �� ���ϵǴ� ���� �����Ѵ�."
	 */
	@Override
	public UserVO findByPk(String userId) throws Exception {
		UserVO vo = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"hr","oracle");
			stmt = conn.createStatement();
			String sql = "SELECT * FROM User4T WHERE user_id = '"+userId+"'";
			rs = stmt.executeQuery(sql);
			
			if( rs.next() ){
				vo = new UserVO();
				vo.setUserId( rs.getString(1) );
				vo.setPasswd( rs.getString(2) );
				vo.setPoint( rs.getInt(3) );
				vo.setUserName( rs.getString(4) );
				vo.setPhoto( rs.getString(5) );
				vo.setAuth(rs.getString(6));
				if( rs.next() ){
					throw new SQLException("2�� �̻��� ��� ���ڵ�");
				}
			}
		}
		catch( Exception e ){ throw e; }
		finally{
			if( rs != null ){ rs.close(); } 
			if( stmt != null ){ stmt.close(); } 
			if( conn != null ){ conn.close(); }
		}
		return vo;
	}
	@Override
	public List<UserVO> findAll() throws Exception {
		UserVO vo = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<UserVO> ls=new ArrayList<UserVO>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"hr","oracle");
			stmt = conn.createStatement();
			String sql = "SELECT * FROM User4T";
			rs = stmt.executeQuery(sql);
			
			while( rs.next() ){
				vo = new UserVO();
				vo.setUserId( rs.getString(1) );
				vo.setPasswd( rs.getString(2) );
				vo.setPoint( rs.getInt(3) );
				vo.setUserName( rs.getString(4) );
				vo.setPhoto( rs.getString(5) );
				vo.setAuth(rs.getString(6));
				ls.add(vo);
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
	public int add(UserVO pvo) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		int rc = 0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"hr","oracle");
			stmt = conn.createStatement();
			String sql = "INSERT INTO User4T VALUES ('"+
				pvo.getUserId() +"','"+
				pvo.getPasswd() +"',1000,'"+
				pvo.getUserName() +"','"+pvo.getPhoto()+"','0001')";
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
	public int movePoint(int no, String downer) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		int rc = 0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"hr","oracle");
			conn.setAutoCommit( false );
			stmt = conn.createStatement();
			
			String l = "UPDATE User4T SET point = point + 50 "+
				"WHERE user_id = ( SELECT user_id FROM "+
				"Bang5T WHERE no = "+ no +")";
			String t = "UPDATE User4T SET point = point - 50 "+
				"WHERE user_id = '"+ downer +"'";			
			int a = stmt.executeUpdate( l );
			int b = stmt.executeUpdate( t );
			conn.commit();
			rc = ( a + b );
		}
		catch( Exception e ){
			if( conn != null ){ conn.rollback(); }
			throw e; 
		}
		finally{
			if( stmt != null ){ stmt.close(); } 
			if( conn != null ){ conn.close(); }
		}
		return rc;
	}
}
