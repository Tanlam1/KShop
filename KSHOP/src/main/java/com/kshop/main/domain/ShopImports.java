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
@Table(name = "shop_imports")
public class ShopImports {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "store_id")
	private ShopStores stores;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Users users;
	
	@Temporal(TemporalType.DATE)
	private Date import_date = new Date();
	
	@Temporal(TemporalType.DATE)
	private Date created_at = new Date();
	
	@Temporal(TemporalType.DATE)
	private Date updated_at;
}
