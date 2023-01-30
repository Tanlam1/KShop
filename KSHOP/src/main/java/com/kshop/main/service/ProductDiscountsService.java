package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.ProductDiscounts;

public interface ProductDiscountsService{

	<S extends ProductDiscounts> List<S> findAll(Example<S> example, Sort sort);

	<S extends ProductDiscounts> List<S> findAll(Example<S> example);

	ProductDiscounts getReferenceById(Long id);

	ProductDiscounts getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends ProductDiscounts> entities);

	ProductDiscounts getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends ProductDiscounts, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(ProductDiscounts entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends ProductDiscounts> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<ProductDiscounts> entities);

	<S extends ProductDiscounts> long count(Example<S> example);

	void deleteInBatch(Iterable<ProductDiscounts> entities);

	<S extends ProductDiscounts> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends ProductDiscounts> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends ProductDiscounts> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends ProductDiscounts> List<S> saveAll(Iterable<S> entities);

	Optional<ProductDiscounts> findById(Long id);

	List<ProductDiscounts> findAllById(Iterable<Long> ids);

	List<ProductDiscounts> findAll(Sort sort);

	Page<ProductDiscounts> findAll(Pageable pageable);

	List<ProductDiscounts> findAll();

	<S extends ProductDiscounts> Optional<S> findOne(Example<S> example);

	<S extends ProductDiscounts> S save(S entity);

	
}
