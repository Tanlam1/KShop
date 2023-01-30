package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.Permissions;

public interface PermissionsService {

	<S extends Permissions> List<S> findAll(Example<S> example, Sort sort);

	<S extends Permissions> List<S> findAll(Example<S> example);

	Permissions getReferenceById(Long id);

	Permissions getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Permissions> entities);

	Permissions getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends Permissions, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Permissions entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends Permissions> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Permissions> entities);

	<S extends Permissions> long count(Example<S> example);

	void deleteInBatch(Iterable<Permissions> entities);

	<S extends Permissions> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Permissions> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Permissions> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends Permissions> List<S> saveAll(Iterable<S> entities);

	Optional<Permissions> findById(Long id);

	List<Permissions> findAllById(Iterable<Long> ids);

	List<Permissions> findAll(Sort sort);

	Page<Permissions> findAll(Pageable pageable);

	List<Permissions> findAll();

	<S extends Permissions> Optional<S> findOne(Example<S> example);

	<S extends Permissions> S save(S entity);
	
}
