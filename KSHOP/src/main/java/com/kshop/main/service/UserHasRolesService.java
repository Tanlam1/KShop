package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.UserHasRoles;

public interface UserHasRolesService {

	<S extends UserHasRoles> List<S> findAll(Example<S> example, Sort sort);

	<S extends UserHasRoles> List<S> findAll(Example<S> example);

	UserHasRoles getReferenceById(Long id);

	UserHasRoles getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends UserHasRoles> entities);

	UserHasRoles getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends UserHasRoles, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(UserHasRoles entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends UserHasRoles> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<UserHasRoles> entities);

	<S extends UserHasRoles> long count(Example<S> example);

	void deleteInBatch(Iterable<UserHasRoles> entities);

	<S extends UserHasRoles> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends UserHasRoles> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends UserHasRoles> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends UserHasRoles> List<S> saveAll(Iterable<S> entities);

	Optional<UserHasRoles> findById(Long id);

	List<UserHasRoles> findAllById(Iterable<Long> ids);

	List<UserHasRoles> findAll(Sort sort);

	Page<UserHasRoles> findAll(Pageable pageable);

	List<UserHasRoles> findAll();

	<S extends UserHasRoles> Optional<S> findOne(Example<S> example);

	<S extends UserHasRoles> S save(S entity);

    List<UserHasRoles> selectsByUserId(Long id);

}
