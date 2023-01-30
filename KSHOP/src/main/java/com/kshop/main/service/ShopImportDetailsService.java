package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.ShopImportDetails;
import com.kshop.main.domain.ShopImports;
import com.kshop.main.domain.ShopStores;

public interface ShopImportDetailsService {

    <S extends ShopImportDetails> List<S> findAll(Example<S> example, Sort sort);

    <S extends ShopImportDetails> List<S> findAll(Example<S> example);

    ShopImportDetails getReferenceById(Long id);

    ShopImportDetails getById(Long id);

    void deleteAll();

    void deleteAll(Iterable<? extends ShopImportDetails> entities);

    ShopImportDetails getOne(Long id);

    void deleteAllInBatch();

    void deleteAllById(Iterable<? extends Long> ids);

    <S extends ShopImportDetails, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    void delete(ShopImportDetails entity);

    void deleteAllByIdInBatch(Iterable<Long> ids);

    void deleteById(Long id);

    <S extends ShopImportDetails> boolean exists(Example<S> example);

    long count();

    void deleteAllInBatch(Iterable<ShopImportDetails> entities);

    <S extends ShopImportDetails> long count(Example<S> example);

    void deleteInBatch(Iterable<ShopImportDetails> entities);

    <S extends ShopImportDetails> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends ShopImportDetails> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends ShopImportDetails> S saveAndFlush(S entity);

    boolean existsById(Long id);

    void flush();

    <S extends ShopImportDetails> List<S> saveAll(Iterable<S> entities);

    Optional<ShopImportDetails> findById(Long id);

    List<ShopImportDetails> findAllById(Iterable<Long> ids);

    List<ShopImportDetails> findAll(Sort sort);

    Page<ShopImportDetails> findAll(Pageable pageable);

    List<ShopImportDetails> findAll();

    <S extends ShopImportDetails> Optional<S> findOne(Example<S> example);

    <S extends ShopImportDetails> S save(S entity);

    
}
