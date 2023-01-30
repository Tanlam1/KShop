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
import com.kshop.main.domain.Users;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.service.UsersService;
import com.kshop.main.utils.Roles;

@Controller
@RequestMapping("/admin-panel")
public class UsersAdminController extends com.kshop.main.controller.Controller {
    
    @Autowired
    HttpServletResponse response;
    @Autowired
    SessionService sessionService;
    @Autowired
    UserHasRolesService userHasRolesService;
    @Autowired
    UsersService usersService;
    
    private List<Long> listRole = Arrays.asList(Constant.ROLES.ADMIN);
    
	@GetMapping("users")
	public String layout(Model model) throws IOException { 
        
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        }
	    
	    dataLayoutMaster.setView("admin/user/list");
	    dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/users/script.js"));
	    	    
	    dataLayoutMaster.setObjs(objsDataLayout);
	    
	    model.addAttribute(__CONTENT__, dataLayoutMaster);
	    
	    return adminLayout;
	}
    
	
    @GetMapping("add/users")
    public String add(Model model) throws IOException { 
        
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        }
        
        Users user = new Users();
        user.setStores(new ShopStores());
        user.setId(-1L);
        user.setCity("");
        
        objsDataLayout.put("user", user);
        
        dataLayoutMaster.setView("admin/user/add");
        
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/users/handle-user.js"));
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
    
    
    @GetMapping("edit/users/{id}")
    public String edit(Model model, @PathVariable Long id) throws IOException { 
        
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        }
        
        Optional<Users> user = usersService.findById(id);
        
        if(user.isEmpty()) {
            response.sendRedirect(Constant.URI.ADMIN + "/users");
        } else {
            
            boolean roleAdmin = Roles.UserArgHasRoles(Arrays.asList(Constant.ROLES.ADMIN), sessionService, userHasRolesService, user.get());
            boolean roleThuKho = Roles.UserArgHasRoles(Arrays.asList(Constant.ROLES.THU_KHO), sessionService, userHasRolesService, user.get());
            boolean roleKeToan = Roles.UserArgHasRoles(Arrays.asList(Constant.ROLES.KE_TOAN), sessionService, userHasRolesService, user.get());
            boolean roleShoper = Roles.UserArgHasRoles(Arrays.asList(Constant.ROLES.SHOPER), sessionService, userHasRolesService, user.get());
            boolean roleTuVan = Roles.UserArgHasRoles(Arrays.asList(Constant.ROLES.TU_VAN), sessionService, userHasRolesService, user.get());
            boolean roleChamSoc = Roles.UserArgHasRoles(Arrays.asList(Constant.ROLES.CHAM_SOC), sessionService, userHasRolesService, user.get());
            boolean roleMoneyAdmin = Roles.UserArgHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY), sessionService, userHasRolesService, user.get());
            boolean roleTroLy = Roles.UserArgHasRoles(Arrays.asList(Constant.ROLES.TRO_LY), sessionService, userHasRolesService, user.get());
            boolean roleChotDon = Roles.UserArgHasRoles(Arrays.asList(Constant.ROLES.CHOT_DON), sessionService, userHasRolesService, user.get());
            boolean roleUploadProduct = Roles.UserArgHasRoles(Arrays.asList(Constant.ROLES.UPLOAD_PRODUCT), sessionService, userHasRolesService, user.get());
            
            objsDataLayout.put("listRoles", Arrays.asList(roleAdmin, roleThuKho, roleKeToan, roleShoper, roleTuVan, roleChamSoc, roleMoneyAdmin, roleTroLy, roleChotDon, roleUploadProduct));            
            objsDataLayout.put("user", user.get());
        }
        
        dataLayoutMaster.setView("admin/user/add");
        
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/users/handle-user.js"));
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
}
