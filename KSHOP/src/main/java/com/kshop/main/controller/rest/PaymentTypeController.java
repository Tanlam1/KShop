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

import com.kshop.main.domain.PaymentTypes;
import com.kshop.main.service.PaymentTypeService;


@RestController
@RequestMapping("/PaymentType")
@CrossOrigin
public class PaymentTypeController {
	
	@Autowired
	PaymentTypeService paymentTypeService;

	
	/** Api get all PaymentTypes 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all PaymentTypes
	public List<PaymentTypes> getAllPaymentTypes() {
		
		return paymentTypeService.findAll();
	}

	
	/** Api get last item in PaymentTypes 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public PaymentTypes getLastPaymentTypes() {
		
		List<PaymentTypes> list = paymentTypeService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in PaymentTypes 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public PaymentTypes getFirstPaymentTypes() {
		
		return paymentTypeService.findAll().get(0);
	}

	
	/** Api get item by id in PaymentTypes 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public PaymentTypes getShopVoucher(@PathVariable Long id) {
		Optional<PaymentTypes> itemPaymentTypes = paymentTypeService.findById(id);
		if(itemPaymentTypes.isPresent()) {
			return itemPaymentTypes.get();
		} 
		
		return null;
	}

	
	/** Api add item to PaymentTypes 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public PaymentTypes addPaymentTypes(@RequestBody PaymentTypes PaymentTypes) {
		
		paymentTypeService.save(PaymentTypes);
		
		return getLastPaymentTypes();
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public PaymentTypes updatePaymentTypes(@RequestBody PaymentTypes PaymentTypes) {
		
		paymentTypeService.save(PaymentTypes);
		
		return paymentTypeService.findById(PaymentTypes.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public PaymentTypes deletePaymentTypes(@PathVariable Long id) {
		
		PaymentTypes item = paymentTypeService.findById(id).get();
		
		paymentTypeService.delete(item);
		
		return item;
	}
}
