package com.kshop.main.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kshop.main.common.APIResponse;
import com.kshop.main.domain.Users;
import com.kshop.main.model.LoginDTO;
import com.kshop.main.model.RegisterDTO;
import com.kshop.main.service.CustomerService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UsersService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthAPIController {
    
    @Autowired
    CustomerService customerService;
    @Autowired
    UsersService usersService;
    @Autowired
    SessionService sessionService;
    @Autowired
    HttpServletResponse httpServletResponse;
    
    @PostMapping("/login")
    public APIResponse login(@RequestBody LoginDTO loginDTO) {
        
        APIResponse response = customerService.login(loginDTO);
        
        return response;
    }
    
    @PostMapping("/register")
    public APIResponse register(@RequestBody RegisterDTO registerDTO) {
        
        APIResponse response = customerService.regiter(registerDTO);
        
        return response;
    }
    
    @PostMapping("/admin/login")
    public APIResponse loginAdmin(@RequestBody LoginDTO loginDTO) throws IOException {
        
        APIResponse response = usersService.login(loginDTO);
        
        return response;
    }
    
}
