package com.kshop.main.controller.admin;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kshop.main.common.Constant;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.utils.Roles;

@Controller
@RequestMapping("/admin-panel/")
public class LocalizationAdminController extends com.kshop.main.controller.Controller {

    @Autowired
    HttpServletResponse response;
    @Autowired
    SessionService sessionService;
    @Autowired
    UserHasRolesService userHasRolesService;
    
    private List<Long> listRole = Arrays.asList(Constant.ROLES.ADMIN);
    
	@GetMapping("translation")
	public String translate(Model model) throws IOException {      
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        } 
	    
	    dataLayoutMaster.setView("admin/localization/translation");
	    	    
	    dataLayoutMaster.setObjs(objsDataLayout);
	    
	    model.addAttribute(__CONTENT__, dataLayoutMaster);
	    
	    return adminLayout;
	}
    
    
    @GetMapping("currency-rates")
    public String currencyRates(Model model) throws IOException {      
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        } 
        
        dataLayoutMaster.setView("admin/localization/currency-rates");
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
    
    
    @GetMapping("taxes")
    public String taxes(Model model) throws IOException {      
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        } 
        
        dataLayoutMaster.setView("admin/localization/taxes");
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
}
