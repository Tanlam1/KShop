package com.kshop.main.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.MoneyOrders;

@Repository
public interface MoneyOrdersRepository extends JpaRepository<MoneyOrders, Long>{

    @Query("SELECT m FROM MoneyOrders m WHERE m.user.id = ?1")
    List<MoneyOrders> selectAllByUserId(Long id);

    @Query("SELECT m FROM MoneyOrders m WHERE m.user.id = ?1 and m.order.id = ?2")
    MoneyOrders selectByUserIdAndOrderId(Long userId, Long orderId);

    @Query("SELECT m FROM MoneyOrders m WHERE m.order.users.id = ?1")
    List<MoneyOrders> selectAllByUserIdOfStore(Long id);

}
