package com.kshop.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.Category;

@Repository
public interface ShopCategoriesRepository extends JpaRepository<Category, Long> {

	List<Category> findByLevel(String id);

	List<Category> findByParentId(String id);
	
}
