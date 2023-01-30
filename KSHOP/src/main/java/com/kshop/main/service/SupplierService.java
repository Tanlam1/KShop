package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.Supplier;

public interface SupplierService{

	<S extends Supplier> List<S> findAll(Example<S> example, Sort sort);

	<S extends Supplier> List<S> findAll(Example<S> example);

	Supplier getReferenceById(Long id);

	Supplier getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Supplier> entities);

	Supplier getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends Supplier, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Supplier entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends Supplier> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Supplier> entities);

	<S extends Supplier> long count(Example<S> example);

	void deleteInBatch(Iterable<Supplier> entities);

	<S extends Supplier> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Supplier> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Supplier> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends Supplier> List<S> saveAll(Iterable<S> entities);

	Optional<Supplier> findById(Long id);

	List<Supplier> findAllById(Iterable<Long> ids);

	List<Supplier> findAll(Sort sort);

	Page<Supplier> findAll(Pageable pageable);

	List<Supplier> findAll();

	<S extends Supplier> Optional<S> findOne(Example<S> example);

	<S extends Supplier> S save(S entity);

}
