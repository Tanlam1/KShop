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
import com.kshop.main.domain.MoneySends;

public interface MoneySendsService {

    <S extends MoneySends> List<S> findAll(Example<S> example, Sort sort);

    <S extends MoneySends> List<S> findAll(Example<S> example);

    MoneySends getReferenceById(Long id);

    MoneySends getById(Long id);

    void deleteAll();

    void deleteAll(Iterable<? extends MoneySends> entities);

    MoneySends getOne(Long id);

    void deleteAllInBatch();

    void deleteAllById(Iterable<? extends Long> ids);

    <S extends MoneySends, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    void delete(MoneySends entity);

    void deleteAllByIdInBatch(Iterable<Long> ids);

    void deleteById(Long id);

    <S extends MoneySends> boolean exists(Example<S> example);

    long count();

    void deleteAllInBatch(Iterable<MoneySends> entities);

    <S extends MoneySends> long count(Example<S> example);

    void deleteInBatch(Iterable<MoneySends> entities);

    <S extends MoneySends> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends MoneySends> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends MoneySends> S saveAndFlush(S entity);

    boolean existsById(Long id);

    void flush();

    <S extends MoneySends> List<S> saveAll(Iterable<S> entities);

    Optional<MoneySends> findById(Long id);

    List<MoneySends> findAllById(Iterable<Long> ids);

    List<MoneySends> findAll(Sort sort);

    Page<MoneySends> findAll(Pageable pageable);

    List<MoneySends> findAll();

    <S extends MoneySends> Optional<S> findOne(Example<S> example);

    <S extends MoneySends> S save(S entity);

    MoneySends selectByUserIdAndEmployeeId(Long userId, Long employeeId);

    List<MoneySends> selectAllByEmployeeId(Long id);

    List<MoneySends> selectAllByUserId(Long id);
    
}
