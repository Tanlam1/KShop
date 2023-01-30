package com.kshop.main.controller.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
import com.kshop.main.domain.ShopStores;
import com.kshop.main.domain.UserHasRoles;
import com.kshop.main.domain.Users;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.ShopStoresService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.service.UsersService;
import com.kshop.main.utils.MD5;
import com.kshop.main.utils.Roles;

@RestController
@RequestMapping("/Users")
@CrossOrigin
public class UsersController {

    @Autowired
    UsersService usersService;
    @Autowired
    SessionService sessionService;
    @Autowired
    ShopStoresService storesService;
    @Autowired
    UserHasRolesService userHasRolesService;

    private List<Users> hiddenPassword(List<Users> list) {
        for (Users item : list) {
            item.setPassword("");
        }
        return list;
    }

    private Users hiddenPassword_(Users item) {
        item.setPassword("");
        return item;
    }

    /**
     * Api get all Users
     * 
     * - GET Method: __/get
     * - Return(JSON): item
     * 
     */
    @GetMapping("/get") // api get all Users
    public APIResponse getAllUsers() {

        APIResponse response = new APIResponse();
        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);

        if (userLogined == null) {

            response.setStatus(19);
            response.setError("Can not GET /get/limit/total. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");

            return response;
        }
        
        boolean isAdmin = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY, Constant.ROLES.ADMIN),
                sessionService, userHasRolesService);

        if(!isAdmin) {

            response.setStatus(19);
            response.setError("Can not GET /get/limit/total. Error code: 19");
            response.setData(Constant.RESPONE.TOKEN_FAIL_ROLE);

            return response;
            
        }
        
        List<Users> list = usersService.findAll();
        
        response.setData(hiddenPassword(list));
        
        return response;
    }

    @GetMapping("/get/{start}/{total}") // api get all Users
    public APIResponse getPageUsers(@PathVariable int start, @PathVariable int total) {

        APIResponse response = new APIResponse();
        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);

        if (userLogined == null) {

            response.setStatus(19);
            response.setError("Can not GET /get/limit/total. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");

            return response;
        }
        
        boolean isAdmin = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY, Constant.ROLES.ADMIN),
                sessionService, userHasRolesService);

        if(!isAdmin) {

            response.setStatus(19);
            response.setError("Can not GET /get/limit/total. Error code: 19");
            response.setData(Constant.RESPONE.TOKEN_FAIL_ROLE);

            return response;
            
        }
        
        List<Users> list = usersService.findAll();     

        Collections.reverse(list);
        int toStart = start*total;
        int toIndex = total + toStart;
        toIndex = toIndex > list.size() ? list.size() : toIndex;
        response.setData(hiddenPassword(list.subList(toStart, toIndex)));
        
        return response;
    }
    
    @GetMapping("/get/hasstore") // api get all Users
    public APIResponse getAllUsersHasStore() {
        APIResponse response = new APIResponse();
        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);

        if (userLogined == null) {

            response.setStatus(19);
            response.setError("Can not GET /get/limit/total. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");

            return response;
        }
        
        boolean isAdmin = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY, Constant.ROLES.ADMIN),
                sessionService, userHasRolesService);

        if(!isAdmin) {

            response.setStatus(19);
            response.setError("Can not GET /get/limit/total. Error code: 19");
            response.setData(Constant.RESPONE.TOKEN_FAIL_ROLE);

            return response;
            
        }
        
        List<Users> list = usersService.findAll();
        List<Users> listRs = new ArrayList<>();
        for(Users user : list) {
            if(user.getStores() != null) {
                listRs.add(user);
            }
        }
        
        response.setData(hiddenPassword(listRs));
        
        return response;
    }

    @GetMapping("/get/hasstore/{start}/{total}") // api get all Users
    public APIResponse getPageUsersHasStore(@PathVariable int start, @PathVariable int total) {
        APIResponse response = new APIResponse();
        Users userLogined = (Users) sessionService.get("admin_kodoku_KShop", null);

        if (userLogined == null) {

            response.setStatus(19);
            response.setError("Can not GET /get/limit/total. Error code: 19");
            response.setData("Token đã hết hạn. Vui lòng đăng nhập lại.");

            return response;
        }
        
        boolean isAdmin = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN_MONEY, Constant.ROLES.ADMIN),
                sessionService, userHasRolesService);

        if(!isAdmin) {

            response.setStatus(19);
            response.setError("Can not GET /get/limit/total. Error code: 19");
            response.setData(Constant.RESPONE.TOKEN_FAIL_ROLE);

            return response;
            
        }
        
        List<Users> list = usersService.findAll();
        List<Users> listRs = new ArrayList<>();
        for(Users user : list) {
            if(user.getStores() != null) {
                listRs.add(user);
            }
        }

        Collections.reverse(listRs);
        int toStart = start*total;
        int toIndex = total + toStart;
        toIndex = toIndex > listRs.size() ? listRs.size() : toIndex;
        response.setData(hiddenPassword(listRs.subList(toStart, toIndex)));
        
        return response;
    }

    /**
     * Api get last item in Users
     * 
     * - GET Method: __/getLast
     * - Return(JSON): list item
     * 
     */
    @GetMapping("/getLast")
    public Users getLastUsers() {

        List<Users> list = usersService.findAll();
        int totalItem = list.size();

        return list.get(totalItem - 1);
    }

    /**
     * Api get first item in Users
     * 
     * - GET Method: __/getFirst
     * - Return(JSON): item
     * 
     */
    @GetMapping("/getFirst")
    public Users getFirstUsers() {

        return usersService.findAll().get(0);
    }

    /**
     * Api get item by id in Users
     * 
     * - GET Method: __/get/{id}
     * - Return(JSON): item
     * 
     */
    @GetMapping("/get/{id}")
    public Users getShopVoucher(@PathVariable Long id) {
        Optional<Users> itemUsers = usersService.findById(id);
        if (itemUsers.isPresent()) {
            return hiddenPassword_(itemUsers.get());
        }

        return null;
    }

    /**
     * Api add item to Users
     * 
     * - POST Method: __/add
     * - --data-raw: JSON
     * - Return(JSON): item
     * 
     */
    @PostMapping("/add")
    public Users addUsers(@RequestBody Users Users) {

        usersService.save(Users);

        return getLastUsers();
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
    public APIResponse updateUsers(@RequestBody Users usersBody) {
        
        APIResponse response = new APIResponse();
        
        Users usersLogin = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        
        if(usersLogin == null) {
            response.setError(400);
            response.setData("Token hết hạn hoặc không tồn tại. Vui lòng thử lại");
            response.setError("Error token failed");
            
            return response;
        }
        
        if(!usersLogin.getId().equals(usersBody.getId())) {
            response.setError(400);
            response.setData("Không thể thực hiện hành động này. Vui lòng thử lại");
            response.setError("Error token failed access");
            
            return response;
        }
        Users users = usersService.findById(usersBody.getId()).get();
        
        users.setUpdated_at(new Date());
        
        usersService.save(users);
        
        response.setData("Chỉnh sửa thành công!");      

        return response;
    }
    
    @PutMapping("/updateMoneyType")
    public APIResponse updateMoneyTypeUsers(@RequestBody Map<Object, String> body) {
        
        APIResponse response = new APIResponse();
        
        Users usersLogin = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        
        if(usersLogin == null) {
            response.setError(400);
            response.setData("Token hết hạn hoặc không tồn tại. Vui lòng thử lại");
            response.setError("Error token failed");
            
            return response;
        }
        if(body.get("data") == null) {
            response.setError(400);
            response.setData("Đã xãy ra lỗi, thiếu key data. Vui lòng thử lại");
            response.setError("Error data key not found");
            
            return response;
            
        }
        Users users = usersService.findById(usersLogin.getId()).get();
        Double soduNgoai = Double.parseDouble(body.get("sodu_ngoai"));
        
        users.setInfo_receive_money(body.get("data"));
        users.setUpdated_at(new Date());
        users.setSodu_ngoai(soduNgoai);
        
        usersService.save(users);
        
        response.setData("Chỉnh sửa thành công!");      

        return response;
    }
    
    @PostMapping("/updateProfile")
    public APIResponse updateProfileUsers(@RequestBody Users users) {
        
        APIResponse response = new APIResponse();
        
        Users usersLogin = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        
        if(usersLogin == null) {
            response.setError(400);
            response.setData("Token hết hạn hoặc không tồn tại. Vui lòng thử lại");
            response.setError("Error token failed");
            
            return response;
        }
        if(!usersLogin.getId().equals(users.getId())) {
            response.setError(400);
            response.setData("Không thể thực hiện hành động này. Vui lòng thử lại");
            response.setError("Error token not access.");
            
            return response;
            
        }
        ShopStores shopStores = users.getStores();
        Optional<ShopStores> stores = storesService.findById(shopStores.getId()); 
        if(stores.isEmpty()) {
            response.setError(400);
            response.setData("Không tìm thấy store này. Vui lòng thử lại");
            response.setError("Error store not found.");
            
            return response;
            
        }
        storesService.save(shopStores);
        
        Users usersPost = usersService.findById(usersLogin.getId()).get();
        
        usersPost.setAddress1(users.getAddress1());
        usersPost.setLast_name(users.getLast_name());
        usersPost.setFirst_name(users.getFirst_name());
        usersPost.setCity(users.getCity());
        usersPost.setState(users.getState());
        usersPost.setWard(users.getWard());
        usersPost.setCode(users.getCode());
        usersPost.setPhone(users.getPhone());
//        usersPost.setEmail(users.getEmail());
        usersPost.setStores(shopStores);
        
        usersService.save(usersPost);
        
        response.setData("Chỉnh sửa thành công.");      

        return response;
    }
    
    @PostMapping("/updateUser")
    public APIResponse AdminUpdateUsers(@RequestBody Users users) {
        
        APIResponse response = new APIResponse();
        
        Users usersLogin = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        
        if(usersLogin == null) {
            response.setError(400);
            response.setData("Token hết hạn hoặc không tồn tại. Vui lòng thử lại");
            response.setError("Error token failed");
            
            return response;
        }
        
        boolean isAdmin = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN), sessionService, userHasRolesService);

        if(!isAdmin) {

            response.setStatus(400);
            response.setError("Can not actions. Error code: 19");
            response.setData(Constant.RESPONE.TOKEN_FAIL_ROLE);

            return response;
        }     
        
        Optional<Users> optionaUser = usersService.findById(users.getId());
        
        if(optionaUser.isEmpty()) {

            response.setStatus(400);
            response.setError("Can not actions. Error code: 19");
            response.setData(Constant.RESPONE.NOT_FOUND_USER);

            return response;
        }
        
        Users userDB = optionaUser.get();
        
        userDB.setAddress1(users.getAddress1());
        userDB.setLast_name(users.getLast_name());
        userDB.setFirst_name(users.getFirst_name());
        userDB.setCity(users.getCity());
        userDB.setState(users.getState());
        userDB.setWard(users.getWard());
        userDB.setCode(users.getCode());
        
        if(!users.getPassword().equals("") && users.getPassword().length() >= 8) {
            userDB.setPassword(MD5.encode(users.getPassword()));
        }
        
        usersService.save(userDB);
        
        response.setData("Chỉnh sửa thành công.");      

        return response;
    }
    
    @PostMapping("/adminAddUser")
    public APIResponse AdminAddUsers(@RequestBody Users users) {
        
        APIResponse response = new APIResponse();
        
        Users usersLogin = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        
        if(usersLogin == null) {
            response.setError(400);
            response.setData("Token hết hạn hoặc không tồn tại. Vui lòng thử lại");
            response.setError("Error token failed");
            
            return response;
        }
        
        boolean isAdmin = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN), sessionService, userHasRolesService);

        if(!isAdmin) {

            response.setStatus(400);
            response.setError("Can not actions. Error code: 19");
            response.setData(Constant.RESPONE.TOKEN_FAIL_ROLE);

            return response;
        } 
        
        if(users.getPassword().equals("") || users.getPassword().length() < 8) {
            response.setStatus(400);
            response.setError("Can not actions. Error code: 19");
            response.setData(Constant.RESPONE.HAS_ERROR + ": Mật khẩu phải từ 8 ký tự trở lên");

            return response;
        }
        
        if(users.getUsername().equals("") || users.getUsername().length() < 8) {
            response.setStatus(400);
            response.setError("Can not actions. Error code: 19");
            response.setData(Constant.RESPONE.HAS_ERROR + ": Tài khoản phải từ 8 ký tự trở lên");

            return response;
        }
        
        Users userRegisted = usersService.findByEmailOrUsername(users.getEmail(), users.getUsername());
        if(userRegisted != null) {
            response.setStatus(400);
            response.setError("Can not actions. Error code: 19");
            response.setData(Constant.RESPONE.HAS_ERROR + ": Tài khoản hoặc email đã tồn tại");

            return response;
        }
        
        Users userDB = new Users();
        
        userDB.setUsername(users.getUsername());
        userDB.setEmail(users.getEmail());
        userDB.setPhone(users.getPhone());
        userDB.setAddress1(users.getAddress1());
        userDB.setLast_name(users.getLast_name());
        userDB.setFirst_name(users.getFirst_name());
        userDB.setCity(users.getCity());
        userDB.setState(users.getState());
        userDB.setWard(users.getWard());
        userDB.setCode(users.getCode());
        userDB.setPassword(MD5.encode(users.getPassword()));
        userDB.setSodu(0D);
        userDB.setSodu_hold(0D);
        userDB.setSodu_ngoai(0D);
        userDB.setStatus(true);
        
        usersService.save(userDB);
        
        response.setData("Thêm mới thành công.");      

        return response;
    }
    
    @PostMapping("/setRoles")
    public APIResponse setRolesUsers(@RequestBody Map<String, Object> body) {
        
        APIResponse response = new APIResponse();
        
        Users usersLogin = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
        
        if(usersLogin == null) {
            response.setError(400);
            response.setData("Token hết hạn hoặc không tồn tại. Vui lòng thử lại");
            response.setError("Error token failed");
            
            return response;
        }
        
        boolean isAdmin = Roles.UserHasRoles(Arrays.asList(Constant.ROLES.ADMIN), sessionService, userHasRolesService);

        if(!isAdmin) {

            response.setStatus(400);
            response.setError("Can not actions. Error code: 19");
            response.setData(Constant.RESPONE.TOKEN_FAIL_ROLE);

            return response;
        }     
        
        List<Boolean> listBl = (List<Boolean>) body.get("list");
        Long user_id = Long.parseLong((String) body.get("user_id"));
        
        boolean isListBoolean = listBl.stream().allMatch(e -> {
           return (e instanceof Boolean); 
        });
        
        if(!isListBoolean) {

            response.setStatus(400);
            response.setError("Can not actions. Error code: 19");
            response.setData(Constant.RESPONE.HAS_ERROR + ": Dữ liệu đầu vào chưa đúng");

            return response;
        }
        
        Optional<Users> userDB = usersService.findById(user_id);
        
        if(userDB.isEmpty()) {

            response.setStatus(400);
            response.setError("Can not actions. Error code: 19");
            response.setData(Constant.RESPONE.NOT_FOUND_USER);

            return response;
        }
        
        List<UserHasRoles> list = userHasRolesService.selectsByUserId(user_id);
        
        Long index = 1L;
        for(boolean e: listBl) {
            boolean hasRoleDB = false;
            for(UserHasRoles uHRole : list) {
                if(uHRole.getRoles().getId().equals(index)) { // đã có trong db
                    
                    if(!e) {// từ chối quyền này
                        
                        userHasRolesService.delete(uHRole);
                    }
                    hasRoleDB = true;
                }
            }
            if(e && !hasRoleDB) { // cho phép quyền này
                UserHasRoles addUHR = new UserHasRoles();
                com.kshop.main.domain.Roles role = new com.kshop.main.domain.Roles();
                role.setId(index);
                
                addUHR.setRoles(role);
                addUHR.setUsers(userDB.get());
                
                userHasRolesService.save(addUHR);
            }
            index++;
        }
        
        response.setData("Cập nhật thành công.");      

        return response;
    }

    /**
     * Api delete item
     * 
     * - DELETE Method: __/delete/{id}
     * - Return(JSON): item
     * 
     */
    @DeleteMapping("/delete/{id}")
    public Users deleteUsers(@PathVariable Long id) {

        Users item = usersService.findById(id).get();

        usersService.delete(item);

        return item;
    }
}
