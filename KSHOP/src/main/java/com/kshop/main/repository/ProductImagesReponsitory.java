package com.kshop.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kshop.main.domain.ProductImages;


public interface ProductImagesReponsitory extends JpaRepository<ProductImages, Long>{

    @Query("SELECT p FROM ProductImages p WHERE p.products.id = ?1")
    List<ProductImages> selectsByProductId(Long id);

}
