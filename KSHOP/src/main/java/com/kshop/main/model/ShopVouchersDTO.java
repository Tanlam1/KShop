package com.kshop.main.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopVouchersDTO {
	
	private Long id;
	
	private String voucher_code;
	
	private String voucher_name;
	
	private String description;
	
	private int uses;
	
	private int max_uses;
	
	private int max_uses_user;
	
	private boolean type;
	
	private double discount_amount;
	
	private boolean is_fixed;
	
	private Date start_date;
	
	private Date end_date;
	
	private Date created_at;
	
	private Date updated_at;
	
	private Date deleted_at;
	
}
