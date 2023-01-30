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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_export_details")
public class ShopExportDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "export_id")
    private ShopExports exports;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;
    
    @Column(columnDefinition="decimal(18,4) default '0.0000' not null")
    private double quantity;
    
    @Column(columnDefinition="decimal(19,4) default '0.0000' not null")
    private double unit_price;
}
