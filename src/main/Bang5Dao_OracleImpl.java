package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Bang5Dao_OracleImpl implements Bang5Dao{

	@Override
	public int add(Bang5VO pvo) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		int rc = 0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:XE",
				"hr","oracle");
			stmt = conn.createStatement();
		
			
			
			String sql = "INSERT INTO Bang5T VALUES "+
				"(SEQ_BANG.NEXTVAL,'"+
				pvo.getText() +"',SYSDATE,'"+
				pvo.getClientIp() + "','"+ pvo.getFsn() +
				"','"+ pvo.getOfn() +"','"+ pvo.getUserId() +"','"+ pvo.getTitle() +"',0,0)";
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
			
			String sql="delete from Bang5T where no="+number;
			
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
	public Bang5VO findByNo(int no) throws Exception {
		Bang5VO vo =null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"hr","oracle");
			stmt = conn.createStatement();
			String sql = "SELECT * From Bang5T where no="+no;
			 
			rs = stmt.executeQuery(sql);
			
			if( rs.next() ){
				vo=new Bang5VO();
				vo.setNo( rs.getInt(1) );
				vo.setText( rs.getString(2) );
				vo.setTheTime( rs.getString(3) );
				vo.setClientIp( rs.getString(4) );
				vo.setFsn( rs.getString(5) );
				vo.setOfn( rs.getString(6) );
				vo.setUserId( rs.getString(7) );
				vo.setTitle(rs.getString(8));
				
				if( rs.next() ){
					throw new SQLException("2개 이상의 결과 레코드");
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
	public int updateCount(int number) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		int rc = 0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:XE",
				"hr","oracle");
			stmt = conn.createStatement();
			String sql="update Bang5T set count=count+1 where no="+number;
			
	
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
	public int findAllCount() throws Exception {
		
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
			
			String	sql= "SELECT count(*)  FROM User4T, Bang5T  WHERE User4T.user_id = Bang5T.user_id and 0=0 ORDER BY no DESC";
			
			
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


	@Override
	public List<Bang5VO> findAll(int start, int end) throws Exception {
		List<Bang5VO> ls = new ArrayList<Bang5VO>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"hr","oracle");
			stmt = conn.createStatement();
			String sql = "SELECT * FROM( SELECT ROWNUM AS rn, a.* FROM(SELECT no, text, TO_CHAR(the_time,'YYYY-MM-DD HH24:MI:SS') as the_time, client_ip, fsn, ofn, Bang5T.user_id, photo,title, count, recommand FROM User4T, Bang5T  WHERE User4T.user_id = Bang5T.user_id and 0=0 ORDER BY no DESC  ) a   )where  rn between "+start+" and  "+end;

			
			rs = stmt.executeQuery(sql);
			while( rs.next() ){
				Bang5VO vo = new Bang5VO();
				vo.setNo( rs.getInt(2) );
				vo.setText( rs.getString(3) );
				vo.setTheTime( rs.getString(4) );
				vo.setClientIp( rs.getString(5) );
				vo.setFsn( rs.getString(6) );
				vo.setOfn( rs.getString(7) );
				vo.setUserId( rs.getString(8) );
				vo.setPhoto( rs.getString(9) );
				vo.setTitle(rs.getString(10));
				vo.setCount(rs.getInt(11));
				vo.setRecommand(rs.getInt(12));
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
	public int findSearchCount(HashMap map) throws Exception {
		String field= (String)map.get("field");
		String value=(String)map.get("searchWord");
		//System.out.println(field+"\t"+value);
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
			String sql="";
			if(field.startsWith("re")){
				field="text";
				//System.out.println(field);
				sql= "select count(*) from (SELECT bang5T.no FROM User4T, Bang5T ,reply  WHERE User4T.user_id = Bang5T.user_id and Bang5T.no=reply.board_no and 0=0  and reply."+field+" like '%"+value+"%' group by bang5T.no)";
				//System.out.println(sql);
			}else{
				sql= "SELECT count(*)  FROM User4T, Bang5T  WHERE User4T.user_id = Bang5T.user_id and 0=0  and Bang5T."+field+" like '%"+value+"%' ORDER BY no DESC";
			}
			
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


	@Override
	public List<Bang5VO> findSearch(HashMap map,int start,int end) throws Exception {
		String field= (String)map.get("field");
		String value=(String)map.get("searchWord");
		//System.out.println(field+"\t"+value);
		
		List<Bang5VO> ls = new ArrayList<Bang5VO>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"hr","oracle");
			stmt = conn.createStatement();
			String sql="";
			if(field.startsWith("re")){
				field="text";
				//System.out.println(field);
				sql = "SELECT * FROM( SELECT ROWNUM AS rn, a.* FROM(SELECT distinct bang5T.no, bang5T.text, TO_CHAR(the_time,'YYYY-MM-DD HH24:MI:SS'), client_ip, fsn, ofn, Bang5T.user_id, photo,title, count, recommand FROM User4T, Bang5T,reply  WHERE User4T.user_id = Bang5T.user_id and Bang5T.no=reply.board_no and 0=0  and reply."+field+" like '%"+value+"%'  ORDER BY bang5T.no DESC  ) a   )where  rn between "+start+" and  "+end;
				//System.out.println(sql);
			}else{
				sql = "SELECT * FROM( SELECT ROWNUM AS rn, a.* FROM(SELECT no, text, TO_CHAR(the_time,'YYYY-MM-DD HH24:MI:SS'), client_ip, fsn, ofn, Bang5T.user_id, photo,title, count, recommand FROM User4T, Bang5T  WHERE User4T.user_id = Bang5T.user_id and 0=0  and Bang5T."+field+" like '%"+value+"%' ORDER BY no DESC  ) a   )where  rn between "+start+" and  "+end;
			}
			
			rs = stmt.executeQuery(sql);
			while( rs.next() ){
				Bang5VO vo = new Bang5VO();
				vo.setNo( rs.getInt(2) );
				vo.setText( rs.getString(3) );
				vo.setTheTime( rs.getString(4) );
				vo.setClientIp( rs.getString(5) );
				vo.setFsn( rs.getString(6) );
				vo.setOfn( rs.getString(7) );
				vo.setUserId( rs.getString(8) );
				vo.setPhoto( rs.getString(9) );
				vo.setTitle(rs.getString(10));
				vo.setCount(rs.getInt(11));
				vo.setRecommand(rs.getInt(12));
				ls.add( vo );
			}
			System.out.println("크기"+ls.size());
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
	public int updateBoard(int number,String title, String text) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		int rc = 0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:XE",
				"hr","oracle");
			stmt = conn.createStatement();
			String sql="update Bang5T set title='"+title+"',text='"+text+"' where no="+number;
			
	
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
	public int updateRecommand(int board_no, RecommandVO vo, String uid) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs=null;
		int rc = 0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"hr","oracle");
			conn.setAutoCommit( false );
			stmt = conn.createStatement();
			
			String r= "SELECT count(*) from recommand where board_no="+board_no +" and user_id='"+uid+"'";
			rs = stmt.executeQuery( r );
			
			if(rs.next()){
				rc=rs.getInt(1);
			}
			if(rc==0){
				String l = "insert into recommand values(seq_rec.nextval,'"+vo.getUserId()+"',"+vo.getBoardNo()+")";
				String t = "update bang5T set recommand=recommand+1 where no="+board_no;			
				
				int a = stmt.executeUpdate( l );
				int b = stmt.executeUpdate( t );
				conn.commit();
				rc = ( a + b );
			}
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












