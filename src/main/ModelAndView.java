package main;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class ModelAndView {
	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	private String viewName = null;
	private Map<String,Object> objects = null;
	
	public ModelAndView(){
		objects = new Hashtable<String,Object>();
	}
	
	public void addObject( String key, Object val ){
		objects.put(key, val);
	}
	
	/*	objects 맵에 put 으로 넣어서 보관중인 것들을
	 * 	request 에 setAttribute 를 호출하여 옮겨주는 역할을 수행
	 * 
	 * 	- 다른 package 에서는 호출할 수 없어야 함. 해서 friendly
	 */
	void fill( HttpServletRequest request ){
		Set<String> ks = objects.keySet();
		for( String key : ks ){
			Object val = objects.get( key );
			request.setAttribute( key, val );
		}
	}
}
/*
	Map<String,String> mp = new Hashtable<String,String>();
	mp.put("apple","사과");
	mp.put("banana","바나나");
	
	Set<String> ks = mp.keySet();
	for( String key : ks ){
		System.out.println( key );	// 순서없이 apple banana
		String value = mp.get( key );
		
		System.out.println( key + "\t" + value );
	}
*/









