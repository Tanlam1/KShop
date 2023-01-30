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

import com.kshop.main.domain.RoleHasPermissions;
import com.kshop.main.service.RoleHasPermissionsService;


@RestController
@RequestMapping("/RoleHasPermissions")
@CrossOrigin
public class RoleHasPermissionsController {
	
	@Autowired
	RoleHasPermissionsService roleHasPermissionsService;

	
	/** Api get all RoleHasPermissions 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all RoleHasPermissions
	public List<RoleHasPermissions> getAllRoleHasPermissions() {
		
		return roleHasPermissionsService.findAll();
	}

	
	/** Api get last item in RoleHasPermissions 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public RoleHasPermissions getLastRoleHasPermissions() {
		
		List<RoleHasPermissions> list = roleHasPermissionsService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in RoleHasPermissions 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public RoleHasPermissions getFirstRoleHasPermissions() {
		
		return roleHasPermissionsService.findAll().get(0);
	}

	
	/** Api get item by id in RoleHasPermissions 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public RoleHasPermissions getShopVoucher(@PathVariable Long id) {
		Optional<RoleHasPermissions> itemRoleHasPermissions = roleHasPermissionsService.findById(id);
		if(itemRoleHasPermissions.isPresent()) {
			return itemRoleHasPermissions.get();
		} 
		
		return null;
	}

	
	/** Api add item to RoleHasPermissions 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public RoleHasPermissions addRoleHasPermissions(@RequestBody RoleHasPermissions RoleHasPermissions) {
		
		roleHasPermissionsService.save(RoleHasPermissions);
		
		return getLastRoleHasPermissions();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public RoleHasPermissions updateRoleHasPermissions(@RequestBody RoleHasPermissions RoleHasPermissions) {
		
		roleHasPermissionsService.save(RoleHasPermissions);
		
		return roleHasPermissionsService.findById(RoleHasPermissions.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public RoleHasPermissions deleteRoleHasPermissions(@PathVariable Long id) {
		
		RoleHasPermissions item = roleHasPermissionsService.findById(id).get();
		
		roleHasPermissionsService.delete(item);
		
		return item;
	}
}
