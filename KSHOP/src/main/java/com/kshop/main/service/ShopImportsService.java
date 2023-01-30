package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.ShopImports;
import com.kshop.main.domain.ShopStores;

public interface ShopImportsService {

    <S extends ShopImports> List<S> findAll(Example<S> example, Sort sort);

    <S extends ShopImports> List<S> findAll(Example<S> example);

    ShopImports getReferenceById(Long id);

    ShopImports getById(Long id);

    void deleteAll();

    void deleteAll(Iterable<? extends ShopImports> entities);

    ShopImports getOne(Long id);

    void deleteAllInBatch();

    void deleteAllById(Iterable<? extends Long> ids);

    <S extends ShopImports, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    void delete(ShopImports entity);

    void deleteAllByIdInBatch(Iterable<Long> ids);

    void deleteById(Long id);

    <S extends ShopImports> boolean exists(Example<S> example);

    long count();

    void deleteAllInBatch(Iterable<ShopImports> entities);

    <S extends ShopImports> long count(Example<S> example);

    void deleteInBatch(Iterable<ShopImports> entities);

    <S extends ShopImports> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends ShopImports> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends ShopImports> S saveAndFlush(S entity);

    boolean existsById(Long id);

    void flush();

    <S extends ShopImports> List<S> saveAll(Iterable<S> entities);

    Optional<ShopImports> findById(Long id);

    List<ShopImports> findAllById(Iterable<Long> ids);

    List<ShopImports> findAll(Sort sort);

    Page<ShopImports> findAll(Pageable pageable);

    List<ShopImports> findAll();

    <S extends ShopImports> Optional<S> findOne(Example<S> example);

    <S extends ShopImports> S save(S entity);

}
