package com.kshop.main.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.MoneySends;

@Repository
public interface MoneySendsRepository extends JpaRepository<MoneySends, Long>{

    @Query("SELECT m FROM MoneySends m WHERE m.user.id = ?1")
    List<MoneySends> selectAllByUserId(Long id);
    
    @Query("SELECT m FROM MoneySends m WHERE m.employee.id = ?1")
    List<MoneySends> selectAllByEmployeeId(Long id);

    @Query("SELECT m FROM MoneySends m WHERE m.user.id = ?1 and m.employee.id = ?2")
    MoneySends selectByUserIdAndEmployeeId(Long userId, Long employeeId);

}
