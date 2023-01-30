package com.kshop.main.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kshop.main.domain.Customers;
import com.kshop.main.domain.Orders;
import com.kshop.main.domain.Users;
import com.kshop.main.repository.OrderRepository;
import com.kshop.main.service.CustomerService;
import com.kshop.main.service.OrderService;
import com.kshop.main.service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {
	@Autowired
	SessionService sessionService;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CustomerService customerService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(sessionService.get("userLogin", null) != null) {
		    Customers customers = (Customers) sessionService.get("userLogin");
		    Customers customer = customerService.findById(customers.getId()).get();
		    
		    customer.setOrders(null);
		    request.setAttribute("UserDataRoot", customer);
		    
		}
		
		return true;
	}
	
	
}
