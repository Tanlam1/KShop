package com.kshop.main.controller.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kshop.main.common.APIResponse;
import com.kshop.main.common.Constant;
import com.kshop.main.domain.Customers;
import com.kshop.main.service.CustomerService;
import com.kshop.main.service.SessionService;
import com.kshop.main.utils.RAND;
import com.kshop.main.vnpay.Config;

@RestController
@RequestMapping("/vnpay")
@CrossOrigin
public class VNPayController {
    
    @Autowired
    HttpServletRequest req;
    @Autowired
    HttpServletResponse resp;
    @Autowired
    CustomerService customerService;
    @Autowired
    SessionService sessionService;
    
    @PostMapping("createPayment")
    public APIResponse createPayment(Model model) throws UnsupportedEncodingException {
        
        APIResponse response = new APIResponse();
        Customers customer = sessionService.get(Constant.SESSION_LOGIN_TYPE.CLIENT, null);
        if(customer == null) {
            response.setError("Not found customer");
            response.setData("Không thể tìm thấy khách hàng hiện tại.");
            response.setStatus(400);
            
            return response;
        }
        
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        //String vnp_Command = "genqr";
//        String vnp_OrderInfo = req.getParameter("vnp_OrderInfo");
        String vnp_OrderInfo = customer.getId() + "|" + RAND.GET(16);
        String orderType = req.getParameter("ordertype");
        String vnp_TxnRef = Config.getRandomNumber(8);
        int mcId = Integer.parseInt(Config.getRandomNumber(8));
//        int amountmc = Integer.parseInt(req.getParameter("amount"));
        String vnp_IpAddr = Config.getIpAddress(req);
        String vnp_TmnCode = Config.vnp_TmnCode;
        String amount = req.getParameter("amount");
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", amount);
        
        vnp_Params.put("vnp_CurrCode", "VND");
        String bank_code = req.getParameter("bankcode");
        if (bank_code != null && !bank_code.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bank_code);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        
        //set payment code vnpay to customer
        Customers customerDB = customerService.findById(customer.getId()).get();
        customerDB.setActivateCode(vnp_OrderInfo);
        customerService.save(customerDB);
        //end set payment code vnpay to customer
        
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = req.getParameter("language");
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Date dt = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(dt);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        Calendar cldvnp_ExpireDate = Calendar.getInstance();
        cldvnp_ExpireDate.add(Calendar.SECOND, 300);
        Date vnp_ExpireDateD = cldvnp_ExpireDate.getTime();
        String vnp_ExpireDate = formatter.format(vnp_ExpireDateD);

        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);        
        
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                //hashData.append(fieldValue); //sử dụng và 2.0.0 và 2.0.1 checksum sha256
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString())); //sử dụng v2.1.0  check sum sha512
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        //String vnp_SecureHash = Config.Sha256(Config.vnp_HashSecret + hashData.toString());
        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        
        Map<String, Object> mapResponse = new HashMap();
        
        mapResponse.put("url_redirect", paymentUrl);
        
        response.setData(mapResponse);
        
        return response;
    }
    
    @GetMapping("return")
    public APIResponse returnVNPay(Model model) throws UnsupportedEncodingException {
        Map fields = new HashMap();
        Enumeration params_req = req.getParameterNames();
        for (Enumeration params = req.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(req.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = req.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = Config.hashAllFields(fields);
        
        APIResponse response = new APIResponse();
        Map<String, Object> mapResponse = new HashMap();
        Map mapResponseItem = new HashMap();
        
        mapResponseItem.put("data", req.getParameterMap());
        String result = "invalid_signature";
        
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(req.getParameter("vnp_TransactionStatus"))) {
                result = "Success";
            } else {
                result = "Failed";
            }

        }
        
        mapResponseItem.put("status", result);
        
        mapResponse.put("result", mapResponseItem);
        
        response.setData(mapResponse);
        
        return response;
    }
}
