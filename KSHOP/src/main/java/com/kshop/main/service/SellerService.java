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
import com.kshop.main.domain.Seller;



public interface SellerService {

    <S extends Seller> List<S> findAll(Example<S> example, Sort sort);

    <S extends Seller> List<S> findAll(Example<S> example);

    Seller getReferenceById(Long id);

    Seller getById(Long id);

    void deleteAll();

    void deleteAll(Iterable<? extends Seller> entities);

    Seller getOne(Long id);

    void deleteAllInBatch();

    void deleteAllById(Iterable<? extends Long> ids);

    <S extends Seller, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    void delete(Seller entity);

    void deleteAllByIdInBatch(Iterable<Long> ids);

    void deleteById(Long id);

    <S extends Seller> boolean exists(Example<S> example);

    long count();

    void deleteAllInBatch(Iterable<Seller> entities);

    <S extends Seller> long count(Example<S> example);

    void deleteInBatch(Iterable<Seller> entities);

    <S extends Seller> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Seller> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Seller> S saveAndFlush(S entity);

    boolean existsById(Long id);

    void flush();

    <S extends Seller> List<S> saveAll(Iterable<S> entities);

    Optional<Seller> findById(Long id);

    List<Seller> findAllById(Iterable<Long> ids);

    List<Seller> findAll(Sort sort);

    Page<Seller> findAll(Pageable pageable);

    List<Seller> findAll();

    <S extends Seller> Optional<S> findOne(Example<S> example);

    <S extends Seller> S save(S entity);

    Seller selectByCustomerId(Long id);

    APIResponse addNewSeller(Seller seller);

    APIResponse acceptSeller(Long id);
    
}
