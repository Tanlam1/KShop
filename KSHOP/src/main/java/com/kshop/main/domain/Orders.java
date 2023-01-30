package com.kshop.main.domain;

import java.io.Serializable;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_orders")
public class Orders implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Users users;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customers customers;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date order_date = new Date();
	
	@Column(columnDefinition = "nvarchar(100)")
	private String shipped_date;
    
    @Column(columnDefinition = "nvarchar(255)")
    private String id_order_ghn;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	private String ship_name;
    
    @Column(columnDefinition = "nvarchar(25) not null default ''")
    private String ship_phone;
	
	@Column(columnDefinition = "nvarchar(500) not null")
	private String ship_address1;
	
	@Column(columnDefinition = "nvarchar(500) default null")
	private String ship_address2;
	
	@Column(columnDefinition = "nvarchar(255) not null")
	private String ship_city;
	
	@Column(columnDefinition = "nvarchar(255) default null")
	private String ship_state;
    
    @Column(columnDefinition = "nvarchar(255) default null")
    private String ship_ward;
	
	@Column(columnDefinition = "nvarchar(50) default null")
	private String ship_postal_code;
	
	@Column(columnDefinition = "nvarchar(255) not null")
	private String ship_country;
	
	@Column(columnDefinition="Decimal(19,4) default '0.0000'")
	private Double shipping_fee;
	
	@ManyToOne
	@JoinColumn(name = "payment_type_id")
	private PaymentTypes paymentTypes;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date paid_date;
	
	@Column(columnDefinition="nvarchar(50) not null")
	private String order_status;
    
    @Column(columnDefinition="nvarchar(500) null")
    private String order_note;
    
    @Column(columnDefinition="float default '0' not null")
    private Double discount_amount;
    
    private boolean is_mo = false;
	
    @DateTimeFormat(iso = ISO.DATE_TIME)
	private Date created_at = new Date();
	
    @DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updated_at;
	
	@JsonIgnore
	@OneToMany(mappedBy = "orders")
	private List<OrderDetails> orderDetails;
	
	
}
