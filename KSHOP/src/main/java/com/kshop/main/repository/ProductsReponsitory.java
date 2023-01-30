package com.kshop.main.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import com.kshop.main.domain.Products;


public interface ProductsReponsitory extends JpaRepository<Products, Long>{

    @Query(value = "SELECT TOP 30 * FROM shop_products ORDER BY NEWID()", nativeQuery = true)
    List<Products> selectBestSellerProducts(int start, int total);

    @Query(value = "SELECT p FROM Products p WHERE p.is_featured = 1")
    Page<Products> findAllByIsFeatured(PageRequest of);

    @Query(value = "SELECT p FROM Products p WHERE p.categories.id = ?1")
    List<Products> selectsAllByCategoryId(Long id);

    @Query(value = "exec sp_SwipLoaiSanPhamTrangPhuc;", nativeQuery = true)
    List<Products> sp_SwipLoaiSanPhamTrangPhuc();

    @Query(value = "exec sp_SwipLoaiSanPhamDienTu;", nativeQuery = true)
    List<Products> sp_SwipLoaiSanPhamDienTu();
    
    @Query(value = "exec sp_SwipLoaiSanPhamNhavsbep;", nativeQuery = true)
    List<Products> sp_SwipLoaiSanPhamNhavsbep();
    
    @Query(value = "SELECT p FROM Products p WHERE p.product_name like %?1%")
    List<Products> findByProductName(String name);


    @Query("SELECT p FROM Products p WHERE p.id = ?1 and p.product_code = ?2")
    Products checkHasProduct(Long idProduct, String productCode);

    @Query(value = "SELECT p FROM Products p WHERE p.users.id = ?1")
    List<Products> selectsAllByUserId(Long id);

}
