package com.kshop.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.PaymentTypes;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentTypes, Long>{

}
