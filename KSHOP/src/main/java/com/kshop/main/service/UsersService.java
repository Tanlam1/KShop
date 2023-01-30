package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.common.APIResponse;
import com.kshop.main.domain.Users;
import com.kshop.main.model.LoginDTO;
import com.kshop.main.model.RegisterDTO;

public interface UsersService {

	<S extends Users> List<S> findAll(Example<S> example, Sort sort);

	<S extends Users> List<S> findAll(Example<S> example);

	Users getReferenceById(Long id);

	Users getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Users> entities);

	Users getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends Users, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Users entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends Users> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Users> entities);

	<S extends Users> long count(Example<S> example);

	void deleteInBatch(Iterable<Users> entities);

	<S extends Users> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Users> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Users> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends Users> List<S> saveAll(Iterable<S> entities);

	Optional<Users> findById(Long id);

	List<Users> findAllById(Iterable<Long> ids);

	List<Users> findAll(Sort sort);

	Page<Users> findAll(Pageable pageable);

	List<Users> findAll();

	<S extends Users> Optional<S> findOne(Example<S> example);

	<S extends Users> S save(S entity);

    APIResponse login(LoginDTO loginDTO);

    APIResponse regiter(RegisterDTO registerDTO);

	Optional<Users> selectsByStoreId(Long id);

    Users findByEmailOrUsername(String email, String username);

}
