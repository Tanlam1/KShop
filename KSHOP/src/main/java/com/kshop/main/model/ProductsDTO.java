package com.kshop.main.model;

import java.time.LocalDateTime;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {

	private long id;
	
	private String product_code;
	
	private String product_name;
	
	private String image;
	
	private String short_description;
	
	private String description;
	
	private Double standard_cost;
	
	private Double list_price;
	
	private String quantity_per_unit;
	
	private short discontinued;
	
	private short is_featured;
	
	private short is_new;
	
//	@ManyToOne
//	@JoinColumn(name = "category_id")
//	private Category categories;
//	
//	@ManyToOne
//	@JoinColumn(name = "supplier_id")
//	private Supplier suppliers;
	
	private Date created_at;
	
	private Date updated_at;
	
}
