package com.kshop.main.controller.rest;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import com.kshop.main.domain.MoneyOrders;
import com.kshop.main.domain.Orders;
import com.kshop.main.domain.Users;
import com.kshop.main.model.ReportSort;
import com.kshop.main.service.MoneyOrdersService;
import com.kshop.main.service.OrderService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.service.UsersService;
import com.kshop.main.utils.Roles;

@RestController
@RequestMapping("/MoneyOrders")
@CrossOrigin
public class MoneyOrdersController {

    @Autowired
    MoneyOrdersService moneyOrdersService;
    @Autowired
    SessionService sessionService;
    @Autowired
    UserHasRolesService userHasRolesService;
    @Autowired
    OrderService orderService;
    @Autowired
    UsersService usersService;

    private List<MoneyOrders> hiddenPassword(List<MoneyOrders> list) {
        for (MoneyOrders item : list) {
            item.getUser().setPassword("");
        }
        return list;
    }

    private MoneyOrders hiddenPassword_(MoneyOrders item) {
        item.getUser().setPassword("");
        return item;
    }

    /**
     * Api get all MoneyOrders
     * 
     * - GET Method: __/get
     * - Return(JSON): item
     * 
     */
    @GetMapping("/get") // api get all MoneyOrders
    public List<MoneyOrders> getAllMoneyOrders() {
        

        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);

        if (userLogined == null) {

            return null;
        }
        
        Long idUserHasStore = sessionService.get(Constant.SESSION_DATA.REPORT_USER_ID, null);
        
