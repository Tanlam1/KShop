package com.kshop.main.controller.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kshop.main.domain.Category;
import com.kshop.main.domain.ProductReviews;
import com.kshop.main.domain.Products;
import com.kshop.main.domain.Users;
import com.kshop.main.service.ProductReviewsService;
import com.kshop.main.service.ProductsService;
import com.kshop.main.service.ShopCategoriesService;
import com.kshop.main.service.UsersService;

@Controller
@RequestMapping("/store")
public class VendorStoreController extends com.kshop.main.controller.Controller {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	ProductsService productsService;
	
	@Autowired
	ShopCategoriesService shopCategoriesService;
	
	@Autowired
	ProductReviewsService productReviewsService;
	
	@GetMapping("")
	public String layout(Model model) {
	    
	    dataLayoutMaster.setView("client/vendor-store/vendor-store");
	    dataLayoutMaster.setMainCss("css/style.min.css");
	    dataLayoutMaster.setJsList(Arrays.asList("/js/vendor-store/vendor-store.js"));
	    
	    objsDataLayout.put("key", "value");
	    
	    dataLayoutMaster.setObjs(objsDataLayout);
	    
	    model.addAttribute(__CONTENT__, dataLayoutMaster);
	    
	    return clientLayout;
	}
	
	@GetMapping("/{id}")
	public String layout(Model model, @PathVariable("id") Long id) {
		Optional<Users> users = usersService.selectsByStoreId(id);
		
		if(users.isEmpty()) {
			//return false
		}
		
		//List san pham co khoa ngoai User_Id
		List<Products> products = productsService.selectsAllByUserId(users.get().getId());
		List<Object> list2 = new ArrayList<Object>();
		Collections.reverse(products);
		Collections.reverse(list2);
		objsDataLayout.put("products", products);
		
		if(products.isEmpty()) {
//			return false;
		}else {
			List<Category> categoryList = new ArrayList<>();
			//Duyet tim Category cua tung san pham
			for(var i = 0; i < products.size(); i++) {
				Optional<Category> category = shopCategoriesService.findById(products.get(i).getCategories().getId());
				if(categoryList.stream().allMatch(e -> !e.getCategory_name().equals(category.get().getCategory_name()))) {
					categoryList.add(category.get());
				}
				
				Map<String, Object> mapItem = new HashMap<String, Object>();
	            List<ProductReviews> productReviews = productReviewsService.selectAllByProductId(products.get(i).getId());
	            OptionalDouble avgReview = productReviews.stream().mapToDouble(e -> e.getRating()).average();
	            
	            Double rating = 0D;
	            if(avgReview.isPresent()) rating = avgReview.getAsDouble();
	            mapItem.put("rating", rating);
	            mapItem.put("review_count", productReviews.size());
	            
	            list2.add(mapItem);
			}
			objsDataLayout.put("categories", categoryList);
			objsDataLayout.put("reviews", list2);
		}
		
		
		dataLayoutMaster.setView("client/vendor-store/store");
	    dataLayoutMaster.setMainCss("../css/style.min.css");
	    dataLayoutMaster.setJsList(Arrays.asList("/js/vendor-store/store.js"));
	    
	    objsDataLayout.put("storeId", id);
	    
	    objsDataLayout.put("key", "value");
	    
	    dataLayoutMaster.setObjs(objsDataLayout);
	    
	    model.addAttribute(__CONTENT__, dataLayoutMaster);
	    
	    return clientLayout;
	}
}
