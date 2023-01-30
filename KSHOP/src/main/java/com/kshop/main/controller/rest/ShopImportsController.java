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

import com.kshop.main.domain.ShopImports;
import com.kshop.main.service.ShopImportsService;


@RestController
@RequestMapping("/ShopImports")
@CrossOrigin
public class ShopImportsController {
	
	@Autowired
	ShopImportsService shopImportsService;

	
	/** Api get all 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") 
	public List<ShopImports> getAllShopImports() {
		List<ShopImports> list = shopImportsService.findAll();
		
		return list;
	}

	/** Api get last item  
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public ShopImports getLastShopImports() {
		
		List<ShopImports> list = shopImportsService.findAll();
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
	public ShopImports getFirstShopImports() {
		
		return shopImportsService.findAll().get(0);
	}

	
	/** Api get item by id 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public ShopImports getShopImports(@PathVariable Long id) {
		Optional<ShopImports> itemShopImports = shopImportsService.findById(id);
		if(itemShopImports.isPresent()) {
			return itemShopImports.get();
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
	public ShopImports addShopStores(@RequestBody ShopImports shopImports) {
		
		shopImportsService.save(shopImports);
		
		return getLastShopImports();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public ShopImports updateShopImports(@RequestBody ShopImports ShopImports) {
		
		shopImportsService.save(ShopImports);
		
		return shopImportsService.findById(ShopImports.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public ShopImports deleteShopImports(@PathVariable Long id) {
		
		ShopImports item = shopImportsService.findById(id).get();
		
		shopImportsService.delete(item);
		
		return item;
	}
}
