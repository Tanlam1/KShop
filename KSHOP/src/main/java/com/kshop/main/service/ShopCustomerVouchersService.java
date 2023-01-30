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

import com.kshop.main.domain.ShopCustomerVouchers;
import com.kshop.main.domain.ShopVouchers;

public interface ShopCustomerVouchersService {

    <S extends ShopCustomerVouchers> List<S> findAll(Example<S> example, Sort sort);

    <S extends ShopCustomerVouchers> List<S> findAll(Example<S> example);

    ShopCustomerVouchers getReferenceById(Long id);

    ShopCustomerVouchers getById(Long id);

    void deleteAll();

    void deleteAll(Iterable<? extends ShopCustomerVouchers> entities);

    ShopCustomerVouchers getOne(Long id);

    void deleteAllInBatch();

    void deleteAllById(Iterable<? extends Long> ids);

    <S extends ShopCustomerVouchers, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    void delete(ShopCustomerVouchers entity);

    void deleteAllByIdInBatch(Iterable<Long> ids);

    void deleteById(Long id);

    <S extends ShopCustomerVouchers> boolean exists(Example<S> example);

    long count();

    void deleteAllInBatch(Iterable<ShopCustomerVouchers> entities);

    <S extends ShopCustomerVouchers> long count(Example<S> example);

    void deleteInBatch(Iterable<ShopCustomerVouchers> entities);

    <S extends ShopCustomerVouchers> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends ShopCustomerVouchers> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends ShopCustomerVouchers> S saveAndFlush(S entity);

    boolean existsById(Long id);

    void flush();

    <S extends ShopCustomerVouchers> List<S> saveAll(Iterable<S> entities);

    Optional<ShopCustomerVouchers> findById(Long id);

    List<ShopCustomerVouchers> findAllById(Iterable<Long> ids);

    List<ShopCustomerVouchers> findAll(Sort sort);

    Page<ShopCustomerVouchers> findAll(Pageable pageable);

    List<ShopCustomerVouchers> findAll();

    <S extends ShopCustomerVouchers> Optional<S> findOne(Example<S> example);

    <S extends ShopCustomerVouchers> S save(S entity);

    boolean isUse(Long customer_id, Long voucher_id);

    ShopCustomerVouchers selectByCustomerIdAndVoucherId(Long customer_id, Long voucher_id);

    List<ShopCustomerVouchers> selectByCustomerIdUsed(Long customer_id);

    List<ShopCustomerVouchers> selectsByVoucherId(Long customer_id);

}
