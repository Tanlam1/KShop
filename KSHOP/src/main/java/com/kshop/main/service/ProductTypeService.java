package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.ProductImages;
import com.kshop.main.domain.ProductType;

public interface ProductTypeService{

    <S extends ProductType> List<S> findAll(Example<S> example, Sort sort);

    <S extends ProductType> List<S> findAll(Example<S> example);

    ProductType getReferenceById(Long id);

    ProductType getById(Long id);

    void deleteAll();

    void deleteAll(Iterable<? extends ProductType> entities);

    ProductType getOne(Long id);

    void deleteAllInBatch();

    void deleteAllById(Iterable<? extends Long> ids);

    <S extends ProductType, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    void delete(ProductType entity);

    void deleteAllByIdInBatch(Iterable<Long> ids);

    void deleteById(Long id);

    <S extends ProductType> boolean exists(Example<S> example);

    long count();

    void deleteAllInBatch(Iterable<ProductType> entities);

    <S extends ProductType> long count(Example<S> example);

    void deleteInBatch(Iterable<ProductType> entities);

    <S extends ProductType> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends ProductType> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends ProductType> S saveAndFlush(S entity);

    boolean existsById(Long id);

    void flush();

    <S extends ProductType> List<S> saveAll(Iterable<S> entities);

    Optional<ProductType> findById(Long id);

    List<ProductType> findAllById(Iterable<Long> ids);

    List<ProductType> findAll(Sort sort);

    Page<ProductType> findAll(Pageable pageable);

    List<ProductType> findAll();

    <S extends ProductType> Optional<S> findOne(Example<S> example);

    <S extends ProductType> S save(S entity);


}
