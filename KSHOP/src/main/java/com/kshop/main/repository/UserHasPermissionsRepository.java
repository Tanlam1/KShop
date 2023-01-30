package com.kshop.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.UserHasPermissions;

@Repository
public interface UserHasPermissionsRepository extends JpaRepository<UserHasPermissions, Long>{
	//Query Creation
}
