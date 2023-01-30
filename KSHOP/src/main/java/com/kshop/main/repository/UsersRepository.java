package com.kshop.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

    Users findOneByEmailIgnoreCaseAndPassword(String email, String password);
	//Query Creation

    Users findOneByEmail(String email);

    Users findFirstByOrderByIdDesc();

    Users findByEmail(String email);

    Users findByEmailOrUsername(String email, String string);

    @Query(value = "SELECT p FROM Users p WHERE p.stores.id = ?1")
	Optional<Users> selectsByStoreId(Long id);
    
}
