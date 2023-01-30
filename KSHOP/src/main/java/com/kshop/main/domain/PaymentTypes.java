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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_payment_types")
public class PaymentTypes implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	private String payment_code;
	
	@Column(columnDefinition = "nvarchar(500) not null")
	private String payment_name;
	
	@Column(columnDefinition = "nvarchar(4000) null")
	private String description;
	
	@Column(columnDefinition = "nvarchar(4000) default null")
	private String image;
	
	@CreationTimestamp
	private LocalDateTime created_at;
	
	@UpdateTimestamp
	private LocalDateTime updated_at;

	@JsonIgnore
	@OneToMany(mappedBy = "paymentTypes")
	private List<Orders> orders;
	
}
