package com.kshop.main.controller.admin;


import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kshop.main.common.Constant;
import com.kshop.main.domain.MoneyOrders;
import com.kshop.main.domain.MoneySends;
import com.kshop.main.domain.ShopStores;
import com.kshop.main.service.MoneyOrdersService;
import com.kshop.main.service.MoneySendsService;
import com.kshop.main.service.OrderService;
import com.kshop.main.service.ProductReviewsService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.ShopStoresService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.utils.Roles;

@Controller
@RequestMapping("/admin-panel")
public class HomeAdminController extends com.kshop.main.controller.Controller {

    @Autowired
    HttpServletResponse response;
    @Autowired
    SessionService sessionService;
    @Autowired
    UserHasRolesService userHasRolesService;
    @Autowired
    MoneySendsService moneySendsService;
    @Autowired
    MoneyOrdersService moneyOrdersService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductReviewsService productReviewsService;
    @Autowired
    ShopStoresService shopStoresService;
    
    private List<Long> listRole = Arrays.asList(Constant.ROLES.ADMIN, Constant.ROLES.SHOPER, Constant.ROLES.ADMIN_MONEY);
    
	@GetMapping({"dashboards", ""})
	public String layout(Model model) throws IOException {      
        boolean isAdmin = Roles.UserHasRoles(listRole, sessionService, userHasRolesService);
        if(!isAdmin) {
            response.sendRedirect("/404");
        } 
        
        String rUri = sessionService.get("redirect-uri", null);
        
        if(rUri != null) { // kiểm tra xem có uri trước khi redirect qua login ko
            sessionService.remove("redirect-uri");
            response.sendRedirect(rUri);
        }
        Locale lc = new Locale("nv","VN"); // định dạng tiền VN
	    
	    dataLayoutMaster.setView("admin/home/home");

	    dataLayoutMaster.setJsList(Arrays.asList(
	            "/admin/assets/js/chart/apex-chart/moment.min.js", 
	            "/admin/assets/js/chart/apex-chart/apex-chart1.js",
	            "/admin/assets/js/chart/apex-chart/stock-prices.js",
	            "/admin/assets/js/chart/apex-chart/chart-custom1.js"));
	    
	    List<MoneySends> listMoneySendsDB = moneySendsService.findAll();
	    Double tongTienGD = listMoneySendsDB
	            .stream()
	            .map(MoneySends::getTotal_money)
	            .reduce(0D, (subtotal, e) -> subtotal + e);
	    
        NumberFormat nf = NumberFormat.getInstance(lc);
        String tongTienGDString = nf.format(tongTienGD);
        
        List<MoneyOrders> listMoneyOrders = moneyOrdersService.findAll();
        
        Double totalMoneyOrders = listMoneyOrders
            .stream()
            .map(MoneyOrders::getTotal_money)
            .reduce(0D, Double::sum);
	    
	    objsDataLayout.put("tongTienGD", tongTienGDString);
	    
	    objsDataLayout.put("tongDonHang", orderService.count());
        
        objsDataLayout.put("danhgia", productReviewsService.count());
        
        objsDataLayout.put("tongStore", shopStoresService.count());
	    
	    dataLayoutMaster.setObjs(objsDataLayout);
	    
	    model.addAttribute(__CONTENT__, dataLayoutMaster);
	    
	    return adminLayout;
	}
    
    @GetMapping("/logout")
    public String logout() {
        
        sessionService.remove(Constant.SESSION_LOGIN_TYPE.ADMIN);
        return "redirect:/admin-panel/";
    }
}
