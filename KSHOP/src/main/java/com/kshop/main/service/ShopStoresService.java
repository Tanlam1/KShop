package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.ShopStores;

public interface ShopStoresService {

	<S extends ShopStores> List<S> findAll(Example<S> example, Sort sort);

	<S extends ShopStores> List<S> findAll(Example<S> example);

	ShopStores getReferenceById(Long id);

	ShopStores getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends ShopStores> entities);

	ShopStores getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends ShopStores, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(ShopStores entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends ShopStores> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<ShopStores> entities);

	<S extends ShopStores> long count(Example<S> example);

	void deleteInBatch(Iterable<ShopStores> entities);

	<S extends ShopStores> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends ShopStores> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends ShopStores> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends ShopStores> List<S> saveAll(Iterable<S> entities);

	Optional<ShopStores> findById(Long id);

	List<ShopStores> findAllById(Iterable<Long> ids);

	List<ShopStores> findAll(Sort sort);

	Page<ShopStores> findAll(Pageable pageable);

	List<ShopStores> findAll();

	<S extends ShopStores> Optional<S> findOne(Example<S> example);

	<S extends ShopStores> S save(S entity);

//	ShopStores checkHasVender(Long idVender, String venderCode);

}
