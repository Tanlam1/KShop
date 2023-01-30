package com.kshop.main.config;

import com.kshop.main.interceptor.AdminAuthenticationInterceptor;
import com.kshop.main.interceptor.AdminIsLoginAuthenticationInerceptor;
import com.kshop.main.interceptor.ClientAuthenticationInterceptor;
import com.kshop.main.interceptor.ClientIsLoginAuthenticationInerceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthenticationInterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private ClientAuthenticationInterceptor clientAuthenticationInterceptor;
	
	@Autowired
	private ClientIsLoginAuthenticationInerceptor clientIsLoginAuthenticationInerceptor;
	
	@Autowired
	private AdminAuthenticationInterceptor adminAuthenticationInterceptor;
	
	@Autowired
	private AdminIsLoginAuthenticationInerceptor adminIsLoginAuthenticationInerceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminAuthenticationInterceptor)
			.addPathPatterns("/admin-panel/**"); // kiểm tra nếu thuộc /admin/* thì gọi authen interceptor
		
		registry.addInterceptor(adminIsLoginAuthenticationInerceptor)
			.addPathPatterns("/admincpanel/login");
		
		registry.addInterceptor(clientAuthenticationInterceptor)
			.addPathPatterns("/profile/**");
		
		registry.addInterceptor(clientAuthenticationInterceptor)
		.addPathPatterns("/cart/**");
		registry.addInterceptor(clientAuthenticationInterceptor)
		.addPathPatterns("/checkout/**");
		
		registry.addInterceptor(clientIsLoginAuthenticationInerceptor)
			.addPathPatterns("/auth/login");
	}

}
