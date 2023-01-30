package com.kshop.main.service;

import com.kshop.main.domain.EmailDetails;

public interface EmailService {
	
	boolean sendSimpleMail(EmailDetails details);
	
	boolean sendMailWithAttachment(EmailDetails details);
}
