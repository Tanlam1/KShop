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
import com.kshop.main.domain.ProductTypeItem;

public interface ProductTypeItemService {

    <S extends ProductTypeItem> List<S> findAll(Example<S> example, Sort sort);

    <S extends ProductTypeItem> List<S> findAll(Example<S> example);

    ProductTypeItem getReferenceById(Long id);

    ProductTypeItem getById(Long id);

    void deleteAll();

    void deleteAll(Iterable<? extends ProductTypeItem> entities);

    ProductTypeItem getOne(Long id);

    void deleteAllInBatch();

    void deleteAllById(Iterable<? extends Long> ids);

    <S extends ProductTypeItem, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    void delete(ProductTypeItem entity);

    void deleteAllByIdInBatch(Iterable<Long> ids);

    void deleteById(Long id);

    <S extends ProductTypeItem> boolean exists(Example<S> example);

    long count();

    void deleteAllInBatch(Iterable<ProductTypeItem> entities);

    <S extends ProductTypeItem> long count(Example<S> example);

    void deleteInBatch(Iterable<ProductTypeItem> entities);

    <S extends ProductTypeItem> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends ProductTypeItem> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends ProductTypeItem> S saveAndFlush(S entity);

    boolean existsById(Long id);

    void flush();

    <S extends ProductTypeItem> List<S> saveAll(Iterable<S> entities);

    Optional<ProductTypeItem> findById(Long id);

    List<ProductTypeItem> findAllById(Iterable<Long> ids);

    List<ProductTypeItem> findAll(Sort sort);

    Page<ProductTypeItem> findAll(Pageable pageable);

    List<ProductTypeItem> findAll();

    <S extends ProductTypeItem> Optional<S> findOne(Example<S> example);

    <S extends ProductTypeItem> S save(S entity);

    List<ProductTypeItem> selectsByProductId(Long id);
    
}
