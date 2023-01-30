package com.kshop.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.ShopVouchers;

@Repository
public interface ShopVouchersRepository extends JpaRepository<ShopVouchers, Long> {

    @Query("SELECT v FROM ShopVouchers v WHERE v.voucher_name = ?1")
    Optional<ShopVouchers> selectByVoucherName(String voucher_name);

}
