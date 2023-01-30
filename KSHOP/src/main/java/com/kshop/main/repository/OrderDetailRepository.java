package com.kshop.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.OrderDetails;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long>{

    @Query("SELECT o FROM OrderDetails o WHERE o.orders.id = ?1")
    List<OrderDetails> selectByOrderId(Long id);
	//Query Creation
}
