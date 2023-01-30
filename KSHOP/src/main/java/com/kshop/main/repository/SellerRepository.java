package com.kshop.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long>{

    @Query("SELECT o FROM Seller o WHERE o.customer.id = ?1")
    Seller selectByCustomerId(Long id);

}
