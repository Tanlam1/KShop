package com.kshop.main.domain;

import java.io.Serializable;
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
@Table(name = "acl_roles")
public class Roles implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "nvarchar(500) not null")
	private String name;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String display_name;
	
	@Column(columnDefinition = "nvarchar(500) not null")
	private String guard_name;
	
	@Temporal(TemporalType.DATE)
	private Date created_at;
	
	@Temporal(TemporalType.DATE)
	private Date updated_at;
}