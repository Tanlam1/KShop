package com.kshop.main.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionsDTO{
	
	private Long id;
	
	private String name;

	private String display_name;
	
	private String guard_name;
	
	private Date created_at;
	
	private Date updated_at;
}