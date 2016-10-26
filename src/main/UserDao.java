package main;

import java.util.List;

public interface UserDao {
	public UserVO findByPk( String userId ) throws Exception;
	public int add( UserVO pvo ) throws Exception;
	
	/*	두개 이상의 논리가 묶이는 경우는 접두어 보다는
	 * 	기능을 알기 쉬운 이름에 집중하는 경향이 있다.
	 */
	public int movePoint( int no, String downer ) throws Exception;
	public	List<UserVO> findAll() throws Exception;
}
