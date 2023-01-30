package com.kshop.main.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_customers")
public class Customers implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "nvarchar(191) not null")
	private String username;
	
	@Column(columnDefinition = "nvarchar(500) not null")
	private String password;

	@Column(columnDefinition = "nvarchar(255) not null")
	private String last_name;
	
	@Column(columnDefinition = "nvarchar(255) not null")
	private String first_name;
	
	private boolean gender;
	
	@Column(columnDefinition = "nvarchar(191) not null")
	private String email;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@Column(columnDefinition = "nvarchar(500) null")
	private String avatar;
	
	@Column(columnDefinition = "nvarchar(255) not null")
	private String code;
	
	@Column(columnDefinition = "nvarchar(255) default null")
	private String company;
	
	@Column(columnDefinition = "nvarchar(25) default null")
	private String phone;
	
	@Column(columnDefinition = "nvarchar(500) default null")
	private String billing_address;
	
	@Column(columnDefinition = "nvarchar(500) default null")
	private String shipping_address;
	
	@Column(columnDefinition = "nvarchar(255) default null")
	private String city;
	
	@Column(columnDefinition = "nvarchar(255) default null")
	private String state;
    
    @Column(columnDefinition = "nvarchar(255) default null")
    private String ward;
	
	@Column(columnDefinition = "nvarchar(15) default null")
	private String postal_code;
	
	@Column(columnDefinition = "nvarchar(255) default null")
	private String country = "Việt Nam";
	
	@Column(columnDefinition = "nvarchar(255) default null")
	private String remember_token;
	
	@Column(name = "activate_code",columnDefinition = "nvarchar(255) default null")
	private String activateCode;
	
	@Column
	private boolean status;
	
	@Temporal(TemporalType.DATE)
	private Date created_at;
	
	@Temporal(TemporalType.DATE)
	private Date updated_at;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customers")
	private List<Orders> orders;
}
