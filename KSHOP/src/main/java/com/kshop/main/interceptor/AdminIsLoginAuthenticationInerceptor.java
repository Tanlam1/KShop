package com.kshop.main.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kshop.main.service.SessionService;

@Component
public class AdminIsLoginAuthenticationInerceptor implements HandlerInterceptor {
	
	@Autowired
	SessionService sessionService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(sessionService.get("admin_kodoku_KShop", null) == null) {
			return true;
		}
		
		response.sendRedirect("/admin-panel/dashboards");
		return false;
	}
	
	
}
