package com.kshop.main.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_vouchers")
public class ShopVouchers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "nvarchar(500) not null")
	private String voucher_code;
	
	@Column(columnDefinition = "nvarchar(500) not null")
	private String voucher_name;
	
	@Column(nullable = false)
	private String description;
    
    @Column(columnDefinition = "Decimal(19,4) NOT NULL default '0.0000'")
    private Double price;
    
    @Column(columnDefinition = "Decimal(19,4) NOT NULL default '0.0000'")
    private Double min_pro; // min price order
    
    @Column(columnDefinition = "Decimal(19,4) NOT NULL default '0.0000'")
    private Double max_disa; // max discount amount
	
	@Column(nullable = false)
	private int uses = 1;
	
	@Column(nullable = false)
	private int max_uses;
	
	@Column(nullable = false)
	private int max_uses_user;
	
	private Long use_by; // chứa id của user (shop)
	
	@Column(nullable = false)
	private int type = 1;
	
	@Column(nullable = false)
	private double discount_amount;
	
	@Column(nullable = false)
	private boolean is_fixed = true;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(nullable = false)
	private Date start_date;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(nullable = false)
	private Date end_date;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date created_at = new Date();
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updated_at;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date deleted_at;
	
}
