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
@Table(name = "shop_stores")
public class ShopStores {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	private String store_code;
	
	@Column(columnDefinition = "nvarchar(500) not null")
	private String store_name;
	
	@Column(columnDefinition = "nvarchar(4000) null")
	private String description;
	
	@Column(columnDefinition = "nvarchar(4000) null")
	private String address;
	
	@Column(columnDefinition = "nvarchar(25) null")
	private String phone;
    
    @Column(columnDefinition = "nvarchar(4000) null")
    private String image;
    
    @Column(columnDefinition = "nvarchar(max) null")
    private String background;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date created_at = new Date();
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updated_at = new Date();
}
