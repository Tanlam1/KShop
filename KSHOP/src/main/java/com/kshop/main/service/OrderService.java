package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;


import com.kshop.main.domain.Orders;



public interface OrderService {

	<S extends Orders> List<S> findAll(Example<S> example, Sort sort);

	<S extends Orders> List<S> findAll(Example<S> example);

	Orders getReferenceById(Long id);

	Orders getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Orders> entities);

	Orders getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends Orders, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Orders entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends Orders> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Orders> entities);

	<S extends Orders> long count(Example<S> example);

	void deleteInBatch(Iterable<Orders> entities);

	<S extends Orders> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Orders> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Orders> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends Orders> List<S> saveAll(Iterable<S> entities);

	Optional<Orders> findById(Long id);

	List<Orders> findAllById(Iterable<Long> ids);

	List<Orders> findAll(Sort sort);

	Page<Orders> findAll(Pageable pageable);

	List<Orders> findAll();

	<S extends Orders> Optional<S> findOne(Example<S> example);

	<S extends Orders> S save(S entity);

    List<Orders> selectByCustomerId(Long id);

    List<Orders> selectByUserId(Long id);

    List<Orders> selectByOrderComleteClose();
	
}
