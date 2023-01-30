package com.kshop.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshop.main.domain.ProductDiscounts;
import com.kshop.main.domain.Supplier;


public interface ProductDiscountsReponsitory extends JpaRepository<ProductDiscounts, Long>{

}
