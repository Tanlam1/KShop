package com.kshop.main.service.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.kshop.main.domain.EmailDetails;
import com.kshop.main.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired private JavaMailSender javaMailSender;
	 
    @Value("${spring.mail.username}") private String sender;
 
    // Method 1
    // To send a simple email
    public boolean sendSimpleMail(EmailDetails details)
    {
 
        // Try block to check for exceptions
        try {
 
            // Creating a simple mail message
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
 
            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
 
            // Sending the mail
            javaMailSender.send(mailMessage);
            return true;
        }
 
        // Catch block to handle the exceptions
        catch (Exception e) {
            return false;
        }
    }
 
    // Method 2
    // To send an email with attachment
    public boolean
    sendMailWithAttachment(EmailDetails details)
    {
        // Creating a mime message
        MimeMessage mimeMessage
            = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
 
        try {
 
            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(template(details.getMsgBody()), true);
            mimeMessageHelper.setSubject(
                details.getSubject());
 
            // Adding the attachment
            if(details.getAttachment() != null) {
                FileSystemResource file
                = new FileSystemResource(
                    new File(details.getAttachment()));
 
                mimeMessageHelper.addAttachment(
                    file.getFilename(), file);
            }
 
            // Sending the mail
            javaMailSender.send(mimeMessage);
            return true;
        }
 
        // Catch block to handle MessagingException
        catch (MessagingException e) {
 
            // Display message when exception occurred
            return false;
        }
    }
    
    /**
     * Không dùng được các css: box-shadow,..
     * @param content
     * 
     * @return
     */
    private String template(String content) {
        return "<div style=\"background-color: #e37c4117; padding: 15px;font-family: 'Google Sans';\">\n" +
                "    <div style=\"margin: auto; background-color: #ffffff; max-width: 600px; padding: 10px; border-top: 6px solid #e37c41; border-radius: 5px;\">\n" +
                "        <div>\n" +
                "            <div style=\"width: 100%;text-align: center;\">\n" +
                "                <img style=\"object-fit: contain;\" src=\"https://i.ibb.co/JrcPYnm/kshop-logo.png\" width=\"120\" height= \"120\" alt=\"logo\"/>\n" +
                "                <span style=\"display: block; font-size: 20px; font-weight: bold; color: #ff0561; text-align: center; border: 3px solid; border-radius: 10px;\">KShop - Mua hàng online giá tốt</span>\n" +
                "            </div>\n" +
                "        </div><br>\n" +
                "        <p style=\"line-height: 22px;\">\n" +
                            content +
                "            \n" +
                "        </p><br>\n" +
                "        <hr style=\"border-top: 1px solid;\" />\n" +
                "        <div style=\"font-style: italic;\">\n" +
                "            <span>Đây là email tự động của hệ thống, vui lòng không trả lời email này!</span>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>";
    }
}
