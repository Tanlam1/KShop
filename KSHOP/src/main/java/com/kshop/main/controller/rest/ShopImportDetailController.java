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

import com.kshop.main.domain.ShopImportDetails;
import com.kshop.main.service.ShopImportDetailsService;


@RestController
@RequestMapping("/ShopImportDetails")
@CrossOrigin
public class ShopImportDetailController {
	
	@Autowired
	ShopImportDetailsService shopImportDetailsService;

	
	/** Api get all 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") 
	public List<ShopImportDetails> getAllShopImportDetails() {
		
		return shopImportDetailsService.findAll();
	}

	/** Api get last item  
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public ShopImportDetails getLastShopImportDetails() {
		
		List<ShopImportDetails> list = shopImportDetailsService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item   
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public ShopImportDetails getFirstShopImportDetails() {
		
		return shopImportDetailsService.findAll().get(0);
	}

	
	/** Api get item by id 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public ShopImportDetails getShopImportDetails(@PathVariable Long id) {
		Optional<ShopImportDetails> itemShopImportDetails = shopImportDetailsService.findById(id);
		if(itemShopImportDetails.isPresent()) {
			return itemShopImportDetails.get();
		} 
		
		return null;
	}

	
	/** Api add item 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public ShopImportDetails addShopStores(@RequestBody ShopImportDetails ShopImportDetails) {
		
		shopImportDetailsService.save(ShopImportDetails);
		
		return getLastShopImportDetails();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public ShopImportDetails updateShopImportDetails(@RequestBody ShopImportDetails ShopImportDetails) {
		
		shopImportDetailsService.save(ShopImportDetails);
		
		return shopImportDetailsService.findById(ShopImportDetails.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public ShopImportDetails deleteShopImportDetails(@PathVariable Long id) {
		
		ShopImportDetails item = shopImportDetailsService.findById(id).get();
		
		shopImportDetailsService.delete(item);
		
		return item;
	}
}
