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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_suppliers")
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	private String supplier_code;
	
	@Column(columnDefinition = "nvarchar(500) not null")
	private String supplier_name;
	
	@Column(columnDefinition = "nvarchar(4000) null")
	private String description;
	
	@Column(columnDefinition = "nvarchar(4000) null")
	private String image;
	
	@Temporal(TemporalType.DATE)
	private Date created_at;
	
	@Temporal(TemporalType.DATE)
	private Date updated_at;
}
