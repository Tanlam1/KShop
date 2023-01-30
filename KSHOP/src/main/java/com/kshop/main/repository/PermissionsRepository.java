package com.kshop.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.Permissions;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long>{
	//Query Creation
}
