package web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.Bang5Dao;
import main.Bang5Dao_OracleImpl;
import main.Bang5VO;
import main.ModelAndView;
import main.RecommandDao;
import main.RecommandDao_OracleImpl;
import main.RecommandVO;
import main.ReplyDao;
import main.ReplyDao_OracleImpl;
import main.ReplyVO;
import main.RequestMapping;
import main.UserDao;
import main.UserDao_OracleImpl;
import main.UserVO;
import main.pageVO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CtrlList 
{
	
	@RequestMapping("/add.do")
	public ModelAndView handleRequest13(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		ModelAndView mnv= new ModelAndView();
		String uid = (String)session.getAttribute("UID");
		if( uid == null ){
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}
		
	
		mnv.setViewName("add");
	
		return mnv;
	}
	@RequestMapping("/like.do")
	public ModelAndView like(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mnv= new ModelAndView();
		HttpSession session= request.getSession();
		
		String uid = (String)session.getAttribute("UID");
		if( uid == null ){
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}
		String board_no1 =request.getParameter("no");
		int board_no=Integer.parseInt(board_no1);
		String cp=request.getParameter("cp");
		
		
		
		Bang5Dao bangdao=new Bang5Dao_OracleImpl();
		RecommandVO vo=new RecommandVO();
		vo.setBoardNo(board_no);
		vo.setUserId(uid);
		bangdao.updateRecommand(board_no, vo,uid);
		mnv.setViewName("redirect:/list.do?cp="+cp);
		return mnv;
		
	}
	
	@RequestMapping("/board_delete.do")
	public ModelAndView handleRequest2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mnv =new ModelAndView();
		Bang5Dao bangdao=new Bang5Dao_OracleImpl();
		
		String no=request.getParameter("no");
		Integer no2=Integer.parseInt(no);
		Bang5VO vo=bangdao.findByNo(no2);
		String cp=request.getParameter("cp");
		
		HttpSession session= request.getSession();
		String uid = (String)session.getAttribute("UID");
		if( uid == null ){
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}else if(uid.equals(vo.getUserId())){
			bangdao.delete(no2);
			mnv.setViewName("redirect:/list.do?cp="+cp);
			return mnv;
		}else{
			mnv.setViewName("redirect:/list.do?cp="+cp);
			return mnv;
		}
	}
	@RequestMapping("/board_update.do")
	public ModelAndView board_update(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mnv =new ModelAndView();
		String no=request.getParameter("no");
		int no1=Integer.parseInt(no);
		String cp=request.getParameter("cp");
		
		Bang5Dao bangdao=new Bang5Dao_OracleImpl();
		
		
		Bang5VO vo=bangdao.findByNo(no1);
		
		HttpSession session= request.getSession();
		String uid = (String)session.getAttribute("UID");
		if( uid == null ){
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}else{
			mnv.addObject("cp", cp);
			mnv.addObject("vo", vo);
			mnv.setViewName("board_update");
			return mnv;
		}
	}
	@RequestMapping("/update.do")
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		ModelAndView mnv=new ModelAndView();
		String title=new String(request.getParameter("title").getBytes("8859_1"),"utf-8");
		String text=new String(request.getParameter("text").getBytes("8859_1"),"utf-8");
		String no=request.getParameter("no");
		int no1=Integer.parseInt(no);
		
		Bang5Dao bangdao=new Bang5Dao_OracleImpl();
		
		String cp=request.getParameter("cp");
		
		HttpSession session= request.getSession();
		String uid = (String)session.getAttribute("UID");
		if( uid == null ){
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}else{
			bangdao.updateBoard(no1, title, text);
			mnv.setViewName("redirect:/list.do?cp="+cp);
			return mnv;
		}
	}
	@RequestMapping("/reply_update.do")
	public ModelAndView reply_update(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mnv =new ModelAndView();
		
		String no=request.getParameter("no");
		String board_no=request.getParameter("board_no");
		
		HttpSession session= request.getSession();
		String uid = (String)session.getAttribute("UID");
		if( uid == null ){
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}else{
			mnv.addObject("no", no);
			mnv.addObject("board_no", board_no);
			mnv.setViewName("reply_update");
			return mnv;
		}
	}
	@RequestMapping("/update2.do")
	public ModelAndView update2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		ModelAndView mnv=new ModelAndView();
		
		String text=new String(request.getParameter("text").getBytes("8859_1"),"utf-8");
		String no=request.getParameter("no");
		String board_no=request.getParameter("board_no");
		int no1=Integer.parseInt(no);
		
		ReplyDao dao=new ReplyDao_OracleImpl();
		
		
		
		HttpSession session= request.getSession();
		String uid = (String)session.getAttribute("UID");
		if( uid == null ){
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}else{
			dao.updateReply(no1, text);
			mnv.setViewName("redirect:/view.do?no="+board_no);
			return mnv;
		}
	}
	
	
	
	@RequestMapping("/reply_delete.do")
	public ModelAndView re_delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mnv =new ModelAndView();
		ReplyDao replydao=new ReplyDao_OracleImpl();
		
		String no=request.getParameter("no");
		String board_no=request.getParameter("board_no");
		Integer no2=Integer.parseInt(no);
		
		
		
		HttpSession session= request.getSession();
		String uid = (String)session.getAttribute("UID");
		if( uid == null ){
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}else{
			replydao.delete(no2);
			mnv.setViewName("redirect:/view.do?no="+board_no);
			return mnv;
		}
	}
	
	@RequestMapping("/reply_insert.do")
	public ModelAndView reply_insert(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mnv =new ModelAndView();
		ReplyDao replydao=new ReplyDao_OracleImpl();
		
		String board_no=request.getParameter("board_no");
		
		
		HttpSession session= request.getSession();
		String uid = (String)session.getAttribute("UID");
		if( uid == null ){
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}else{
			

			ReplyVO rvo=new ReplyVO();
			rvo.setBang_no(Integer.parseInt(request.getParameter("board_no")));
			rvo.setText(new String(request.getParameter("text").getBytes("8859_1"),"utf-8"));
			rvo.setUserId(uid);
			replydao.add(rvo);
			
			
			
			mnv.setViewName("redirect:/view.do?no="+board_no);
			return mnv;
		}
	}
	@RequestMapping("/file_down.do")
	public ModelAndView handleRequest3(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		ModelAndView mnv=new ModelAndView();
		
		
		String uid = (String)session.getAttribute("UID");
		if( uid == null ){
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}
		
		String fsn = request.getParameter("fsn");
		String ofn = request.getParameter("ofn");
		String no = request.getParameter("no");
		
		Exception err = null;
		try{
			File f = new File("D:\\upload\\" + fsn );
			if( !f.exists() ){
				response.setStatus(404);
				return null;
			}

			int no2 = Integer.parseInt( no );
			UserDao dao = new UserDao_OracleImpl();
			int rc = dao.movePoint( no2, uid );
			
			if( rc < 2 ){
				
				response.setStatus(500);
				return null;
			}
			else
			{
				//	파일 다운로드시에 미리 통보하는 MIME TYPE 
				response.setContentType("application/octet-stream");
				response.setContentLength( (int)f.length() );
				response.setHeader("Content-Disposition",
					"attachment;filename=" + ofn );
				
				OutputStream out = response.getOutputStream();
				InputStream in = new FileInputStream( f );
				
				int len = 0;
				byte[] buf = new byte[1024*8];
				while( ( len = in.read( buf ) ) != -1 ){
					out.write( buf, 0, len );
					out.flush();
				}
				in.close();
				out.close();
			}
		}
		catch( Exception e ){ err = e; }
		
		if( err != null ){
			response.setStatus(500 );
		}
		return null;
	}
	@RequestMapping("/add2.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mnv=new ModelAndView();
		HttpSession session= request.getSession();
		String uid=(String)session.getAttribute("UID");
		
		if(uid==null){
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}
		MultipartRequest mpr=new MultipartRequest(request,
				"D:\\upload\\",1024*1024*1024,"utf-8",
				new DefaultFileRenamePolicy());
		Bang5VO pvo=new Bang5VO();
		pvo.setTitle(mpr.getParameter("title"));
		pvo.setText(mpr.getParameter("text"));
		pvo.setOfn(mpr.getOriginalFileName("one"));
		pvo.setFsn(mpr.getFilesystemName("one"));
		pvo.setClientIp(request.getRemoteAddr());
		pvo.setUserId(uid);
		
		Bang5Dao bangdao=new Bang5Dao_OracleImpl();
		bangdao.add(pvo);
		
		mnv.setViewName("redirect:/list.do");
		return mnv;
	}
	@RequestMapping("/list.do")
	public ModelAndView handleRequest5(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mnv= new ModelAndView();
		
		HashMap map=new HashMap();
		
		HttpSession session= request.getSession();
		String uid=(String)session.getAttribute("UID");
		
		if(uid==null){
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}
		mnv.addObject("id", uid);
		
		
		Bang5Dao bangdao=new Bang5Dao_OracleImpl();
		UserDao userDao=new UserDao_OracleImpl();
		UserVO uvo=userDao.findByPk(uid);
		
		pageVO pv=new pageVO();
		String cp=request.getParameter("cp");
		int blockStart;
		int blockSize=5;                                                                                  
		
		if(cp!=null||cp!="null"){
			if(cp==null){
				blockStart =1;
				cp="1";
			}else{
				int cp1=Integer.parseInt(cp);
				blockStart=((cp1-1))*blockSize+1;
				
			}
		}else{
			blockStart =1;
			cp="1";
		}
		
		
		
		
		String field=request.getParameter("field");
		String word=request.getParameter("searchword");
		String searchWord = null;
		if(word!=null){
			searchWord=new String(word.getBytes("8859_1"),"utf-8");
		}
		if(field!=null||searchWord!=null){
			
			map.put("field", field);
			map.put("searchWord", searchWord);
		}
		
	
		int blockEnd=blockStart+blockSize-1;
		pv.setBlockStart(blockStart);
		pv.setBlockEnd(blockEnd);
		List<Bang5VO> ls=new ArrayList<Bang5VO>();
		
		int count=0;
		if(map.containsKey("field")){
			//System.out.println("1");
			count=bangdao.findSearchCount(map);
			ls=bangdao.findSearch(map,blockStart,blockEnd);
		}else{
			System.out.println("2");
			count=bangdao.findAllCount();//전체레코드갯수
			ls=bangdao.findAll(blockStart, blockEnd);
		}
		
		System.out.println("카운트"+count);
		int np=((count-1)/blockSize)+1;
		//System.out.println("test"+blockStart+"\t"+blockEnd );
		
		
		mnv.addObject("cp", cp);
		mnv.addObject("np", np);
		mnv.addObject("pv", pv);
		mnv.addObject("count", count);
		mnv.addObject("uvo", uvo);
		mnv.addObject("l", ls);
		mnv.setViewName("list");
		return mnv;
	}
	
	@RequestMapping("/view.do")
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mnv= new ModelAndView();
		
		HttpSession session= request.getSession();
		String uid=(String)session.getAttribute("UID");
		
		if(uid==null){
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}
		UserDao userDao=new UserDao_OracleImpl();
		UserVO uvo=userDao.findByPk(uid);
		String no=request.getParameter("no");
		int no1=Integer.parseInt(no);
		
		
		Bang5Dao bangdao=new Bang5Dao_OracleImpl();
		ReplyDao replyDao=new ReplyDao_OracleImpl();
		
		Bang5VO vo=bangdao.findByNo(no1);
		List<ReplyVO> ls=replyDao.findbyBn(no1);
		
		if(!vo.getUserId().equals(uid)){
			bangdao.updateCount(no1);
		}
		mnv.addObject("uvo", uvo);
		mnv.addObject("id", uid);
		mnv.addObject("bvo", vo);
		mnv.addObject("rls", ls);
		mnv.setViewName("view");
		return mnv;
	}
}
