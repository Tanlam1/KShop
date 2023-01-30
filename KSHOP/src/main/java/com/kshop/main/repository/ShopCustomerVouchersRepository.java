package com.kshop.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.ShopCustomerVouchers;
import com.kshop.main.domain.ShopVouchers;

@Repository
public interface ShopCustomerVouchersRepository extends JpaRepository<ShopCustomerVouchers, Long> {

    @Query("SELECT s FROM ShopCustomerVouchers s WHERE s.customers.id = ?1 and s.vouchers.id = ?2")
    ShopCustomerVouchers selectCustomerCanUse(Long customer_id, Long voucher_id);

    @Query("SELECT s FROM ShopCustomerVouchers s WHERE s.customers.id = ?1 and s.is_used = true")
    List<ShopCustomerVouchers> selectByCustomerIdUsed(Long customer_id);
    
    @Query("SELECT s FROM ShopCustomerVouchers s WHERE s.vouchers.id = ?1")
    List<ShopCustomerVouchers> selectsByVoucherId(Long voucher_id);    


}
