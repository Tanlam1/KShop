package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.Roles;

public interface RolesService {

	<S extends Roles> List<S> findAll(Example<S> example, Sort sort);

	<S extends Roles> List<S> findAll(Example<S> example);

	Roles getReferenceById(Long id);

	Roles getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Roles> entities);

	Roles getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends Roles, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Roles entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends Roles> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Roles> entities);

	<S extends Roles> long count(Example<S> example);

	void deleteInBatch(Iterable<Roles> entities);

	<S extends Roles> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Roles> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Roles> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends Roles> List<S> saveAll(Iterable<S> entities);

	Optional<Roles> findById(Long id);

	List<Roles> findAllById(Iterable<Long> ids);

	List<Roles> findAll(Sort sort);

	Page<Roles> findAll(Pageable pageable);

	List<Roles> findAll();

	<S extends Roles> Optional<S> findOne(Example<S> example);

	<S extends Roles> S save(S entity);

}
