package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.Products;

public interface ProductsService {

	<S extends Products> List<S> findAll(Example<S> example, Sort sort);

	<S extends Products> List<S> findAll(Example<S> example);

	Products getReferenceById(Long id);

	Products getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Products> entities);

	Products getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends Products, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Products entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends Products> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Products> entities);

	<S extends Products> long count(Example<S> example);

	void deleteInBatch(Iterable<Products> entities);

	<S extends Products> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Products> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Products> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends Products> List<S> saveAll(Iterable<S> entities);

	Optional<Products> findById(Long id);

	List<Products> findAllById(Iterable<Long> ids);

	List<Products> findAll(Sort sort);

	Page<Products> findAll(Pageable pageable);

	List<Products> findAll();

	<S extends Products> Optional<S> findOne(Example<S> example);

	<S extends Products> S save(S entity);

    List<Products> selectBestSellerProducts(int start, int total);

    Page<Products> findAllByIsFeatured(PageRequest of);

    List<Products> selectsAllByCategoryId(Long id);

    List<Products> sp_SwipLoaiSanPhamTrangPhuc();

    List<Products> sp_SwipLoaiSanPhamDienTu();

    List<Products> sp_SwipLoaiSanPhamNhavsbep();


    List<Products> findByProductName(String name);

    Products checkHasProduct(Long idProduct, String productCode);

    List<Products> selectsAllByUserId(Long id);



}
