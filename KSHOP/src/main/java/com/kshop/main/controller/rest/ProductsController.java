package com.kshop.main.controller.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import com.kshop.main.domain.ProductImages;
import com.kshop.main.domain.ProductReviews;
import com.kshop.main.domain.ProductTypeItem;
import com.kshop.main.domain.Products;
import com.kshop.main.repository.ProductsReponsitory;

import com.kshop.main.domain.Seller;
import com.kshop.main.domain.ShopStores;
import com.kshop.main.domain.UserHasRoles;
import com.kshop.main.domain.Users;

import com.kshop.main.domain.Products;
import com.kshop.main.service.ProductImagesService;
import com.kshop.main.service.ProductReviewsService;
import com.kshop.main.service.ProductTypeItemService;
import com.kshop.main.service.ProductsService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.utils.Roles;


@RestController
@RequestMapping("/Products")
@CrossOrigin
public class ProductsController {
	
	@Autowired
	ProductsService productsService;
	
	@Autowired
	ProductsReponsitory productsReponsitory;
	@Autowired
	SessionService sessionService;
	@Autowired
    UserHasRolesService userHasRolesService;
	@Autowired
	ProductImagesService productImagesService;
	@Autowired
	ProductTypeItemService productTypeItemService;
	@Autowired
	ProductReviewsService productReviewsService;

	
	/** Api get all Products 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all Products
	public APIResponse getAllProducts() {
		
	    APIResponse response = new APIResponse();
        List<Products> list = productsService.findAll();
        List<Object> list2 = new ArrayList<Object>();
        
        for(Products item: list) {
            item.setUsers(null);
            Map<String, Object> mapItem = new HashMap<String, Object>();
            List<ProductReviews> productReviews = productReviewsService.selectAllByProductId(item.getId());
            OptionalDouble avgReview = productReviews.stream().mapToDouble(e -> e.getRating()).average();
            
            Double rating = 0D;
            if(avgReview.isPresent()) rating = avgReview.getAsDouble();
            mapItem.put("rating", rating);
            mapItem.put("review_count", productReviews.size());
            
            list2.add(mapItem);
        }
        
        Map<String, Object> map = new HashMap();
        
        map.put("products", list);
        map.put("reviews", list2);
        response.setData(map);
        response.setStatus(200);
        
        return response;
	}
	
	@GetMapping("/admin/get") // api get all Products
    public APIResponse getAllProductsAdmin() {
        
        APIResponse response = new APIResponse();
        Users userLogined = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        List<Products> list = new ArrayList<Products>();
        
        if(userLogined != null) {
            boolean isAdmin = Roles.UserHasRoles(Arrays.asList(1L), sessionService, userHasRolesService);
            boolean isShop = Roles.UserHasRoles(Arrays.asList(4L), sessionService, userHasRolesService);
            
            if(isAdmin) {
                
                list = productsService.findAll();
            } else if(isShop) {
                
                list = productsService.selectsAllByUserId(userLogined.getId());
            } else {
                
                response.setStatus(400);
                response.setError("Can not GET /get");
                response.setData("Đã có lỗi xảy ra khi cấp quyền get sản phẩm. Vui lòng thử lại.");
                
                return response;
            }
        }
        
        for(Products item: list) {
            item.setUsers(null);
        }
        
        Map<String, Object> map = new HashMap();
        
        map.put("products", list);
        response.setData(map);
        response.setStatus(200);
        
        return response;
    }
	
	/** Api get all Products 
     * 
     * - GET Method: __/get
     * - Return(JSON): item
     * 
     * */
    @GetMapping("/get/{start}/{total}") // api get all Products
    public APIResponse getPageProducts(@PathVariable int start, @PathVariable int total) {

        APIResponse response = new APIResponse();
        List<Products> list = productsService.findAll();
        List<Object> list2 = new ArrayList<Object>();
        
        for(Products item: list) {
	    	item.setUsers(null);
	    	Map<String, Object> mapItem = new HashMap<String, Object>();
            List<ProductReviews> productReviews = productReviewsService.selectAllByProductId(item.getId());
            OptionalDouble avgReview = productReviews.stream().mapToDouble(e -> e.getRating()).average();
            
            Double rating = 0D;
            if(avgReview.isPresent()) rating = avgReview.getAsDouble();
            mapItem.put("rating", rating);
            mapItem.put("review_count", productReviews.size());
            
            list2.add(mapItem);
	    }
        Collections.reverse(list);
        Collections.reverse(list2);
        int toStart = start*total;
        int toIndex = total + toStart;
        toIndex = toIndex > list.size() ? list.size() : toIndex;
        
        Map<String, Object> map = new HashMap();
        
        map.put("products", list.subList(toStart, toIndex));
        map.put("reviews", list2.subList(toStart, toIndex));
        response.setData(map);
        response.setStatus(200);
        
        return response;
    }
    
