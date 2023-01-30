package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.UserHasPermissions;

public interface UserHasPermissionsService {

	<S extends UserHasPermissions> List<S> findAll(Example<S> example, Sort sort);

	<S extends UserHasPermissions> List<S> findAll(Example<S> example);

	UserHasPermissions getReferenceById(Long id);

	UserHasPermissions getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends UserHasPermissions> entities);

	UserHasPermissions getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends UserHasPermissions, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(UserHasPermissions entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends UserHasPermissions> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<UserHasPermissions> entities);

	<S extends UserHasPermissions> long count(Example<S> example);

	void deleteInBatch(Iterable<UserHasPermissions> entities);

	<S extends UserHasPermissions> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends UserHasPermissions> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends UserHasPermissions> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends UserHasPermissions> List<S> saveAll(Iterable<S> entities);

	Optional<UserHasPermissions> findById(Long id);

	List<UserHasPermissions> findAllById(Iterable<Long> ids);

	List<UserHasPermissions> findAll(Sort sort);

	Page<UserHasPermissions> findAll(Pageable pageable);

	List<UserHasPermissions> findAll();

	<S extends UserHasPermissions> Optional<S> findOne(Example<S> example);

	<S extends UserHasPermissions> S save(S entity);

}
