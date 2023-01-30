package com.kshop.main.service;

import javax.servlet.http.Cookie;

public interface CookieService {

	Cookie add(String name, String value, int hours, String... path);

	String getValue(String name, String defaultValue);

	Cookie get(String name);

	void remove(String name, String... path);

}
