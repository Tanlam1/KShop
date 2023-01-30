package com.kshop.main.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kshop.main.service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ClientAuthenticationInterceptor implements HandlerInterceptor {

	@Autowired
	private SessionService sessionService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(sessionService.get("userLogin", null) != null) { // có session username => login ok => ko làm gì
			return true;
		}
		
		sessionService.set("redirect-uri", request.getRequestURI()); // lưu lại uri hiện tại cho session redirect-uri
		response.sendRedirect("/auth/login"); // chuyển qua trang login
		
		return false;
	}
	
}
