package com.kshop.main.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_products")
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "nvarchar(500) not null")
	private String product_code;
	
	@Column(columnDefinition = "nvarchar(500) not null")
	private String product_name;
	
	@Column(columnDefinition = "nvarchar(4000) null")
	private String image;
	
	@Column(columnDefinition = "nvarchar(250) null")
	private String short_description;
	
	@Column(columnDefinition = "nvarchar(4000) null")
	private String description;
	
	@Column(columnDefinition = "Decimal(19,4) NOT NULL default '0.0000'")
	private Double standard_cost;
	
	@Column(columnDefinition = "Decimal(19,4) NOT NULL default '0.0000'")
	private Double list_price;
    
    @Column(columnDefinition = "Decimal(18,4) NOT NULL default '0.0000'")
    private Double quantity; // số lượng còn lại của sản phẩm
    
    @Column(columnDefinition = "Decimal(18,4) NOT NULL default '1.0000'")
    private Double height; // chiều cao của sản phẩm
    
    @Column(columnDefinition = "Decimal(18,4) NOT NULL default '1.0000'")
    private Double width; // Chiều rộng của sản phẩm
    
    @Column(columnDefinition = "Decimal(18,4) NOT NULL default '0.0000'")
    private Double weight; // Khối lượng của sản phẩm
    
    @Column(columnDefinition = "Decimal(18,4) NOT NULL default '0.0000'")
    private Double length; // Chiều dài của sản phẩm
    
    @Column(columnDefinition = "nvarchar(50) null")
    private String quantity_per_unit;
    
    @Column(columnDefinition = "nvarchar(500) null")
    private String type_list;
	
	private boolean discontinued;
	
	private boolean is_featured;
	
	private boolean is_new;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category categories;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier suppliers;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date created_at = new Date();
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updated_at = new Date();
	
	@JsonIgnore
	@OneToMany(mappedBy = "products")
	private List<OrderDetails> orderDetails;
	
	@JsonIgnore
	@OneToMany(mappedBy = "products")
	private List<ProductImages> productImages;
}
