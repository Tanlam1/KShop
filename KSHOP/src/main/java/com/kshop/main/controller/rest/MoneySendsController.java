package com.kshop.main.controller.rest;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import com.kshop.main.domain.EmailDetails;
import com.kshop.main.domain.MoneySends;
import com.kshop.main.domain.Users;
import com.kshop.main.model.ReportSort;
import com.kshop.main.service.EmailService;
import com.kshop.main.service.MoneySendsService;
import com.kshop.main.service.OrderService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.service.UsersService;
import com.kshop.main.utils.Roles;

@RestController
@RequestMapping("/MoneySends")
@CrossOrigin
public class MoneySendsController {

    @Autowired
    MoneySendsService moneySendsService;
    @Autowired
    SessionService sessionService;
    @Autowired
    UserHasRolesService userHasRolesService;
    @Autowired
    OrderService orderService;
    @Autowired
    UsersService usersService;
    @Autowired
    EmailService emailService;

    private List<MoneySends> hiddenPassword(List<MoneySends> list) {
        for (MoneySends item : list) {
            item.getUser().setPassword("");
        }
        return list;
    }

    private MoneySends hiddenPassword_(MoneySends item) {
        item.getUser().setPassword("");
        return item;
    }

    /**
     * Api get all MoneySends
     * 
     * - GET Method: __/get
     * - Return(JSON): item
     * 
     */
    @GetMapping("/get") // api get all MoneySends
    public List<MoneySends> getAllMoneySends() {

        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);

        if (userLogined == null) {

            return null;
        }
        
        Long idUserHasStore = sessionService.get(Constant.SESSION_DATA.REPORT_USER_ID, null);

