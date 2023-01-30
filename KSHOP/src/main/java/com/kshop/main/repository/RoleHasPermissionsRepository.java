package com.kshop.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.RoleHasPermissions;

@Repository
public interface RoleHasPermissionsRepository extends JpaRepository<RoleHasPermissions, Long>{
	//Query Creation
}
