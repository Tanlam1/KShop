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

import com.kshop.main.domain.ProductImages;
import com.kshop.main.domain.ProductType;
import com.kshop.main.service.ProductTypeService;


@RestController
@RequestMapping("/ProductType")
@CrossOrigin
public class ProductTypeController {
	
	@Autowired
	ProductTypeService productTypeService;

	
	/** Api get all ProductType 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all ProductType
	public List<ProductType> getAllProductType() {
		List<ProductType> list = productTypeService.findAll();
				
		return list;
	}

	
	/** Api get last item in ProductType 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public ProductType getLastProductType() {
		
		List<ProductType> list = productTypeService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in ProductType 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public ProductType getFirstProductType() {
		
		return productTypeService.findAll().get(0);
	}

	
	/** Api get item by id in ProductType 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public ProductType getShopVoucher(@PathVariable Long id) {
		Optional<ProductType> itemProductType = productTypeService.findById(id);
		if(itemProductType.isPresent()) {
			return itemProductType.get();
		} 
		
		return null;
	}

	
	/** Api add item to ProductType 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public ProductType addProductType(@RequestBody ProductType ProductType) {
		
		productTypeService.save(ProductType);
		
		return getLastProductType();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public ProductType updateProductType(@RequestBody ProductType ProductType) {
		
		productTypeService.save(ProductType);
		
		return productTypeService.findById(ProductType.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public ProductType deleteProductType(@PathVariable Long id) {
		
		ProductType item = productTypeService.findById(id).get();
		
		productTypeService.delete(item);
		
		return item;
	}
}
