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

import com.kshop.main.domain.UserHasPermissions;
import com.kshop.main.service.UserHasPermissionsService;


@RestController
@RequestMapping("/UserHasPermissions")
@CrossOrigin
public class UserHasPermissionsController {
	
	@Autowired
	UserHasPermissionsService userHasPermissionsService;

	
	/** Api get all UserHasPermissions 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all UserHasPermissions
	public List<UserHasPermissions> getAllUserHasPermissions() {
		
		return userHasPermissionsService.findAll();
	}

	
	/** Api get last item in UserHasPermissions 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public UserHasPermissions getLastUserHasPermissions() {
		
		List<UserHasPermissions> list = userHasPermissionsService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in UserHasPermissions 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public UserHasPermissions getFirstUserHasPermissions() {
		
		return userHasPermissionsService.findAll().get(0);
	}

	
	/** Api get item by id in UserHasPermissions 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public UserHasPermissions getShopVoucher(@PathVariable Long id) {
		Optional<UserHasPermissions> itemUserHasPermissions = userHasPermissionsService.findById(id);
		if(itemUserHasPermissions.isPresent()) {
			return itemUserHasPermissions.get();
		} 
		
		return null;
	}

	
	/** Api add item to UserHasPermissions 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public UserHasPermissions addUserHasPermissions(@RequestBody UserHasPermissions UserHasPermissions) {
		
		userHasPermissionsService.save(UserHasPermissions);
		
		return getLastUserHasPermissions();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public UserHasPermissions updateUserHasPermissions(@RequestBody UserHasPermissions UserHasPermissions) {
		
		userHasPermissionsService.save(UserHasPermissions);
		
		return userHasPermissionsService.findById(UserHasPermissions.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public UserHasPermissions deleteUserHasPermissions(@PathVariable Long id) {
		
		UserHasPermissions item = userHasPermissionsService.findById(id).get();
		
		userHasPermissionsService.delete(item);
		
		return item;
	}
}
