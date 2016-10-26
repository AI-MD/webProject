package main;

import java.util.List;

public interface UserDao {
	public UserVO findByPk( String userId ) throws Exception;
	public int add( UserVO pvo ) throws Exception;
	
	/*	�ΰ� �̻��� ���� ���̴� ���� ���ξ� ���ٴ�
	 * 	����� �˱� ���� �̸��� �����ϴ� ������ �ִ�.
	 */
	public int movePoint( int no, String downer ) throws Exception;
	public	List<UserVO> findAll() throws Exception;
}
