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
import com.kshop.main.domain.Customers;
import com.kshop.main.exception.UserNotFoundException;
import com.kshop.main.model.LoginDTO;
import com.kshop.main.model.RegisterDTO;


public interface CustomerService {

	<S extends Customers> List<S> findAll(Example<S> example, Sort sort);

	<S extends Customers> List<S> findAll(Example<S> example);

	Customers getReferenceById(Long id);

	Customers getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Customers> entities);

	Customers getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends Customers, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Customers entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends Customers> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Customers> entities);

	<S extends Customers> long count(Example<S> example);

	void deleteInBatch(Iterable<Customers> entities);

	<S extends Customers> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Customers> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Customers> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends Customers> List<S> saveAll(Iterable<S> entities);

	Optional<Customers> findById(Long id);

	List<Customers> findAllById(Iterable<Long> ids);

	List<Customers> findAll(Sort sort);

	Page<Customers> findAll(Pageable pageable);

	List<Customers> findAll();

	<S extends Customers> Optional<S> findOne(Example<S> example);

	<S extends Customers> S save(S entity);

    APIResponse login(LoginDTO loginDTO);

    APIResponse regiter(RegisterDTO registerDTO);

	void updatePassword(Customers customers, String newPassowrd);

	Customers getToken(String activateCode);

	void updateResetPassword(String token, String email) throws UserNotFoundException;

}