        boolean isAdminMoney = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY, Constant.ROLES.ADMIN),
                sessionService, userHasRolesService);
        List<MoneySends> list = moneySendsService.findAll();
        if (!isAdminMoney) {
            list = moneySendsService.selectAllByUserId(userLogined.getId());
        }
        if (isAdminMoney && idUserHasStore != null) {
            list = moneySendsService.selectAllByUserId(idUserHasStore);
            
        }
        for (MoneySends item : list) {
            Users employee = new Users();
            employee.setUsername(item.getEmployee().getUsername());

            item.setEmployee(employee);
        }
        return hiddenPassword(list);
    }

    /**
     * Api get last item in MoneySends
     * 
     * - GET Method: __/getLast
     * - Return(JSON): list item
     * 
     */
    @GetMapping("/getLast")
    public MoneySends getLastMoneySends() {

        List<MoneySends> list = moneySendsService.findAll();
        int totalItem = list.size();

        return hiddenPassword_(list.get(totalItem - 1));
    }

    /**
     * Api get first item in MoneySends
     * 
     * - GET Method: __/getFirst
     * - Return(JSON): item
     * 
     */
    @GetMapping("/getFirst")
    public MoneySends getFirstMoneySends() {

        return hiddenPassword_(moneySendsService.findAll().get(0));
    }

    @GetMapping("/get/{start}/{total}")
    public APIResponse getPageMoneySends(@PathVariable int start, @PathVariable int total) {

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
        List<MoneySends> list = moneySendsService.findAll();        
        if (!isAdminMoney) {
            list = moneySendsService.selectAllByUserId(userLogined.getId());
        }
        if (isAdminMoney && idUserHasStore != null) {
            list = moneySendsService.selectAllByUserId(idUserHasStore);
        }
        for (MoneySends item : list) {
            Users employee = new Users();
            employee.setUsername(item.getEmployee().getUsername());

            item.setEmployee(employee);
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
     * Api get item by id in MoneySends
     * 
     * - GET Method: __/get/{id}
     * - Return(JSON): item
     * 
     */
    @GetMapping("/get/{id}")
    public MoneySends getShopOrder(@PathVariable Long id) {
        Optional<MoneySends> itemMoneySends = moneySendsService.findById(id);
        if (itemMoneySends.isPresent()) {
            return hiddenPassword_(itemMoneySends.get());
        }

        return null;
    }

    @GetMapping("/get/UserId/{id}")
    public List<MoneySends> getByIdCustomer(@PathVariable Long id) {
        List<MoneySends> itemMoneySends = moneySendsService.selectAllByUserId(id);
        if (!itemMoneySends.isEmpty()) {
            return hiddenPassword(itemMoneySends);
        }

        return null;
    }

    /**
     * Api add item to MoneySends
     * 
     * - POST Method: __/add
     * - --data-raw: JSON
     * - Return(JSON): response
     * 
     */
    @PostMapping("/add")
    public APIResponse addMoneySends(@RequestBody MoneySends moneySends) {

        APIResponse response = new APIResponse();
        Users userLogin = sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        if (userLogin == null) {

            response.setStatus(19);
            response.setError("Can not POST /add. Error code: 19");
            response.setData(Constant.RESPONE.TOKEN_DIE);

            return response;
        }

        boolean isAdminMoney = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY), sessionService,
                userHasRolesService);

        if (!isAdminMoney) {

            response.setStatus(400);
            response.setError("Can not POST /add. Error code: 400");
            response.setData(Constant.RESPONE.TOKEN_FAIL_ROLE);

            return response;

        }

        Optional<Users> userDB = usersService.findById(userLogin.getId());
        if (userDB.isEmpty()) {

            response.setStatus(400);
            response.setError("Can not POST /add. Error code: 400");
            response.setData(Constant.RESPONE.NOT_FOUND_USER);

            return response;
        }

        Optional<Users> userPost = usersService.findById(moneySends.getUser().getId());
        if (userPost.isEmpty()) {

            response.setStatus(401);
            response.setError("Can not POST /add. Error code: 400");
            response.setData(Constant.RESPONE.NOT_FOUND_USER);

            return response;

        }

        if (userPost.get().getPay_info_next() == null) {

            response.setStatus(401);
            response.setError("Can not POST /add. Error code: 400");
            response.setData("Shop này chưa đủ điều kiện nhận tiền");

            return response;
        }

        Double total = userPost.get().getSodu() - userPost.get().getSodu_ngoai() + userPost.get().getSodu_hold();

        userPost.get().setSodu(userPost.get().getSodu_ngoai());
        userPost.get().setSodu_hold(0D);
        userPost.get().setPay_info_next(null);

        moneySends.setUser(userPost.get());
        moneySends.setEmployee(userDB.get());
        moneySends.setInfo_banking(userPost.get().getInfo_receive_money());
        moneySends.setTotal_money(total);
        moneySendsService.save(moneySends);

        String[] payments = moneySends.getInfo_banking().split("\\|");
        String bankName = payments[1];
        if (bankName.equals("CK"))
            bankName = payments[3];

        Locale lc = new Locale("nv", "VN"); // định dạng tiền VN
        NumberFormat nf = NumberFormat.getInstance(lc);
        String tongTienNhan = nf.format(total);

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy"); // định dạng ngày lại thành string
        String strDate = formatter.format(moneySends.getCreated_at());

        String content = "<h3>Xin chào,<b> " + userPost.get().getStores().getStore_name() + "</b></h3>"
                + "<p style=\" background-color: #fafafa; padding: 12px; font-size: 16px; text-align: center; font-weight: bold;color: #123456e3; \"> Cảm ơn bạn đã dùng dịch vụ của chúng tôi. Chúc bạn và đội ngũ của bạn thật nhiều may mắn và thành công. </p>"
                + "<p style=\" font-size: 14px; \">Thông báo chuyển tiền thành công đến tài khoản của bạn</p>"
                + "<ul style=\"\r\n"
                + "    padding-left: 0;\r\n"
                + "\">\r\n"
                + "    <li style=\"\r\n"
                + "    padding: 12px 0;\r\n"
                + "\">Tài khoản: <b>" + userPost.get().getUsername() + "</b></li>\r\n"
                + "    <li style=\"\r\n"
                + "    padding: 12px 0;\r\n"
                + "\">Cửa hàng: <b>" + userPost.get().getStores().getStore_name() + "</b></li>\r\n"
                + "    <li style=\"\r\n"
                + "    padding: 12px 0;\r\n"
                + "\">Ngày nhận tiền: <b>" + strDate + "</b></li>\r\n"
                + "    <li style=\"\r\n"
                + "    padding: 12px 0;\r\n"
                + "\">Thông tin người nhận: <b>" + payments[0] + "</b>, <b>" + bankName + "</b>, <b>" + payments[2]
                + "</b></li><li style=\"\r\n"
                + "    padding: 12px 0;\r\n"
                + "\">Tổng tiền nhận: <b>" + tongTienNhan + " VNĐ</b></li>\r\n"
                + "    \r\n"
                + "    <li style=\"\r\n"
                + "    padding: 12px 0;\r\n"
                + "\"><b>" + moneySends.getNote() + "</b></li>\r\n"
                + " <li style=\"padding:12px 0\">Hình ảnh đính kèm:</li>   "
                + "<li style=\"\r\n"
                + "    list-style: none;\r\n"
                + "    text-align: center;\r\n"
                + "\"><img width=\"250px\" height=\"250px\" src=\"" + moneySends.getImage()
                + "\" alt=\"Banking\" jslog=\"138226; u014N:xr6bB; 53:W2ZhbHNlLDBd\" style=\"object-fit:cover;\"></li>\r\n"
                + "</ul>";

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(userPost.get().getEmail());
        emailDetails.setSubject("Thông báo nhận tiền của shop | KShop - Mua hàng online giá tốt");
        emailDetails.setMsgBody(content);

        emailService.sendMailWithAttachment(emailDetails);

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
    public MoneySends updateMoneySends(@RequestBody MoneySends MoneySends) {

        moneySendsService.save(MoneySends);

        return hiddenPassword_(moneySendsService.findById(MoneySends.getId()).get());
    }

    /**
     * Api delete item
     * 
     * - DELETE Method: __/delete/{id}
     * - Return(JSON): item
     * 
     */
    @DeleteMapping("/delete/{id}")
    public MoneySends deleteMoneySends(@PathVariable Long id) {

        MoneySends item = moneySendsService.findById(id).get();

        moneySendsService.delete(item);

        return hiddenPassword_(item);
    }

    @DeleteMapping("/delete/userid/{id}")
    public APIResponse deleteMoneySendsAll(@PathVariable Long id) {

        List<MoneySends> list = moneySendsService.selectAllByUserId(id);

        for (MoneySends item : list) {

            moneySendsService.delete(item);
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
        List<MoneySends> list = moneySendsService.findAll();        
        if (!isAdminMoney) {
            list = moneySendsService.selectAllByUserId(userLogined.getId());
        }
        if (isAdminMoney && idUserHasStore != null) {
            list = moneySendsService.selectAllByUserId(idUserHasStore);
        }
        for (MoneySends item : list) {
            Users employee = new Users();
            employee.setUsername(item.getEmployee().getUsername());

            item.setEmployee(employee);
        }

        Timestamp timestamp = new Timestamp(sort.getStart_date().getTime());
        long tsStart = timestamp.getTime() - 7 * 60 * 60 * 1000; // trừ lại 7 tiếng vì GMT+7
        timestamp = new Timestamp(sort.getEnd_date().getTime());
        long tsEnd = timestamp.getTime() + (17 * 60 * 60 * 1000); // vẫn trừ nhưng tăng lên một ngày mới chuẩn

        List<MoneySends> result = list
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
