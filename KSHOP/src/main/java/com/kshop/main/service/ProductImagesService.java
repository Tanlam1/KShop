package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.ProductImages;

public interface ProductImagesService{

	<S extends ProductImages> List<S> findAll(Example<S> example, Sort sort);

	<S extends ProductImages> List<S> findAll(Example<S> example);

	ProductImages getReferenceById(Long id);

	ProductImages getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends ProductImages> entities);

	ProductImages getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends ProductImages, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(ProductImages entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends ProductImages> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<ProductImages> entities);

	<S extends ProductImages> long count(Example<S> example);

	void deleteInBatch(Iterable<ProductImages> entities);

	<S extends ProductImages> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends ProductImages> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends ProductImages> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends ProductImages> List<S> saveAll(Iterable<S> entities);

	Optional<ProductImages> findById(Long id);

	List<ProductImages> findAllById(Iterable<Long> ids);

	List<ProductImages> findAll(Sort sort);

	Page<ProductImages> findAll(Pageable pageable);

	List<ProductImages> findAll();

	<S extends ProductImages> Optional<S> findOne(Example<S> example);

	<S extends ProductImages> S save(S entity);

    List<ProductImages> selectsByProductId(Long id);

}
