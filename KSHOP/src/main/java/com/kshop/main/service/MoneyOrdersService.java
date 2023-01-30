package com.kshop.main.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.kshop.main.domain.MoneyOrders;

public interface MoneyOrdersService {

    <S extends MoneyOrders> List<S> findAll(Example<S> example, Sort sort);

    <S extends MoneyOrders> List<S> findAll(Example<S> example);

    MoneyOrders getReferenceById(Long id);

    MoneyOrders getById(Long id);

    void deleteAll();

    void deleteAll(Iterable<? extends MoneyOrders> entities);

    MoneyOrders getOne(Long id);

    void deleteAllInBatch();

    void deleteAllById(Iterable<? extends Long> ids);

    <S extends MoneyOrders, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    void delete(MoneyOrders entity);

    void deleteAllByIdInBatch(Iterable<Long> ids);

    void deleteById(Long id);

    <S extends MoneyOrders> boolean exists(Example<S> example);

    long count();

    void deleteAllInBatch(Iterable<MoneyOrders> entities);

    <S extends MoneyOrders> long count(Example<S> example);

    void deleteInBatch(Iterable<MoneyOrders> entities);

    <S extends MoneyOrders> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends MoneyOrders> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends MoneyOrders> S saveAndFlush(S entity);

    boolean existsById(Long id);

    void flush();

    <S extends MoneyOrders> List<S> saveAll(Iterable<S> entities);

    Optional<MoneyOrders> findById(Long id);

    List<MoneyOrders> findAllById(Iterable<Long> ids);

    List<MoneyOrders> findAll(Sort sort);

    Page<MoneyOrders> findAll(Pageable pageable);

    List<MoneyOrders> findAll();

    <S extends MoneyOrders> Optional<S> findOne(Example<S> example);

    <S extends MoneyOrders> S save(S entity);

    List<MoneyOrders> selectAllByUserId(Long id);

    MoneyOrders selectByUserIdAndOrderId(Long UserId, Long OrderId);

    List<MoneyOrders> selectAllByUserIdOfStore(Long id);

	
}
