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

import com.kshop.main.domain.ShopExports;
import com.kshop.main.service.ShopExportsService;


@RestController
@RequestMapping("/ShopExports")
@CrossOrigin
public class ShopExportController {
	
	@Autowired
	ShopExportsService shopExportsService;

	
	/** Api get all 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") 
	public List<ShopExports> getAllShopExports() {
		
		return shopExportsService.findAll();
	}

	/** Api get last item  
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public ShopExports getLastShopExports() {
		
		List<ShopExports> list = shopExportsService.findAll();
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
	public ShopExports getFirstShopExports() {
		
		return shopExportsService.findAll().get(0);
	}

	
	/** Api get item by id 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public ShopExports getShopExports(@PathVariable Long id) {
		Optional<ShopExports> itemShopExports = shopExportsService.findById(id);
		if(itemShopExports.isPresent()) {
			return itemShopExports.get();
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
	public ShopExports addShopStores(@RequestBody ShopExports shopExports) {
		
		shopExportsService.save(shopExports);
		
		return getLastShopExports();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public ShopExports updateShopExports(@RequestBody ShopExports ShopExports) {
		
		shopExportsService.save(ShopExports);
		
		return shopExportsService.findById(ShopExports.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public ShopExports deleteShopExports(@PathVariable Long id) {
		
		ShopExports item = shopExportsService.findById(id).get();
		
		shopExportsService.delete(item);
		
		return item;
	}
}
