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
import com.kshop.main.domain.Products;
import com.kshop.main.domain.ShopCustomerVouchers;
import com.kshop.main.domain.ShopProductVouchers;
import com.kshop.main.domain.ShopVouchers;
import com.kshop.main.domain.Users;
import com.kshop.main.service.CustomerService;
import com.kshop.main.service.ProductsService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.ShopCustomerVouchersService;
import com.kshop.main.service.ShopProductVouchersService;
import com.kshop.main.service.ShopVouchersService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.service.UsersService;


@RestController
@RequestMapping("/ShopProductVouchers")
@CrossOrigin
public class ShopProductVouchersController {
	
	@Autowired
	ShopProductVouchersService shopProductVouchersService;
	@Autowired
	ShopVouchersService shopVouchersService;
	@Autowired
    ShopCustomerVouchersService shopCustomerVouchersService;
    @Autowired
    SessionService sessionService;
    @Autowired
    UserHasRolesService userHasRolesService;
    @Autowired
    UsersService usersService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductsService productsService;

	
	/** Api get all ShopProductVouchers 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all ShopProductVouchers
	public List<ShopProductVouchers> getAllShopProductVouchers() {
		List<ShopProductVouchers> list = shopProductVouchersService.findAll();
		
		return list;
	}
	
	@GetMapping("/get/used/{product_id}") // api get all ShopProductVouchers
    public List<ShopProductVouchers> getAllShopCustomerId(@PathVariable Long product_id) {
        List<ShopProductVouchers> list = shopProductVouchersService.selectsByProductId(product_id);
        
        return list;
    }

	
	/** Api get last item in ShopProductVouchers 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public ShopProductVouchers getLastShopProductVouchers() {
		
		List<ShopProductVouchers> list = shopProductVouchersService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in ShopProductVouchers 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public ShopProductVouchers getFirstShopProductVouchers() {
		
		return shopProductVouchersService.findAll().get(0);
	}

	
	/** Api get item by id in ShopProductVouchers 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public ShopProductVouchers getShopVoucher(@PathVariable Long id) {
		Optional<ShopProductVouchers> itemShopProductVouchers = shopProductVouchersService.findById(id);
		if(itemShopProductVouchers.isPresent()) {
			return itemShopProductVouchers.get();
		}
		
		return null;
	}
	
	@GetMapping("/get/productid/{id}")
    public List<ShopProductVouchers> getByProductId(@PathVariable Long id) {
	    List<ShopProductVouchers> itemShopProductVouchers = shopProductVouchersService.selectsByProductId(id);
        if(!itemShopProductVouchers.isEmpty()) {
            return itemShopProductVouchers;
        }
        
        return Arrays.asList();
    }
	
	@GetMapping("/get/{product_id}/{voucher_id}")
    public APIResponse getIsUse(@PathVariable Long product_id, @PathVariable Long voucher_id) {
        boolean isUse = shopProductVouchersService.isUse(product_id, voucher_id);
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
    
    @GetMapping("/get/{product_id}/{voucher_id}/get")
    public APIResponse get(@PathVariable Long product_id, @PathVariable Long voucher_id) {
        boolean isUse = shopProductVouchersService.isUse(product_id, voucher_id);
        APIResponse response = new APIResponse();
        if(isUse) {
            ShopProductVouchers ShopProductVouchers = shopProductVouchersService.selectByProductIdAndVoucherId(product_id, voucher_id);
            
            ShopVouchers shopVouchers = shopVouchersService.findById(voucher_id).get();
            shopVouchers.setUses(shopVouchers.getUses() + 1);
            
            shopVouchersService.save(shopVouchers);
            
            response.setData(ShopProductVouchers);
            response.setStatus(200);
            
        } else {
            response.setData("Voucher của bạn không thể dùng cho sản phẩm này!");
            response.setStatus(400);
        }
        
        return response;
    }
    
    @PostMapping("/remove/{voucher_id}")
    public APIResponse removeCustomerVoucher(@PathVariable Long voucher_id) {
        APIResponse response = new APIResponse();
        ShopVouchers shopVouchers = shopVouchersService.findById(voucher_id).get();
        int uses = shopVouchers.getUses() - 1;
        shopVouchers.setUses(uses < 1 ? 1 : uses);
        
        shopVouchersService.save(shopVouchers);
        response.setData("Đã xóa voucher khỏi giỏ hàng của bạn!");
        response.setStatus(200);
        
        return response;
    }

	
	/** Api add item to ShopProductVouchers 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public ShopProductVouchers addShopProductVouchers(@RequestBody ShopProductVouchers ShopProductVouchers) {
		
		shopProductVouchersService.save(ShopProductVouchers);
		
		return getLastShopProductVouchers();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public ShopProductVouchers updateShopProductVouchers(@RequestBody ShopProductVouchers ShopProductVouchers) {
		
		shopProductVouchersService.save(ShopProductVouchers);
		
		return shopProductVouchersService.findById(ShopProductVouchers.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public ShopProductVouchers deleteShopProductVouchers(@PathVariable Long id) {
		
		ShopProductVouchers item = shopProductVouchersService.findById(id).get();
		
		shopProductVouchersService.delete(item);
		
		return item;
	}
	
	@GetMapping("/get/voucherid/{voucher_id}")
    public APIResponse getByVoucherId(@PathVariable Long voucher_id) {
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
            
            List<ShopProductVouchers> list = shopProductVouchersService.selectsByVoucherId(voucher_id);
            Map<String, Object> _map = new HashMap<>();
            List<Map<String, Object>> listRp = new ArrayList<>(); 
            list.stream().forEach(e -> {
                Map<String, Object> map = new HashMap<>();
                Map<String, Object> maps = new HashMap<>();
                maps.put("id", e.getProducts().getId());
                maps.put("product_name", e.getProducts().getProduct_name());
                map.put("product", maps);
                listRp.add(map);
            });
            _map.put("products", listRp);
            response.setData(_map);
        } else {
            response.setStatus(400);
            response.setError("Token error");
            response.setData("Token đã hết hạn hoặc không tồn tại. Vui lòng thử lại.");
        }
        return response;
    }
    
    @PostMapping("/adds")
    public APIResponse addsVoucher(@RequestBody List<ShopProductVouchers> body) {
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
            
            for(ShopProductVouchers item: body) {
                Products productGet = productsService.getById(item.getProducts().getId());
                
                if(!productGet.getUsers().getId().equals(userDB.getId())) {
                    System.out.println("aa2");
                    response.setStatus(400);
                    response.setError("Voucher error add");
                    response.setData("Bạn không thể truy cập sản phẩm này.");
                    
                    return response;
                }
                
                ShopProductVouchers productVoucher = shopProductVouchersService.selectByProductIdAndVoucherId(item.getProducts().getId(), body.get(0).getVouchers().getId());
                
                if(productVoucher != null) {

                    response.setStatus(400);
                    response.setError("Voucher error add");
                    response.setData("Sản phẩm này đã được thêm mã rồi.");
                    
                    return response;
                }
            }
            
            shopProductVouchersService.saveAll(body);
            response.setData("Lưu thành công mã giảm giá cho sản phẩm.");
            
        } else {
            response.setStatus(400);
            response.setError("Token error");
            response.setData("Token đã hết hạn hoặc không tồn tại. Vui lòng thử lại.");
        }
        return response;
    }
}
