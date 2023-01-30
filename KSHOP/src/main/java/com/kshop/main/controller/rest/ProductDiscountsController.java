package com.kshop.main.controller.rest;

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

import com.kshop.main.domain.ProductDiscounts;
import com.kshop.main.domain.ProductDiscounts;
import com.kshop.main.service.ProductDiscountsService;


@RestController
@RequestMapping("/ProductDiscounts")
@CrossOrigin
public class ProductDiscountsController {
	
	@Autowired
	ProductDiscountsService productDiscountsService;

	
	/** Api get all ProductDiscounts 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all ProductDiscounts
	public List<ProductDiscounts> getAllProductDiscounts() {
		
		return productDiscountsService.findAll();
	}

	
	/** Api get last item in ProductDiscounts 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public ProductDiscounts getLastProductDiscounts() {
		
		List<ProductDiscounts> list = productDiscountsService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in ProductDiscounts 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public ProductDiscounts getFirstProductDiscounts() {
		
		return productDiscountsService.findAll().get(0);
	}

	
	/** Api get item by id in ProductDiscounts 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public ProductDiscounts getShopVoucher(@PathVariable Long id) {
		Optional<ProductDiscounts> itemProductDiscounts = productDiscountsService.findById(id);
		if(itemProductDiscounts.isPresent()) {
			return itemProductDiscounts.get();
		} 
		
		return null;
	}

	
	/** Api add item to ProductDiscounts 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public ProductDiscounts addProductDiscounts(@RequestBody ProductDiscounts ProductDiscounts) {
		
		productDiscountsService.save(ProductDiscounts);
		
		return getLastProductDiscounts();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public ProductDiscounts updateProductDiscounts(@RequestBody ProductDiscounts ProductDiscounts) {
		
		productDiscountsService.save(ProductDiscounts);
		
		return productDiscountsService.findById(ProductDiscounts.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public ProductDiscounts deleteProductDiscounts(@PathVariable Long id) {
		
		ProductDiscounts item = productDiscountsService.findById(id).get();
		
		productDiscountsService.delete(item);
		
		return item;
	}
}
