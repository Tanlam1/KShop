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

import com.kshop.main.domain.UserHasRoles;
import com.kshop.main.service.UserHasRolesService;


@RestController
@RequestMapping("/UserHasRoles")
@CrossOrigin
public class UserHasRolesController {
	
	@Autowired
	UserHasRolesService userHasRolesService;

	
	/** Api get all UserHasRoles 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all UserHasRoles
	public List<UserHasRoles> getAllUserHasRoles() {
		
		return userHasRolesService.findAll();
	}

	
	/** Api get last item in UserHasRoles 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public UserHasRoles getLastUserHasRoles() {
		
		List<UserHasRoles> list = userHasRolesService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in UserHasRoles 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public UserHasRoles getFirstUserHasRoles() {
		
		return userHasRolesService.findAll().get(0);
	}

	
	/** Api get item by id in UserHasRoles 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public UserHasRoles getShopVoucher(@PathVariable Long id) {
		Optional<UserHasRoles> itemUserHasRoles = userHasRolesService.findById(id);
		if(itemUserHasRoles.isPresent()) {
			return itemUserHasRoles.get();
		} 
		
		return null;
	}
	
    /** Api get all item by user id in UserHasRoles 
     * 
     * - GET Method: __/get/{id}
     * - Return(JSON): item
     * 
     * */
    @GetMapping("/get/UserId/{id}")
    public List<UserHasRoles> getByUserId(@PathVariable Long id) {
        List<UserHasRoles> itemUserHasRoles = userHasRolesService.selectsByUserId(id);
        if(!itemUserHasRoles.isEmpty()) {
            for(UserHasRoles item: itemUserHasRoles) {
                item.getUsers().setPassword("");
            }
            return itemUserHasRoles;
        } 
        
        return null;
    }
	
	/** Api add item to UserHasRoles 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public UserHasRoles addUserHasRoles(@RequestBody UserHasRoles UserHasRoles) {
		
		userHasRolesService.save(UserHasRoles);
		
		return getLastUserHasRoles();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public UserHasRoles updateUserHasRoles(@RequestBody UserHasRoles UserHasRoles) {
		
		userHasRolesService.save(UserHasRoles);
		
		return userHasRolesService.findById(UserHasRoles.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public UserHasRoles deleteUserHasRoles(@PathVariable Long id) {
		
		UserHasRoles item = userHasRolesService.findById(id).get();
		
		userHasRolesService.delete(item);
		
		return item;
	}
}
