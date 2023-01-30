package com.kshop.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kshop.main.domain.ShopExports;
import com.kshop.main.domain.ShopImports;
import com.kshop.main.domain.ShopStores;
import com.kshop.main.domain.ShopVouchers;

@Repository
public interface ShopExportsRepository extends JpaRepository<ShopExports, Long> {

}
