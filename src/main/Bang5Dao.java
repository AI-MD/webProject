package main;

import java.util.HashMap;
import java.util.List;

public interface Bang5Dao {
	public int add( Bang5VO pvo ) throws Exception;
	public int delete( int number ) throws Exception;
	public Bang5VO findByNo(int no) throws Exception;
	public int updateCount(int number) throws Exception;
	public int findAllCount() throws Exception;
	public List<Bang5VO> findAll(int start, int end) throws Exception;
	public int findSearchCount(HashMap map) throws Exception;
	public List<Bang5VO> findSearch(HashMap map,int start, int end) throws Exception;
	public int updateBoard(int number, String title, String text) throws Exception;
	public int updateRecommand(int board_no, RecommandVO vo, String uid) throws Exception;
}
