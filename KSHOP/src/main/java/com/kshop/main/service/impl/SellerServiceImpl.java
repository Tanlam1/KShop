package com.kshop.main.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.mail.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.kshop.main.common.APIResponse;
import com.kshop.main.common.Constant;
import com.kshop.main.domain.Category;
import com.kshop.main.domain.Customers;
import com.kshop.main.domain.EmailDetails;
import com.kshop.main.domain.Orders;
import com.kshop.main.domain.Roles;
import com.kshop.main.domain.Seller;
import com.kshop.main.domain.ShopStores;
import com.kshop.main.domain.UserHasRoles;
import com.kshop.main.domain.Users;
import com.kshop.main.repository.CustomerRepository;
import com.kshop.main.repository.OrderRepository;
import com.kshop.main.repository.SellerRepository;
import com.kshop.main.repository.ShopCategoriesRepository;
import com.kshop.main.service.EmailService;
import com.kshop.main.service.OrderService;
import com.kshop.main.service.SellerService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.ShopStoresService;
import com.kshop.main.service.UserHasRolesService;
import com.kshop.main.service.UsersService;
import com.kshop.main.utils.MD5;
import com.kshop.main.utils.RAND;

@Service
public class SellerServiceImpl implements SellerService {
    
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ShopCategoriesRepository categoriesRepository;
    @Autowired
    SessionService sessionService;
    @Autowired
    EmailService emailService;
    @Autowired
    UserHasRolesService userHasRolesService;
    @Autowired
    ShopStoresService shopStoresService;
    @Autowired
    UsersService usersService;

    @Override
    public <S extends Seller> S save(S entity) {
        return sellerRepository.save(entity);
    }

    @Override
    public <S extends Seller> Optional<S> findOne(Example<S> example) {
        return sellerRepository.findOne(example);
    }

    @Override
    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    @Override
    public Page<Seller> findAll(Pageable pageable) {
        return sellerRepository.findAll(pageable);
    }

    @Override
    public List<Seller> findAll(Sort sort) {
        return sellerRepository.findAll(sort);
    }

    @Override
    public List<Seller> findAllById(Iterable<Long> ids) {
        return sellerRepository.findAllById(ids);
    }

    @Override
    public Optional<Seller> findById(Long id) {
        return sellerRepository.findById(id);
    }

    @Override
    public <S extends Seller> List<S> saveAll(Iterable<S> entities) {
        return sellerRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        sellerRepository.flush();
    }

    @Override
    public boolean existsById(Long id) {
        return sellerRepository.existsById(id);
    }

