package com.kshop.main.controller.admin;


import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kshop.main.utils.RAND;

@Controller
@RequestMapping("admincpanel")
public class AuthAdminController extends com.kshop.main.controller.Controller {
    
	@GetMapping("login")
	public String layout(Model model) {
	    
	    return "admin/auth/login";
	}
	
    @GetMapping("forgot")
    public String forgot(Model model) {
        
        return "admin/auth/forgot";
    }
}
