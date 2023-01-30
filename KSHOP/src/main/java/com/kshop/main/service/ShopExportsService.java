package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.ShopExports;
import com.kshop.main.domain.ShopImports;
import com.kshop.main.domain.ShopStores;

public interface ShopExportsService {

    <S extends ShopExports> List<S> findAll(Example<S> example, Sort sort);

    <S extends ShopExports> List<S> findAll(Example<S> example);

    ShopExports getReferenceById(Long id);

    ShopExports getById(Long id);

    void deleteAll();

    void deleteAll(Iterable<? extends ShopExports> entities);

    ShopExports getOne(Long id);

    void deleteAllInBatch();

    void deleteAllById(Iterable<? extends Long> ids);

    <S extends ShopExports, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    void delete(ShopExports entity);

    void deleteAllByIdInBatch(Iterable<Long> ids);

    void deleteById(Long id);

    <S extends ShopExports> boolean exists(Example<S> example);

    long count();

    void deleteAllInBatch(Iterable<ShopExports> entities);

    <S extends ShopExports> long count(Example<S> example);

    void deleteInBatch(Iterable<ShopExports> entities);

    <S extends ShopExports> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends ShopExports> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends ShopExports> S saveAndFlush(S entity);

    boolean existsById(Long id);

    void flush();

    <S extends ShopExports> List<S> saveAll(Iterable<S> entities);

    Optional<ShopExports> findById(Long id);

    List<ShopExports> findAllById(Iterable<Long> ids);

    List<ShopExports> findAll(Sort sort);

    Page<ShopExports> findAll(Pageable pageable);

    List<ShopExports> findAll();

    <S extends ShopExports> Optional<S> findOne(Example<S> example);

    <S extends ShopExports> S save(S entity);

    

}
