package com.kshop.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long>{
	//Query Creation
}
