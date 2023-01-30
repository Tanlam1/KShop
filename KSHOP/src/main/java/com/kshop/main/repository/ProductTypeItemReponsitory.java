package com.kshop.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kshop.main.domain.ProductImages;
import com.kshop.main.domain.ProductType;
import com.kshop.main.domain.ProductTypeItem;


public interface ProductTypeItemReponsitory extends JpaRepository<ProductTypeItem, Long>{

    @Query("SELECT p FROM ProductTypeItem p WHERE p.products.id = ?1")
    List<ProductTypeItem> selectsByProductId(Long id);


}
