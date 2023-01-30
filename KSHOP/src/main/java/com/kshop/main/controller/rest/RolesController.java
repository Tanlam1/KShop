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

import com.kshop.main.domain.Roles;
import com.kshop.main.service.RolesService;


@RestController
@RequestMapping("/Roles")
@CrossOrigin
public class RolesController {
	
	@Autowired
	RolesService rolesService;

	
	/** Api get all Roles 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all Roles
	public List<Roles> getAllRoles() {
		
		return rolesService.findAll();
	}

	
	/** Api get last item in Roles 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public Roles getLastRoles() {
		
		List<Roles> list = rolesService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in Roles 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public Roles getFirstRoles() {
		
		return rolesService.findAll().get(0);
	}

	
	/** Api get item by id in Roles 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public Roles getShopVoucher(@PathVariable Long id) {
		Optional<Roles> itemRoles = rolesService.findById(id);
		if(itemRoles.isPresent()) {
			return itemRoles.get();
		} 
		
		return null;
	}

	
	/** Api add item to Roles 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public Roles addRoles(@RequestBody Roles Roles) {
		
		rolesService.save(Roles);
		
		return getLastRoles();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public Roles updateRoles(@RequestBody Roles Roles) {
		
		rolesService.save(Roles);
		
		return rolesService.findById(Roles.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public Roles deleteRoles(@PathVariable Long id) {
		
		Roles item = rolesService.findById(id).get();
		
		rolesService.delete(item);
		
		return item;
	}
}
