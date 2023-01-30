package com.kshop.main.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.kshop.main.domain.MoneyOrders;
import com.kshop.main.domain.MoneySends;
import com.kshop.main.repository.MoneyOrdersRepository;
import com.kshop.main.repository.MoneySendsRepository;
import com.kshop.main.service.MoneyOrdersService;
import com.kshop.main.service.MoneySendsService;

@Service
public class MoneySendsImpl implements MoneySendsService {
    
    @Autowired
    MoneySendsRepository moneySendsRepository;

    @Override
    public List<MoneySends> selectAllByUserId(Long id) {
        return moneySendsRepository.selectAllByUserId(id);
    }

    @Override
    public List<MoneySends> selectAllByEmployeeId(Long id) {
        return moneySendsRepository.selectAllByEmployeeId(id);
    }

    @Override
    public MoneySends selectByUserIdAndEmployeeId(Long userId, Long employeeId) {
        return moneySendsRepository.selectByUserIdAndEmployeeId(userId, employeeId);
    }

    @Override
    public <S extends MoneySends> S save(S entity) {
        return moneySendsRepository.save(entity);
    }

    @Override
    public <S extends MoneySends> Optional<S> findOne(Example<S> example) {
        return moneySendsRepository.findOne(example);
    }

    @Override
    public List<MoneySends> findAll() {
        return moneySendsRepository.findAll();
    }

    @Override
    public Page<MoneySends> findAll(Pageable pageable) {
        return moneySendsRepository.findAll(pageable);
    }

    @Override
    public List<MoneySends> findAll(Sort sort) {
        return moneySendsRepository.findAll(sort);
    }

    @Override
    public List<MoneySends> findAllById(Iterable<Long> ids) {
        return moneySendsRepository.findAllById(ids);
    }

    @Override
    public Optional<MoneySends> findById(Long id) {
        return moneySendsRepository.findById(id);
    }

    @Override
    public <S extends MoneySends> List<S> saveAll(Iterable<S> entities) {
        return moneySendsRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        moneySendsRepository.flush();
    }

    @Override
    public boolean existsById(Long id) {
        return moneySendsRepository.existsById(id);
    }

    @Override
    public <S extends MoneySends> S saveAndFlush(S entity) {
        return moneySendsRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends MoneySends> List<S> saveAllAndFlush(Iterable<S> entities) {
        return moneySendsRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends MoneySends> Page<S> findAll(Example<S> example, Pageable pageable) {
        return moneySendsRepository.findAll(example, pageable);
    }

    @Override
    public void deleteInBatch(Iterable<MoneySends> entities) {
        moneySendsRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends MoneySends> long count(Example<S> example) {
        return moneySendsRepository.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<MoneySends> entities) {
        moneySendsRepository.deleteAllInBatch(entities);
    }

    @Override
    public long count() {
        return moneySendsRepository.count();
    }

    @Override
    public <S extends MoneySends> boolean exists(Example<S> example) {
        return moneySendsRepository.exists(example);
    }

    @Override
    public void deleteById(Long id) {
        moneySendsRepository.deleteById(id);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        moneySendsRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void delete(MoneySends entity) {
        moneySendsRepository.delete(entity);
    }

    @Override
    public <S extends MoneySends, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return moneySendsRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        moneySendsRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAllInBatch() {
        moneySendsRepository.deleteAllInBatch();
    }

    @Override
    public MoneySends getOne(Long id) {
        return moneySendsRepository.getOne(id);
    }

    @Override
    public void deleteAll(Iterable<? extends MoneySends> entities) {
        moneySendsRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        moneySendsRepository.deleteAll();
    }

    @Override
    public MoneySends getById(Long id) {
        return moneySendsRepository.getById(id);
    }

    @Override
    public MoneySends getReferenceById(Long id) {
        return moneySendsRepository.getReferenceById(id);
    }

    @Override
    public <S extends MoneySends> List<S> findAll(Example<S> example) {
        return moneySendsRepository.findAll(example);
    }

    @Override
    public <S extends MoneySends> List<S> findAll(Example<S> example, Sort sort) {
        return moneySendsRepository.findAll(example, sort);
    }
    
    
}
