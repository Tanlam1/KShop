package com.kshop.main.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MoneyOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Users user;
    
    @Column(columnDefinition = "Decimal(19,4) NOT NULL default '0.0000'")
    private Double total_money;
    
    private boolean status;
        
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date created_at = new Date();
    
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date updated_at = new Date();
    
}
