package com.kshop.main.controller.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kshop.main.common.APIResponse;
import com.kshop.main.common.Constant;
import com.kshop.main.domain.ShopStores;
import com.kshop.main.domain.Users;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.ShopStoresService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.service.UsersService;
import com.kshop.main.utils.Roles;


@RestController
@RequestMapping("/ShopStores")
@CrossOrigin
public class ShopStoresController {
	
	@Autowired
	ShopStoresService shopStoresService;
	@Autowired
	UserHasRolesService userHasRolesService;
	@Autowired
	SessionService sessionService;
	@Autowired
	UsersService usersService;

	
	/** Api get all ShopStores 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all ShopStores
	public List<ShopStores> getAllShopStores() {
		
		return shopStoresService.findAll();
	}

	/** Api get last item in Category 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/get/{start}/{total}") // api get all Products
	public List<ShopStores> getPageStore(@PathVariable int start, @PathVariable int total){
		
		return shopStoresService.findAll(PageRequest.of(start, total)).getContent();
	}
	/** Api get last item in ShopStores 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public ShopStores getLastShopStores() {
		
		List<ShopStores> list = shopStoresService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in ShopStores 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public ShopStores getFirstShopStores() {
		
		return shopStoresService.findAll().get(0);
	}

	
	/** Api get item by id in ShopStores 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public ShopStores getShopVoucher(@PathVariable Long id) {
		Optional<ShopStores> itemShopStores = shopStoresService.findById(id);
		if(itemShopStores.isPresent()) {
			return itemShopStores.get();
		} 
		
		return null;
	}

	
	/** Api add item to ShopStores 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public ShopStores addShopStores(@RequestBody ShopStores ShopStores) {
		
		shopStoresService.save(ShopStores);
		
		return getLastShopStores();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public ShopStores updateShopStores(@RequestBody ShopStores ShopStores) {
		
		shopStoresService.save(ShopStores);
		
		return shopStoresService.findById(ShopStores.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public ShopStores deleteShopStores(@PathVariable Long id) {
		
		ShopStores item = shopStoresService.findById(id).get();
		
		shopStoresService.delete(item);
		
		return item;
	}
	
	@GetMapping("/min-pay/{start}/{total}")
	public APIResponse getMinPay(@PathVariable int start, @PathVariable int total) {

        Map<String, Object> map = new HashMap<>();
        APIResponse response = new APIResponse(); 
	    Users userLogin = sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
	    
	    if(userLogin == null) {
	        
	        response.setData(Constant.RESPONE.TOKEN_DIE);
	        response.setStatus(400);
	        response.setError("TOKEN DIE. Error: 400");
	        
	        return response;
	    }
	    
	    boolean isAdminMoney = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY), sessionService, userHasRolesService);	    	   
        
        if(!isAdminMoney) {
            
            response.setData(Constant.RESPONE.TOKEN_FAIL_ROLE);
            response.setStatus(400);
            response.setError("TOKEN FAIL ROLE. Error: 400");
            
            return response;
        }
        
        List<Users> listUser = usersService.findAll();
        Collections.reverse(listUser);
        int toStart = start*total;
        int toIndex = total + toStart;
        toIndex = toIndex > listUser.size() ? listUser.size() : toIndex;
        List<Map<String, Object>> listMap = new ArrayList<>();
        
        listUser.subList(toStart, toIndex).forEach(e -> {
            Map<String, Object> mapFE = new HashMap<>();
           boolean isPassed = (e.getSodu() - e.getSodu_ngoai() + e.getSodu_hold()) >= Constant.SHOP_PAY.MIN; 
           if(isPassed) {
               mapFE.put("pay_info_next", getPayInfoNext());
               
               //cập nhật lại pay của user
               if(e.getPay_info_next() == null && e.getSodu_hold() == 0) {
                   
                   String payNextt = (String) getPayInfoNext().get("pay_info");
                   e.setPay_info_next(payNextt);
                   e.setSodu_hold(Constant.SHOP_PAY.MIN);
                   e.setSodu(e.getSodu() - e.getSodu_hold());
                   
                   usersService.save(e);
               }
           } else {
               mapFE.put("pay_info_next", null);
           }
           mapFE.put("user", e);
           listMap.add(mapFE);
        });        	   
	    map.put("stores", listMap);
	    map.put("pay_content", getPayInfoNext());
        response.setData(map);
	    
	    return response;
	}
	
	public Map<String, Object> getPayInfoNext() {
	    Map<String, Object> map = new HashMap<>();
	    Date currDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);
        int month = cal.get(Calendar.MONTH) + 1;
        int datee = cal.get(Calendar.DATE);
        int year = cal.get(Calendar.YEAR);
        
        map.put("min_pay", Constant.SHOP_PAY.MIN);
        map.put("passed_date", datee);
        map.put("passed_month", month);
        
        String pay_info = "";
        int monthPay = (month + 1) > 12 ? (month - 11) : (month + 1);
        year = (month + 1) > 12 ? year + 1 : year;
        if(datee <= 16) {
            pay_info = "Ngày 01 tháng " + monthPay + " năm " + year + " (Đợt 1)";
        } else {
            pay_info = "Ngày 16 tháng " + monthPay + " năm " + year + " (Đợt 2)";
        }
        map.put("pay_info", pay_info);
        
        return map;
	}
}
