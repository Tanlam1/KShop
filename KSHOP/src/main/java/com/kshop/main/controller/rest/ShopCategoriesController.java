package com.kshop.main.controller.rest;

import java.util.List;
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

import com.kshop.main.domain.Category;
import com.kshop.main.service.ShopCategoriesService;


@RestController
@RequestMapping("/ShopCategories")
@CrossOrigin
public class ShopCategoriesController {
	
	@Autowired
	ShopCategoriesService shopCategoriesService;

	
	/** Api get all Category 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all Category
	public List<Category> getAllCategory() {
		
		return shopCategoriesService.findAll();
	}
	
	/** Api get last item in Category 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/get/{start}/{total}") // api get all Products
	public List<Category> getPageCategory(@PathVariable int start, @PathVariable int total){
		
		return shopCategoriesService.findAll(PageRequest.of(start, total)).getContent();
	}
	/** Api get last item in Category 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public Category getLastCategory() {
		
		List<Category> list = shopCategoriesService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in Category 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public Category getFirstCategory() {
		
		return shopCategoriesService.findAll().get(0);
	}
	
	/** Api get item by id in Category 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public Category getShopVoucher(@PathVariable Long id) {
		Optional<Category> itemCategory = shopCategoriesService.findById(id);
		if(itemCategory.isPresent()) {
			return itemCategory.get();
		} 
		
		return null;
	}
	
	
	/** Api get item by id in Category 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/level/{id}")
	public List<Category> getLevelCategories(@PathVariable String id) {
		List<Category> itemLevelCategory = shopCategoriesService.findByLevel(id);
		if(!itemLevelCategory.isEmpty()) {
			return itemLevelCategory;
		} 
		return null;
	}
	
	/** Api get item by id in Category 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/parent/{id}")
	public List<Category> getParentIdCategories(@PathVariable String id) {
		List<Category> itemParentIdCategories = shopCategoriesService.findByParentId(id);
		if(!itemParentIdCategories.isEmpty()) {
			return itemParentIdCategories;
		} 
		return null;
	}

	
	/** Api add item to Category 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public Category addCategory(@RequestBody Category Category) {
		
		shopCategoriesService.save(Category);
		
		return getLastCategory();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public Category updateCategory(@RequestBody Category Category) {
		
		shopCategoriesService.save(Category);
		
		return shopCategoriesService.findById(Category.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public Category deleteCategory(@PathVariable Long id) {
		
		Category item = shopCategoriesService.findById(id).get();
		
		shopCategoriesService.delete(item);
		
		return item;
	}
}
