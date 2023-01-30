package com.kshop.main.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "shop_categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	private String category_code;
	
	@Column(columnDefinition = "nvarchar(500) not null")
	private String category_name;
	
	@Column(columnDefinition = "nvarchar(4000) null")
	private String description;
	
	@Column(columnDefinition = "nvarchar(4000) null")
	private String image;
	
	@Column(columnDefinition = "varchar(10) null")
	private String level;

	@Column(name="parent_id",columnDefinition = "varchar(10) null")
	private String parentId;
	
	@Temporal(TemporalType.DATE)
	private Date created_at = new Date();;
	
	@Temporal(TemporalType.DATE)
	private Date updated_at = new Date();;
}
