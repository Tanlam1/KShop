package com.kshop.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHasRolesDTO {
	
	private Long id;
	
	private UsersDTO usersDTO;
	
	private RolesDTO rolesDTO;
}
