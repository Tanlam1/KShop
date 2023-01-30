package com.kshop.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>{

    List<Orders> findAllById(long id);

    @Query("SELECT o FROM Orders o WHERE o.customers.id = ?1")
    List<Orders> selectByCustomerId(Long id);

    @Query("SELECT o FROM Orders o WHERE o.users.id = ?1")
    List<Orders> selectByUserId(Long id);

    @Query("SELECT o FROM Orders o WHERE o.order_status like 'Complete' or o.order_status like 'Close'")
    List<Orders> selectByOrderComleteClose();

}
