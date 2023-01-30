package com.kshop.main.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO{
	
	private Long id;
	
	private String username;
	
	private String password;
	
	private String last_name;
	
	private String first_name;
	
	private short gender;
	
	private String email;
	
	private Date birthday;
	
	private String avatar;
	
	private String code;
	
	private String job_title;
	
	private String department;
	
	private Long manager_id;
	
	private String phone;
	
	private String address1;
	
	private String address2;
	
	private String city;

	private String state;

	private String postal_code;

	private String country;
	
	private String remember_token;
	
	private String active_code;
	
	private short status;
	
	private Date created_at;
	
	private Date updated_at;
}