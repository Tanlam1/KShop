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
import com.kshop.main.domain.Products;
import com.kshop.main.domain.UserHasRoles;
import com.kshop.main.domain.Users;
import com.kshop.main.service.ProductImagesService;
import com.kshop.main.service.ProductsService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;


@RestController
@RequestMapping("/ProductImages")
@CrossOrigin
public class ProductImagesController {
	
	@Autowired
	ProductImagesService productImagesService;
	@Autowired
	SessionService sessionService;
	@Autowired
	UserHasRolesService userHasRolesService;
	@Autowired
	ProductsService productsService;
	
	/** Api get all ProductImages 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all ProductImages
	public List<ProductImages> getAllProductImages() {
		
		return productImagesService.findAll();
	}

	
	/** Api get last item in ProductImages 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public ProductImages getLastProductImages() {
		
		List<ProductImages> list = productImagesService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in ProductImages 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public ProductImages getFirstProductImages() {
		
		return productImagesService.findAll().get(0);
	}

	
	/** Api get item by id in ProductImages 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public ProductImages getShopVoucher(@PathVariable Long id) {
		Optional<ProductImages> itemProductImages = productImagesService.findById(id);
		if(itemProductImages.isPresent()) {
			return itemProductImages.get();
		} 
		
		return null;
	}
	
	@GetMapping("/get/productid/{id}")
    public List<ProductImages> getByProductId(@PathVariable Long id) {
	    List<ProductImages> itemProductImages = productImagesService.selectsByProductId(id);
	    
        if(!itemProductImages.isEmpty()) {
            for(ProductImages item: itemProductImages) {
                item.getProducts().setUsers(null);                
            }
            return itemProductImages;
        } 
        
        return Arrays.asList(new ProductImages());
    }

	
	/** Api add item to ProductImages 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public ProductImages addProductImages(@RequestBody ProductImages ProductImages) {
		
		productImagesService.save(ProductImages);
		
		return getLastProductImages();
	}
	
    @PostMapping("/adds")
    public APIResponse addProductsImages(@RequestBody List<ProductImages> list) {
        
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
            
            response.setData("Bạn không có quyền thêm hình ảnh sản phẩm, vui lòng liên hệ quản lý của bạn.");
            response.setStatus(21);
            response.setError("Failed to upload product img");
        } else if(!isShopUpload) {

            response.setData("Không thể upload hình của bạn vào sản phẩm của shop khác");
            response.setStatus(61);
            response.setError("Failed to upload product img");            
        } else {
            
            for(ProductImages item: list) {
                productImagesService.save(item);
            }
            
            response.setData("Upload hình ảnh thành công");
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
	public ProductImages updateProductImages(@RequestBody ProductImages ProductImages) {
		
		productImagesService.save(ProductImages);
		
		return productImagesService.findById(ProductImages.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public APIResponse deleteProductImages(@PathVariable Long id) {
		
	    APIResponse response = new APIResponse();
        
        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);
        if(userLogined == null) {
            
            response.setStatus(19);
            response.setError("Can not POST /add. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");
            
            return response;
        }
        List<UserHasRoles> listRoles = userHasRolesService.selectsByUserId(userLogined.getId());
        Optional<ProductImages> productImages = productImagesService.findById(id);
        
        if(productImages.isEmpty()) {

            response.setData("Không tìm thấy hình ảnh này.");
            response.setStatus(404);
            response.setError("Failed in product img");      
            
            return response;
        }
        
        boolean isSHOPER = listRoles.stream().anyMatch(e -> e.getRoles().getId() == 10);
        boolean isShopAction = productImages.get().getProducts().getUsers().getId().equals(userLogined.getId());
        
        
        if(!isSHOPER) {
            
            response.setData("Bạn không có quyền thực hiện thao tác này, vui lòng liên hệ quản lý của bạn.");
            response.setStatus(21);
            response.setError("Failed in product img");
        } else if(!isShopAction) {

            response.setData("Bạn không thể thực hiện thao tác trên sản phẩm này.");
            response.setStatus(61);
            response.setError("Failed in product img");            
        } else {
            
            productImagesService.delete(productImages.get());
            
            response.setData("Xóa hình ảnh thành công.");
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
        List<ProductImages> list = productImagesService.selectsByProductId(id);
        
        boolean isSHOPER = listRoles.stream().anyMatch(e -> e.getRoles().getId() == 10);
        Products product = productsService.findById(id).get();
        boolean isShopAction = product != null && product.getUsers().getId().equals(userLogined.getId());
        
        
        if(!isSHOPER) {
            
            response.setData("Bạn không có quyền thực hiện thao tác này, vui lòng liên hệ quản lý của bạn.");
            response.setStatus(21);
            response.setError("Failed in product img");
        } else if(list.isEmpty()) {

            response.setData("Không tìm thấy sản phẩm này.");
            response.setStatus(404);
            response.setError("Failed in product img");            
        } else if(!isShopAction) {

            response.setData("Bạn không thể thực hiện thao tác trên sản phẩm này.");
            response.setStatus(61);
            response.setError("Failed in product img");            
        } else {
            
            for(ProductImages item: list) {
                productImagesService.delete(item);
            }
            
            response.setData("Xóa hình ảnh thành công.");
            response.setStatus(200);
            
        }           
        
        return response;
    }
}
