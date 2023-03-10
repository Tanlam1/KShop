package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.ShopExportDetails;
import com.kshop.main.domain.ShopImportDetails;
import com.kshop.main.domain.ShopImports;
import com.kshop.main.domain.ShopStores;

public interface ShopExportDetailsService {

    <S extends ShopExportDetails> List<S> findAll(Example<S> example, Sort sort);

    <S extends ShopExportDetails> List<S> findAll(Example<S> example);

    ShopExportDetails getReferenceById(Long id);

    ShopExportDetails getById(Long id);

    void deleteAll();

    void deleteAll(Iterable<? extends ShopExportDetails> entities);

    ShopExportDetails getOne(Long id);

    void deleteAllInBatch();

    void deleteAllById(Iterable<? extends Long> ids);

    <S extends ShopExportDetails, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    void delete(ShopExportDetails entity);

    void deleteAllByIdInBatch(Iterable<Long> ids);

    void deleteById(Long id);

    <S extends ShopExportDetails> boolean exists(Example<S> example);

    long count();

    void deleteAllInBatch(Iterable<ShopExportDetails> entities);

    <S extends ShopExportDetails> long count(Example<S> example);

    void deleteInBatch(Iterable<ShopExportDetails> entities);

    <S extends ShopExportDetails> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends ShopExportDetails> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends ShopExportDetails> S saveAndFlush(S entity);

    boolean existsById(Long id);

    void flush();

    <S extends ShopExportDetails> List<S> saveAll(Iterable<S> entities);

    Optional<ShopExportDetails> findById(Long id);

    List<ShopExportDetails> findAllById(Iterable<Long> ids);

    List<ShopExportDetails> findAll(Sort sort);

    Page<ShopExportDetails> findAll(Pageable pageable);

    List<ShopExportDetails> findAll();

    <S extends ShopExportDetails> Optional<S> findOne(Example<S> example);

    <S extends ShopExportDetails> S save(S entity);
    
}
