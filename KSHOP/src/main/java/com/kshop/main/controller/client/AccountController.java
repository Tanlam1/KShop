package com.kshop.main.controller.client;

import java.util.Arrays;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kshop.main.common.Constant;
import com.kshop.main.domain.Customers;
import com.kshop.main.domain.Seller;
import com.kshop.main.service.SellerService;
import com.kshop.main.service.SessionService;

@Controller
@RequestMapping("/profile")
public class AccountController extends com.kshop.main.controller.Controller {
	
    @Autowired
    SellerService sellerService;
    @Autowired
    SessionService sessionService;
	
	@GetMapping("")
	public String layout(Model model) {
	    
	    dataLayoutMaster.setView("client/account/account");
        dataLayoutMaster.setMainCss("css/style.min.css");
        dataLayoutMaster.setJsList(Arrays.asList("/js/profile/script.js"));
        
        Customers customer = (Customers) sessionService.get(Constant.SESSION_LOGIN_TYPE.CLIENT, null);
        Seller sellerDTO = new Seller();
        Seller itemSeller = sellerService.selectByCustomerId(customer.getId());
	    
        if(itemSeller != null) {
            sellerDTO = itemSeller;
        }
        
        objsDataLayout.put("seller", sellerDTO);
	    dataLayoutMaster.setObjs(objsDataLayout);	    
	    model.addAttribute(__CONTENT__, dataLayoutMaster);
	    
	    return clientLayout;
	}
}
