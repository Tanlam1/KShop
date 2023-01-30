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

import com.kshop.main.domain.ShopExportDetails;
import com.kshop.main.domain.ShopExportDetails;
import com.kshop.main.service.ShopExportDetailsService;
import com.kshop.main.service.ShopExportDetailsService;


@RestController
@RequestMapping("/ShopExportDetails")
@CrossOrigin
public class ShopExportDetailController {
	
	@Autowired
	ShopExportDetailsService shopExportDetailsService;

	
	/** Api get all 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") 
	public List<ShopExportDetails> getAllShopExportDetails() {
		
		return shopExportDetailsService.findAll();
	}

	/** Api get last item  
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public ShopExportDetails getLastShopExportDetails() {
		
		List<ShopExportDetails> list = shopExportDetailsService.findAll();
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
	public ShopExportDetails getFirstShopExportDetails() {
		
		return shopExportDetailsService.findAll().get(0);
	}

	
	/** Api get item by id 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public ShopExportDetails getShopExportDetails(@PathVariable Long id) {
		Optional<ShopExportDetails> itemShopExportDetails = shopExportDetailsService.findById(id);
		if(itemShopExportDetails.isPresent()) {
			return itemShopExportDetails.get();
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
	public ShopExportDetails addShopStores(@RequestBody ShopExportDetails ShopExportDetails) {
		
		shopExportDetailsService.save(ShopExportDetails);
		
		return getLastShopExportDetails();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public ShopExportDetails updateShopExportDetails(@RequestBody ShopExportDetails ShopExportDetails) {
		
		shopExportDetailsService.save(ShopExportDetails);
		
		return shopExportDetailsService.findById(ShopExportDetails.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public ShopExportDetails deleteShopExportDetails(@PathVariable Long id) {
		
		ShopExportDetails item = shopExportDetailsService.findById(id).get();
		
		shopExportDetailsService.delete(item);
		
		return item;
	}
}
