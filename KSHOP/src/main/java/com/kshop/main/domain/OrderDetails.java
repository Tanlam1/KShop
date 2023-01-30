package com.kshop.main.domain;

import java.io.Serializable;
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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_order_details")
public class OrderDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders orders;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Products products;
	
	@Column(columnDefinition="decimal(18,4) default '0.0000' not null")
	private Double quantity;
	
	@Column(columnDefinition="decimal(19,4) default '0.0000' not null")
	private Double unit_price;
	
	@Column(columnDefinition="float default '0' not null")
	private Double discount_percentage;
	
	@Column(columnDefinition="float default '0' not null")
	private Double discount_amout;
	
	@Column(columnDefinition="nvarchar(50) default null")
	private String order_detail_status = "Chờ xác nhận";
    
    @Column(columnDefinition="nvarchar(MAX) null")
    private String order_detail_name = "Chờ xác nhận";
	
    @DateTimeFormat(iso = ISO.DATE_TIME)
	private Date date_allocated = new Date();
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "orderDetails")
//	private List<> ;
}
