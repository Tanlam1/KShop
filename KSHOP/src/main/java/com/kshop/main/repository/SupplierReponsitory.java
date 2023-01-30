package com.kshop.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshop.main.domain.Supplier;


public interface SupplierReponsitory extends JpaRepository<Supplier, Long>{

}