    @Override
    public <S extends Seller> S saveAndFlush(S entity) {
        return sellerRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Seller> List<S> saveAllAndFlush(Iterable<S> entities) {
        return sellerRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends Seller> Page<S> findAll(Example<S> example, Pageable pageable) {
        return sellerRepository.findAll(example, pageable);
    }

    @Override
    public void deleteInBatch(Iterable<Seller> entities) {
        sellerRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends Seller> long count(Example<S> example) {
        return sellerRepository.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<Seller> entities) {
        sellerRepository.deleteAllInBatch(entities);
    }

    @Override
    public long count() {
        return sellerRepository.count();
    }

    @Override
    public <S extends Seller> boolean exists(Example<S> example) {
        return sellerRepository.exists(example);
    }

    @Override
    public void deleteById(Long id) {
        sellerRepository.deleteById(id);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        sellerRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void delete(Seller entity) {
        sellerRepository.delete(entity);
    }

    @Override
    public <S extends Seller, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return sellerRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        sellerRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAllInBatch() {
        sellerRepository.deleteAllInBatch();
    }

    @Override
    public Seller getOne(Long id) {
        return sellerRepository.getOne(id);
    }

    @Override
    public void deleteAll(Iterable<? extends Seller> entities) {
        sellerRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        sellerRepository.deleteAll();
    }

    @Override
    public Seller getById(Long id) {
        return sellerRepository.getById(id);
    }

    @Override
    public Seller getReferenceById(Long id) {
        return sellerRepository.getReferenceById(id);
    }

    @Override
    public <S extends Seller> List<S> findAll(Example<S> example) {
        return sellerRepository.findAll(example);
    }

    @Override
    public <S extends Seller> List<S> findAll(Example<S> example, Sort sort) {
        return sellerRepository.findAll(example, sort);
    }

    @Override
    public Seller selectByCustomerId(Long id) {
        // TODO Auto-generated method stub
        return sellerRepository.selectByCustomerId(id);
    }

    @Override
    public APIResponse addNewSeller(Seller seller) {
        
        APIResponse response = new APIResponse();
        
        if(seller.getCategory() == null ||
            seller.getCategory().getId() == null ||            
            seller.getCustomer()== null ||
            seller.getCustomer().getId() == null ||            
            seller.getEmail() == null ||
            seller.getName() == null ||
            seller.getCode() == null ||
            seller.getId() != null ||
            seller.getType() == null) {
            
            
            response.setError("Error something value field");
            response.setData("Đã có lỗi xảy ra, vui lòng kiểm tra lại dữ liệu nhập vào.");
            response.setStatus(400);
            
            return response;
        }
            
        
        Optional<Category> category = categoriesRepository.findById(seller.getCategory().getId());
        Optional<Customers> customer = customerRepository.findById(seller.getCustomer().getId());
        Customers customerLogin = (Customers) sessionService.get(Constant.SESSION_LOGIN_TYPE.CLIENT, null);
        
        if(category.isEmpty()) {
            
            response.setError("Error not found category id");
            response.setData("Đã có lỗi xảy ra, không tìm thấy id category.");
            response.setStatus(400);
            
            return response;
            
        }
        
        if(customer.isEmpty()) {
            
            response.setError("Error not found customer id");
            response.setData("Đã có lỗi xảy ra, không tìm thấy id customer.");
            response.setStatus(400);
            
            return response;
            
        }
        
        if(customerLogin == null) {
            
            response.setError("Error login token expired");
            response.setData("Mã đăng nhập hết hạn. Vui lòng đăng nhập lại.");
            response.setStatus(400);
            
            return response;
            
        }
        
        if(!customer.get().getId().equals(customerLogin.getId())) {
            
            response.setError("Error this token does not have the right to manipulate the specified account");
            response.setData("Mã token này không có quyền tương tác đến tài khoản được chỉ định.");
            response.setStatus(400);
            
            return response;
            
        }
        
        Seller sellerInDB = sellerRepository.selectByCustomerId(seller.getCustomer().getId());
        
        if(sellerInDB != null) {
            
            response.setError("Error wait admin accept");
            response.setData("Bạn đã đăng ký mở shop và đang đợi duyệt.");
            response.setStatus(400);
            
            return response;
            
        }
        
        sellerRepository.save(seller);
        
        Map<String, Object> map = new HashMap();
        
        map.put("msg", "Đăng ký shop thành công, vui lòng chờ quản trị viên duyệt");
        response.setData(map);
        response.setStatus(200);
        
        String content = "<div style=\"padding: 18px; background-color: #369; border-radius: 8px; margin: 0 14px; font-size: 15px; font-weight: bold; color: #fafafa; text-transform: uppercase; text-align: center;\">\n"
                + "  Bạn đã đăng ký mở shop thành công. Yêu cầu của bạn hiện đang trong trạng thái chờ duyệt\n"
                + "</div>";
        
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(seller.getEmail());
        emailDetails.setSubject("Đăng ký mở shop thành công | KShop - Mua hàng online giá tốt");
        emailDetails.setMsgBody(content);
        
        emailService.sendMailWithAttachment(emailDetails);
        
        return response;
    }

    @Override
    public APIResponse acceptSeller(Long id) {
        APIResponse response = new APIResponse();
        
        Optional<Seller> optionalSeller = sellerRepository.findById(id);
        
        if(optionalSeller.isEmpty()) {
            response.setError("Error not found by id");
            response.setData("Đã có lỗi xảy ra, không tìm thấy id này.");
            response.setStatus(400);
            
            return response;
        }
        
        Seller seller = optionalSeller.get();     
        
        if(seller.isStatus()) {
            response.setError("Error accept shop");
            response.setData("Đã có lỗi xảy ra, người bán này đã được duyệt rồi.");
            response.setStatus(400);
            
            return response;
        }
        
        Users userLogin = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);
                
        if(userLogin == null) {
            
            response.setError("Error login token expired");
            response.setData("Mã đăng nhập hết hạn. Vui lòng đăng nhập lại.");
            response.setStatus(400);
            
            return response;
            
        }
        
        List<UserHasRoles> list = userHasRolesService.selectsByUserId(userLogin.getId());

        boolean isAdmin = list.stream().anyMatch(e -> e.getRoles().getId() == 1);

        if (!isAdmin) {
            response.setError("Error this token does not have the right.");
            response.setData("Bạn không có quyền tương tác đến chức năng được chỉ định.");
            response.setStatus(400);

            return response;
        }
        
        seller.setStatus(true); // set status seller thành true
                
        sellerRepository.save(seller); // update seller
        
        Customers customerCS = seller.getCustomer(); // Customer Create Store
        
        //Tạo mới store
        ShopStores storeBase = new ShopStores();
        storeBase.setAddress(customerCS.getShipping_address() + ", " +
                customerCS.getWard() + ", " +
                customerCS.getState() + ", " +
                customerCS.getCity());
        storeBase.setPhone(seller.getPhone());
        storeBase.setStore_name(seller.getName());
        storeBase.setStore_code(RAND.GET(12));
        storeBase.setImage(Constant.IMG_DEFAULT.STORE);
        
        //add store 
        shopStoresService.save(storeBase);
        
        
        //Tạo mới user chứa store ở trên
        List<ShopStores> listStores = shopStoresService.findAll();
        int totalItem = listStores.size();
        
        Users userBase = new Users();
        userBase.setUsername(customerCS.getUsername() + (RAND.GET(4).toLowerCase()));
        String password = RAND.GET(16);
        userBase.setPassword(MD5.encode(password));
        userBase.setLast_name(customerCS.getLast_name());
        userBase.setFirst_name(customerCS.getFirst_name());
        userBase.setGender(customerCS.isGender());
        userBase.setEmail(customerCS.getEmail());
        userBase.setBirthday(customerCS.getBirthday());
        userBase.setPhone(customerCS.getPhone());
        userBase.setSodu(0D);
        userBase.setSodu_hold(0D);
        userBase.setSodu_ngoai(0D);
        userBase.setStatus(true);
        userBase.setWard(customerCS.getWard());
        userBase.setState(customerCS.getState());
        userBase.setCity(customerCS.getCity());
        userBase.setCountry(customerCS.getCountry());
        userBase.setCode(seller.getCode());
        userBase.setManager_id(userLogin.getId());
        userBase.setAddress1(customerCS.getShipping_address());
        userBase.setAddress2(customerCS.getBilling_address());
        userBase.setPostal_code("99999");
        userBase.setStores(listStores.get(totalItem - 1));
        
        //add user
        usersService.save(userBase);
        
        //Phân quyền cho user vừa tạo, gồm 4 và 10 (đăng sp, và shop)
        UserHasRoles uRoles = new UserHasRoles();
        Roles roles = new Roles();
        roles.setId(10L);
        
        List<Users> listUsers = usersService.findAll();
        uRoles.setRoles(roles);
        uRoles.setUsers(listUsers.get(listUsers.size() - 1));
        userHasRolesService.save(uRoles);
        
        uRoles = new UserHasRoles();
        roles = new Roles();
        
        roles.setId(4L);
        uRoles.setRoles(roles);
        uRoles.setUsers(listUsers.get(listUsers.size() - 1));
        userHasRolesService.save(uRoles);
        
        Map<String, Object> map = new HashMap();
        
        map.put("msg", "Đã chấp thuận yêu cầu mở shop của khách hàng.");
        map.put("user", userBase);
        map.put("store", storeBase);
        response.setData(map);
        response.setStatus(200);
        
        String content = "<div style=\"padding: 14px;background-color: #3eac2a;font-size: 14px;font-weight:bold;color: #ffffff;text-transform:uppercase;text-align:center\">Chúc mừng, yêu cầu mở shop của bạn đã được duyệt.\r\n"
                + "</div>"
                + "<h4 style=\"\r\n"
                + "    text-align: center;\r\n"
                + "    margin: 27px 0 10px;\r\n"
                + "    font-size: 16px;\r\n"
                + "\">Chào mừng bạn gia nhập KShop - Mua hàng online giá tốt</h4>"
                + "<p style=\"\r\n"
                + "    text-align: center;\r\n"
                + "    margin: 27px 0 10px;\r\n"
                + "    font-size: 16px;\r\n"
                + "\">Bên dưới là thông tin đăng nhập vào shop của bạn</p>"
                + "<div style=\"\r\n"
                + "    margin: 6px 0;\r\n"
                + "\">\r\n"
                + "    <b>Tên SHOP</b>: " + seller.getName() +"\r\n"
                + "</div>"
                + "<div style=\"\r\n"
                + "    margin: 6px 0;\r\n"
                + "\">\r\n"
                + "    <b>Tài khoản</b>: " + userBase.getUsername() + "\r\n"
                + "</div>"
                + "<div style=\"\r\n"
                + "    margin: 6px 0;\r\n"
                + "\">\r\n"
                + "    <b>Mật khẩu(bôi đen để hiển thị):</b> <span style=\"\r\n"
                + "    background: #35f36b;\r\n"
                + "    color: #35f36b;\r\n"
                + "    padding: 2px;\r\n"
                + "\">" + password + "</span>\r\n"
                + "</div>"
                + "<a href=\"" + Constant.DIR.WEB_ROOT + Constant.URI.ADMIN + "/dashboards\" style=\"padding: 10px;background-color: #2a67ac;border-radius: 6px;margin: 14px 14px 0 0;font-size: 14px;font-weight: 500;color: #ffffff;text-align:center;display: block;text-decoration: none;\">Click vào đây để đăng nhập\r\n"
                + "</a>"
                + "<p style=\"\r\n"
                + "    margin: 30px 0 0;\r\n"
                + "    padding: 10px;\r\n"
                + "    text-align: center;\r\n"
                + "    background-color: #12345614;\r\n"
                + "    border-radius: 4px;\r\n"
                + "    color: #3867b3;\r\n"
                + "    font-size: 14px;\r\n"
                + "    font-weight: bold;\r\n"
                + "\">Sau khi đăng nhập, hãy làm theo hướng dẫn để hoàn thiện các bước còn lại của tài khoản bạn nhé.</p>";
        
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(seller.getEmail());
        emailDetails.setSubject("Yêu cầu của mở shop đã được duyệt | KShop - Mua hàng online giá tốt");
        emailDetails.setMsgBody(content);
        
        emailService.sendMailWithAttachment(emailDetails);
        
        return response;
        
    }
    
}
