package com.kshop.main.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "acl_users")
public class Users implements Serializable{
	
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
	
	@Column(columnDefinition = "not null")
	private boolean gender;
	
	@Column(columnDefinition = "nvarchar(191) not null")
	private String email;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
    
    @Column(columnDefinition = "Decimal(19,4) NOT NULL default '0.0000'")
    private Double sodu;
    
    @Column(columnDefinition = "Decimal(19,4) NOT NULL default '0.0000'")
    private Double sodu_hold;
    
    @Column(columnDefinition = "Decimal(19,4) NOT NULL default '0.0000'")
    private Double sodu_ngoai;
    
    @Column(columnDefinition = "nvarchar(2255)")
    private String pay_info_next;
    
    @Column(columnDefinition = "nvarchar(MAX)")
    private String info_receive_money;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String avatar;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String code;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String job_title;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String department;
	
	@Column
	private Long manager_id;
	
	@Column(columnDefinition = "nvarchar(25)")
	private String phone;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String address1;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String address2;
    
    @Column(columnDefinition = "nvarchar(255)")
    private String city;
    
    @Column(columnDefinition = "nvarchar(255)")
    private String ward;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String state;
	
	@Column(columnDefinition = "nvarchar(15)")
	private String postal_code;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String country;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String remember_token;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String active_code;
	
	@Column
	private boolean status;
	
	@ManyToOne
    @JoinColumn(name = "store_id")
	private ShopStores stores;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date created_at = new Date();
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updated_at = new Date();
	
	@JsonIgnore
	@OneToMany(mappedBy = "users")
	private List<Orders> orders;
}