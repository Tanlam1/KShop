package com.kshop.main.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.ShopUsersCart;

@Repository
public interface ShopUsersCartRepository extends JpaRepository<ShopUsersCart, Long>{

    @Query("SELECT o FROM ShopUsersCart o WHERE o.customer.id = ?1")
    List<ShopUsersCart> selectAllByCustomerId(Long id);

    @Query("SELECT o FROM ShopUsersCart o WHERE o.product.id = ?1")
    List<ShopUsersCart> selectAllByProductId(long id);

    @Query("SELECT o FROM ShopUsersCart o WHERE o.product.id = ?1 and o.type_item_id = ?2 and o.customer.id = ?3")
    ShopUsersCart selectByProductIdOfCustomerAndTypeId(long id, String type_item_id, Long customerId);

    @Query("SELECT o FROM ShopUsersCart o WHERE o.product.id = ?1 and o.customer.id = ?2")
    List<ShopUsersCart> selectAllByProductIdOfCustomer(Long id, Long customerId);

}
