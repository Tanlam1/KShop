package com.kshop.main.controller.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.kshop.main.domain.Customers;
import com.kshop.main.domain.ShopCustomerVouchers;
import com.kshop.main.domain.ShopVouchers;
import com.kshop.main.domain.Users;
import com.kshop.main.service.CustomerService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.ShopCustomerVouchersService;
import com.kshop.main.service.ShopVouchersService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.service.UsersService;
import com.kshop.main.utils.Roles;


@RestController
@RequestMapping("/ShopCustomerVouchers")
@CrossOrigin
public class ShopCustomerVouchersController {
	
	@Autowired
	ShopCustomerVouchersService shopCustomerVouchersService;
	@Autowired
	ShopVouchersService shopVouchersService;
	@Autowired
	SessionService sessionService;
	@Autowired
	UserHasRolesService userHasRolesService;
	@Autowired
	UsersService usersService;
	@Autowired
	CustomerService customerService;
	
	/** Api get all ShopCustomerVouchers 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all ShopCustomerVouchers
	public List<ShopCustomerVouchers> getAllShopCustomerVouchers() {
		List<ShopCustomerVouchers> list = shopCustomerVouchersService.findAll();
		for(ShopCustomerVouchers item: list) {
		    item.getCustomers().setPassword("");
		}
		return list;
	}
	
	@GetMapping("/get/used/{customer_id}") // api get all ShopCustomerVouchers
    public List<ShopCustomerVouchers> getAllShopCustomerId(@PathVariable Long customer_id) {
        List<ShopCustomerVouchers> list = shopCustomerVouchersService.selectByCustomerIdUsed(customer_id);
        for(ShopCustomerVouchers item: list) {
            item.getCustomers().setPassword("");
        }
        return list;
    }

	
	/** Api get last item in ShopCustomerVouchers 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public ShopCustomerVouchers getLastShopCustomerVouchers() {
		
		List<ShopCustomerVouchers> list = shopCustomerVouchersService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in ShopCustomerVouchers 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public ShopCustomerVouchers getFirstShopCustomerVouchers() {
		
		return shopCustomerVouchersService.findAll().get(0);
	}

	
	/** Api get item by id in ShopCustomerVouchers 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public ShopCustomerVouchers getShopVoucher(@PathVariable Long id) {
		Optional<ShopCustomerVouchers> itemShopCustomerVouchers = shopCustomerVouchersService.findById(id);
		if(itemShopCustomerVouchers.isPresent()) {
			return itemShopCustomerVouchers.get();
		}
		
		return null;
	}
	
	@GetMapping("/get/{customer_id}/{voucher_id}")
    public APIResponse getIsUse(@PathVariable Long customer_id, @PathVariable Long voucher_id) {
        boolean isUse = shopCustomerVouchersService.isUse(customer_id, voucher_id);
        APIResponse response = new APIResponse();
        if(isUse) {
            response.setData("Voucher này có thể sử dụng");
            response.setStatus(200);
            
        } else {
            response.setData("Bạn không thể sử dụng hoặc Voucher này đã được áp dụng cho tài khoản của bạn!");
            response.setStatus(400);
        }
        
        return response;
    }
    
    @PostMapping("/get/{customer_id}/{voucher_id}/apply")
    public APIResponse get(@PathVariable Long customer_id, @PathVariable Long voucher_id) {
        boolean isUse = shopCustomerVouchersService.isUse(customer_id, voucher_id);
        APIResponse response = new APIResponse();
        if(isUse) {
            ShopCustomerVouchers shopCustomerVouchers = shopCustomerVouchersService.selectByCustomerIdAndVoucherId(customer_id, voucher_id);
            shopCustomerVouchers.set_used(true);
            
            ShopVouchers shopVouchers = shopVouchersService.findById(voucher_id).get();
            shopVouchers.setUses(shopVouchers.getUses() + 1);
            
            shopVouchersService.save(shopVouchers);
            shopCustomerVouchersService.save(shopCustomerVouchers);
            response.setData("Áp dụng voucher thành công!");
            response.setStatus(200);
            
        } else {
            response.setData("Voucher này đã được áp dụng cho tài khoản của bạn!");
            response.setStatus(400);
        }
        
        return response;
    }
    
    @PostMapping("/get/{customer_id}/{voucher_id}/remove")
    public APIResponse removeCustomerVoucher(@PathVariable Long customer_id, @PathVariable Long voucher_id) {
        boolean isUse = shopCustomerVouchersService.isUse(customer_id, voucher_id);
        APIResponse response = new APIResponse();
        if(!isUse) {
            ShopCustomerVouchers shopCustomerVouchers = shopCustomerVouchersService.selectByCustomerIdAndVoucherId(customer_id, voucher_id);
            shopCustomerVouchers.set_used(false);
            
            ShopVouchers shopVouchers = shopVouchersService.findById(voucher_id).get();
            int uses = shopVouchers.getUses() - 1;
            shopVouchers.setUses(uses < 1 ? 1 : uses);
            
            shopVouchersService.save(shopVouchers);
            shopCustomerVouchersService.save(shopCustomerVouchers);
            response.setData("Đã xóa voucher khỏi giỏ hàng của bạn!");
            response.setStatus(200);
            
        } else {
            response.setData("Voucher này chưa được áp dụng cho tài khoản của bạn!");
            response.setStatus(400);
        }
        
        return response;
    }

	
	/** Api add item to ShopCustomerVouchers 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public ShopCustomerVouchers addShopCustomerVouchers(@RequestBody ShopCustomerVouchers ShopCustomerVouchers) {
		
		shopCustomerVouchersService.save(ShopCustomerVouchers);
		
		return getLastShopCustomerVouchers();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public ShopCustomerVouchers updateShopCustomerVouchers(@RequestBody ShopCustomerVouchers ShopCustomerVouchers) {
		
		shopCustomerVouchersService.save(ShopCustomerVouchers);
		
		return shopCustomerVouchersService.findById(ShopCustomerVouchers.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public ShopCustomerVouchers deleteShopCustomerVouchers(@PathVariable Long id) {
		
		ShopCustomerVouchers item = shopCustomerVouchersService.findById(id).get();
		
		shopCustomerVouchersService.delete(item);
		
		return item;
	}
	
	@DeleteMapping("/delete/customer/{id}")
    public APIResponse deleteAllByCustomerId(@PathVariable Long id) {
        
        
        List<ShopCustomerVouchers> list = shopCustomerVouchersService.selectByCustomerIdUsed(id);
        
        for(ShopCustomerVouchers item: list) {
            shopCustomerVouchersService.delete(item);
        }
        
        return new APIResponse();
    }
    
    @GetMapping("/get/voucherid/{voucher_id}")
    public APIResponse getByCustomerId(@PathVariable Long voucher_id) {
        APIResponse response = new APIResponse();
        Users userLogined = sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        if(userLogined != null) {
            Users userDB = usersService.getById(userLogined.getId());
            Optional<ShopVouchers> voucher = shopVouchersService.findById(voucher_id);                       
            
            if(voucher.isEmpty()) {
                response.setStatus(400);
                response.setError("Voucher not defined");
                response.setData("Mã giảm giá không tồn tại.");
                
                return response;
                
            }

            if(voucher.get().getUse_by() == null) {
                response.setStatus(400);
                response.setError("Voucher not reg");
                response.setData("Bạn chưa đăng ký mã giảm giá này.");
                
                return response;
            }

            if(!voucher.get().getUse_by().equals(userDB.getId())) {

                response.setStatus(400);
                response.setError("Voucher not access");
                response.setData("Bạn không thể truy cập mã giảm giá này.");
                
                return response;
            }
            
            List<ShopCustomerVouchers> list = shopCustomerVouchersService.selectsByVoucherId(voucher_id);
            Map<String, Object> _map = new HashMap<>();
            List<Map<String, Object>> listRp = new ArrayList<>(); 
            list.stream().forEach(e -> {
                Map<String, Object> map = new HashMap<>();
                Map<String, Object> maps = new HashMap<>();
                maps.put("id", e.getCustomers().getId());
                maps.put("username", e.getCustomers().getUsername());
                map.put("customer", maps);
                listRp.add(map);
            });
            _map.put("customers", listRp);
            response.setData(_map);
        } else {
            response.setStatus(400);
            response.setError("Token error");
            response.setData("Token đã hết hạn hoặc không tồn tại. Vui lòng thử lại.");
        }
        return response;
    }
    
    @PostMapping("/adds")
    public APIResponse addsVoucher(@RequestBody List<ShopCustomerVouchers> body) {
        APIResponse response = new APIResponse();
        Users userLogined = sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        if(userLogined != null) {
            Users userDB = usersService.getById(userLogined.getId());
            Optional<ShopVouchers> voucher = shopVouchersService.findById(body.get(0).getVouchers().getId());                       
            
            if(voucher.isEmpty()) {
                response.setStatus(400);
                response.setError("Voucher not defined");
                response.setData("Mã giảm giá không tồn tại.");
                
                return response;
                
            }

            if(voucher.get().getUse_by() == null) {
                response.setStatus(400);
                response.setError("Voucher not reg");
                response.setData("Bạn chưa đăng ký mã giảm giá này.");
                
                return response;
            }

            if(!voucher.get().getUse_by().equals(userDB.getId())) {

                response.setStatus(400);
                response.setError("Voucher not access");
                response.setData("Bạn không thể truy cập mã giảm giá này.");
                
                return response;
            }
            
            for(ShopCustomerVouchers item: body) {
                ShopCustomerVouchers customerVoucher = shopCustomerVouchersService.selectByCustomerIdAndVoucherId(item.getCustomers().getId(), body.get(0).getVouchers().getId());
                
                if(customerVoucher != null) {

                    response.setStatus(400);
                    response.setError("Voucher error add");
                    response.setData("Khách hàng đã được thêm mã này rồi.");
                    
                    return response;
                }
            }
            
            shopCustomerVouchersService.saveAll(body);
            response.setData("Lưu thành công mã giảm giá của khách hàng.");
            
        } else {
            response.setStatus(400);
            response.setError("Token error");
            response.setData("Token đã hết hạn hoặc không tồn tại. Vui lòng thử lại.");
        }
        return response;
    }
}
