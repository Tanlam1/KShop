package com.kshop.main.controller.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kshop.main.domain.ProductImages;
import com.kshop.main.domain.Products;
import com.kshop.main.service.ProductImagesService;
import com.kshop.main.service.ProductsService;

@Controller
@RequestMapping("/")
public class HomeController extends com.kshop.main.controller.Controller {
    
    @Autowired
    ProductsService productsService;
    @Autowired
    ProductImagesService productImagesService;
	
	@GetMapping({"/", "index.html"})
	public String layout(Model model) {
	    
	    dataLayoutMaster.setView("client/home/home");
        dataLayoutMaster.setJsList(Arrays.asList("/js/home/script.js"));
	    
	    dataLayoutMaster.setObjs(objsDataLayout);
	    
	    model.addAttribute(__CONTENT__, dataLayoutMaster);
	    
	    return clientLayout;
	}

    
    //get login, register
    @GetMapping("login-ajax.html") 
    public String loginAjax() {
        return "login-ajax";
    }
}
