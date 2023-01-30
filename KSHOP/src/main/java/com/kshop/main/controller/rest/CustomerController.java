package com.kshop.main.controller.rest;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
import com.kshop.main.domain.Customers;
import com.kshop.main.exception.UserNotFoundException;
import com.kshop.main.model.ForgotPasswordDTO;
import com.kshop.main.model.ResetPasswordDTO;
import com.kshop.main.service.CustomerService;
import com.kshop.main.service.SessionService;
import com.kshop.main.utils.MD5;
import com.kshop.main.utils.Utility;

import net.bytebuddy.utility.RandomString;


@RestController
@RequestMapping("/Customer")
@CrossOrigin
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	@Autowired
	SessionService sessionService;
	@Autowired
    private JavaMailSender mailSender;
	
	private List<Customers> hiddenPassword(List<Customers> list) {
        for(Customers item : list) {
            item.setPassword("");
        }
        return list;
    }
    
    private Customers hiddenPassword_(Customers item) {
        item.setPassword("");
        return item;
    }
	
	/** Api get all Customers 
	 * 
	 * - GET Method: __/get
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get") // api get all Customers
	public List<Customers> getAllCustomers() {
		
		return hiddenPassword(customerService.findAll());
	}

	
	/** Api get last item in Customers 
	 * 
	 * - GET Method: __/getLast
	 * - Return(JSON): list item
	 * 
	 * */
	@GetMapping("/getLast")
	public Customers getLastCustomers() {
		
		List<Customers> list = customerService.findAll();
		int totalItem = list.size();
		
		return list.get(totalItem - 1);
	}

	
	/** Api get first item in Customers 
	 * 
	 * - GET Method: __/getFirst
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/getFirst")
	public Customers getFirstCustomers() {
		
		return customerService.findAll().get(0);
	}

	
	/** Api get item by id in Customers 
	 * 
	 * - GET Method: __/get/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@GetMapping("/get/{id}")
	public Customers getShopVoucher(@PathVariable Long id) {
		Optional<Customers> itemCustomers = customerService.findById(id);
		if(itemCustomers.isPresent()) {
			return hiddenPassword_(itemCustomers.get());
		} 
		
		return null;
	}

	
	/** Api add item to Customers 
	 * 
	 * - POST Method: __/add
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PostMapping("/add")
	public Customers addCustomers(@RequestBody Customers Customers) {
		
		customerService.save(Customers);
		
		return hiddenPassword_(getLastCustomers());
	}
	
	//change password
	@PostMapping("/changepassword")
    public APIResponse changePassword(@RequestBody Map<String, String> objMap) {
        
        APIResponse response = new APIResponse();
        
        String password = MD5.encode(objMap.get("currentPass"));
        String new_password = objMap.get("newPass");
        
        Customers customers = (Customers) sessionService.get("userLogin");
        if(customers == null) {
            response.setData("Đã xảy ra lỗi, vui lòng thử lại. Error code: 169");
            return response;
        }
        Customers customer = customerService.findById(customers.getId()).get();
        System.out.println(new_password);
        if(customer.getPassword().equals(password)) {
            
            response.setStatus(200);            
            customer.setPassword(MD5.encode(new_password));   
            
            customerService.save(customer);
            
            response.setData("Đổi mật khẩu thành công");
        } else {
            
            response.setStatus(104);
            response.setData("Mật khẩu cũ không đúng.");
        }
        
        return response;
    }
	
	@PostMapping("/forgot-password")
    public APIResponse forgotPassword(@RequestBody ForgotPasswordDTO emailDTO, HttpServletRequest request) {
        
        APIResponse response = new APIResponse();
        
        String email = emailDTO.getEmail();
        String token = RandomString.make(50);

        
        try {
        	response.setStatus(200);
        	response.setData("Success");
        	customerService.updateResetPassword(token, email);
        	
        	String resetPasswordLink = Utility.getSiteURL(request) + "/customers/reset-password?token=" + token;
        	
        	System.out.println(resetPasswordLink);
        	
        	sendEmail(email, resetPasswordLink);
     	
		} catch (UserNotFoundException e) {
			// TODO: handle exception
			response.setStatus(401);
			response.setData("Email không tồn tại!");
		} catch (UnsupportedEncodingException | MessagingException e) {
			response.setStatus(401);
			response.setData("Email không tồn tại!");
		}

        return response;
    }
	
	@PostMapping("/reset-password")
    public APIResponse resetPassword(@RequestBody ResetPasswordDTO pass, @Param(value = "token") String token) {
        
        APIResponse response = new APIResponse();
        
        String newPass = pass.getNewPass();
        
        Customers customers = customerService.getToken(token);
        if (customers == null) {
        	response.setStatus(401);
			response.setData("Invalid Token!");
			
		}else {
			customerService.updatePassword(customers, newPass);
			response.setStatus(200);
			response.setData("You have successfully chaged your password!");
			sessionService.remove("userLogin");
		}
          
        return response;
    }
	
	private void sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
		// TODO Auto-generated method stub
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("contact@gmail.com", "Support");
		helper.setTo(email);
		
		String subject = "Here's the link to reset your password";
		
		String content = "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\r\n"
				+ "    <!--100% body table-->\r\n"
				+ "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\r\n"
				+ "        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\r\n"
				+ "        <tr>\r\n"
				+ "            <td>\r\n"
				+ "                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\r\n"
				+ "                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td style=\"height:80px;\">&nbsp;</td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td style=\"text-align:center;\">\r\n"
				+ "                          <a href=\"https://rakeshmandal.com\" title=\"logo\" target=\"_blank\">\r\n"
				+ "                            <img width=\"60\" src=\"https://i.ibb.co/hL4XZp2/android-chrome-192x192.png\" title=\"logo\" alt=\"logo\">\r\n"
				+ "                          </a>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td style=\"height:20px;\">&nbsp;</td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td>\r\n"
				+ "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\r\n"
				+ "                                style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td style=\"height:40px;\">&nbsp;</td>\r\n"
				+ "                                </tr>\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td style=\"padding:0 35px;\">\r\n"
				+ "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">You have\r\n"
				+ "                                            requested to reset your password</h1>\r\n"
				+ "                                        <span\r\n"
				+ "                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\r\n"
				+ "                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\r\n"
				+ "                                            We cannot simply send you your old password. A unique link to reset your\r\n"
				+ "                                            password has been generated for you. To reset your password, click the\r\n"
				+ "                                            following link and follow the instructions.\r\n"
				+ "                                        </p>\r\n"
				+ "                                        <a href=\"" + resetPasswordLink + "\"\r\n"
				+ "                                            style=\"background:#20e277;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">Reset\r\n"
				+ "                                            Password</a>\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td style=\"height:40px;\">&nbsp;</td>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </table>\r\n"
				+ "                        </td>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td style=\"height:20px;\">&nbsp;</td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td style=\"text-align:center;\">\r\n"
				+ "                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>www.rakeshmandal.com</strong></p>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td style=\"height:80px;\">&nbsp;</td>\r\n"
				+ "                    </tr>\r\n"
				+ "                </table>\r\n"
				+ "            </td>\r\n"
				+ "        </tr>\r\n"
				+ "    </table>\r\n"
				+ "    <!--/100% body table-->\r\n"
				+ "</body>";
		
//		String content = "<p>Hello,</p>"
//				+"<p>You have requested to reset your password</p>"
//				+"<p>Link reset password</p>"
//				+"<a href=\""+ resetPasswordLink + "\" >Change your password</a>";
		
		helper.setSubject(subject);
		helper.setText(content, true);
		
		mailSender.send(message);
	}

	
	/** Api update item 
	 * 
	 * - PUT Method: __/update
	 * - --data-raw: JSON
	 * - Return(JSON): item
	 * 
	 * */
	@PutMapping("/update")
	public Customers updateCustomers(@RequestBody Customers Customers) {
	    
	    Customers customer = (Customers) sessionService.get("userLogin");
	    if(customer == null) return customer;
	    String password = customerService.findById(customer.getId()).get().getPassword();
	    
	    Customers.setEmail(customer.getEmail());
	    Customers.setPassword(password);
	    Customers.setUsername(customer.getUsername());
	    Customers.setCreated_at(customer.getCreated_at());
	    
	    boolean status = true;
	    //check xác nhận người dùng
	    //....
	    
	    Customers.setStatus(status);
		
		customerService.save(Customers);
		
		return customerService.findById(Customers.getId()).get();
	}
	
	
	/** Api delete item 
	 * 
	 * - DELETE Method: __/delete/{id}
	 * - Return(JSON): item
	 * 
	 * */
	@DeleteMapping("/delete/{id}")
	public Customers deleteCustomers(@PathVariable Long id) {
		
		Customers item = customerService.findById(id).get();
		
		customerService.delete(item);
		
		return item;
	}
}
