package com.kshop.main.controller.admin;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kshop.main.common.Constant;
import com.kshop.main.domain.Orders;
import com.kshop.main.domain.Users;
import com.kshop.main.service.OrderService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.service.UsersService;
import com.kshop.main.utils.Roles;

@Controller
@RequestMapping("/admin-panel")
public class OrdersAdminController extends com.kshop.main.controller.Controller {

    @Autowired
    HttpServletResponse response;
    @Autowired
    SessionService sessionService;
    @Autowired
    UserHasRolesService userHasRolesService;
    @Autowired
    OrderService orderService;
    @Autowired
    UsersService usersService;
    @Autowired
    HttpServletRequest httpServletRequest;
    
    private List<Long> listRole = Arrays.asList(Constant.ROLES.ADMIN, Constant.ROLES.SHOPER);
    
    
	@GetMapping("orders")
	public String layout(Model model) throws IOException { 
	    
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        } 
        sessionService.remove(Constant.SESSION_DATA.REPORT_USER_ID);
	    dataLayoutMaster.setView("admin/order/list");
	    dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/order/script.js"));
	    	    
	    dataLayoutMaster.setObjs(objsDataLayout);
	    
	    model.addAttribute(__CONTENT__, dataLayoutMaster);
	    
	    return adminLayout;
	}
    
	
    @GetMapping("tracking-orders")
    public String tracking(Model model) throws IOException { 
        
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        } 
        
        dataLayoutMaster.setView("admin/order/tracking");
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
    
    
    @GetMapping("detail-orders/{id}")
    public String detail(Model model, @PathVariable("id") String id) throws IOException { 
        
        boolean isAdmin = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN), sessionService, userHasRolesService);
        boolean isAdminMoney = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY), sessionService, userHasRolesService);
        boolean isShoper = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.SHOPER), sessionService, userHasRolesService);
        if(!isAdmin && !isAdminMoney && !isShoper) {
            response.sendRedirect("/404");
        }
        
        if(!isAdmin) {
            Users userLogin = sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
            Optional<Orders> orders = orderService.findById(Long.parseLong(id));
            if(orders.isEmpty()) {
                response.sendRedirect("/404");
            }
            
            if(!orders.get().getUsers().getId().equals(userLogin.getId())) {
                response.sendRedirect("/404");
            }
        }
        
        
        dataLayoutMaster.setView("admin/order/detail");
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/orderdetail/orderdetail.js"));
        objsDataLayout.put("orderId", id);
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
}
