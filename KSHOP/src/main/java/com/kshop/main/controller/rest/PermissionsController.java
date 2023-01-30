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

import com.kshop.main.domain.Permissions;
import com.kshop.main.service.PermissionsService;


@RestController
@RequestMapping("/Permissions")
@CrossOrigin
public class PermissionsController {
	
	@Autowired
	PermissionsService permissionsService;

	
	/** Api get all Permissions 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all Permissions
	public List<Permissions> getAllPermissions() {
		
		return permissionsService.findAll();
	}

	
	/** Api get last item in Permissions 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public Permissions getLastPermissions() {
		
		List<Permissions> list = permissionsService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in Permissions 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public Permissions getFirstPermissions() {
		
		return permissionsService.findAll().get(0);
	}

	
	/** Api get item by id in Permissions 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public Permissions getShopVoucher(@PathVariable Long id) {
		Optional<Permissions> itemPermissions = permissionsService.findById(id);
		if(itemPermissions.isPresent()) {
			return itemPermissions.get();
		} 
		
		return null;
	}

	
	/** Api add item to Permissions 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public Permissions addPermissions(@RequestBody Permissions Permissions) {
		
		permissionsService.save(Permissions);
		
		return getLastPermissions();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public Permissions updatePermissions(@RequestBody Permissions Permissions) {
		
		permissionsService.save(Permissions);
		
		return permissionsService.findById(Permissions.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public Permissions deletePermissions(@PathVariable Long id) {
		
		Permissions item = permissionsService.findById(id).get();
		
		permissionsService.delete(item);
		
		return item;
	}
}
