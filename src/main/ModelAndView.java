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
	
	/*	objects �ʿ� put ���� �־ �������� �͵���
	 * 	request �� setAttribute �� ȣ���Ͽ� �Ű��ִ� ������ ����
	 * 
	 * 	- �ٸ� package ������ ȣ���� �� ����� ��. �ؼ� friendly
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
	mp.put("apple","���");
	mp.put("banana","�ٳ���");
	
	Set<String> ks = mp.keySet();
	for( String key : ks ){
		System.out.println( key );	// �������� apple banana
		String value = mp.get( key );
		
		System.out.println( key + "\t" + value );
	}
*/









