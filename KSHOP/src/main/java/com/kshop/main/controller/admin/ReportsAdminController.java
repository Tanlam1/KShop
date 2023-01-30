package com.kshop.main.controller.admin;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kshop.main.common.Constant;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.utils.Roles;

@Controller
@RequestMapping("/admin-panel/reports")
public class ReportsAdminController extends com.kshop.main.controller.Controller {
    
    @Autowired
    HttpServletResponse response;
    @Autowired
    SessionService sessionService;
    @Autowired
    UserHasRolesService userHasRolesService;
    @Autowired
    HttpServletRequest httpServletRequest;
    
    private List<Long> listRole = Arrays.asList(Constant.ROLES.ADMIN, Constant.ROLES.SHOPER, Constant.ROLES.ADMIN_MONEY);
    
	@GetMapping("")
	public String layout(Model model) throws IOException { 
        
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        }
        sessionService.remove(Constant.SESSION_DATA.REPORT_USER_ID);
	    dataLayoutMaster.setView("admin/report/report");

        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/js/chart/apex-chart/apex-chart1.js",
                "/admin/assets/js/chart/apex-chart/moment.min.js", 
                "/admin/assets/js/chart/apex-chart/stock-prices.js",
                "/admin/assets/js/report/report.js",
                "/admin/assets/reports/analytics-add.js",
                "/admin/assets/reports/analytics-bank.js"));
	    	    
	    dataLayoutMaster.setObjs(objsDataLayout);
	    
	    model.addAttribute(__CONTENT__, dataLayoutMaster);
	    
	    return adminLayout;
	}
	
	@GetMapping("store-{id}")
    public String reportStore(Model model, @PathVariable Long id) throws IOException { 
        
        boolean isAdmin = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN, Constant.ROLES.ADMIN_MONEY), sessionService, userHasRolesService);
        if(!isAdmin) {
            sessionService.remove(Constant.SESSION_DATA.REPORT_USER_ID);
            response.sendRedirect("/404");
        } else {
            sessionService.set(Constant.SESSION_DATA.REPORT_USER_ID, id);
        }
        
        
        dataLayoutMaster.setView("admin/report/store-report");

        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/js/chart/apex-chart/apex-chart1.js",
                "/admin/assets/js/chart/apex-chart/moment.min.js", 
                "/admin/assets/js/chart/apex-chart/stock-prices.js",
                "/admin/assets/js/report/report.js",
                "/admin/assets/reports/analytics-add.js",
                "/admin/assets/reports/analytics-bank.js"));
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
	
	@GetMapping("money-order")
    public String layoutMoneyOrder(Model model) throws IOException {      
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        } 
        
        dataLayoutMaster.setView("admin/report/money-order");
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/reports/money-order.js"));
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
    
    @GetMapping("money-send")
    public String layoutMoneySend(Model model) throws IOException {      
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        } 
        
        dataLayoutMaster.setView("admin/report/money-send");
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/reports/money-send.js"));
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
    
    @GetMapping("analytics-add")
    public String layoutMoneyOrderAnalytics(Model model) throws IOException {      
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        } 
        
        dataLayoutMaster.setView("admin/report/analytics-add");
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/reports/analytics-add.js"));
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
    
    @GetMapping("analytics-bank")
    public String layoutMoneySendAnalytics(Model model) throws IOException {      
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        } 
        
        dataLayoutMaster.setView("admin/report/analytics-bank");
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/reports/analytics-bank.js"));
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
}
