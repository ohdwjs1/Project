package com.sist.web.util;

import javax.servlet.http.Cookie;

public class CookieManager {
	//객체생성없이 간단한 함수를 호출하기 위해서 static으로 정의
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
