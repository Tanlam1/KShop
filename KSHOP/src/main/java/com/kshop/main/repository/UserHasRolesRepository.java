package com.kshop.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.UserHasRoles;

@Repository
public interface UserHasRolesRepository extends JpaRepository<UserHasRoles, Long>{

    @Query("SELECT o FROM UserHasRoles o WHERE o.users.id = ?1")
    List<UserHasRoles> selectsByUserId(Long id);
	//Query Creation
}
