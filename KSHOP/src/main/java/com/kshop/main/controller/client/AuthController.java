package com.kshop.main.controller.client;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kshop.main.service.SessionService;

@Controller
@RequestMapping("/auth")
public class AuthController extends com.kshop.main.controller.Controller {
	
    @Autowired
    SessionService sessionService;
	
	@GetMapping("login")
	public String layout(Model model) {
	    
	    dataLayoutMaster.setView("client/login/login");
        dataLayoutMaster.setMainCss("/css/style.min.css");
        dataLayoutMaster.setJsList(Arrays.asList("/js/login/script.js"));
	    	    
	    dataLayoutMaster.setObjs(objsDataLayout);	    
	    model.addAttribute(__CONTENT__, dataLayoutMaster);
	    
	    return clientLayout;
	}
    
    @GetMapping("/logout")
    public String logout() {
        
        sessionService.remove("userLogin");
        return "redirect:/";
    }
}
