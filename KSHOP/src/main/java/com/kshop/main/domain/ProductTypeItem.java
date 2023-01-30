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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_products_type_item")
public class ProductTypeItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Products products;
    
    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;
    
    @Column(columnDefinition = "nvarchar(500) null")
    private String image;
    
    @Column(columnDefinition = "nvarchar(500) not null")
    private String name;
    
    @Column(columnDefinition = "nvarchar(500) null")
    private String list_imgs;
}
