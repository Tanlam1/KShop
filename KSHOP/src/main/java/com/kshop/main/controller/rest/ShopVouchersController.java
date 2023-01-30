package com.kshop.main.controller.rest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
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
import com.kshop.main.domain.ShopVouchers;
import com.kshop.main.domain.Users;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.ShopVouchersService;
import com.kshop.main.service.UsersService;


@RestController
@RequestMapping("/ShopVouchers")
@CrossOrigin
public class ShopVouchersController {
	
	@Autowired
	ShopVouchersService shopVouchersService;
    @Autowired
    SessionService sessionService;
    @Autowired
    UsersService usersService;

	
	/** Api get all ShopVouchers 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all ShopVouchers
	public List<ShopVouchers> getAllShopVouchers() {
		
		return shopVouchersService.findAll();
	}

	/** Api get all Products 
     * 
     * - GET Method: __/get
     * - Return(JSON): item
     * 
     * */
    @GetMapping("/get/{start}/{total}") // api get all Products
    public List<ShopVouchers> getPageCoupon(@PathVariable int start, @PathVariable int total) {
        
        List<ShopVouchers> list = shopVouchersService.findAll();
        
        Collections.reverse(list);
        int toStart = start*total;
	        // 12
	        // start-total
	        // 0-5 : toStart = 0*5, toIndex = toStart+total = 5 
	        // 1-5 : toStart = 1*5, toIndex = toStart+total = 10
	        // 2-5 : toStart = 2*5, toIndex = toStart+total = 15 => 12
        int toIndex = total + toStart;
        toIndex = toIndex > list.size() ? list.size() : toIndex;
        
        return list.subList(toStart, toIndex);
    }
	
	/** Api get last item in ShopVouchers 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public ShopVouchers getLastShopVouchers() {
		
		List<ShopVouchers> list = shopVouchersService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in ShopVouchers 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public ShopVouchers getFirstShopVouchers() {
		
		return shopVouchersService.findAll().get(0);
	}

	
	/** Api get item by id in ShopVouchers 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public ShopVouchers getShopVoucher(@PathVariable Long id) {
		Optional<ShopVouchers> itemShopVouchers = shopVouchersService.findById(id);
		if(itemShopVouchers.isPresent()) {
			return itemShopVouchers.get();
		}
		
		return null;
	}
	
	@GetMapping("/get/vouchername/{voucher_name}")
    public ShopVouchers getByVoucherName(@PathVariable String voucher_name) {
        Optional<ShopVouchers> itemShopVouchers = shopVouchersService.selectByVoucherName(voucher_name);
        if(itemShopVouchers.isPresent()) {
            return itemShopVouchers.get();
        }
        
        return new ShopVouchers();
    }

	
	/** Api add item to ShopVouchers 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public ShopVouchers addShopVouchers(@RequestBody ShopVouchers shopVouchers) {
		
		shopVouchersService.save(shopVouchers);
		
		return getLastShopVouchers();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public ShopVouchers updateShopVouchers(@RequestBody ShopVouchers shopVouchers) {
        
        Optional<ShopVouchers> voucherDB = shopVouchersService.findById(shopVouchers.getId());
        if(voucherDB.isPresent()) {
            shopVouchers.setUse_by(voucherDB.get().getUse_by());
        }
		
		shopVouchersService.save(shopVouchers);
		
		return shopVouchersService.findById(shopVouchers.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public ShopVouchers deleteShopVouchers(@PathVariable Long id) {
		
		ShopVouchers item = shopVouchersService.findById(id).get();
		
		shopVouchersService.delete(item);
		
		return item;
	}
	
	@GetMapping("/regVoucher/{id}")
	public APIResponse regVoucher(@PathVariable Long id) {
	    
	    APIResponse response = new APIResponse();
	    Users userLogined = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
	    
	    if(userLogined == null) {
	        response.setStatus(400);
            response.setError("Can not GET. Error code: 400");
            response.setData("Token đã hết hạn hoặc không tồn tại. Vui lòng thử lại.");
            
            return response;
	    }
	    
        Users userDB = usersService.findById(userLogined.getId()).get();
	    Optional<ShopVouchers> voucher = shopVouchersService.findById(id);
	    if(voucher.isEmpty()) {
            response.setStatus(400);
            response.setError("Not found voucher. Error code: 400");
            response.setData("Không tìm thấy voucher này.");
            
            return response;
	        
	    }
	    
        if(voucher.get().getUse_by() != null) {
            response.setStatus(400);
            response.setError("Can not reg voucher. Error by unique");
            response.setData("Bạn không thể đăng ký voucher này.");
            
            return response;
            
        }
        
        if(userDB.getSodu() < voucher.get().getPrice()) {
            response.setStatus(400);
            response.setError("Can not reg voucher. Error by price");
            response.setData("Tài khoản của bạn không đủ tiền để đăng ký voucher này. Vui lòng nạp thêm");
            
            return response;
            
        }
        
        //trừ tiền user
        if(voucher.get().getPrice() > 0) {
            
            userDB.setSodu(userDB.getSodu() - voucher.get().getPrice());
            usersService.save(userDB);
        }
        
        voucher.get().setUse_by(userLogined.getId());
        shopVouchersService.save(voucher.get());
        response.setData("Đăng ký thành công voucher. Voucher: " + voucher.get().getVoucher_name());
        
	    return response;
	    
	}
}