    @GetMapping("/admin/get/{start}/{total}") // api get all Products
    public APIResponse getPageProductsAdmin(@PathVariable int start, @PathVariable int total) {

        APIResponse response = new APIResponse();
        Users userLogined = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        List<Products> list = new ArrayList<Products>();
        
        if(userLogined != null) {
            boolean isAdmin = Roles.UserHasRoles(Arrays.asList(1L), sessionService, userHasRolesService);
            boolean isShop = Roles.UserHasRoles(Arrays.asList(4L), sessionService, userHasRolesService);
            
            if(isAdmin) {
                
                list = productsService.findAll();
            } else if(isShop) {
                
                list = productsService.selectsAllByUserId(userLogined.getId());
            } else {
                
                response.setStatus(400);
                response.setError("Can not GET /get");
                response.setData("Đã có lỗi xảy ra khi cấp quyền get sản phẩm. Vui lòng thử lại.");
                
                return response;
            }
        }
        
        for(Products item: list) {
            item.setUsers(null);
        }
        Collections.reverse(list);
        int toStart = start*total;
        int toIndex = total + toStart;
        toIndex = toIndex > list.size() ? list.size() : toIndex;
        
        Map<String, Object> map = new HashMap();
        
        map.put("products", list.subList(toStart, toIndex));
        response.setData(map);
        response.setStatus(200);
        
        return response;
    }
    
    /** Api get Products best-seller
     * 
     * - GET Method: __/get
     * - Return(JSON): item
     * 
     * */
    @GetMapping("/get/best-seller/{start}/{total}")
    public List<Products> getBestSellerProducts(@PathVariable int start, @PathVariable int total) {
        
        
        
        return productsService.selectBestSellerProducts(start, total);
    }
    
    /** Api get Products best-popular
     * 
     * - GET Method: __/get
     * - Return(JSON): item
     * 
     * */
    @GetMapping("/get/best-popular/{start}/{total}")
    public List<Products> getBestPopularProducts(@PathVariable int start, @PathVariable int total) {
        
        
        
        return productsService.selectBestSellerProducts(start, total);
    }
    
