package com.kshop.main.controller.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.kshop.main.common.APIResponse;
import com.kshop.main.common.Constant;
import com.kshop.main.domain.Customers;
import com.kshop.main.domain.OrderDetails;
import com.kshop.main.domain.Orders;
import com.kshop.main.domain.ProductReviews;
import com.kshop.main.domain.Products;
import com.kshop.main.domain.Users;
import com.kshop.main.model.ReportSort;
import com.kshop.main.service.CustomerService;
import com.kshop.main.service.OrderDetailService;
import com.kshop.main.service.OrderService;
import com.kshop.main.service.ProductReviewsService;
import com.kshop.main.service.ProductsService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.service.UsersService;
import com.kshop.main.utils.MD5;
import com.kshop.main.utils.Roles;


@RestController
@RequestMapping("/Orders")
@CrossOrigin
public class OrdersController {
	
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	SessionService sessionService;
	@Autowired
	UsersService usersService;
	@Autowired
	CustomerService customerService;
    @Autowired
    UserHasRolesService userHasRolesService;
    @Autowired
    ProductsService productsService;
    @Autowired
    ProductReviewsService productReviewsService;

	
	/** Api get all Orders 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all Orders
	public List<Orders> getAllOrders() {
	    
        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);
        
        if(userLogined == null) {
            
            return null;
        }
        boolean isAdminAndAdminMoney = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY, Constant.ROLES.ADMIN), sessionService, userHasRolesService);
        boolean isAdminMoney = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY), sessionService, userHasRolesService);
	    
        Long idUserHasStore = sessionService.get(Constant.SESSION_DATA.REPORT_USER_ID, null);
        
        List<Orders> list = orderService.selectByUserId(userLogined.getId());
        if(isAdminAndAdminMoney) {
            list = orderService.findAll();
        }
        if(isAdminMoney) {
            list = orderService.selectByOrderComleteClose();
        }
        if(isAdminAndAdminMoney && idUserHasStore != null) {
            list = orderService.selectByUserId(idUserHasStore);
        }
	    
	    for(Orders item : list) {
	        item.setCustomers(null);
	        item.setUsers(null);
	    }
		
		return list;
	}
	/** Api get last item in Orders 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/get/{start}/{total}")
	public APIResponse getPageOrders(@PathVariable int start, @PathVariable int total){
        
		APIResponse response = new APIResponse();
		Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);
		
		if(userLogined == null) {
            
            response.setStatus(19);
            response.setError("Can not GET /get/limit/total. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");
            
            return response;
        }
		boolean isAdminAndAdminMoney = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY, Constant.ROLES.ADMIN), sessionService, userHasRolesService);
		boolean isAdminMoney = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY), sessionService, userHasRolesService);
		
		Long idUserHasStore = sessionService.get(Constant.SESSION_DATA.REPORT_USER_ID, null);
		
		List<Orders> list = orderService.selectByUserId(userLogined.getId());
		if(isAdminAndAdminMoney) {
		    list = orderService.findAll();
		}
        if(isAdminMoney) {
            list = orderService.selectByOrderComleteClose();
        }
		if(isAdminAndAdminMoney && idUserHasStore != null) {
		    list = orderService.selectByUserId(idUserHasStore);
		}
		
		if(list.isEmpty()) {
            
            response.setStatus(201);
            response.setData("Không có đơn hàng nào được tìm thấy");
            
            return response;
		    
		}
		
		Collections.reverse(list);
        int toStart = start*total;
        int toIndex = total + toStart;
        toIndex = toIndex > list.size() ? list.size() : toIndex;
        response.setStatus(200);
        response.setData(list.subList(toStart, toIndex));
        
        return response;
	}
    @PostMapping("/updatestatus/{idOrder}")
    public APIResponse changeStatus(@PathVariable Long idOrder, @RequestBody Map<String, String> body){
        
        APIResponse response = new APIResponse();
        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);
        
        if(userLogined == null) {
            
            response.setStatus(19);
            response.setError("Can not GET /updatestatus/id. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");
            
            return response;
        }
        
        Optional<Orders> item = orderService.findById(idOrder);
        
        if(item.isEmpty()) {
            
            response.setStatus(201);
            response.setData("Không tìm thấy đơn hàng này.");
            
            return response;
            
        }
        
        item.get().setOrder_status(body.get("status"));
        
        orderService.save(item.get());
        
        if(item.get().getOrder_status().equalsIgnoreCase("Complete")) { // cập nhật lại quantity của sản phẩm
            List<OrderDetails> listOrderDetails = orderDetailService.selectByOrderId(item.get().getId());
            
            listOrderDetails.forEach(e -> {
                Products products = e.getProducts(); 
                Double quantity = products.getQuantity() - e.getQuantity();
                quantity = quantity < 0 ? 0 : quantity;
                
                products.setQuantity(quantity);
                
                productsService.save(products);
            });
        }
        
        response.setStatus(200);
        response.setData("Cập nhập thành công trạng thái đơn hàng");
        
        return response;
    }
	/** Api get last item in Orders 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public Orders getLastOrders() {
		
		List<Orders> list = orderService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in Orders 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public Orders getFirstOrders() {
		
		return orderService.findAll().get(0);
	}

	
	/** Api get item by id in Orders 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public Orders getShopOrder(@PathVariable Long id) {
		Optional<Orders> itemOrders = orderService.findById(id);
		if(itemOrders.isPresent()) {
			return itemOrders.get();
		} 
		
		return null;
	}

	
	/** Api get all item by customer id in Orders 
     * 
     * - GET Method: __/get/customerid/{id}
     * - Return(JSON): items
     * 
     * */
    @GetMapping("/get/customerid/{id}")
    public APIResponse getShopOrderByCId(@PathVariable Long id) {
        APIResponse response = new APIResponse();
        Customers customerLogin = sessionService.get(Constant.SESSION_LOGIN_TYPE.CLIENT, null);      
        Optional<Customers> customerPost = customerService.findById(id);
        if(customerPost.isEmpty()) {
            response.setStatus(400);
            response.setData(Constant.RESPONE.TOKEN_DIE);
            response.setError("Not found order.  Error 400");
            
            return response;
        }
        
        if(customerLogin == null) {
            response.setStatus(400);
            response.setData(Constant.RESPONE.TOKEN_DIE);
            response.setError("Token die.  Error 400");
            
            return response;
        }
        
        Customers customerDB = customerService.getById(customerLogin.getId());
        
        if(!customerDB.getId().equals(customerPost.get().getId())) {
            response.setStatus(400);
            response.setData(Constant.RESPONE.TOKEN_NOT_ACCEPT);
            response.setError("Token not accept.  Error 400");
            
            return response;
            
        }
                
        List<Orders> itemOrders = orderService.selectByCustomerId(id);
        List<String> detailProductList = new ArrayList();
        Map map = new HashMap();
        for(Orders order : itemOrders) {
            
            List<OrderDetails> orderDetailList = orderDetailService.selectByOrderId(order.getId());
            String resultText = "";
            for(OrderDetails orderDetail: orderDetailList) {
                resultText +=  ", " + orderDetail.getOrder_detail_name();
            }
            resultText = resultText.substring(2);
            detailProductList.add(resultText);
            order.getUsers().setPassword(null);
        }
        
        map.put("orders", itemOrders);
        map.put("order_detail_product", detailProductList);
        
        response.setData(map);
        return response;
    }
	
