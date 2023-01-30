package com.kshop.main.controller.rest;

import java.util.Iterator;
import java.util.List;
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
import com.kshop.main.domain.Customers;
import com.kshop.main.domain.ShopProductVouchers;
import com.kshop.main.domain.ShopUsersCart;
import com.kshop.main.domain.ShopVouchers;
import com.kshop.main.domain.Users;
import com.kshop.main.service.CustomerService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.ShopProductVouchersService;
import com.kshop.main.service.ShopUsersCartService;
import com.kshop.main.service.ShopVouchersService;


@RestController
@RequestMapping("/ShopUsersCart")
@CrossOrigin
public class ShopUsersCartController {
	
	@Autowired
	ShopUsersCartService shopUsersCartService;
	@Autowired
	SessionService sessionService;
	@Autowired
	ShopProductVouchersService shopProductVouchersService;
	@Autowired
	ShopVouchersService shopVouchersService;
	@Autowired
	CustomerService customerService;


	private List<ShopUsersCart> hiddenPassword(List<ShopUsersCart> list) {
	    for(ShopUsersCart item : list) {
	        item.getCustomer().setPassword("");
	    }
	    return list;
	}
	
	private ShopUsersCart hiddenPassword_(ShopUsersCart item) {
        item.getCustomer().setPassword("");
        return item;
    }
	
	/** Api get all ShopUsersCart 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all ShopUsersCart
	public List<ShopUsersCart> getAllShopUsersCart() {
	    
		return hiddenPassword(shopUsersCartService.findAll());
	}

	
	/** Api get last item in ShopUsersCart 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public ShopUsersCart getLastShopUsersCart() {
		
		List<ShopUsersCart> list = shopUsersCartService.findAll();
		int totalItem = list.size();
		
		return hiddenPassword_(list.get(totalItem - 1));
	}

	
	/** Api get first item in ShopUsersCart 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public ShopUsersCart getFirstShopUsersCart() {
		
		return hiddenPassword_(shopUsersCartService.findAll().get(0));
	}

	
	/** Api get item by id in ShopUsersCart 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public ShopUsersCart getShopOrder(@PathVariable Long id) {
		Optional<ShopUsersCart> itemShopUsersCart = shopUsersCartService.findById(id);
		if(itemShopUsersCart.isPresent()) {
			return hiddenPassword_(itemShopUsersCart.get());
		} 
		
		return null;
	}
	
	@GetMapping("/get/CustomerId/{id}")
    public List<ShopUsersCart> getByIdCustomer(@PathVariable Long id) {
        List<ShopUsersCart> itemShopUsersCart = shopUsersCartService.selectAllByCustomerId(id);
        if(!itemShopUsersCart.isEmpty()) {
            return hiddenPassword(itemShopUsersCart);
        } 
        
        return null;
    }
	
	/** Api add item to ShopUsersCart 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): response
	 * 
	 * */
	@PostMapping("/add")
	public APIResponse addShopUsersCart(@RequestBody ShopUsersCart ShopUsersCart) {
		
	    APIResponse response = new APIResponse();
	    Optional<Customers> customer = customerService.findById(ShopUsersCart.getCustomer().getId());
        if(customer.isEmpty()) {
            
            response.setStatus(19);
            response.setError("Can not POST /add. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");
            
            return response;
        }
        response.setStatus(41609);
        response.setData("Error!");
        response.setError("Error vui lòng thử lại");
        
        if(ShopUsersCart.getQuantity() < 1) {
            response.setData("Số lượng không hợp lệ");
            response.setStatus(21);
            response.setError("Error quantity!");
            
            return response;
        }
		
	    boolean result = shopUsersCartService.addToCart(ShopUsersCart, customer.get().getId());
	    if(result) {
	        response.setData("Success");
	        response.setStatus(200);
	        response.setError("");
        }
		
		return response;
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public ShopUsersCart updateShopUsersCart(@RequestBody ShopUsersCart ShopUsersCart) {
		
		shopUsersCartService.save(ShopUsersCart);
		
		return hiddenPassword_(shopUsersCartService.findById(ShopUsersCart.getId()).get());
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public ShopUsersCart deleteShopUsersCart(@PathVariable Long id) {
		
		ShopUsersCart item = shopUsersCartService.findById(id).get();
		
		if(item.is_discount()) {
		    
		    ShopProductVouchers shopProductVouchers = item.getShopProductVouchers();
		    
		    Optional<ShopVouchers> shopVouchers = shopVouchersService.findById(shopProductVouchers.getVouchers().getId());
		    
		    if(shopVouchers.isPresent()) {
		        ShopVouchers shopVouchers2 = shopVouchers.get();
		        int uses = (shopVouchers2.getUses() - 1) < 1 ? 1 : shopVouchers2.getUses() - 1;
		        shopVouchers2.setUses(uses);
		        shopVouchersService.save(shopVouchers.get());
		    }
		}
		
		shopUsersCartService.delete(item);
		
		return hiddenPassword_(item);
	}
	
	@DeleteMapping("/delete/customerid/{customer_id}")
    public APIResponse deleteShopUsersCartAll(@PathVariable Long customer_id) {
        
        List<ShopUsersCart> list = shopUsersCartService.selectAllByCustomerId(customer_id);
        
        for(ShopUsersCart item: list) {
            if(item.is_checked()) {
                shopUsersCartService.delete(item);
            }
        }
        
        return new APIResponse();
    }
}
