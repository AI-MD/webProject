package main;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import sun.reflect.Reflection;


/*
 * old���� service���� ������ ���� for���� �ȵ���. 
 * 
 * init�ɶ� map Ŭ������ �������� �ϳ� ������ �ذ��� (�ߺ��� uri�Ȱ� �ִٸ� �����߻�) 
 */
public class DispatcherServlet extends HttpServlet
{
	
	//private CtrlAll ctrl = null;	
	private ServletContext sc=null;
	
	@Override
	public void service(HttpServletRequest request, 
		HttpServletResponse response )
		throws ServletException, IOException 
	{
		String cp = request.getContextPath();
		String uri = request.getRequestURI();
		uri = uri.substring( cp.length() );
		
		//List<Hashtable<String, Method>> map_arr  = (List<Hashtable<String, Method>>)sc.getAttribute("map_arr");
		Hashtable<String, Method> map  = (Hashtable<String, Method>)sc.getAttribute("map");
		Hashtable<String, Class<? extends Object>> map_class  = (Hashtable<String, Class<? extends Object>>)sc.getAttribute("map_class");
		try{
						
			//Set<Class<?>> classes=(Set<Class<?>>)sc.getAttribute("classes");
		
			//int flag = 0;
			//int cnt = 0;
			//for( Class<? extends Object> cls : classes ){
				//Hashtable<String,Method> map = map_arr.get(cnt);
				//System.out.println("test : " + uri);
				
				Class<? extends Object> cls=map_class.get( uri );
				if(cls!=null){
					
					Method method = map.get( uri );
					if( method != null ){
						try{
							ModelAndView mnv = (ModelAndView)method.
								invoke( cls.newInstance(), request, response );
							if( mnv == null ){
								return;
							}
							else{
								//flag = 1;
								String vn = mnv.getViewName();
								if( vn.startsWith("redirect:") ){
									String l = vn.substring(9);
									response.sendRedirect( cp + l );
								}else{
									String l = "/" + vn + ".jsp";
									RequestDispatcher rd = request.
										getRequestDispatcher( l );
									mnv.fill( request );
									rd.forward( request, response );
								}
							}
						}
						catch( Exception e ){ e.printStackTrace(); }
					}else{
						System.out.println( uri + 
								"�� �ش��ϴ� Controller ����" );
					}
				}
			//	cnt++;
			//}
			
			//if(flag == 0){
				else{
					System.out.println( uri + 
							"�� �ش��ϴ� Controller ����" );
				}
			//}
		}
		catch( Exception e ){}
	}

	@Override
	public void init(ServletConfig config) 
		throws ServletException 
	{
		//ctrl = new CtrlAll();
		sc=config.getServletContext();
		
		/*
		 * ��Ű�� ���÷��� 
		 */
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());

		Reflections reflections = new Reflections(new ConfigurationBuilder()
		    .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
		    .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
		    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("web.controller"))));
		
		Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
		
		
		
		//sc.setAttribute("classes", classes);//��Ű�� �������� ���� 
		Hashtable<String,Method> map = new Hashtable<String,Method>();
		Hashtable<String,Class<? extends Object>> map_class = new Hashtable<String,Class<? extends Object>>();
		//List<Hashtable<String, Method>> map_arr  = new ArrayList<Hashtable<String,Method>>();
		for( Class<? extends Object> cls : classes ){
			//System.out.println("12312312312");
			
			Method[] mtds = cls.getMethods();
			for( Method mtd : mtds )
			{
				RequestMapping mapping = mtd.
						getAnnotation( RequestMapping.class );
					Class<?>[] ptype = mtd.getParameterTypes();
					Class<?> rtype = mtd.getReturnType();
					
					if( mapping != null && 
						rtype == ModelAndView.class &&
						ptype.length == 2 &&
						ptype[0] == HttpServletRequest.class && 
						ptype[1] == HttpServletResponse.class )
					{
						String uri2 = mapping.value();
						if(map.containsKey(uri2)||map_class.containsKey(uri2)){
							throw new ServletException();
						}
						
						map.put( uri2, mtd );
						map_class.put(uri2,cls);
					}
			}
			
			//map_arr.add(map);
		}
		//sc.setAttribute("map_arr", map_arr);//����Ʈ�� Ŭ������� ���ε� ����� ���� 
		sc.setAttribute("map", map);
		sc.setAttribute("map_class", map_class);
	}

	
}











