package com.kshop.main.controller.admin;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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
import com.kshop.main.domain.Customers;
import com.kshop.main.domain.Products;
import com.kshop.main.domain.ShopVouchers;
import com.kshop.main.domain.Users;
import com.kshop.main.service.CustomerService;
import com.kshop.main.service.ProductsService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.ShopVouchersService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.utils.Roles;

@Controller
@RequestMapping("/admin-panel")
public class CouponsAdminController extends com.kshop.main.controller.Controller {
    
	@Autowired
	ShopVouchersService shopVouchersService;
	@Autowired
    HttpServletResponse response;
	@Autowired
	SessionService sessionService;
	@Autowired
	UserHasRolesService userHasRolesService;
	@Autowired
	ProductsService productsService;
	@Autowired
	CustomerService customerService;
	
	private List<Long> listRole = Arrays.asList(1L);
	
    @GetMapping("coupons")
    public String index(Model model) throws IOException {      
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        } 
        dataLayoutMaster.setView("admin/coupon/coupon");
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/js/coupon/coupon.js"));
                
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
    
    @GetMapping("create/coupon")
    public String create(Model model) throws IOException {      
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        } 
    	ShopVouchers shopVouchers = new ShopVouchers();
    	shopVouchers.setId(-1L);
    	shopVouchers.setStart_date(new Date());
    	shopVouchers.setEnd_date(new Date());
        
        objsDataLayout.put("shopVouchers", shopVouchers);
        dataLayoutMaster.setView("admin/coupon/create");
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/js/coupon/add.js", "/admin/assets/js/ckeditor.js", "/admin/assets/js/ckeditor-custom.js"));        
        
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
    
    @GetMapping("edit/coupon/{idVoucher}")
    public String edit(Model model, @PathVariable Long idVoucher)  throws IOException {
        
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        } 
        
    	Optional<ShopVouchers> shopVouchers = shopVouchersService.findById(idVoucher);
        
        if(!shopVouchers.isPresent()) {
            
            objsDataLayout.put("shopVouchers", new ShopVouchers());
            response.sendRedirect("/admin-panel/coupons");
        } else {
            
            objsDataLayout.put("shopVouchers", shopVouchers.get());
        }
        
        dataLayoutMaster.setView("admin/coupon/create");
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/js/coupon/add.js", "/admin/assets/js/ckeditor.js", "/admin/assets/js/ckeditor-custom.js"));        
       
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
    
    @GetMapping("shop-voucher")
    public String shoper(Model model) throws IOException {      
        boolean isRole = Roles.UserHasRoles(Arrays.asList(1L, 4L), sessionService, userHasRolesService);
        if(!isRole) {
            response.sendRedirect("/404");
        } 
        Users userLogined = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        dataLayoutMaster.setView("admin/shop-voucher/shop-voucher");
        List<ShopVouchers> list = shopVouchersService.findAll();
        Collections.reverse(list);
        List<ShopVouchers> listRegisted = list.stream().filter(e -> e.getUse_by() != null && e.getUse_by().equals(userLogined.getId())).toList();
        List<ShopVouchers> listNotReg= list.stream().filter(e -> e.getUse_by() == null).toList();
        
        dataLayoutMaster.setJsList(Arrays.asList("/admin/assets/js/shop-voucher/shop-voucher.js"));
        objsDataLayout.put("vouchers", list);
        objsDataLayout.put("vouchersNotReg", listNotReg);
        objsDataLayout.put("vouchersRegisted", listRegisted);
        
        List<Products> products = productsService.selectsAllByUserId(userLogined.getId());
        objsDataLayout.put("products", products);
        
        List<Customers> customers = customerService.findAll();
        objsDataLayout.put("customers", customers);
        
        dataLayoutMaster.setObjs(objsDataLayout);
        
        model.addAttribute(__CONTENT__, dataLayoutMaster);
        
        return adminLayout;
    }
}