    /** Api get Products featured
     * 
     * - GET Method: __/get
     * - Return(JSON): item
     * 
     * */
    @GetMapping("/get/featured/{start}/{total}")
    public List<Products> getFeaturedProducts(@PathVariable int start, @PathVariable int total) {        
         
        return productsService.findAllByIsFeatured(PageRequest.of(start, total)).getContent();
    }

	
	/** Api get last item in Products 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public Products getLastProducts() {
		
		List<Products> list = productsService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in Products 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public Products getFirstProducts() {
		
		return productsService.findAll().get(0);
	}

	
	/** Api get item by id in Products 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public Products getShopVoucher(@PathVariable Long id) {
		Optional<Products> itemProducts = productsService.findById(id);
		if(itemProducts.isPresent()) {
			return itemProducts.get();
		} 
		
		return null;
	}

	@GetMapping("/get/products/{name}")
	public APIResponse getShopVoucher(@PathVariable String name) {
		APIResponse response = new APIResponse();
		List<Products> itemProducts = productsReponsitory.findByProductName(name);
		List<Object> list2 = new ArrayList<Object>();
		Map<String, Object> map = new HashMap<>();
		if(!itemProducts.isEmpty()) {
		    for(Products item: itemProducts) {
	            Map<String, Object> mapItem = new HashMap<String, Object>();
	            List<ProductReviews> productReviews = productReviewsService.selectAllByProductId(item.getId());
	            OptionalDouble avgReview = productReviews.stream().mapToDouble(e -> e.getRating()).average();
	            
	            Double rating = 0D;
	            if(avgReview.isPresent()) rating = avgReview.getAsDouble();
	            mapItem.put("rating", rating);
	            mapItem.put("review_count", productReviews.size());
	            
	            list2.add(mapItem);
	        }
		    
			map.put("products", itemProducts);
			map.put("reviews", list2);
			response.setData(map);
			return response;
		} 
		map.put("products", new ArrayList<Products>());
		response.setData(map);
		return response;
	}
	
	@GetMapping("/get/products/{name}/{start}/{total}") // api get all Products
    public APIResponse getPageProductsName(@PathVariable String name, @PathVariable int start, @PathVariable int total) {
		APIResponse response = new APIResponse();
		List<Products> itemProducts = productsReponsitory.findByProductName(name);
		List<Object> list2 = new ArrayList<Object>();
        Collections.reverse(itemProducts);
        int toStart = start * total;
        for(Products item: itemProducts) {
            Map<String, Object> mapItem = new HashMap<String, Object>();
            List<ProductReviews> productReviews = productReviewsService.selectAllByProductId(item.getId());
            OptionalDouble avgReview = productReviews.stream().mapToDouble(e -> e.getRating()).average();
            
            Double rating = 0D;
            if(avgReview.isPresent()) rating = avgReview.getAsDouble();
            mapItem.put("rating", rating);
            mapItem.put("review_count", productReviews.size());
            
            list2.add(mapItem);
        }
        int toIndex = total + toStart;
        toIndex = toIndex > itemProducts.size() ? itemProducts.size() : toIndex;
        Map<String, Object> map = new HashMap<>();
		map.put("products", itemProducts.subList(toStart, toIndex));
		map.put("reviews", list2.subList(toStart, toIndex));
		response.setData(map);
        return response;
    }
    
    @GetMapping("/get/categoryid/{id}")
    public List<Products> getByIdCategory(@PathVariable Long id) {
        List<Products> itemProducts = productsService.selectsAllByCategoryId(id);
        if(!itemProducts.isEmpty()) {
            return itemProducts;
        } 
        
        return null;
    }
    
    @GetMapping("/get/category/trangphuc")
    public List<Products> getByTrangphuc() {
        List<Products> itemProducts = productsService.sp_SwipLoaiSanPhamTrangPhuc();
        if(!itemProducts.isEmpty()) {
            return itemProducts;
        } 
        
        return null;
    }
    
    @GetMapping("/get/category/dientu")
    public List<Products> getByDientu() {
        List<Products> itemProducts = productsService.sp_SwipLoaiSanPhamDienTu();
        if(!itemProducts.isEmpty()) {
            return itemProducts;
        } 
        
        return null;
    }
    
    @GetMapping("/get/category/nhavsbep")
    public List<Products> getByNhavsbep() {
        List<Products> itemProducts = productsService.sp_SwipLoaiSanPhamNhavsbep();
        if(!itemProducts.isEmpty()) {
            return itemProducts;
        } 
        
        return null;
    }

	
	/** Api add item to Products 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public APIResponse addProducts(@RequestBody Products products) {
        
        APIResponse response = new APIResponse();
		
	    Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);
	    if(userLogined == null) {
	        
	        response.setStatus(19);
	        response.setError("Can not POST /add. Error code: 19");
	        response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");
	        
	        return response;
	    }
	    List<UserHasRoles> list = userHasRolesService.selectsByUserId(userLogined.getId());
        
        boolean isSHOPER = list.stream().anyMatch(e -> e.getRoles().getId() == 10);
        
        if(!isSHOPER) {
            response.setData("Bạn không có quyền đăng sản phẩm, vui lòng liên hệ quản lý của bạn.");
            response.setStatus(21);
            response.setError("Faild to upload product");
        } else {
            
            products.setUsers(userLogined);
            
            productsService.save(products);
            response.setData(getLastProducts());
            response.setStatus(200);
            
//            productsService.createImport(getLastProducts());
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
	public APIResponse updateProducts(@RequestBody Products products) {
		
	    APIResponse response = new APIResponse();
        
        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);
        Products productsDB = productsService.findById(products.getId()).get();
        if(userLogined == null) {
            
            response.setStatus(19);
            response.setError("Can not POST /add. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");
            
            return response;
        }
        List<UserHasRoles> list = userHasRolesService.selectsByUserId(userLogined.getId());
        
        boolean isSHOPER = list.stream().anyMatch(e -> e.getRoles().getId() == 10);
        
        if(!isSHOPER) {
            response.setData("Bạn không có quyền sửa sản phẩm, vui lòng liên hệ quản lý của bạn.");
            response.setStatus(21);
            response.setError("Faild to update product");
        } else if(productsDB == null || !userLogined.getId().equals(productsDB.getUsers().getId())) {
            response.setData("Bạn không thể sửa sản phẩm này, vui lòng thử lại sau.");
            response.setStatus(21);
            response.setError("Faild to update product");
        } else {
            
            products.setUsers(userLogined);
            
            productsService.save(products);
            response.setData(getLastProducts());
            response.setStatus(200);
            
//            productsService.createImport(getLastProducts());
        }           
        
        return response;
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public APIResponse deleteProducts(@PathVariable Long id) {
	    APIResponse response = new APIResponse();
        
        Users userLogined = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        Products item = productsService.findById(id).get();
        if(userLogined == null) {
            
            response.setStatus(19);
            response.setError("Can not POST /add. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");
            
            return response;
        }
        List<UserHasRoles> list = userHasRolesService.selectsByUserId(userLogined.getId());
        
        boolean isSHOPER = list.stream().anyMatch(e -> e.getRoles().getId() == 10);
        
        if(!isSHOPER) {
            response.setData("Bạn không có quyền xóa sản phẩm, vui lòng liên hệ quản lý của bạn.");
            response.setStatus(21);
            response.setError("Faild to delete product");
        } else if(item == null || !userLogined.getId().equals(item.getUsers().getId())) {
            response.setData("Bạn không thể xóa sản phẩm này, vui lòng thử lại sau.");
            response.setStatus(21);
            response.setError("Faild to delete product");
        } else {
            //xóa img product
            List<ProductImages> listProductImages = productImagesService.selectsByProductId(item.getId());
            for(ProductImages i : listProductImages) {
                productImagesService.delete(i);
            }
            //xoa product type item
            List<ProductTypeItem> listProductTypeItem = productTypeItemService.selectsByProductId(item.getId());
            for(ProductTypeItem i : listProductTypeItem) {
                productTypeItemService.delete(i);
            }
            //xóa discount
            //reviews
            //product voucher
            //orderdetail
            //export import
            
            //xóa product
            productsService.delete(item);
            
            response.setData("Xóa thành công sản phẩm");
            response.setStatus(200);
        }   
        
		
		return response;
	}
}
