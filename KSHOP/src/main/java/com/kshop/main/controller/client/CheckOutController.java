package com.kshop.main.controller.client;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kshop.main.common.Constant;
import com.kshop.main.domain.Customers;
import com.kshop.main.domain.ShopUsersCart;
import com.kshop.main.service.CustomerService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.ShopUsersCartService;
import com.kshop.main.vnpay.Config;

@Controller
@RequestMapping("/checkout")
public class CheckOutController extends com.kshop.main.controller.Controller {
	
    @Autowired
    ShopUsersCartService shopUsersCartService;
    @Autowired
    SessionService sessionService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @Autowired
    CustomerService customerService;
    
    private boolean isCheckout() {
        Customers customers = sessionService.get("userLogin", null);
        List<ShopUsersCart> list = shopUsersCartService.selectAllByCustomerId(customers.getId());
        
        boolean isCheckout = list.stream().anyMatch(e -> e.is_checked());
        
        return isCheckout;
    }
	
	@GetMapping("")
	public String layout(Model model) throws IOException {

        Customers customer = sessionService.get(Constant.SESSION_LOGIN_TYPE.CLIENT, null);
        if(customer == null) {
            response.sendRedirect("/auth/login");
        }
	    if(!isCheckout()) {
	        response.sendRedirect("/cart");
	    }
        Customers customerDB = customerService.findById(customer.getId()).get();
	    
	    Map fields = new HashMap();
        Enumeration params_req = request.getParameterNames();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String activeCode = customerDB.getActivateCode();
        
        boolean status = false;
        
        if(activeCode != null) {
            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
            String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
            String[] activeCodes = activeCode.split("\\|");
            if (fields.containsKey("vnp_SecureHashType")) {
                fields.remove("vnp_SecureHashType");
            }
            if (fields.containsKey("vnp_SecureHash")) {
                fields.remove("vnp_SecureHash");
            }
            String signValue = Config.hashAllFields(fields);
            
            if (signValue.equals(vnp_SecureHash)) {
                if ("00".equals(request.getParameter("vnp_TransactionStatus")) && 
                        activeCodes[0].equals(String.valueOf(customerDB.getId())) && 
                        activeCode.equals(vnp_OrderInfo)) {
                    
                    status = true;
                } else {
                    
                    //false payment method
                }
                
                customerDB.setActivateCode(null);
                customerService.save(customerDB);
            } else {
                //false chữ ký
                
            }
        } else {
            // Else
        }
        
        if(!status) {

            if(request.getParameter("vnp_SecureHash") != null) {
                response.sendRedirect("/checkout");
            }            
        }
        
        
        objsDataLayout.put("status", status);
	    
	    dataLayoutMaster.setView("client/checkout/checkout");
	    dataLayoutMaster.setMainCss("css/style.min.css");
	    dataLayoutMaster.setJsList(Arrays.asList("/js/checkout/checkout.js"));	    
	    
	    dataLayoutMaster.setObjs(objsDataLayout);
	    
	    model.addAttribute(__CONTENT__, dataLayoutMaster);
	    
	    return clientLayout;
	}
	
	
}
