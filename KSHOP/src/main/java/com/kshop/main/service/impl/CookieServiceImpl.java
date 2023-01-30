package com.kshop.main.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshop.main.service.CookieService;

@Service
public class CookieServiceImpl implements CookieService {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Override
	public Cookie get(String name) {
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equalsIgnoreCase(name)) return cookie;
			}
		}
		
		return null;
	}
	
	@Override
	public String getValue(String name, String defaultValue) {
		Cookie cookie = get(name);
		
		if(cookie != null) {
			return cookie.getValue();
		}
		
		return null;
	}
	
	@Override
	public Cookie add(String name, String value, int hours, String... path) {
		String pathDomain = "/";
		if(path.length == 1) pathDomain = path[0];
		
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(hours * 60 * 60);
		cookie.setPath(pathDomain);
		
		response.addCookie(cookie);		
		return cookie;
		
	}
	
	@Override
	public void remove(String name, String... path) {
		String pathDomain = "/";
		if(path.length == 1) pathDomain = path[0];
		add(name, "", -1, pathDomain);
	}
}
