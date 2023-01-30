package com.kshop.main.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kshop.main.common.APIResponse;
import com.kshop.main.common.Constant;
import com.kshop.main.domain.EmailDetails;
import com.kshop.main.domain.UserHasRoles;
import com.kshop.main.domain.Users;
import com.kshop.main.service.EmailService;
import com.kshop.main.service.SessionService;
import com.kshop.main.service.UserHasRolesService;

@RestController
@CrossOrigin
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserHasRolesService userHasRolesService;

    // Sending a simple Email
    @PostMapping("/sendMail")
    public APIResponse sendMail(@RequestBody EmailDetails details) {

        APIResponse response = new APIResponse();

        Users userLogin = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);

        if (userLogin == null) {
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
        response.setData("Gửi email thành công.");
        response.setStatus(200);

        boolean status = emailService.sendSimpleMail(details);

        if (!status) {
            response.setError("Error when sending email.");
            response.setData("Đã có lỗi xảy ra. Gửi email thất bại.");
            response.setStatus(400);
        }

        return response;
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public APIResponse sendMailWithAttachment(
            @RequestBody EmailDetails details) {

        APIResponse response = new APIResponse();

        Users userLogin = (Users) sessionService.get(Constant.SESSION_LOGIN_TYPE.ADMIN, null);

        if (userLogin == null) {
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
        response.setData("Gửi email thành công.");
        response.setStatus(200);

        boolean status = emailService.sendMailWithAttachment(details);

        if (!status) {
            response.setError("Error when sending email.");
            response.setData("Đã có lỗi xảy ra. Gửi email thất bại.");
            response.setStatus(400);
        }

        return response;
    }
}
