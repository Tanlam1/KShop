package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;


import com.kshop.main.domain.OrderDetails;



public interface OrderDetailService {

	<S extends OrderDetails> List<S> findAll(Example<S> example, Sort sort);

	<S extends OrderDetails> List<S> findAll(Example<S> example);

	OrderDetails getReferenceById(Long id);

	OrderDetails getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends OrderDetails> entities);

	OrderDetails getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends OrderDetails, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(OrderDetails entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends OrderDetails> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<OrderDetails> entities);

	<S extends OrderDetails> long count(Example<S> example);

	void deleteInBatch(Iterable<OrderDetails> entities);

	<S extends OrderDetails> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends OrderDetails> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends OrderDetails> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends OrderDetails> List<S> saveAll(Iterable<S> entities);

	Optional<OrderDetails> findById(Long id);

	List<OrderDetails> findAllById(Iterable<Long> ids);

	List<OrderDetails> findAll(Sort sort);

	Page<OrderDetails> findAll(Pageable pageable);

	List<OrderDetails> findAll();

	<S extends OrderDetails> Optional<S> findOne(Example<S> example);

	<S extends OrderDetails> S save(S entity);

    List<OrderDetails> selectByOrderId(Long id);
	
}
