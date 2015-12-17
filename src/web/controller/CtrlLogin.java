package web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.Bang5Dao;
import main.Bang5Dao_OracleImpl;
import main.Bang5VO;
import main.ModelAndView;
import main.RequestMapping;
import main.UserDao;
import main.UserDao_OracleImpl;
import main.UserVO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CtrlLogin 
{
	
	
	@RequestMapping("/main.do")
	public ModelAndView handleRequest9(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		ModelAndView mnv= new ModelAndView();
		mnv.setViewName("main");
	
		return mnv;
	}
	
	@RequestMapping("/login2.do")
	public ModelAndView handleRequest6(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mnv=new ModelAndView();
		
		String userId=request.getParameter("userId");
		String passwd=request.getParameter("passwd");
		if(userId==null||userId.equals("")||
				passwd==null||passwd.equals(""))
		{
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}
		UserDao userDao=new UserDao_OracleImpl();
		UserVO fvo=userDao.findByPk(userId);
		if(fvo==null){//해당 ID 없음 
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}else{
			if(passwd.equals(fvo.getPasswd())){//로그인 성공 
				HttpSession session =request.getSession();
				session.setAttribute("UID", userId);
				
				mnv.setViewName("redirect:/list.do");
			}else{
				mnv.setViewName("redirect:/main.do");
				return mnv;
			}
		}
		return mnv;
	}
	@RequestMapping("/reg_add.do")
	public ModelAndView reg(HttpServletRequest request,HttpServletResponse response) throws Exception{
				
		
		ModelAndView mnv=new ModelAndView();
		String userId=	request.getParameter("userId");
		String passwd=request.getParameter("passwd");
		String userName=request.getParameter("userName");
		
		UserDao userDao=new UserDao_OracleImpl();
		UserVO fvo=userDao.findByPk(userId);
		if(fvo!=null){
			mnv.setViewName("redirect:/reg.do");
			return mnv;
		}
		
		
		if(userId==null||userId.equals("")||passwd==null||passwd.equals("")){
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}
		
		try{
			userName=new String(userName.getBytes("8859_1"),"utf-8");
		}catch(Exception e){
			
		}
		String photo =request.getParameter("photo");
		
		UserVO pvo =new UserVO();
		pvo.setUserId(userId);
		pvo.setPasswd(passwd);
		pvo.setPhoto(photo);
		pvo.setUserName(userName);
		
		
		userDao.add(pvo);
		
		
		mnv.setViewName("redirect:/main.do");
		return mnv;
	}
	@RequestMapping("/userList.do")
	public ModelAndView handleRequest14(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mnv= new ModelAndView();
		HttpSession session= request.getSession();
		String uid = (String)session.getAttribute("UID");
		if( uid == null ){
			mnv.setViewName("redirect:/main.do");
			return mnv;
		}
		UserDao dao = new UserDao_OracleImpl();
		List<UserVO> ls=dao.findAll();
		mnv.addObject("ul", ls);
		mnv.setViewName("userList");
	
		return mnv;
	}
	@RequestMapping("/logout.do")
	public ModelAndView handleRequest15(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		session.removeAttribute("UID");
		
		
		ModelAndView mnv= new ModelAndView();
		mnv.setViewName("main");
	
		return mnv;
	}
	

	@RequestMapping("/reg.do")
	public ModelAndView handleRequest10(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mnv= new ModelAndView();
		
		
		mnv.setViewName("reg");
		return mnv;
	}
}
