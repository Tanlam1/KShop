package com.kshop.main.interceptor;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kshop.main.common.Constant;
import com.kshop.main.domain.UserHasRoles;
import com.kshop.main.domain.Users;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.service.UsersService;

@Component
public class AdminAuthenticationInterceptor implements HandlerInterceptor {

	@Autowired
	private SessionService sessionService;
	@Autowired
	private UserHasRolesService userHasRolesService;
	@Autowired
	private UsersService usersService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(sessionService.get("admin_kodoku_KShop", null) != null) { // có session username => login ok => ko làm gì				   
		    
		    Users userLogin = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
		    Users user = usersService.findById(userLogin.getId()).get();
		    user.setPassword(null);
		    user.setRemember_token(null);
            request.setAttribute("AdminDataRoot", user);
            
            List<UserHasRoles> list = userHasRolesService.selectsByUserId(user.getId());
            
            boolean isAdmin = list.stream().anyMatch(e -> e.getRoles().getId() == 1);
            boolean isShoper = list.stream().anyMatch(e -> e.getRoles().getId() == 4);
            boolean isAdminMoney = list.stream().anyMatch(e -> e.getRoles().getId() == 7);
            request.setAttribute("isAdmin", isAdmin);
            request.setAttribute("isShoper", isShoper);
            request.setAttribute("isAdminMoney", isAdminMoney);
            
            if(user.getInfo_receive_money() == null && isShoper && !request.getRequestURI().equals("/admin-panel/money-receive")) {
                
                response.sendRedirect(Constant.URI.ADMIN + "/money-receive");
            }
            
		    return true;
		}
		
		sessionService.set("redirect-uri", request.getRequestURI()); // lưu lại uri hiện tại cho session redirect-uri
		response.sendRedirect("/admincpanel/login"); // chuyển qua trang login
		
		return false;
	}
	
}