	/** Api add item to Orders 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public Orders addOrders(@RequestBody Orders Orders) {
		
		orderService.save(Orders);
		
		return getLastOrders();
	}
    @PostMapping("/adds")
    public List<Orders> addOrders(@RequestBody List<Orders> Orders) {
        List<Orders> result = new ArrayList<>();
        for(Orders item: Orders) {
            if(item.getPaymentTypes().getId() == 2) {
                item.setPaid_date(new Date());
            }
            orderService.save(item);
            Orders orders = getLastOrders();
            result.add(orders);
        }
        
        return result;
    }

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/updateGHNOrderID")
	public APIResponse updateOrders(@RequestBody Orders orders) {
	    APIResponse response = new APIResponse();
	    
	    Users userLogin = sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);	    
	    Optional<Orders> ordersDB = orderService.findById(orders.getId());
	    if(ordersDB.isEmpty()) {
	        response.setStatus(400);
	        response.setData(Constant.RESPONE.NOT_FOUND);
	        response.setError("Not found order.  Error 400");
	        
	        return response;
	    }
	    
        if(userLogin == null) {
            response.setStatus(400);
            response.setData(Constant.RESPONE.TOKEN_DIE);
            response.setError("Token die.  Error 400");
            
            return response;
        }
	    
        Users userDB = usersService.getById(userLogin.getId());
        
        if(!userDB.getId().equals(ordersDB.get().getUsers().getId())) {
            response.setStatus(400);
            response.setData(Constant.RESPONE.TOKEN_NOT_ACCEPT);
            response.setError("Token not accept.  Error 400");
            
            return response;
            
        }
        
        if(ordersDB.get().getId_order_ghn() != null) {
            response.setStatus(400);
            response.setData(Constant.RESPONE.ALREADY_UPDATED);
            response.setError("ALREADY UPDATED.  Error 400");
            
            return response;
            
        }
        
        ordersDB.get().setId_order_ghn(orders.getId_order_ghn());
        
		orderService.save(ordersDB.get());
		
		response.setData("Cập nhật thành công");
		
		return response;
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public Orders deleteOrders(@PathVariable Long id) {
		
		Orders item = orderService.findById(id).get();
		
		orderService.delete(item);
		
		return item;
	}

    @PostMapping("/report")
    public APIResponse getReportMoneySend(@RequestBody ReportSort sort) {
        
        APIResponse response = new APIResponse();
        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);
        
        if(userLogined == null) {
            
            response.setStatus(19);
            response.setError("Can not GET /get/limit/total. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");
            
            return response;
        }
        boolean isAdminAndAdminMoney = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY, Constant.ROLES.ADMIN), sessionService, userHasRolesService);
        
        Long idUserHasStore = sessionService.get(Constant.SESSION_DATA.REPORT_USER_ID, null);

        List<Orders> list = orderService.selectByUserId(userLogined.getId());
        if(isAdminAndAdminMoney) {
            list = orderService.findAll();
        }
        if(isAdminAndAdminMoney && idUserHasStore != null) {
            list = orderService.selectByUserId(idUserHasStore);
        }
        
        if(list.isEmpty()) {
            
            response.setStatus(201);
            response.setData("Không có đơn hàng nào được tìm thấy");
            
            return response;
            
        }
        
        Calendar calendar = Calendar.getInstance();
        Map<String, Object> map = new HashMap<>();
                
        Timestamp timestamp = new Timestamp(sort.getStart_date().getTime());
        long tsStart = timestamp.getTime() - 7*60*60 * 1000; // trừ lại 7 tiếng vì GMT+7
        timestamp = new Timestamp(sort.getEnd_date().getTime());
        long tsEnd = timestamp.getTime() + (17 * 60 * 60 * 1000); // vẫn trừ nhưng tăng lên một ngày mới chuẩn
        
        List<Orders> result = list
            .stream()
            .filter(e -> {
                Timestamp timestamps = new Timestamp(e.getCreated_at().getTime());
                long tsItem = timestamps.getTime();
                
                if(sort.getType() == 0) {
                    return (tsItem >= tsStart && tsItem <= tsEnd);
                } else if(sort.getType() == 1) { // get theo tháng cụ thể của năm hiện tại
                    Date currentDate = new Date();
                    calendar.setTime(currentDate);
                    int year = calendar.get(Calendar.YEAR);
                    calendar.setTime(e.getCreated_at());
                    int monthDB = calendar.get(Calendar.MONTH) + 1;
                    int yearDB = calendar.get(Calendar.YEAR);    
                    
                    return (monthDB == sort.getTimeSpeci() && year == yearDB );
                } else if(sort.getType() == 2) { // get theo năm cụ thể
                    calendar.setTime(e.getCreated_at());
                    int yearDB = calendar.get(Calendar.YEAR);    
                    
                    return (yearDB == sort.getTimeSpeci());
                } else {
                    return false;
                }                                
            }).collect(Collectors.toList());            
        
        Collections.reverse(result);
        map.put("result", result);
        response.setData(map);
        
        return response;
    }
    
    @PutMapping("/cancel/{id}")
    public APIResponse cancelOrders(@PathVariable Long id, @RequestBody Map<String, Object> textCancel) {
        
        APIResponse response = new APIResponse();
        
        Customers customerLogin = sessionService.get(Constant.SESSION_LOGIN_TYPE.CLIENT, null);      
        Optional<Orders> ordersDB = orderService.findById(id);
        if(ordersDB.isEmpty()) {
            response.setStatus(400);
            response.setData(Constant.RESPONE.NOT_FOUND);
            response.setError("Not found order.  Error 400");
            
            return response;
        }
        
        if(customerLogin == null) {
            response.setStatus(400);
            response.setData(Constant.RESPONE.TOKEN_DIE);
            response.setError("Token die.  Error 400");
            
            return response;
        }
        
        Customers customerDB = customerService.getById(customerLogin.getId());
        
        if(!customerDB.getId().equals(ordersDB.get().getCustomers().getId())) {
            response.setStatus(400);
            response.setData(Constant.RESPONE.TOKEN_NOT_ACCEPT);
            response.setError("Token not accept.  Error 400");
            
            return response;
            
        }
        
        if(!ordersDB.get().getOrder_status().equals(Constant.ORDER_STATUS.NEW)) {
            response.setStatus(400);
            response.setData("Không thể hủy đơn hàng này. Vui lòng liên hệ bộ phận hỗ trợ để biết thêm chi tiết.");
            response.setError("CAN NOT UPDATE.  Error 400");
            
            return response;
            
        }
        String order_note = String.valueOf(textCancel.get("content"));
        ordersDB.get().setOrder_status(Constant.ORDER_STATUS.CANCEL);
        ordersDB.get().setOrder_note(order_note);
        
        orderService.save(ordersDB.get());
        
        response.setData("Hủy đơn hàng thành công.");
        
        return response;
    }
    
    @GetMapping("/getReviewList")
    public APIResponse getReviewList() {
        
        APIResponse response = new APIResponse();
        
        Customers customerLogin = (Customers) sessionService.get(Constant.SESSION_LOGIN_TYPE.CLIENT, null);
        
        if(customerLogin == null) {
            response.setError(400);
            response.setData("Token hết hạn hoặc không tồn tại. Vui lòng thử lại");
            response.setError("Error token failed");
            
            return response;
        }
        
        List<Orders> listOrder = orderService.selectByCustomerId(customerLogin.getId());
        
        listOrder = listOrder.stream().filter(e -> {
            return (e.getOrder_status().equals(Constant.ORDER_STATUS.COMPLETE) || e.getOrder_status().equals(Constant.ORDER_STATUS.CLOSE));
             
        }).collect(Collectors.toList());
        
        Map map = new HashMap();
        List<Object> listDT = new ArrayList();
        List<Long> listIdProduct = new ArrayList();
        for(Orders order : listOrder) {
            List<OrderDetails> orderDetailList = orderDetailService.selectByOrderId(order.getId());

            for(OrderDetails orderDetail: orderDetailList) {
                Map mapItem = new HashMap();
                if(listIdProduct.stream().allMatch(e -> !e.equals(orderDetail.getProducts().getId()))) {

                    ProductReviews productReview = productReviewsService.selectByCustomerIdAndProductId(customerLogin.getId(), orderDetail.getProducts().getId());
                    
                    if(productReview != null) { // người dùng đã review rồi
                        Map mapPV = new HashMap();
                        mapPV.put("noidung", productReview.getComment());
                        mapPV.put("rating", productReview.getRating());
                        mapPV.put("created_at", productReview.getCreated_at());
                        
                        mapItem.put("review_status", mapPV);
                    } else {
                        mapItem.put("review_status", false);
                    }
                    mapItem.put("detail_order", orderDetail);
                    
                    listDT.add(mapItem);
                    listIdProduct.add(orderDetail.getProducts().getId());
                }
            }
            
            order.getUsers().setPassword(null);
        }
        map.put("response", listDT);
        
        response.setData(map);      

        return response;
    }
}
