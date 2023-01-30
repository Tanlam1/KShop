package com.kshop.main.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopUsersCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition="Decimal(18,4) default '0.0000'")
    private Double quantity;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;
    
    @ManyToOne
    @JoinColumn(name = "shop_product_vouchers_id")
    private ShopProductVouchers shopProductVouchers;
    
    private boolean is_discount;
    
    private boolean is_checked;
    
    @Column(columnDefinition = "nvarchar(255) null")
    private String type_item_id;
        
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date created_at;
    
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date updated_at;
    
}
