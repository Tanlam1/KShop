package com.kshop.main.controller.client;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store-list")
public class VendorStoreListController extends com.kshop.main.controller.Controller {
	
	@GetMapping("")
	public String layout(Model model) {
	    
	    dataLayoutMaster.setView("client/vendor-store/vendor-store-list");
	    dataLayoutMaster.setMainCss("css/style.min.css");
	    dataLayoutMaster.setJsList(Arrays.asList("/js/vendor-store/vendor-store-list.js"));
	    
	    objsDataLayout.put("key", "value");
	    
	    dataLayoutMaster.setObjs(objsDataLayout);
	    
	    model.addAttribute(__CONTENT__, dataLayoutMaster);
	    
	    return clientLayout;
	}
}
