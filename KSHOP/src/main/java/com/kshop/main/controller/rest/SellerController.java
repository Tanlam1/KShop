package com.kshop.main.controller.rest;

import java.util.Collections;
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
import com.kshop.main.domain.EmailDetails;
import com.kshop.main.domain.Seller;
import com.kshop.main.domain.UserHasRoles;
import com.kshop.main.domain.Users;
import com.kshop.main.service.CustomerService;
import com.kshop.main.service.EmailService;
import com.kshop.main.service.SellerService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;


@RestController
@RequestMapping("/Seller")
@CrossOrigin
public class SellerController {
	
	@Autowired
	SellerService sellerService;
	@Autowired
	SessionService sessionService;
	@Autowired
	UserHasRolesService userHasRolesService;
	@Autowired
	private EmailService emailService;
	@Autowired
	CustomerService customerService;

	
	/** Api get all Seller 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all Seller
	public APIResponse getAllSeller() {
	    
	    APIResponse response = new APIResponse();
	    Users userLogined = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        
        if(userLogined == null) {
            
            response.setStatus(19);
            response.setError("Can not GET /get. Error code: 19");
            response.setData("Token đã hết hạn hoặc token này không thể truy cập. Vui lòng đăng nhập lại.");
            
            return response;
        }
	    
	    List<Seller> list = sellerService.findAll();
	    
	    for(Seller item : list) {
	        item.getCustomer().setPassword(null);
	    }
	    
	    Map<String, Object> map = new HashMap();
        
        map.put("sellers", list);
        response.setData(map);
        response.setStatus(200);
		
		return response;
	}
	
	/** Api get start total item in Seller 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/get/{start}/{total}")
	public APIResponse getPageSeller(@PathVariable int start, @PathVariable int total){
		
		APIResponse response = new APIResponse();
		Users userLogined = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
		
		if(userLogined == null) {
            
            response.setStatus(19);
            response.setError("Can not GET /get/limit/total. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");
            
            return response;
        }
		
		List<Seller> list = sellerService.findAll();
		
		
		Collections.reverse(list);
        int toStart = start*total;
        int toIndex = total + toStart;
        toIndex = toIndex > list.size() ? list.size() : toIndex;
        response.setStatus(200);
        response.setData(list.subList(toStart, toIndex));
        
        return response;
	}
	
	/** Api get last item in Seller 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public Seller getLastSeller() {
		
		List<Seller> list = sellerService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in Seller 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public Seller getFirstSeller() {
		
		return sellerService.findAll().get(0);
	}

	
	/** Api get item by id in Seller 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public APIResponse getSeller(@PathVariable Long id) {
	    APIResponse response = new APIResponse();
        Users userLogined = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        
        if(userLogined == null) {
            
            response.setStatus(19);
            response.setError("Can not GET /get. Error code: 19");
            response.setData("Token đã hết hạn hoặc token này không thể truy cập. Vui lòng đăng nhập lại.");
            
            return response;
        }
		Optional<Seller> itemSeller = sellerService.findById(id);
		if(itemSeller.isEmpty()) {
		    response.setStatus(400);
            response.setError("Seller not found by id");
            response.setData("Không tìm thấy kết quả nào.");
            
            return response;
		} 
		
		itemSeller.get().getCustomer().setPassword(null);
		response.setData(itemSeller.get());
        response.setStatus(200);
		
		return response;
	}

	
	/** Api get all item by customer id in Seller 
     * 
     * - GET Method: __/get/customerid/{id}
     * - Return(JSON): items
     * 
     * */
    @GetMapping("/get/customerid/{id}")
    public APIResponse getSellerByCId(@PathVariable Long id) {
        APIResponse response = new APIResponse();
        Users userLogined = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        
        if(userLogined == null) {
            
            response.setStatus(19);
            response.setError("Can not GET /get. Error code: 19");
            response.setData("Token đã hết hạn hoặc token này không thể truy cập. Vui lòng đăng nhập lại.");
            
            return response;
        }
        
        Seller itemSeller = sellerService.selectByCustomerId(id);
        if(itemSeller == null) {
            response.setStatus(400);
            response.setError("Seller not found by id customer");
            response.setData("Không tìm thấy kết quả nào.");
            
            return response;
        } 
        
        itemSeller.getCustomer().setPassword(null);
        
        response.setData(itemSeller);
        response.setStatus(200);
        
        return response;
    }
	
	/** Api add item to Seller 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */    
	@PostMapping("/add")
	public APIResponse addSeller(@RequestBody Seller Seller) {
				
		return sellerService.addNewSeller(Seller);
	}
	
	
    @PostMapping("/accept/{id}")
    public APIResponse accept(@PathVariable Long id) {
                
        return sellerService.acceptSeller(id);
    }

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public Seller updateSeller(@RequestBody Seller Seller) {
		
		sellerService.save(Seller);
		
		return sellerService.findById(Seller.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public APIResponse deleteSeller(@PathVariable Long id) {
		
	    APIResponse response = new APIResponse();

        Users userLogin = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);

        if (userLogin == null) {
            response.setError("Error login token expired");
            response.setData("Mã đăng nhập hết hạn. Vui lòng đăng nhập lại.");
            response.setStatus(400);

            return response;
        }

        List<UserHasRoles> list = userHasRolesService.selectsByUserId(userLogin.getId());

        boolean isAdmin = list.stream().anyMatch(e -> e.getRoles().getId() == 1);

        if (!isAdmin) {
            response.setError("Error this token does not have the right.");
            response.setData("Bạn không có quyền tương tác đến chức năng được chỉ định.");
            response.setStatus(400);

            return response;
        }
	    
		Optional<Seller> item = sellerService.findById(id);
		
		if(item.isEmpty()) {
            response.setError("Not found by id.");
            response.setData("Không tìm thấy id này.");
            response.setStatus(400);

            return response;
		    
		}
		
		if(item.get().isStatus()) {
            response.setError("Store can not delete.");
            response.setData("Không thể thay đổi trạng thái của người bán này.");
            response.setStatus(400);

            return response;
            
        }
		
		sellerService.delete(item.get());
        response.setData("Từ chối thành công người đăng ký bán này.");
        response.setStatus(200);
        
        String content = "<div style=\"padding: 14px;border-radius:8px;margin:0 14px;font-size:15px;font-weight:bold;color: #181546;text-align:center\">"
                + "Rất tiếc, yêu cầu mở shop của bạn chưa được chấp thuận. Vui lòng xem lại điều khoản của chúng tôi và thử lại.<br> Cảm ơn bạn.\r\n"
                + "</div>";
        
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(item.get().getEmail());
        emailDetails.setSubject("Thông báo đăng ký bán hàng | KShop - Mua hàng online giá tốt");
        emailDetails.setMsgBody(content);
        
        emailService.sendMailWithAttachment(emailDetails);
		
		return response;
	}
}
