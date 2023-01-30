package com.kshop.main.controller.admin;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kshop.main.common.Constant;
import com.kshop.main.domain.ShopStores;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.ShopStoresService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.utils.Roles;

@Controller
@RequestMapping("/admin-panel")
public class VendorsAdminController extends com.kshop.main.controller.Controller {
    
	@Autowired
	ShopStoresService shopStoresService;
	@Autowired
    HttpServletResponse response;
	@Autowired
    SessionService sessionService;
    @Autowired
    UserHasRolesService userHasRolesService;
    
    private List<Long> listRole = Arrays.asList(Constant.ROLES.ADMIN);
	
	@GetMapping("vendors")
	public String layout(Model model) throws IOException { 
        
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        }
	    
	    dataLayoutMaster.setView("admin/vendor/vendor");
	    dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/js/vendor/vendor.js"));
	    	    
	    dataLayoutMaster.setObjs(objsDataLayout);
	    model.addAttribute(__CONTENT__, dataLayoutMaster);
	    
	    return adminLayout;
	}
    
	
    @GetMapping("create/vendors")
    public String create(Model model) throws IOException { 
        
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        }
    	ShopStores shopStores = new ShopStores();
    	shopStores.setId(-1L);
    	
    	objsDataLayout.put("shopStores", shopStores);
        
        dataLayoutMaster.setView("admin/vendor/create");
        
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/js/vendor/add.js", "/admin/assets/js/ckeditor.js", "/admin/assets/js/ckeditor-custom.js"));
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
    
    
    @GetMapping("require/vendors")
    public String requireVendors(Model model) throws IOException { 
        
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        }
        
        dataLayoutMaster.setView("admin/vendor/require");
        
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/js/vendor/require.js"));
        
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
    
    @GetMapping("edit/vendors/{idShopStore}")
    public String edit(Model model, @PathVariable Long idShopStore) throws IOException { 
        
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        }
        
        Optional<ShopStores> shopStores = shopStoresService.findById(idShopStore);
        
        if(!shopStores.isPresent()) {
            
            objsDataLayout.put("shopStore", new ShopStores());
            response.sendRedirect("/admin-panel/vendors");
        } else {
            
            objsDataLayout.put("shopStores", shopStores.get());
        }
        
        dataLayoutMaster.setView("admin/vendor/create");
        
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/js/vendor/add.js", "/admin/assets/js/ckeditor.js", "/admin/assets/js/ckeditor-custom.js"));
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
}
