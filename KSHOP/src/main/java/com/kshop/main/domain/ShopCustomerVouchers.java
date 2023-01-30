package com.kshop.main.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "shop_customer_vouchers")
public class ShopCustomerVouchers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "customer_id")
	private Customers customers;
	
	@ManyToOne
    @JoinColumn(name = "voucher_id")
	private ShopVouchers vouchers;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date created_at = new Date();
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updated_at;
	
	private boolean is_used;

}
