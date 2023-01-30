package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.PaymentTypes;


public interface PaymentTypeService {

	<S extends PaymentTypes> List<S> findAll(Example<S> example, Sort sort);

	<S extends PaymentTypes> List<S> findAll(Example<S> example);

	PaymentTypes getReferenceById(Long id);

	PaymentTypes getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends PaymentTypes> entities);

	PaymentTypes getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends PaymentTypes, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(PaymentTypes entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends PaymentTypes> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<PaymentTypes> entities);

	<S extends PaymentTypes> long count(Example<S> example);

	void deleteInBatch(Iterable<PaymentTypes> entities);

	<S extends PaymentTypes> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends PaymentTypes> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends PaymentTypes> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends PaymentTypes> List<S> saveAll(Iterable<S> entities);

	Optional<PaymentTypes> findById(Long id);

	List<PaymentTypes> findAllById(Iterable<Long> ids);

	List<PaymentTypes> findAll(Sort sort);

	Page<PaymentTypes> findAll(Pageable pageable);

	List<PaymentTypes> findAll();

	<S extends PaymentTypes> Optional<S> findOne(Example<S> example);

	<S extends PaymentTypes> S save(S entity);
	
}
