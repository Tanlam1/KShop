package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.kshop.main.domain.ShopVouchers;

public interface ShopVouchersService {

	<S extends ShopVouchers> List<S> findAll(Example<S> example, Sort sort);

	<S extends ShopVouchers> List<S> findAll(Example<S> example);

	ShopVouchers getReferenceById(Long id);

	ShopVouchers getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends ShopVouchers> entities);

	ShopVouchers getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends ShopVouchers, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(ShopVouchers entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends ShopVouchers> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<ShopVouchers> entities);

	<S extends ShopVouchers> long count(Example<S> example);

	void deleteInBatch(Iterable<ShopVouchers> entities);

	<S extends ShopVouchers> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends ShopVouchers> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends ShopVouchers> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends ShopVouchers> List<S> saveAll(Iterable<S> entities);

	Optional<ShopVouchers> findById(Long id);

	List<ShopVouchers> findAllById(Iterable<Long> ids);

	List<ShopVouchers> findAll(Sort sort);

	Page<ShopVouchers> findAll(Pageable pageable);

	List<ShopVouchers> findAll();

	<S extends ShopVouchers> Optional<S> findOne(Example<S> example);

	<S extends ShopVouchers> S save(S entity);

    Optional<ShopVouchers> selectByVoucherName(String voucher_name);

}
