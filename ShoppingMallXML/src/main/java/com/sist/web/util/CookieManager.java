package com.sist.web.util;

import javax.servlet.http.Cookie;

public class CookieManager {
	//��ü�������� ������ �Լ��� ȣ���ϱ� ���ؼ� static���� ����
	public static String getCookie(Cookie[] coos, String name){
		if(coos !=null){
			for(Cookie ck :coos){
				if(name.equals(ck.getName())){
					return ck.getValue();
				}
			}
		}
		return "";
	}
}