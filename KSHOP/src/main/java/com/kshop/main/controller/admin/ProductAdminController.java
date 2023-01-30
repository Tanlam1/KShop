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
import com.kshop.main.domain.Category;
import com.kshop.main.domain.Products;
import com.kshop.main.domain.Supplier;
import com.kshop.main.service.ProductsService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.utils.Roles;

@Controller
@RequestMapping("/admin-panel")
public class ProductAdminController extends com.kshop.main.controller.Controller {
    
    @Autowired
    ProductsService productsService;
    @Autowired
    HttpServletResponse response;
    @Autowired
    SessionService sessionService;
    @Autowired
    UserHasRolesService userHasRolesService;
    
    private List<Long> listRole = Arrays.asList(Constant.ROLES.ADMIN, Constant.ROLES.SHOPER);
    
	@GetMapping("product")
	public String layout(Model model) throws IOException { 
        
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        }
	    
	    dataLayoutMaster.setView("admin/product/product");
	    dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/product/script.js"));
	    	    
	    dataLayoutMaster.setObjs(objsDataLayout);
	    
	    model.addAttribute(__CONTENT__, dataLayoutMaster);
	    
	    return adminLayout;
	}
	
	@GetMapping("add/product")
    public String add(Model model) throws IOException { 
        
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        }
	    Products product = new Products();
	    product.setCategories(new Category());
        product.setSuppliers(new Supplier());
        product.setId(-1L);
	    
	    objsDataLayout.put("product", product);
        
        dataLayoutMaster.setView("admin/product/add");
        
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/product/add.js", "/admin/assets/js/ckeditor.js", "/admin/assets/js/ckeditor-custom.js"));
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
    
    @GetMapping("edit/product/{idProduct}")
    public String edit(Model model, @PathVariable Long idProduct) throws IOException { 
        
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        }
        
        Optional<Products> product = productsService.findById(idProduct);
        
        if(!product.isPresent()) {
            
            objsDataLayout.put("product", new Products());
            response.sendRedirect("/admin-panel/product");
        } else {
            
            objsDataLayout.put("product", product.get());
        }
        
        dataLayoutMaster.setView("admin/product/add");
        
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/product/add.js", "/admin/assets/js/ckeditor.js", "/admin/assets/js/ckeditor-custom.js"));
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
}
