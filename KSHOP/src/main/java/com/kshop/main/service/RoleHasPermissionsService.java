package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.RoleHasPermissions;

public interface RoleHasPermissionsService {

	<S extends RoleHasPermissions> List<S> findAll(Example<S> example, Sort sort);

	<S extends RoleHasPermissions> List<S> findAll(Example<S> example);

	RoleHasPermissions getReferenceById(Long id);

	RoleHasPermissions getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends RoleHasPermissions> entities);

	RoleHasPermissions getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends RoleHasPermissions, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(RoleHasPermissions entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends RoleHasPermissions> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<RoleHasPermissions> entities);

	<S extends RoleHasPermissions> long count(Example<S> example);

	void deleteInBatch(Iterable<RoleHasPermissions> entities);

	<S extends RoleHasPermissions> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends RoleHasPermissions> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends RoleHasPermissions> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends RoleHasPermissions> List<S> saveAll(Iterable<S> entities);

	Optional<RoleHasPermissions> findById(Long id);

	List<RoleHasPermissions> findAllById(Iterable<Long> ids);

	List<RoleHasPermissions> findAll(Sort sort);

	Page<RoleHasPermissions> findAll(Pageable pageable);

	List<RoleHasPermissions> findAll();

	<S extends RoleHasPermissions> Optional<S> findOne(Example<S> example);

	<S extends RoleHasPermissions> S save(S entity);


}
