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
import com.kshop.main.domain.ShopProductVouchers;
import com.kshop.main.domain.ShopVouchers;

public interface ShopProductVouchersService {

    <S extends ShopProductVouchers> List<S> findAll(Example<S> example, Sort sort);

    <S extends ShopProductVouchers> List<S> findAll(Example<S> example);

    ShopProductVouchers getReferenceById(Long id);

    ShopProductVouchers getById(Long id);

    void deleteAll();

    void deleteAll(Iterable<? extends ShopProductVouchers> entities);

    ShopProductVouchers getOne(Long id);

    void deleteAllInBatch();

    void deleteAllById(Iterable<? extends Long> ids);

    <S extends ShopProductVouchers, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    void delete(ShopProductVouchers entity);

    void deleteAllByIdInBatch(Iterable<Long> ids);

    void deleteById(Long id);

    <S extends ShopProductVouchers> boolean exists(Example<S> example);

    long count();

    void deleteAllInBatch(Iterable<ShopProductVouchers> entities);

    <S extends ShopProductVouchers> long count(Example<S> example);

    void deleteInBatch(Iterable<ShopProductVouchers> entities);

    <S extends ShopProductVouchers> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends ShopProductVouchers> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends ShopProductVouchers> S saveAndFlush(S entity);

    boolean existsById(Long id);

    void flush();

    <S extends ShopProductVouchers> List<S> saveAll(Iterable<S> entities);

    Optional<ShopProductVouchers> findById(Long id);

    List<ShopProductVouchers> findAllById(Iterable<Long> ids);

    List<ShopProductVouchers> findAll(Sort sort);

    Page<ShopProductVouchers> findAll(Pageable pageable);

    List<ShopProductVouchers> findAll();

    <S extends ShopProductVouchers> Optional<S> findOne(Example<S> example);

    <S extends ShopProductVouchers> S save(S entity);

    List<ShopProductVouchers> selectsByProductId(Long product_id);

    ShopProductVouchers selectCustomerCanUse(Long product_id, Long voucher_id);

    boolean isUse(Long product_id, Long voucher_id);

    ShopProductVouchers selectByProductIdAndVoucherId(Long product_id, Long voucher_id);

    Optional<ShopProductVouchers> selectByProductId(Long id);

    List<ShopProductVouchers> selectsByVoucherId(Long voucher_id);


}
