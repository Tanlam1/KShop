package com.kshop.main.controller.rest;

import java.util.Arrays;
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
import com.kshop.main.domain.ProductImages;
import com.kshop.main.domain.ProductTypeItem;
import com.kshop.main.domain.Products;
import com.kshop.main.domain.UserHasRoles;
import com.kshop.main.domain.Users;
import com.kshop.main.service.ProductTypeItemService;
import com.kshop.main.service.ProductsService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;


@RestController
@RequestMapping("/ProductTypeItem")
@CrossOrigin
public class ProductTypeItemController {
	
	@Autowired
	ProductTypeItemService productTypeItemService;
	@Autowired
    SessionService sessionService;
    @Autowired
    UserHasRolesService userHasRolesService;
    @Autowired
    ProductsService productsService;

	
	/** Api get all ProductTypeItem 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all ProductTypeItem
	public List<ProductTypeItem> getAllProductTypeItem() {
		
		return productTypeItemService.findAll();
	}

	
	/** Api get last item in ProductTypeItem 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public ProductTypeItem getLastProductTypeItem() {
		
		List<ProductTypeItem> list = productTypeItemService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in ProductTypeItem 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public ProductTypeItem getFirstProductTypeItem() {
		
		return productTypeItemService.findAll().get(0);
	}

	
	/** Api get item by id in ProductTypeItem 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public ProductTypeItem getShopVoucher(@PathVariable Long id) {
		Optional<ProductTypeItem> itemProductTypeItem = productTypeItemService.findById(id);
		if(itemProductTypeItem.isPresent()) {
		    itemProductTypeItem.get().getProducts().setUsers(null);
			return itemProductTypeItem.get();
		} 
		
		return null;
	}
    
    @GetMapping("/get/productid/{id}")
    public List<ProductTypeItem> getByProductId(@PathVariable Long id) {
        List<ProductTypeItem> productTypeItems = productTypeItemService.selectsByProductId(id);
        
        if(!productTypeItems.isEmpty()) {
            for(ProductTypeItem item: productTypeItems) {
                item.getProducts().setUsers(null);                
            }
            return productTypeItems;
        } 
        
        return Arrays.asList(new ProductTypeItem());
    }

	
	/** Api add item to ProductTypeItem 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public ProductTypeItem addProductTypeItem(@RequestBody ProductTypeItem ProductTypeItem) {
		
		productTypeItemService.save(ProductTypeItem);
		
		return getLastProductTypeItem();
	}
	
    @PostMapping("/adds")
    public APIResponse addsProductTypeItem(@RequestBody List<ProductTypeItem> list) {
        
        APIResponse response = new APIResponse();
        
        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);
        if(userLogined == null) {
            
            response.setStatus(19);
            response.setError("Can not POST /add. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");
            
            return response;
        }
        List<UserHasRoles> listRoles = userHasRolesService.selectsByUserId(userLogined.getId());
        
        boolean isSHOPER = listRoles.stream().anyMatch(e -> e.getRoles().getId() == 10);
        boolean isShopUpload = list.stream().allMatch(e -> {
            Products productsCheck = productsService.findById(e.getProducts().getId()).get();
            
            return productsCheck.getUsers().getId().equals(userLogined.getId());                
            
        });
        
        if(!isSHOPER) {
            
            response.setData("Bạn không có quyền này, vui lòng liên hệ quản lý của bạn.");
            response.setStatus(21);
            response.setError("Faild to upload product img");
        } else if(!isShopUpload) {

            response.setData("Không thể thêm type vào sản phẩm của shop khác");
            response.setStatus(61);
            response.setError("Faild to upload product img");            
        } else {
            
            for(ProductTypeItem item: list) {
                productTypeItemService.save(item);
            }
            
            response.setData("Thêm type cho product thành công");
            response.setStatus(200);
            
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
	public ProductTypeItem updateProductTypeItem(@RequestBody ProductTypeItem ProductTypeItem) {
		
		productTypeItemService.save(ProductTypeItem);
		
		return productTypeItemService.findById(ProductTypeItem.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public APIResponse deleteProductTypeItem(@PathVariable Long id) {
		
	    APIResponse response = new APIResponse();
        
        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);
        if(userLogined == null) {
            
            response.setStatus(19);
            response.setError("Can not POST /add. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");
            
            return response;
        }
        List<UserHasRoles> listRoles = userHasRolesService.selectsByUserId(userLogined.getId());
        Optional<ProductTypeItem> productTypeItem = productTypeItemService.findById(id);
        
        if(productTypeItem.isEmpty()) {

            response.setData("Không tìm thấy gì cả :<.");
            response.setStatus(404);
            response.setError("Failed in product type");    
            
            return response;
        }
        
        boolean isSHOPER = listRoles.stream().anyMatch(e -> e.getRoles().getId() == 10);
        boolean isShopAction = productTypeItem.get().getProducts().getUsers().getId().equals(userLogined.getId());
        
        
        if(!isSHOPER) {
            
            response.setData("Bạn không có quyền thực hiện thao tác này, vui lòng liên hệ quản lý của bạn.");
            response.setStatus(21);
            response.setError("Failed in product type");
        } else if(!isShopAction) {

            response.setData("Bạn không thể thực hiện thao tác trên sản phẩm này.");
            response.setStatus(61);
            response.setError("Failed in product type");            
        } else {
            
            productTypeItemService.delete(productTypeItem.get());
            
            response.setData("Xóa loại sản phẩm thành công.");
            response.setStatus(200);
            
        }           
        
        return response;
	}
    
    @DeleteMapping("/delete/all/productid/{id}")
    public APIResponse deleteAllByProductId(@PathVariable Long id) {
        
        APIResponse response = new APIResponse();
        
        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);
        if(userLogined == null) {
            
            response.setStatus(19);
            response.setError("Can not POST /add. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");
            
            return response;
        }
        List<UserHasRoles> listRoles = userHasRolesService.selectsByUserId(userLogined.getId());
        List<ProductTypeItem> list = productTypeItemService.selectsByProductId(id);
        
        boolean isSHOPER = listRoles.stream().anyMatch(e -> e.getRoles().getId() == 10);
        Products product = productsService.findById(id).get();
        boolean isShopAction = product != null && product.getUsers().getId().equals(userLogined.getId());
        
        
        if(!isSHOPER) {
            
            response.setData("Bạn không có quyền thực hiện thao tác này, vui lòng liên hệ quản lý của bạn.");
            response.setStatus(21);
            response.setError("Faild in product type");
        } else if(list.isEmpty()) {

            response.setData("Không tìm thấy sản phẩm này.");
            response.setStatus(404);
            response.setError("Faild in product type");            
        } else if(!isShopAction) {

            response.setData("Bạn không thể thực hiện thao tác trên sản phẩm này.");
            response.setStatus(61);
            response.setError("Faild in product type");            
        } else {
            
            
            for(ProductTypeItem item: list) {
                productTypeItemService.delete(item);
            }
            
            response.setData("Xóa loại sản phẩm thành công.");
            response.setStatus(200);
            
        }           
        
        return response;
    }
    
    @DeleteMapping("/delete/all/productid/typeid/{id}/{type_id}")
    public APIResponse deleteAllByProductIdAndTypeID(@PathVariable Long id, @PathVariable Long type_id) {
        
        APIResponse response = new APIResponse();
        
        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);
        if(userLogined == null) {
            
            response.setStatus(19);
            response.setError("Can not POST /add. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");
            
            return response;
        }
        List<UserHasRoles> listRoles = userHasRolesService.selectsByUserId(userLogined.getId());
        List<ProductTypeItem> list = productTypeItemService.selectsByProductId(id);
        
        boolean isSHOPER = listRoles.stream().anyMatch(e -> e.getRoles().getId() == 10);
        Products product = productsService.findById(id).get();
        boolean isShopAction = product != null && product.getUsers().getId().equals(userLogined.getId());
        
        
        if(!isSHOPER) {
            
            response.setData("Bạn không có quyền thực hiện thao tác này, vui lòng liên hệ quản lý của bạn.");
            response.setStatus(21);
            response.setError("Faild in product type");
        } else if(list.isEmpty()) {

            response.setData("Không tìm thấy sản phẩm này.");
            response.setStatus(404);
            response.setError("Faild in product type");            
        } else if(!isShopAction) {

            response.setData("Bạn không thể thực hiện thao tác trên sản phẩm này.");
            response.setStatus(61);
            response.setError("Faild in product type");            
        } else {
            
            
            for(ProductTypeItem item: list) {
                if(item.getProductType().getId().equals(type_id)) {
                    
                    productTypeItemService.delete(item);
                }
                
            }
            
            response.setData("Xóa loại sản phẩm thành công.");
            response.setStatus(200);
            
        }           
        
        return response;
    }
}
