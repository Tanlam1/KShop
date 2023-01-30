package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;


import com.kshop.main.domain.ShopUsersCart;

public interface ShopUsersCartService {

    <S extends ShopUsersCart> List<S> findAll(Example<S> example, Sort sort);

    <S extends ShopUsersCart> List<S> findAll(Example<S> example);

    ShopUsersCart getReferenceById(Long id);

    ShopUsersCart getById(Long id);

    void deleteAll();

    void deleteAll(Iterable<? extends ShopUsersCart> entities);

    ShopUsersCart getOne(Long id);

    void deleteAllInBatch();

    void deleteAllById(Iterable<? extends Long> ids);

    <S extends ShopUsersCart, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    void delete(ShopUsersCart entity);

    void deleteAllByIdInBatch(Iterable<Long> ids);

    void deleteById(Long id);

    <S extends ShopUsersCart> boolean exists(Example<S> example);

    long count();

    void deleteAllInBatch(Iterable<ShopUsersCart> entities);

    <S extends ShopUsersCart> long count(Example<S> example);

    void deleteInBatch(Iterable<ShopUsersCart> entities);

    <S extends ShopUsersCart> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends ShopUsersCart> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends ShopUsersCart> S saveAndFlush(S entity);

    boolean existsById(Long id);

    void flush();

    <S extends ShopUsersCart> List<S> saveAll(Iterable<S> entities);

    Optional<ShopUsersCart> findById(Long id);

    List<ShopUsersCart> findAllById(Iterable<Long> ids);

    List<ShopUsersCart> findAll(Sort sort);

    Page<ShopUsersCart> findAll(Pageable pageable);

    List<ShopUsersCart> findAll();

    <S extends ShopUsersCart> Optional<S> findOne(Example<S> example);

    <S extends ShopUsersCart> S save(S entity);

    List<ShopUsersCart> selectAllByCustomerId(Long id);

    boolean addToCart(ShopUsersCart shopUsersCart, Long id);
	
	
}
