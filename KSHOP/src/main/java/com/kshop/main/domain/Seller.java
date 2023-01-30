package com.kshop.main.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_seller")
public class Seller implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(columnDefinition="nvarchar(255) not null")
    private String name;
    
    @Column(columnDefinition="nvarchar(255) not null")
    private String code;
    
    @Column(columnDefinition="nvarchar(25) not null")
    private String phone;
    
    @Column(columnDefinition="nvarchar(255) not null")
    private String email;
    
    @Column(columnDefinition="nvarchar(100) not null")
    private String type;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    private boolean status;
    
    @DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdAt = new Date();
	
}
