package com.kshop.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kshop.main.domain.ProductReviews;


public interface ProductReviewsReponsitory extends JpaRepository<ProductReviews, Long>{

	@Query("Select p from ProductReviews p where p.products.id = ?1")	
	List<ProductReviews> selectAllByProductId(Long id);

	@Query("Select p from ProductReviews p where p.customers.id = ?1 and p.products.id = ?2")
    ProductReviews selectByCustomerIdAndProductId(Long customerID, Long productID);

}
