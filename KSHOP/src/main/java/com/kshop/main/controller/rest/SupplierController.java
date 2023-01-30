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

import com.kshop.main.domain.Supplier;

import com.kshop.main.service.SupplierService;


@RestController
@RequestMapping("/Supplier")
@CrossOrigin
public class SupplierController {
	
	@Autowired
	SupplierService supplierService;

	
	/** Api get all Supplier 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all Supplier
	public List<Supplier> getAllSupplier() {
		
		return supplierService.findAll();
	}

	
	/** Api get last item in Supplier 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public Supplier getLastSupplier() {
		
		List<Supplier> list = supplierService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in Supplier 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public Supplier getFirstSupplier() {
		
		return supplierService.findAll().get(0);
	}

	
	/** Api get item by id in Supplier 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public Supplier getShopVoucher(@PathVariable Long id) {
		Optional<Supplier> itemSupplier = supplierService.findById(id);
		if(itemSupplier.isPresent()) {
			return itemSupplier.get();
		} 
		
		return null;
	}

	
	/** Api add item to Supplier 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public Supplier addSupplier(@RequestBody Supplier Supplier) {
		
		supplierService.save(Supplier);
		
		return getLastSupplier();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public Supplier updateSupplier(@RequestBody Supplier Supplier) {
		
		supplierService.save(Supplier);
		
		return supplierService.findById(Supplier.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public Supplier deleteSupplier(@PathVariable Long id) {
		
		Supplier item = supplierService.findById(id).get();
		
		supplierService.delete(item);
		
		return item;
	}
}
