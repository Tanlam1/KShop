package com.kshop.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleHasPermissionsDTO{
	
	private Long id;
	
	private RolesDTO rolesDTO;
	
	private PermissionsDTO permissionsDTO;
}
