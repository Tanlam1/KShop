package com.kshop.main.controller.admin;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.mail.Session;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kshop.main.common.Constant;
import com.kshop.main.domain.Category;
import com.kshop.main.utils.Roles;
import com.kshop.main.domain.ShopStores;
import com.kshop.main.domain.UserHasRoles;
import com.kshop.main.domain.Users;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.ShopCategoriesService;
import com.kshop.main.service.ShopStoresService;
import com.kshop.main.service.UserHasRolesService;

@Controller
@RequestMapping("/admin-panel")
public class CategoriesAdminController extends com.kshop.main.controller.Controller {
    
	@Autowired
	ShopCategoriesService shopCategoriesService;
	@Autowired
    HttpServletResponse response;
	@Autowired
	SessionService sessionService;
	@Autowired
	UserHasRolesService userHasRolesService;
	
    @GetMapping("categories")
    public String index(Model model) throws IOException {
        boolean isAdmin = Roles.UserHasRoles(Arrays.asList(1L), sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        }
        
        dataLayoutMaster.setView("admin/categories/categories");
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/js/category/category.js"));
    
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
    
    @GetMapping("create/category")
    public String create(Model model) throws IOException {
        boolean isAdmin = Roles.UserHasRoles(Arrays.asList(1L), sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        }
    	Category categories = new Category();
    	categories.setId(-1L);
        
    	objsDataLayout.put("categories", categories);
    	
        dataLayoutMaster.setView("admin/categories/create");

        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/js/ckeditor.js", "/admin/assets/js/ckeditor-custom.js", "/admin/assets/js/category/create.js"));
        
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
    
    @GetMapping("edit/category/{idCategory}")
    public String edit(Model model, @PathVariable Long idCategory) throws IOException {
        
        boolean isAdmin = Roles.UserHasRoles(Arrays.asList(1L), sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        }
        
        Optional<Category> categories = shopCategoriesService.findById(idCategory);
        
        if(!categories.isPresent()) {
            
            objsDataLayout.put("categories", new Category());
            response.sendRedirect("/admin-panel/categories");
        } else {
            
            objsDataLayout.put("categories", categories.get());
        }
        
        dataLayoutMaster.setView("admin/categories/create");
        
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/js/category/create.js", "/admin/assets/js/ckeditor.js", "/admin/assets/js/ckeditor-custom.js"));
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
}
