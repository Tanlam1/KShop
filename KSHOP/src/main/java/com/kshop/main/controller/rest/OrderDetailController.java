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

import com.kshop.main.domain.OrderDetails;
import com.kshop.main.service.OrderDetailService;

import lombok.experimental.var;


@RestController
@RequestMapping("/OrderDetail")
@CrossOrigin
public class OrderDetailController {
	
	@Autowired
	OrderDetailService orderDetailService;

	
	/** Api get all OrderDetails 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all OrderDetails
	public List<OrderDetails> getAllOrderDetails() {
		List<OrderDetails> list = orderDetailService.findAll();
		
		for(OrderDetails item: list) {
		    item.getOrders().setUsers(null);
		    item.getOrders().setCustomers(null);
		}
		return list;
	}

	
	/** Api get last item in OrderDetails 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public OrderDetails getLastOrderDetails() {
		
		List<OrderDetails> list = orderDetailService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in OrderDetails 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public OrderDetails getFirstOrderDetails() {
		
		return orderDetailService.findAll().get(0);
	}

	
	/** Api get item by id in OrderDetails 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public OrderDetails getShopOrderDetail(@PathVariable Long id) {
		Optional<OrderDetails> itemOrderDetails = orderDetailService.findById(id);
		if(itemOrderDetails.isPresent()) {
			return itemOrderDetails.get();
		} 
		
		return null;
	}
	
	/** Api get all item by order id in OrderDetails 
     * 
     * - GET Method: __/get/{id}
     * - Return(JSON): item
     * 
     * */
    @GetMapping("/get/orderid/{id}")
    public List<OrderDetails> getByOrderId(@PathVariable Long id) {
        List<OrderDetails> itemOrderDetails = orderDetailService.selectByOrderId(id);
        if(!itemOrderDetails.isEmpty()) {
            for(OrderDetails item : itemOrderDetails) {
                item.getOrders().getUsers().setPassword("");
                item.getOrders().getCustomers().setPassword("");
            }
            return itemOrderDetails;
        } 
        
        return null;
    }

	
	/** Api add item to OrderDetails 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public OrderDetails addOrderDetails(@RequestBody OrderDetails OrderDetails) {
		
		orderDetailService.save(OrderDetails);
		
		return getLastOrderDetails();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public OrderDetails updateOrderDetails(@RequestBody OrderDetails OrderDetails) {
		
		orderDetailService.save(OrderDetails);
		
		return orderDetailService.findById(OrderDetails.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public OrderDetails deleteOrderDetails(@PathVariable Long id) {
		
		OrderDetails item = orderDetailService.findById(id).get();
		
		orderDetailService.delete(item);
		
		return item;
	}
}
