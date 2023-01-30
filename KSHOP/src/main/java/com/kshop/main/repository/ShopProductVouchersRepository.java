package com.kshop.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.ShopCustomerVouchers;
import com.kshop.main.domain.ShopProductVouchers;
import com.kshop.main.domain.ShopVouchers;

@Repository
public interface ShopProductVouchersRepository extends JpaRepository<ShopProductVouchers, Long> {

    @Query("SELECT s FROM ShopProductVouchers s WHERE s.products.id = ?1 and s.vouchers.id = ?2")
    ShopProductVouchers selectProductCanUse(Long product_id, Long voucher_id);

    @Query("SELECT s FROM ShopProductVouchers s WHERE s.products.id = ?1")
    List<ShopProductVouchers> selectsByProductId(Long product_id);

    @Query("SELECT s FROM ShopProductVouchers s WHERE s.products.id = ?1")
    Optional<ShopProductVouchers> selectByProductId(Long id);

    @Query("SELECT s FROM ShopProductVouchers s WHERE s.vouchers.id = ?1")
    List<ShopProductVouchers> selectsByVoucherId(Long voucher_id);


}