        boolean isAdminMoney = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY, Constant.ROLES.ADMIN),
                sessionService, userHasRolesService);
        List<MoneyOrders> list = moneyOrdersService.findAll();
        if (!isAdminMoney) {
            list = moneyOrdersService.selectAllByUserIdOfStore(userLogined.getId());
        }
        if(isAdminMoney && idUserHasStore != null) {
            list = moneyOrdersService.selectAllByUserIdOfStore(idUserHasStore);
        }
        for (MoneyOrders item : list) {
            Users employee = new Users();
            employee.setUsername(item.getUser().getUsername());

            item.setUser(employee);
        }

        return hiddenPassword(list);
    }

    /**
     * Api get last item in MoneyOrders
     * 
     * - GET Method: __/getLast
     * - Return(JSON): list item
     * 
     */
    @GetMapping("/getLast")
    public MoneyOrders getLastMoneyOrders() {

        List<MoneyOrders> list = moneyOrdersService.findAll();
        int totalItem = list.size();

        return hiddenPassword_(list.get(totalItem - 1));
    }

    /**
     * Api get first item in MoneyOrders
     * 
     * - GET Method: __/getFirst
     * - Return(JSON): item
     * 
     */
    @GetMapping("/getFirst")
    public MoneyOrders getFirstMoneyOrders() {

        return hiddenPassword_(moneyOrdersService.findAll().get(0));
    }

    @GetMapping("/get/{start}/{total}")
    public APIResponse getPageMoneyOrders(@PathVariable int start, @PathVariable int total) {
        
        APIResponse response = new APIResponse();
        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);

        if (userLogined == null) {

            response.setStatus(19);
            response.setError("Can not GET /get/limit/total. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");

            return response;
        }
        
        Long idUserHasStore = sessionService.get(Constant.SESSION_DATA.REPORT_USER_ID, null);

        boolean isAdminMoney = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY, Constant.ROLES.ADMIN),
                sessionService, userHasRolesService);
        List<MoneyOrders> list = moneyOrdersService.findAll();
        if (!isAdminMoney) {
            list = moneyOrdersService.selectAllByUserIdOfStore(userLogined.getId());
        }
        if(isAdminMoney && idUserHasStore != null) {
            list = moneyOrdersService.selectAllByUserIdOfStore(idUserHasStore);
        }
        for (MoneyOrders item : list) {
            Users employee = new Users();
            employee.setUsername(item.getUser().getUsername());

            item.setUser(employee);
        }

        if (list.isEmpty()) {

            response.setStatus(201);
            response.setData("Không có đơn hàng nào được tìm thấy");

            return response;

        }

        Collections.reverse(list);
        int toStart = start * total;
        int toIndex = total + toStart;
        toIndex = toIndex > list.size() ? list.size() : toIndex;
        response.setStatus(200);
        response.setData(list.subList(toStart, toIndex));

        return response;
    }

    /**
     * Api get item by id in MoneyOrders
     * 
     * - GET Method: __/get/{id}
     * - Return(JSON): item
     * 
     */
    @GetMapping("/get/{id}")
    public MoneyOrders getShopOrder(@PathVariable Long id) {
        Optional<MoneyOrders> itemMoneyOrders = moneyOrdersService.findById(id);
        if (itemMoneyOrders.isPresent()) {
            return hiddenPassword_(itemMoneyOrders.get());
        }

        return null;
    }

    @GetMapping("/get/UserId/{id}")
    public List<MoneyOrders> getByIdCustomer(@PathVariable Long id) {
        List<MoneyOrders> itemMoneyOrders = moneyOrdersService.selectAllByUserId(id);
        if (!itemMoneyOrders.isEmpty()) {
            return hiddenPassword(itemMoneyOrders);
        }

        return null;
    }

    /**
     * Api add item to MoneyOrders
     * 
     * - POST Method: __/add
     * - --data-raw: JSON
     * - Return(JSON): response
     * 
     */
    @PostMapping("/add")
    public APIResponse addMoneyOrders(@RequestBody MoneyOrders moneyOrders) {

        APIResponse response = new APIResponse();
        Users userLogin = sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        if (userLogin == null) {

            response.setStatus(19);
            response.setError("Can not POST /add. Error code: 19");
            response.setData(Constant.RESPONE.TOKEN_DIE);

            return response;
        }

        boolean isAdminMoney = Roles.UserHasRoles(Arrays.asList(7L), sessionService, userHasRolesService);

        if (!isAdminMoney) {

            response.setStatus(400);
            response.setError("Can not POST /add. Error code: 400");
            response.setData(Constant.RESPONE.TOKEN_FAIL_ROLE);

            return response;

        }

        Optional<Users> userPost = usersService.findById(userLogin.getId());
        if (userPost.isEmpty()) {

            response.setStatus(400);
            response.setError("Can not POST /add. Error code: 400");
            response.setData(Constant.RESPONE.NOT_FOUND_USER);

            return response;
        }

        Optional<Orders> orderPost = orderService.findById(moneyOrders.getOrder().getId());
        if (orderPost.isEmpty()) {

            response.setStatus(400);
            response.setError("Can not POST /add. Error code: 400");
            response.setData(Constant.RESPONE.NOT_FOUND_ORDER);

            return response;
        }

        if (!orderPost.get().getOrder_status().equals("Complete") && !orderPost.get().getOrder_status().equals("Close")) {

            response.setStatus(400);
            response.setError("Can not POST /add. Error code: 400");
            response.setData("Thất bại. Đơn hàng này chưa hoàn thành!");

            return response;
        }

        MoneyOrders moneyOrdersDB = moneyOrdersService.selectByUserIdAndOrderId(userPost.get().getId(),
                moneyOrders.getOrder().getId());
        if (moneyOrdersDB != null) {

            response.setStatus(400);
            response.setError("Can not POST /add. Error code: 400");
            response.setData(Constant.RESPONE.HAS_ERROR);

            return response;

        }

        if (moneyOrders.isStatus()) { // cộng tiền cho user
            Users userUpdate = orderPost.get().getUsers();
            userUpdate.setSodu(userUpdate.getSodu() + moneyOrders.getTotal_money());

            usersService.save(userUpdate);
        }

        orderPost.get().set_mo(true);
        orderPost.get().setOrder_status(Constant.ORDER_STATUS.CLOSE);
        orderService.save(orderPost.get()); // chuyển lại thành đã xử lý order này

        moneyOrders.setUser(userPost.get());
        moneyOrdersService.save(moneyOrders);

        response.setData("Successfully");

        return response;
    }

    /**
     * Api update item
     * 
     * - PUT Method: __/update
     * - --data-raw: JSON
     * - Return(JSON): item
     * 
     */
    @PutMapping("/update")
    public MoneyOrders updateMoneyOrders(@RequestBody MoneyOrders MoneyOrders) {

        moneyOrdersService.save(MoneyOrders);

        return hiddenPassword_(moneyOrdersService.findById(MoneyOrders.getId()).get());
    }

    /**
     * Api delete item
     * 
     * - DELETE Method: __/delete/{id}
     * - Return(JSON): item
     * 
     */
    @DeleteMapping("/delete/{id}")
    public MoneyOrders deleteMoneyOrders(@PathVariable Long id) {

        MoneyOrders item = moneyOrdersService.findById(id).get();

        moneyOrdersService.delete(item);

        return hiddenPassword_(item);
    }

    @DeleteMapping("/delete/userid/{id}")
    public APIResponse deleteMoneyOrdersAll(@PathVariable Long id) {

        List<MoneyOrders> list = moneyOrdersService.selectAllByUserId(id);

        for (MoneyOrders item : list) {

            moneyOrdersService.delete(item);
        }

        return new APIResponse();
    }

    @PostMapping("/report")
    public APIResponse getReportMoneySend(@RequestBody ReportSort sort) {

        APIResponse response = new APIResponse();
        Calendar calendar = Calendar.getInstance();
        Map<String, Object> map = new HashMap<>();
        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);

        if (userLogined == null) {

            response.setStatus(19);
            response.setError("Can not GET /get/limit/total. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");

            return response;
        }
        
        Long idUserHasStore = sessionService.get(Constant.SESSION_DATA.REPORT_USER_ID, null);
        
        boolean isAdminMoney = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY, Constant.ROLES.ADMIN),
                sessionService, userHasRolesService);
        List<MoneyOrders> list = moneyOrdersService.findAll();
        if (!isAdminMoney) {
            list = moneyOrdersService.selectAllByUserIdOfStore(userLogined.getId());
        }
        if(isAdminMoney && idUserHasStore != null) {
            list = moneyOrdersService.selectAllByUserIdOfStore(idUserHasStore);
        }
        for (MoneyOrders item : list) {
            Users employee = new Users();
            employee.setUsername(item.getUser().getUsername());

            item.setUser(employee);
        }

        Timestamp timestamp = new Timestamp(sort.getStart_date().getTime());
        long tsStart = timestamp.getTime() - 7 * 60 * 60 * 1000; // trừ lại 7 tiếng vì GMT+7
        timestamp = new Timestamp(sort.getEnd_date().getTime());
        long tsEnd = timestamp.getTime() + (17 * 60 * 60 * 1000); // vẫn trừ nhưng tăng lên một ngày mới chuẩn

        List<MoneyOrders> result = list
                .stream()
                .filter(e -> {
                    Timestamp timestamps = new Timestamp(e.getCreated_at().getTime());
                    long tsItem = timestamps.getTime();

                    if (sort.getType() == 0) {
                        return (tsItem >= tsStart && tsItem <= tsEnd);
                    } else if (sort.getType() == 1) { // get theo tháng cụ thể của năm hiện tại
                        Date currentDate = new Date();
                        calendar.setTime(currentDate);
                        int year = calendar.get(Calendar.YEAR);
                        calendar.setTime(e.getCreated_at());
                        int monthDB = calendar.get(Calendar.MONTH) + 1;
                        int yearDB = calendar.get(Calendar.YEAR);

                        return (monthDB == sort.getTimeSpeci() && year == yearDB);
                    } else if (sort.getType() == 2) { // get theo năm cụ thể
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
}
